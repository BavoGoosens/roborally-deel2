package roborally.model;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Raw;
import roborally.property.*;
import roborally.utils.*;

/**
 * Een klasse om robots voor te stellen.
 * 
 * @invar	De hoeveelheid energie van een robot moet altijd geldig zijn.
 * 			|isValidRobotEnergy(getEnergy(), this)
 * 
 * @author 	Bavo Goosens (1e bachelor informatica, r0297884), Samuel Debruyn (1e bachelor informatica, r0305472)
 * 
 * @version 3.0
 */
public class Robot extends Entity{

	/**
	 * De energiekost van 1 move (geen rekening houdend met bezittingen).
	 */
	private final static Energy MOVE_COST = new Energy(500);
	/**
	 * De energiekost van 1 draai.
	 */
	public final static Energy TURN_COST = new Energy(100);
	/**
	 * De energiekost die bij een move bijkomt per kg gewicht in bezittingen.
	 */
	private final static Energy MOVE_COST_PER_KG = new Energy(50);
	/**
	 * De maximale bovengrens van de energie van een robot.
	 */
	public final static Energy MAX_ENERGY = new Energy(20000);
	/**
	 * De kost van 1 schot met zijn laser.
	 */
	public final static Energy SHOOT_COST = new Energy(1000);
	/**
	 * De energie die afgetrokken wordt van de maximale energie wanneer de robot geraakt wordt.
	 */
	public final static Energy SHOOT_DAMAGE = new Energy(4000);
	/**
	 * De energie van de robot.
	 */
	private Energy energy;
	/**
	 * Het maxima van de energy 
	 */
	private Energy maxEnergy;
	/**
	 * De oriëntatie van de robot.
	 */
	private Orientation orientation;
	/**
	 * De lijst van voorwerpen die de robot bezit.
	 * 
	 * @note	Gebruik altijd Collections.sort met een ItemComparator() wanneer deze lijst gewijzigd wordt.
	 */
	private ArrayList<Item> possessions = new ArrayList<>();
	/**
	 * De robot kan een programma laden en uitvoeren.
	 */
	private Program program;

	/**
	 * Deze methode maakt een nieuwe robot aan.
	 * 
	 * @param	orientation
	 * 			De initiële oriëntatie van de robot.
	 * 
	 * @param	energy
	 * 			De initiële oriëntatie van de robot.
	 * 
	 * @post	De energie van de robot is gelijk aan de opgegeven energie.
	 * 			|new.getEnergy().equals(energy)
	 * 
	 * @post	De oriëntatie van de robot is gelijk aan de opgegeven oriëntatie.
	 * 			|new.getOrientation().equals(orientation)
	 * 
	 * @post	De maximale energie van deze robot is gelijk aan het gedefinieerde maximum voor alle robots.
	 * 			|new.getMaxEnergy().equals(MAX_ENERGY)
	 */
	public Robot(Orientation orientation, Energy energy){
		setMaxEnergy(MAX_ENERGY);
		setEnergy(energy);
		setOrientation(orientation);
	}

	/**
	 * Deze methode wijzigt de energie van de robot.
	 * 
	 * @param	energy
	 * 			De nieuwe energie die de robot moet krijgen.
	 * 
	 * @post	De energie van de robot is gelijk aan de gegeven energie.
	 * 			|new.getEnergy().equals(energy)
	 */
	private void setEnergy(Energy energy) {
		this.energy = energy;
	}

	/**
	 * Methode om de oriëntatie van een robot te wijzigen.
	 *  
	 * @param 	or
	 * 			De oriëntatie die de robot moet krijgen.
	 * 
	 * @post	De oriëntatie van de robot is gelijk aan de gegeven parameter.
	 * 			|new.getOrientation() == or
	 */
	private void setOrientation(Orientation or) {
		orientation = or;
	}

	/**
	 * Methode om de oriëntatie van de robot te verkrijgen.
	 * 
	 * @return 	De oriëntatie van de robot.
	 * 			|orientation 			
	 */
	@Basic
	public Orientation getOrientation(){
		return orientation;
	}

	/**
	 * Methode om de energie van de robot te verkrijgen.
	 * 
	 * @return 	De energie van de robot.
	 * 			|energy
	 */
	@Basic
	public Energy getEnergy(){
		return energy;
	}

	/**
	 * Deze methode geeft de maximale energie van de robot terug.
	 * 
	 * @return	De maximale energie van de robot.
	 * 			|maxEnergy
	 */
	@Basic
	public Energy getMaxEnergy() {
		return maxEnergy;
	}

	/**
	 * Deze methode stelt de bovengrens in van de energie van de robot
	 * 
	 * @param	maxEnergy
	 * 			De nieuwe waarde die de maximale energie van de robot moet worden.
	 * 
	 * @post	De energie is kleiner of gelijk aan de maximale energie.
	 * 			|new.getMaxEnergy().equals(maxEnergy)
	 * 
	 * @note	Wanneer deze gewijzigd wordt is het aangeraden om verifyEnergy() op te roepen.
	 */
	public void setMaxEnergy(Energy maxEnergy) {
		this.maxEnergy = maxEnergy;
	}

	/**
	 * Methode die controleert of de opgegeven hoeveelheid energie een geldige hoeveelheid is.
	 * 
	 * @param 	energy
	 * 			De hoeveelheid energie.
	 * 
	 * @param 	robot
	 * 			De robot waarvoor deze waarde nagekeken moet worden.
	 *
	 * @return	Boolean die weergeeft of de hoeveelheid energie geldig is.
	 * 			|(energy.getEnergy() <= robot.getMaxEnergy().getEnergy())
	 */
	public static boolean isValidRobotEnergy(Energy energy, Robot robot){
		return (energy.getEnergy() <= robot.getMaxEnergy().getEnergy());
	}

	/**
	 * Deze methode berekent de verhouding tussen de huidige hoeveelheid energie en de maximale hoeveelheid energie.
	 * 
	 * @return	De verhouding tussen de huidige hoeveelheid energie en de maximale hoeveelheid energie.
	 * 			|getEnergy().getEnergy()/getMaxEnergy().getEnergy()
	 */
	public double getEnergyFraction(){
		return getEnergy().getEnergy()/getMaxEnergy().getEnergy();
	}

	/**
	 * Draait de robot 1 keer in wijzerzin.
	 * 
	 * @throws  NotEnoughEnergyException 
	 * 			De robot heeft onvoldoende energie om te bewegen.
	 * 			|!canTurn()
	 * 
	 * @post	De nieuwe oriëntatie van de robot is gelijk aan de volgende oriëntatie in wijzerzin.
	 * 			|new.getOrientation() == this.getOrienation().getClockwiseOrientation()
	 * 
	 * @post	De energie van de robot is verminderd met benodigde energie voor een draai.
	 * 			|new.getEnergy().equals(Energy.energyDifference(this.getEnergy(), TURN_COST))
	 */
	public void turnClockWise() throws NotEnoughEnergyException{
		if(!canTurn()){
			throw new NotEnoughEnergyException(getEnergy());
		}
		setOrientation(getOrientation().getClockwiseOrientation());
		setEnergy(Energy.energyDifference(getEnergy(), TURN_COST));
	}

	/**
	 * Draait de robot 1 keer in tegenwijzerzin.
	 * 
	 * @throws  NotEnoughEnergyException 
	 * 			De robot heeft onvoldoende energie om te bewegen.
	 * 			|!canTurn()
	 * 
	 * @post	De nieuwe oriëntatie van de robot is gelijk aan de volgende oriëntatie in wijzerzin.
	 * 			|new.getOrientation() == this.getOrienation().getCounterClockwiseOrientation()
	 * 
	 * @post	De energie van de robot is verminderd met benodigde energie voor een draai.
	 * 			|new.getEnergy().equals(Energy.energyDifference(this.getEnergy(), TURN_COST))
	 */
	public void turnCounterClockWise() throws NotEnoughEnergyException{
		if(!canTurn()){
			throw new NotEnoughEnergyException(getEnergy());
		}
		setOrientation(getOrientation().getCounterClockwiseOrientation());
		setEnergy(Energy.energyDifference(getEnergy(), TURN_COST));
	}

	/**
	 * Deze methode beweegt de robot een stap vooruit indien mogelijk.
	 * 
	 * 
	 * @throws 	IllegalStateException
	 * 			De robot heeft onvoldoende energie om te bewegen of de positie waarnaar bewogen moet worden is ongeldig of reeds bezet.
	 * 			|!canMove() || !Position.isValidPosition(getOrientation().getNextPosition(getPosition())) || !getBoard().isPlacableOnPosition(Calculator.getNextPosition(this.getPosition(), this.getOrientation()))
	 * 
	 * @post	De robot staat een plaats verder.
	 * 			|new.getPosition().equals(getNextPosition(this.getPosition(), this.getOrientation())) == true
	 * 
	 * @post	De robot heeft energie verbruikt.
	 * 			|new.getEnergy().equals(Energy.energyDifference(this.getEnergy(), moveCost(this))) == true
	 */
	//TODO: Exceptions en doc.
	public void move() throws IllegalStateException{
		if(!this.canMove()){
			throw new IllegalStateException("De robot heeft onvoldoende energie om te bewegen.");
		}
		Position destination;
		try{
			destination = getNextPosition(this.getPosition(), this.getOrientation());
		}catch (IllegalStateException e){
			throw new IllegalStateException("De positie waarnaar bewogen moet worden is ongeldig.");
		}
		if(this.getBoard().isPlacableOnPosition(destination,this)){
			this.setPosition(destination);
			this.setEnergy(Energy.energyDifference(this.getEnergy(), moveCost(this)));
		}else{
			throw new IllegalStateException("De positie is al bezet.");
		}
	}

	/**
	 * Deze methode geeft de energie terug die nodig is om een bepaalde plaats te bereiken.
	 * 
	 * @param 	position
	 * 			De plaats die bereikt moet worden.
	 * 
	 * @return	De energie die nodig is om de plaats te bereiken.
	 * 			|aStarOnTo(this, position).get(position.toString()).getGCost()
	 * 
	 * @throws 	TargetNotReachableException
	 * 			Het doel is niet bereikbaar.
	 * 			|!getReachables(this).containsKey(position.toString())
	 */
	public Energy getEnergyRequiredToReach(Position position) throws TargetNotReachableException{
		if (getReachables(this).containsKey(position.toString()))
			return aStarOnTo(this, position).get(position.toString()).getGCost();
		throw new TargetNotReachableException(position);
	}

	/**
	 * Deze methode verplaatst de robot zo dicht mogelijk bij een andere robot.
	 * 
	 * @param	robot
	 * 			De robot waar naartoe moet bewogen worden.
	 * 
	 * @post	De robot staat zo dicht mogelijk bij de gegeven robot met een zo laag mogelijk energieverbruik.
	 * 			|TODO: formele post doc bij moveNextTo()
	 * 			Het is niet het geval dat er een positie P bestaat op dit board die bereikbaar is door beide robots waarvoor geldt dat zowel 
	 * 			de manhattan als de energy om er te geraken minimaal is. (dees omzetten naar logica)
	 */
	public void moveNextTo(Robot robot){
		//TODO: nakijken
		if (!this.equals(robot) && robot != null && this.getBoard().equals(robot.getBoard())) {
			HashMap<String,Node> thisReachables = getReachables(this);
			HashMap<String,Node> otherReachables = getReachables(robot);
			Set<String> thisKeys = thisReachables.keySet();
			Set<String> otherKeys = otherReachables.keySet();
			ArrayList<PositionPair> posPairs = new ArrayList<>();
			for(String thisPosString: thisKeys){
				for(String otherPosString: otherKeys){
					Node thisNode = thisReachables.get(thisPosString);
					Node otherNode = otherReachables.get(otherPosString);
					PositionPair toAdd = new PositionPair(Robot.getPositionFromString(thisPosString), Robot.getPositionFromString(otherPosString),thisNode.getGCost(),
							otherNode.getGCost(), thisNode.getOrientation(),otherNode.getOrientation());
					posPairs.add(toAdd);
				}
			}
			ArrayList<PositionPair> semiValidPosPairs = new ArrayList<>();
			for(PositionPair pp: posPairs){
				if((pp.getManhattanDistance() != 0) && !(pp.getPos1().toString().equals(pp.getPos2().toString())))
					semiValidPosPairs.add(pp);
			}
			if(!semiValidPosPairs.isEmpty()){
				Collections.sort(semiValidPosPairs, new PositionPairComparatorDistance());
				PositionPair firstpp = semiValidPosPairs.get(0);
				ArrayList<PositionPair> validPosPairs = new ArrayList<>();
				for(PositionPair pp: semiValidPosPairs){
					if((pp.getManhattanDistance() == firstpp.getManhattanDistance()))
						validPosPairs.add(pp);
				}
				Collections.sort(validPosPairs, new PositionPairComparatorEnergy());
				PositionPair thePair = validPosPairs.get(0);
				if (!this.getPosition().equals(thePair.getPos1())){
					this.getBoard().removeEntity(this);
					this.getBoard().cleanBoardPosition(this.getPosition());
					this.putOnBoard(this.getBoard(), thePair.getPos1());
				}
				if (!robot.getPosition().equals(thePair.getPos2())){
					robot.getBoard().removeEntity(robot);
					robot.getBoard().cleanBoardPosition(robot.getPosition());
					robot.putOnBoard(this.getBoard(), thePair.getPos2());
				}
				this.setOrientation(thePair.getOr1());
				robot.setOrientation(thePair.getOr2());
				this.setEnergy(Energy.energyDifference(this.getEnergy(),thePair.getCost1()));
				robot.setEnergy(Energy.energyDifference(robot.getEnergy(),thePair.getCost2()));	
			}
		}
	}

	/**
	 * Deze methode doet een robot schieten met zijn laser.
	 * 
	 * @throws 	EntityNotOnBoardException
	 * 			De robot staat niet op een bord en kan bijgevolg niet schieten.
	 * 			|!isOnBoard()
	 * 
	 * @post	De robot verliest energie. De hoeveelheid is bepaald in de constante SHOOT_COST.
	 * 			|new.getEnergy().equals(Energy.energyDifference(this.getEnergy(), SHOOT_COST))
	 * 
	 * @effect	Mogelijks wordt een object geraakt en vernietigd.
	 * 			|if(getShootTarget() != null)
	 * 			|	getShootTarget().damage()
	 * 
	 * @post	De robot is energie kwijt die bepaald werd in SHOOT_COST.
	 * 			|new.getEnergy().equals((Energy.energyDifference(this.getEnergy(), SHOOT_COST)))
	 */
	public void shoot() throws EntityNotOnBoardException{
		if(!isOnBoard()){
			throw new EntityNotOnBoardException();
		}
		setEnergy(Energy.energyDifference(getEnergy(), SHOOT_COST));
		Entity target = getShootTarget();
		if(target != null)
			target.damage();
	}

	/**
	 * Deze methode geeft het doelwit terug dat geraakt kan worden wanneer de robot schiet.
	 * 
	 * @return	Het doelwit dat geraakt kan worden wanneer de robot schiet.
	 * 			|Position beginpos = getPosition();
	 * 			|while(getBoard().isValidBoardPosition(getNextPosition(beginpos, getOrientation()))){
	 * 			|	beginpos = getNextPosition(beginpos, getOrientation());
	 * 			|	HashSet<Entity> content = getBoard().getEntityOnPosition(beginpos);
	 * 			|	if(content != null){
	 * 			|		Random rndm = new Random();
	 * 			|		Entity[] results = (Entity[]) content.toArray();
	 * 			|		results[rndm.nextInt(results.length)]
	 * 			|	}
	 * 			|}
	 */
	private Entity getShootTarget(){
		Position beginpos = getPosition();
		while(getBoard().isValidPosition(getNextPosition(beginpos, getOrientation()))){
			beginpos = getNextPosition(beginpos, getOrientation());
			HashSet<Entity> content = getBoard().getEntityOnPosition(beginpos);
			if(content != null){
				Random rndm = new Random();
				Entity[] results = (Entity[]) content.toArray();
				return results[rndm.nextInt(results.length)];
			}
		}
		return null;
	}

	/**
	 * Deze methode laadt een robot op met de opgegeven hoeveelheid energie.
	 * 
	 * @param 	energy
	 * 			Energie waarmee moet opgeladen worden.
	 * 
	 * @post	De robot is opgeladen met de opgegeven hoeveelheid energie.
	 * 			|if(!isValidRobotEnergy(Energy.energySum(getEnergy(), energy).getEnergy(), this)){
	 * 			|	new.getEnergy().equals(this.getMaxEnergy())
	 * 			|}else{
	 * 			|	new.getEnergy().equals(Energy.energySum(this.getEnergy(), energy))
	 * 			|}
	 * 			
	 */
	public void recharge(Energy energy){
		Energy newEnergy = Energy.energySum(getEnergy(), energy);
		if(!isValidRobotEnergy(newEnergy, this))
			newEnergy = getMaxEnergy();
		setEnergy(newEnergy);
	}

	/**
	 * Deze methode wordt opgeroepen wanneer de robot geraakt wordt door een laser of een surprise box.
	 * 
	 * @post	De maximale energie van de robot is gedaald met de hoeveelheid gedefinieerd in SHOOT_DAMAGE.
	 * 			|new.getMaxEnergy().equals(Energy.energyDifference(this.getMaxEnergy(), SHOOT_DAMAGE))
	 * 
	 * @effect	De huidige energie wordt nagekeken en indien nodig verbeterd.
	 * 			|verifyEnergy()
	 */
	@Override
	protected void damage(){
		if (getMaxEnergy().getEnergy() > SHOOT_DAMAGE.getEnergy()){
			setMaxEnergy(Energy.energyDifference(getMaxEnergy(), SHOOT_DAMAGE));
			verifyEnergy();
		} else {
			destroy();
		}
	}

	/**
	 * Deze methode kijkt na of de energie van de robot groter is dan de huidige maximale energie van de robot. Indien deze groter is dan wordt de energie van de robot ingesteld op zijn maximale energie.
	 * 
	 * @post	De energie van de robot is kleiner of gelijk aan zijn maximale energie.
	 * 			|new.getEnergy().getEnergy() <= new.getMaxEnergy().getEnergy()
	 */
	private void verifyEnergy(){
		if(getEnergy().getEnergy() > getMaxEnergy().getEnergy())
			setEnergy(getMaxEnergy());
	}

	/**
	 * Deze methode berekent de kost van 1 move.
	 * 
	 * @param	robot
	 * 			De robot waarvoor de berekening moet uitgevoerd worden.
	 * 
	 * @return	de kost terug van 1 move
	 * 			|Energy.energySum(MOVE_COST, new Energy(MOVE_COST_PER_KG.getEnergy()*(robot.getTotalWeight().getWeight() / 1000)))
	 */
	private static Energy moveCost(Robot robot){
		return Energy.energySum(MOVE_COST, new Energy(MOVE_COST_PER_KG.getEnergy()*(robot.getTotalWeight().getWeight() / 1000)));
	}

	/**
	 * Geeft het totale gewicht van alles wat de robot draagt.
	 * 
	 * @return	Het totale gewicht van alles wat de robot draagt.
	 * 			|int totalWeight = 0;
	 * 			|for(Item item: getPossessions()){
	 * 			|	totalWeight += item.getWeight().getWeight();
	 * 			|}
	 * 			|new Weight(totalWeight)
	 */
	public Weight getTotalWeight(){
		int totalWeight = 0;
		for(Item item: getPossessions()){
			totalWeight += item.getWeight().getWeight();
		}
		return new Weight(totalWeight);
	}

	/**
	 * Geeft een lijst terug van alle items die de robot momenteel draagt.
	 * 
	 * @return	De lijst van items.
	 * 			|possessions
	 */
	public ArrayList<Item> getPossessions(){
		return possessions;
	}

	/**
	 * Deze methode neemt een voorwerp op in de bezittingen van de robot.
	 * 
	 * @param 	item
	 * 			Het voorwerp dat moet opgenomen worden.
	 * 
	 * @throws 	IllegalPositionException
	 * 			Het voorwerp staat niet op dezelfde positie als de robot.
	 * 			|!getPosition().equals(item.getPosition())
	 * 
	 * @throws 	EntityNotOnBoardException
	 * 			De robot staat niet op een bord of het voorwerp staat niet op hetzelfde bord als de robot.
	 * 			|!isOnBoard() || (getBoard() != item.getBoard())
	 * 
	 * @post	Het voorwerp zit in de bezittingen van de robot.
	 * 			|new.getPossessions().contains(item)
	 * 
	 * @post	Het voorwerp staat niet meer op een bord.
	 * 			|!item.isOnBoard()
	 */
	public void pickUp(Item item) throws EntityNotOnBoardException, IllegalPositionException{
		if(!isOnBoard()){
			throw new EntityNotOnBoardException();
		}else if(getBoard() != item.getBoard()){
			throw new EntityNotOnBoardException();
		}else if(!getPosition().equals(item.getPosition())){
			throw new IllegalPositionException(item.getPosition());
		}else{
			item.removeFromBoard();
			getPossessions().add(item);
			Collections.sort(getPossessions(), new ItemComparator());
		}
	}

	/**
	 * Deze methode gebruikt een voorwerp dat de robot bezit.
	 * 
	 * @param	item
	 * 			Het voorwerp dat gebruikt moet worden.
	 * 
	 * @throws	IllegalArgumentException
	 * 			De robot bezit het voorwerp niet.
	 * 			|getPossessions().contains(item)
	 * 
	 * @effect	Als het voorwerp een surprisebox is dan wordt doSurpriseBoxAction() aangeroepen en wordt de surprisebox getermineerd en uit de bezittingen verwijderd.
	 * 			|if(item instanceof SurpriseBox){
	 * 			|	doSurpriseBoxAction(item)
	 * 			|	getPossessions().remove(item)
	 * 			|	item.destroy()
	 * TODO: doc afwerken
	 * 
	 */
	public void use(Item item) throws IllegalArgumentException{
		if(!getPossessions().contains(item))
			throw new IllegalArgumentException("Dit voorwerp is niet in het bezit van de robot.");
		if(item.isTerminated()){
			getPossessions().remove(item);
		}else{
			if (item instanceof Battery){
				Battery batt = (Battery) item;
				Energy canRecharge = Energy.energyDifference(getMaxEnergy(), getEnergy());
				if(Energy.energyDifference(batt.getEnergy(), canRecharge).getEnergy() > 0){
					// Er blijft energie over in de batterij en de robot is volledig opgeladen.
					setEnergy(getMaxEnergy());
					batt.setEnergy(Energy.energyDifference(batt.getEnergy(), canRecharge));
				}else if(Energy.energyDifference(canRecharge, batt.getEnergy()).getEnergy() > 0){
					// De robot is niet volledig opgeladen en de batterij is leeg.
					setEnergy(Energy.energyDifference(canRecharge, batt.getEnergy()));
					getPossessions().remove(batt);
					batt.destroy();
				}else{
					// De robot is volledig opgeladen en de batterij is leeg.
					setEnergy(getMaxEnergy());
					getPossessions().remove(batt);
					batt.destroy();
				}
			} else if (item instanceof SurpriseBox){
				doSurpriseBoxAction(item);
				getPossessions().remove(item);
				item.destroy();
			}else if (item instanceof RepairKit){
				RepairKit repairKit = (RepairKit) item;
				Energy canIncrease = Energy.energyDifference(MAX_ENERGY, getMaxEnergy());
				Energy repKitIncrease = new Energy(repairKit.getEnergy().getEnergy() / 2);
				if(Energy.energyDifference(repKitIncrease, canIncrease).getEnergy() > 0){
					// Er blijft energie over in de repair kit en de robot zit aan zijn maximum energie.
					setMaxEnergy(MAX_ENERGY);
					repairKit.setEnergy(new Energy(Energy.energyDifference(repKitIncrease, canIncrease).getEnergy() * 2));
				}else if(Energy.energyDifference(canIncrease, repKitIncrease).getEnergy() > 0){
					// De robot is niet volledig opgeladen en de repair kit is leeg.
					setMaxEnergy(Energy.energyDifference(canIncrease, repKitIncrease));
					getPossessions().remove(repairKit);
					repairKit.destroy();
				}else{
					// De robot is volledig opgeladen en de repair kit is leeg.
					setMaxEnergy(MAX_ENERGY);
					getPossessions().remove(repairKit);
					repairKit.destroy();
				}
			}
		}
	}
	
	/**
	 * Deze methode voert een willekeurige actie van een surprise box uit.
	 * 
	 * @param	item
	 * 			De surprise box waarvoor een willekeurige actie uitgevoerd wordt.
	 * 
	 * @post	De robot is geraakt door een explosie, de robot is geteleporteerd naar een willekeurige positie (de nieuwe positie kan gelijk zijn aan de positie die de robot had) of de robot draagt een nieuw voorwerp.
	 * 			|new.getMaxEnergy().equals(new Energy(0)) || new.getMaxEnergy().getEnergy() < this.getMaxEnergy().getEnergy() || new.getPosessions.size() == this.getPossessions.size() + 1
	 */
	public void doSurpriseBoxAction(Item item){
		Random rand = new Random();
		int choice = 1 + rand.nextInt(3);
		if (choice == 1){
			//explosie
			damage();
		}else if (choice == 2){
			//teleporteren
			setPosition(getBoard().getRandomPosition(this));
		}else{
			//random new item
			choice = 1 + rand.nextInt(3);
			if (choice == 1){
				//SurpriseBox
				SurpriseBox box = new SurpriseBox(item.getWeight());
				getPossessions().add(box);
			}else if (choice == 2){
				//Battery
				Battery bat = new Battery(new Energy(rand.nextInt((int)Battery.MAX_ENERGY.getEnergy()) + 1),item.getWeight());
				getPossessions().add(bat);
			}else{
				//Repairkit
				RepairKit kit = new RepairKit(new Energy(rand.nextInt((int)RepairKit.MAX_ENERGY.getEnergy()) + 1) ,item.getWeight());
				getPossessions().add(kit);
			}
		}
	}

	/**
	 * Deze methode plaatst een Item uit de bezittingen van de robot op de huidige positie van de robot in het bord van de robot.
	 * 
	 * @param	item
	 * 			De batterij die op het bord geplaatst moet worden.
	 * 
	 * @throws 	IllegalArgumentException
	 * 			Het item is niet in het bezit van de robot.
	 * 
	 * @throws 	IllegalStateException
	 * 		 	Het item staat niet op een bord.
	 * 
	 * @post	Het item is niet langer in het bezit van de robot.
	 * 			|new.getPossessions().contains(item) == false
	 * 
	 * @post	Het item bevindt zich op dezelfde positie als de robot.
	 * 			|(new item).getPosition().equals(new.getPosition()) == true
	 * 
	 * @post	Het item bevindt zich op hetzelfde bord als de robot.
	 * 			|(new item).getBoard().equals(new.getBoard()) == true
	 */
	public void drop(Item item) throws IllegalArgumentException, IllegalStateException{
		if(!isOnBoard()){
			throw new IllegalStateException("De robot staat niet op een bord.");
		}else if(!getPossessions().contains(item)){
			throw new IllegalArgumentException("De robot heeft dit item niet.");
		}else{
			getPossessions().remove(item);
			item.putOnBoard(getBoard(), getPosition());
			Collections.sort(getPossessions(), new ItemComparator());
		}
	}

	/**
	 * Deze methode kijkt na of de robot kan draaien.
	 * 
	 * @return	Boolean die true is als de robot voldoende energie heeft om te draaien.
	 * 			|if(this.getEnergy().getEnergy() - TURN_COST.getEnergy() < 0)
	 * 			|	false
	 * 			|true
	 */
	@Raw
	public boolean canTurn(){
		if(this.getEnergy().getEnergy() - TURN_COST.getEnergy() < 0)
			return false;
		return true;
	}

	/**
	 * Deze methode kijkt na of de robot kan moven.
	 * 
	 * @return	Boolean die true is als de robot voldoende energie heeft om te moven.
	 * 			|if(this.getEnergy().getEnergy() - moveCost(this).getEnergy() < 0)
	 * 			|	false
	 * 			|true
	 * 
	 * @note	Deze methode houdt enkel rekening met de minimale energie en niet met de positie.
	 */
	@Raw
	public boolean canMove(){
		if(this.getEnergy().getEnergy() - moveCost(this).getEnergy() < 0)
			return false;
		return true;
	}

	/*
	 * De robot en al zijn bezittingen worden vernietigd.
	 * 
	 * @post	De robot heeft geen bezittingen meer.
	 * 			|new.getPossessions().isEmpty()
	 * 
	 * @see 	roborally.model.Entity#destroy()
	 */
	@Override
	public void destroy() {
		for(Item item: this.getPossessions()){
			item.destroy();
		}
		this.getPossessions().clear();
		super.destroy();
	}

	/**
	 * Deze methode geeft de volgende positie weer met de huidige oriëntatie.
	 * 
	 * @param	pos
	 * 			Positie van waaruit vertrokken moet worden.
	 * 
	 * @param	or
	 * 			De huidige oriëntatie.
	 * 
	 * @return	De volgende positie met de huidige oriëntatie
	 * 			|if(or.equals(Orientation.UP))
	 *			|	new Position(pos.getX(), pos.getY() - 1)
	 *			|if(or.equals(Orientation.RIGHT))
	 *			|	new Position(pos.getX() + 1, pos.getY())
	 *			|if(or.equals(Orientation.DOWN))
	 *			|	new Position(pos.getX(), pos.getY() + 1);
	 *			|if(or.equals(Orientation.LEFT))
	 *			|	new Position(pos.getX() - 1, pos.getY())
	 *			|null
	 *
	 * @throws	IllegalStateException
	 * 			Er bestaat geen verdere positie meer met deze oriëntatie.
	 */
	private Position getNextPosition(Position pos, Orientation or) throws IllegalStateException{
		Position result = null;
		switch(or){
		case UP:
			try {
				result = new Position(pos.getX(), pos.getY() - 1);
			} catch (IllegalArgumentException e) {
				throw new IllegalStateException("Er bestaat geen verdere positie meer met deze oriëntatie.");
			}
			break;
		case RIGHT:
			try {
				result = new Position(pos.getX() + 1, pos.getY());
			} catch (IllegalArgumentException e) {
				throw new IllegalStateException("Er bestaat geen verdere positie meer met deze oriëntatie.");
			}
			break;
		case DOWN:
			try {
				result = new Position(pos.getX(), pos.getY() + 1);
			} catch (IllegalArgumentException e) {
				throw new IllegalStateException("Er bestaat geen verdere positie meer met deze oriëntatie.");
			}
			break;
		case LEFT:
			try {
				result = new Position(pos.getX() - 1, pos.getY());
			} catch (IllegalArgumentException e) {
				throw new IllegalStateException("Er bestaat geen verdere positie meer met deze oriëntatie.");
			}
			break;
		}
		return result;
	}

	/**
	 * 
	 * @param map
	 * @return
	 */
	private Node getMinimalFNode(HashMap<String, Node> map){
		Collection<Node> c = map.values();
		Iterator<Node> itr = c.iterator();
		Node minimalNode = itr.next();
		for (Node node : c){
			if (node.getFCost().getEnergy() < minimalNode.getFCost().getEnergy())
				minimalNode = node;
		}
		return minimalNode;
	}

	/**
	 * methode voor het aantal turns terug te geven om van een node met orientatie m naar een nabijgelegen node te 
	 * bewegen (vlak naast)
	 *  
	 * @param 	node
	 * 
	 * @param 	pos 
	 * 	
	 * @return
	 */
	private int getTurns(Node node, Position pos){
		int result = 0;
		if(node.getPosition().getX() == pos.getX() && node.getPosition().getY() == pos.getY())
			return result;
		/*
		 * In dit gedeelte kijken we de verhouding van de huidige robot met zijn bestemming na. Hier worden alle gevallen overlopen.
		 * Om dit visueel voor te stellen staan er letters die de posities voorstellen. De hoekpunten zijn in wijzerszin A, B, C en D.
		 * Vervolgens worden de middens van elke rand voorgesteld met E, F, G en H.
		 */
		if(pos.getX() == node.getPosition().getX() && pos.getY() < node.getPosition().getY()){
			// E
			switch(node.getOrientation()){
			case RIGHT:
			case LEFT:
				result = 1;
				break;
			case DOWN:
				result = 2;
				break;
			}
		}else if(pos.getX() == node.getPosition().getX() && pos.getY() > node.getPosition().getY()){
			// G
			switch(node.getOrientation()){
			case LEFT:
			case RIGHT:
				result = 1;
				break;
			case UP:
				result = 2;
				break;
			}
		}else if(pos.getX() > node.getPosition().getX() && pos.getY() == node.getPosition().getY()){
			// F
			switch(node.getOrientation()){
			case DOWN:
			case UP:
				result = 1;
				break;
			case LEFT:
				result = 2;
				break;
			}
		}else if(pos.getX() > node.getPosition().getX() && pos.getY() < node.getPosition().getY()){
			// B
			switch(node.getOrientation()){
			case RIGHT:
			case UP:
				result = 1;
				break;
			case DOWN:
			case LEFT:
				result = 2;
				break;
			}
		}else if(pos.getX() > node.getPosition().getX() && pos.getY() > node.getPosition().getY()){
			// C
			switch(node.getOrientation()){
			case DOWN:
			case RIGHT:
				result = 1;
				break;
			case LEFT:
			case UP:
				result = 2;	
				break;
			}
		}else if(pos.getX() < node.getPosition().getX() && pos.getY() == node.getPosition().getY()){
			// H
			switch(node.getOrientation()){
			case UP:
			case DOWN:
				result = 1;
				break;
			case RIGHT:
				result = 2;
				break;
			}
		}else if(pos.getX() < node.getPosition().getX() && pos.getY() < node.getPosition().getY()){
			// A
			switch(node.getOrientation()){
			case UP:
			case LEFT:
				result = 1;
				break;
			case RIGHT:
			case DOWN:
				result = 2;
				break;
			}
		}else if(pos.getX() < node.getPosition().getX() && pos.getY() > node.getPosition().getY()){
			// D
			switch(node.getOrientation()){
			case LEFT:
			case DOWN:
				result = 1;
				break;
			case UP:
			case RIGHT:
				result = 2;
				break;
			}
		}
		return result;
	}

	/**
	 * 
	 * @param 	currentNode
	 * 
	 * @param 	pos
	 * 
	 * @param 	robot
	 * 
	 * @return	
	 * 
	 */
	private Energy getGCost(Node currentNode, Position pos, Robot robot) {
		return Energy.energySum(Energy.energySum(currentNode.getGCost(), moveCost(robot)),
				new Energy(TURN_COST.getEnergy()*getTurns(currentNode, pos)));
	}

	/**
	 * Deze methode gaat de heuristieke kost naar een gegeven positie vanuit de meegegeven node berekenen.
	 * 
	 * @param 	position
	 * 			
	 * @param 	orientation
	 * 			
	 * @param 	pos
	 * 			
	 * @param 	robot
	 * 			
	 * @return	Energy
	 * 		
	 * 			
	 */
	private Energy getHCost(Position position, Orientation orientation, Position pos, Robot robot) {
		//de manhattan kost om met de robot van de beginpositie tot de eindpos te geraken.
		Energy manHattanCost = new Energy(moveCost(robot).getEnergy() * (int) calculateManhattan(position, pos));
		Energy turnCost = new Energy(TURN_COST.getEnergy()*getTurns(new Node(position,orientation,robot.getBoard()),pos));
		return Energy.energySum(manHattanCost, turnCost);
	}

	/**
	 * Deze methode gaat de orientatie bepalen die een node op een positie volgend op de currentnode zou krijgen.
	 *   
	 * @param 	currentNode
	 * 
	 * @param 	pos
	 * 
	 * @return
	 * 
	 */
	private Orientation getNodeOrientation(Node currentNode, Position pos) {
		Position previousPosition = currentNode.getPosition();
		if (previousPosition.getX() == pos.getX()){
			if (previousPosition.getY() < pos.getY())
				return Orientation.DOWN;
			return Orientation.UP;
		}
		if (previousPosition.getX() > pos.getX())
			return Orientation.LEFT;
		return Orientation.RIGHT;
	}

	/**
	 * Deze methode gaat op basis van het A* algorithme het optimale pad naar een gegeven positie teruggeven.
	 * 
	 * @param 	a
	 * 
	 * @param 	pos
	 * 
	 * @return
	 * 
	 * 	
	 */
	private HashMap<String,Node> aStarOnTo(Robot a, Position pos){
		//deze gaat direct naar de positie die opgegeven wordt

		HashMap<String,Node> open = new HashMap<>(); 
		// de experimentele posities die nog geëvalueerd moeten/kunnen worden
		Node startNode = new Node(a.getPosition(), a.getBoard(), new Energy(0) , 
				getHCost(a.getPosition(), a.getOrientation(),pos, a),a.getOrientation(), null);

		open.put(a.getPosition().toString(), startNode);
		// de startPositie aan de open list toevoegen
		HashMap<String,Node> closed = new HashMap<>(); 
		// de lijst met al geëvalueerde posities
		Board board = a.getBoard();

		while ( !open.isEmpty()){
			Node currentNode = getMinimalFNode(open);
			if (pos.toString().equals(currentNode.getPosition().toString())){
				open.remove(currentNode.getPosition().toString());
				closed.put(currentNode.getPosition().toString(), currentNode);
				return closed;
			}

			open.remove(currentNode.getPosition().toString());
			closed.put(currentNode.getPosition().toString(), currentNode);

			ArrayList<Position> neighbours = currentNode.getPosition().getNeighbours(a.getBoard());
			for (Position neighbour : neighbours){
				double gCostNeighbour = getGCost(currentNode, neighbour, a ).getEnergy();
				if (closed.containsKey(neighbour.toString())){
					if(closed.get(neighbour.toString()).getGCost().getEnergy() > gCostNeighbour){
						closed.get(neighbour.toString()).setGCost(new Energy(gCostNeighbour));
						closed.get(neighbour.toString()).setParent(currentNode);
					}
					continue;
				}
				else if ((open.containsKey(neighbour.toString())) && (open.get(neighbour.toString()).getGCost().getEnergy() > gCostNeighbour)){
					open.get(neighbour.toString()).setGCost(new Energy(gCostNeighbour));
					open.get(neighbour.toString()).setParent(currentNode);
				}
				else if (board.isPlacableOnPosition(neighbour,a)){
					open.put(neighbour.toString(), new Node(neighbour,board, new Energy(gCostNeighbour),
							getHCost(neighbour,getNodeOrientation(currentNode,neighbour) , pos, a),
							getNodeOrientation(currentNode,neighbour),currentNode));
				} else 
					open.remove(neighbour);
			} 
		}
		return closed;
	}

	/**
	 * Deze methode gaat posities waar een wall of een andere robot op staat uit de ArrayList gaan verwijderen.
	 * 
	 * @param 	neighbours
	 * 
	 * @param 	board
	 * 
	 * @return	
	 * 			
	 */
	private ArrayList<Position> removeOccupied(ArrayList<Position> neighbours, Board board) {
		ArrayList<Position> result = new ArrayList<>();
		for (Position pos : neighbours){
			if (board.isPlacableOnPosition(pos, this)){
				result.add(pos);
			}
		}
		return result;
	}

	/**
	 * Deze methode gaat alle nodes teruggeven waar deze robot zou kunnen geraken.
	 * 
	 * @param 	robot
	 * 			De robot waarvan je de haalbare posities wilt weten.
	 * 
	 * @return	HashMap<String,Node> 
	 * 			
	 */
	private HashMap<String,Node> getReachables(Robot robot){
		Energy upperbound = robot.getEnergy();
		ArrayList<String> explorable = new ArrayList<>();
		explorable.add(robot.getPosition().toString());
		HashMap<String,Node> reachables = new HashMap<>();

		while(!explorable.isEmpty()){
			Position currentPos = getPositionFromString(explorable.get(0));
			HashMap<String,Node> pad = aStarOnTo(robot,currentPos);
			Node currentNode = pad.get(currentPos.toString());
			explorable.remove(0);
			if(currentNode.getGCost().getEnergy() <= upperbound.getEnergy()){
				reachables.put(currentPos.toString(),currentNode);
				ArrayList<Position> preNeighbours = currentPos.getNeighbours(robot.getBoard());
				ArrayList<Position> neighbours = removeOccupied(preNeighbours,robot.getBoard());
				for(Position neighbour: neighbours){
					if((!reachables.containsKey(neighbour.toString())) && (!explorable.contains(neighbour.toString())))
						explorable.add(neighbour.toString());
				}
			}			
		}
		return reachables;
	}

	/**
	 * Deze methode gaat van een string van de vorm long1,long2 een object maken met die longs als positions.
	 * 
	 * @param 	posString
	 * 			De string waarvan je het Positie object wil maken.
	 * 
	 * @return	Position 
	 * 			|result.getX() = Long.parseLong(posString.split(",")[0])
	 * 			|result.getY() = Long.parseLong(posString.split(",")[1])
	 *
	 * @throws IllegalArgumentException
	 */
	public static Position getPositionFromString(String posString) throws IllegalArgumentException{
		if(posString.indexOf(",") == -1)
			throw new IllegalArgumentException("De String is niet geformatteerd als een positie.");
		String[] split = posString.split(", ");
		long x, y;
		try{
			x = Long.parseLong(split[0]);
		}catch(NumberFormatException e){
			throw new IllegalArgumentException(e.getMessage());
		}
		try{
			y = Long.parseLong(split[1]);
		}catch(NumberFormatException e){
			throw new IllegalArgumentException(e.getMessage());
		}
		Position result;
		try{
			result = new Position(x, y);
		}catch(IllegalArgumentException e){
			throw new IllegalArgumentException(e.getMessage());
		}
		return result;		
	}

	/**
	 * Deze Methode gaat de manhattandistance van punt A met pos pos1 tot punt B met pos pos2 berekenen.
	 * 
	 * @param 	pos1
	 * 			Het eerste coördinatenkoppel.
	 * 
	 * @param 	pos2
	 * 	 		Het tweede coördinatenkoppel.
	 * 
	 *@return 	long 
	 *			Een long die de manhattan teruggeeft.
	 *			|return result = (Math.abs(pos1.getX() - pos2.getX()) + Math.abs(pos1.getY() - pos2.getY()))
	 * 
	 */
	public static long calculateManhattan(Position pos1, Position pos2){
		return (Math.abs(pos1.getX() - pos2.getX()) + Math.abs(pos1.getY() - pos2.getY()));
	}

	@Override
	public String toString(){
		return "Position: " + this.getPosition().toString() + " Energy: " + this.getEnergy().toString() + " MaxEnergy : " +
				this.getMaxEnergy().toString();
	}

	public int loadProgramFromFile(String path) throws FileNotFoundException {

		Program prog = new Program(path);
		this.setProgram(prog);
		return 0;


	}

	private void setProgram(Program prog) {
		this.program = prog;
	}

	public int saveProgramToFile(String path) {
		// TODO Auto-generated method stub
		return 0;
	}

	public String prettyPrintProgram() {
		return getProgram().getPrettyPrint();
	}

	private Program getProgram() {
		return program;
	}

	public void stepn(int n) {
		// TODO Auto-generated method stub

	}

}

// hier ga ik de Random todo's zetten dus dingen die volgens mij ng moeten gebeuren.
//TODO: Inputstream met scanner om die programmas deftig te kunnen inlezen.
//TODO: Private shit beginnen maken.
//TODO: merge aanpassen.
//TODO: Alles van die programma's. 
//TODO: Testen veel testen.
//TODO: Error gevonden voor pick up 