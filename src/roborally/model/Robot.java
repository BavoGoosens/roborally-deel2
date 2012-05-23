package roborally.model;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Random;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Raw;
import roborally.program.Program;
import roborally.property.*;
import roborally.util.*;
import roborally.exception.*;

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
	 * De oriëntatie van de robot.
	 */
	private Orientation orientation;

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
	 * Het maxima van de energy 
	 */
	private Energy maxEnergy;
	
	/**
	 * De maximale bovengrens van de energie van een robot.
	 */
	public final static Energy MAX_ENERGY = new Energy(20000);
	
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
	 * De energie van de robot.
	 */
	private Energy energy;

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
	 * De energiekost van 1 draai.
	 */
	public final static Energy TURN_COST = new Energy(100);

	/**
	 * Deze methode beweegt de robot een stap vooruit indien mogelijk.
	 * 
	 * 
	 * @throws 	IllegalPositionException
	 * 			De positie waarnaar bewogen moet worden is ongeldig of reeds bezet.
	 * 			|AStarPath.getNextPosition(getPosition(), getOrientation()) == null || !getBoard().isPlacableOnPosition(AStarPath.getNextPosition(getPosition(), getOrientation()),this)
	 * 
	 * @throws	NotEnoughEnergyException
	 * 			De robot heeft onvoldoende energie om te bewegen.
	 * 			|!canMove()
	 * 
	 * @post	De robot staat een plaats verder.
	 * 			|new.getPosition().equals(getNextPosition(this.getPosition(), this.getOrientation()))
	 * 
	 * @post	De robot heeft energie verbruikt.
	 * 			|new.getEnergy().equals(Energy.energyDifference(this.getEnergy(), moveCost(this)))
	 */
	public void move() throws NotEnoughEnergyException, IllegalPositionException{
		if(!canMove()){
			throw new NotEnoughEnergyException(getEnergy());
		}
		Position destination;
		destination = AStarPath.getNextPosition(getPosition(), getOrientation());
		if(destination == null)
			throw new IllegalPositionException(destination);
		if(getBoard().isPlacableOnPosition(destination,this)){
			setPosition(destination);
			setEnergy(Energy.energyDifference(getEnergy(), moveCost(this)));
		}else{
			throw new IllegalPositionException(destination);
		}
	}

	/**
	 * Deze methode geeft de energie terug die nodig is om een bepaalde plaats te bereiken.
	 * 
	 * @param 	position
	 * 			De plaats die bereikt moet worden.
	 * 
	 * @return	De energie die nodig is om de plaats te bereiken.
	 * 			|(new AStarPath(this, position)).path.get(position).getGCost()
	 * 
	 * @throws 	TargetNotReachableException
	 * 			Het doel is niet bereikbaar.
	 * 			|!AStarPath.getReachables(this).containsKey(position)
	 */
	public Energy getEnergyRequiredToReach(Position position) throws TargetNotReachableException{
		if (AStarPath.getReachables(this).containsKey(position)){
			AStarPath aSPath = new AStarPath(this, position);
			return aSPath.path.get(position).getGCost();
		}
		throw new TargetNotReachableException(position);
	}

	/**
	 * Deze methode verplaatst de robot zo dicht mogelijk bij een andere robot.
	 * 
	 * @param	robot
	 * 			De robot waar naartoe moet bewogen worden.
	 * 
	 * @throws	IllegalArgumentException 
	 * 			De opgegeven robot is ongeldig.
	 * 			|this.equals(robot) || robot != null || this.getBoard() != (robot.getBoard())
	 * 
	 * @post	De robot staat zo dicht mogelijk bij de gegeven robot met een zo laag mogelijk energieverbruik.
	 * 			|if(PositionPair.getRobotsPositionpair(this, robot) != null)
	 * 			|	new.getPosition() == PositionPair.getRobotsPositionpair(this, robot).getPos1()
	 * 			|	(new robot).getPosition() == PositionPair.getRobotsPositionpair(this, robot).getPos2()
	 * 			|	new.getOrientation() == PositionPair.getRobotsPositionpair(this, robot).getOr1()
	 * 			|	(new robot).getOrientation() == PositionPair.getRobotsPositionpair(this, robot).getOr2()
	 * 			|	new.getEnergy() == Energy.energyDifference(this.getEnergy(),PositionPair.getRobotsPositionpair(this, robot).getCost1())
	 * 			|	(new robot).getEnergy() == Energy.energyDifference(robot.getEnergy(),PositionPair.getRobotsPositionpair(this, robot).getCost2())
	 * 			|}
	 * 
	 */
	public void moveNextTo(Robot robot) throws IllegalArgumentException{
		if (this.equals(robot) || robot == null || this.getBoard() != (robot.getBoard())) {
			throw new IllegalArgumentException("De opgegeven robot is ongeldig.");
		}
		PositionPair thePair = PositionPair.getRobotsPositionpair(this, robot);
		if(thePair != null){
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
	 * De kost van 1 schot met zijn laser.
	 */
	public final static Energy SHOOT_COST = new Energy(1000);

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
		while(getBoard().isValidPosition(AStarPath.getNextPosition(beginpos, getOrientation()))){
			beginpos = AStarPath.getNextPosition(beginpos, getOrientation());
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
	 * De energie die afgetrokken wordt van de maximale energie wanneer de robot geraakt wordt.
	 */
	public final static Energy SHOOT_DAMAGE = new Energy(4000);

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
	public static Energy moveCost(Robot robot){
		return Energy.energySum(MOVE_COST, new Energy(MOVE_COST_PER_KG.getEnergy()*(robot.getTotalWeight().getWeight() / 1000)));
	}
	
	/**
	 * De energiekost van 1 move (geen rekening houdend met bezittingen).
	 */
	private final static Energy MOVE_COST = new Energy(500);
	/**
	 * De energiekost die bij een move bijkomt per kg gewicht in bezittingen.
	 */
	private final static Energy MOVE_COST_PER_KG = new Energy(50);

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
	 * De lijst van voorwerpen die de robot bezit.
	 * 
	 * @note	Gebruik altijd Collections.sort met een ItemComparator() wanneer deze lijst gewijzigd wordt.
	 */
	private ArrayList<Item> possessions = new ArrayList<>();

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
	 * Deze methode plaatst een voorwerp uit de bezittingen van de robot op de huidige positie van de robot in het bord van de robot.
	 * 
	 * @param	item
	 * 			Het voorwerp dat op het bord geplaatst moet worden.
	 * 
	 * @throws 	IllegalArgumentException
	 * 			Het item is niet in het bezit van de robot.
	 * 
	 * @throws 	EntityNotOnBoardException
	 * 		 	De robot staat niet op een bord.
	 * 
	 * @post	Het voorwerp is niet langer in het bezit van de robot.
	 * 			|!new.getPossessions().contains(item)
	 * 
	 * @post	Het voorwerp bevindt zich op dezelfde positie als de robot.
	 * 			|(new item).getPosition().equals(new.getPosition())
	 * 
	 * @post	Het voorwerp bevindt zich op hetzelfde bord als de robot.
	 * 			|(new item).getBoard() == new.getBoard()
	 */
	public void drop(Item item) throws IllegalArgumentException, EntityNotOnBoardException{
		if(!isOnBoard()){
			throw new EntityNotOnBoardException();
		}else if(!getPossessions().contains(item)){
			throw new IllegalArgumentException("De robot heeft dit item niet.");
		}else{
			getPossessions().remove(item);
			item.putOnBoard(getBoard(), getPosition());
		}
	}

	/**
	 * Deze methode kijkt na of de robot kan draaien.
	 * 
	 * @return	Boolean die true is als de robot voldoende energie heeft om te draaien.
	 * 			|(this.getEnergy().getEnergy() - TURN_COST.getEnergy() >= 0)
	 */
	@Raw
	public boolean canTurn(){
		return (this.getEnergy().getEnergy() - TURN_COST.getEnergy() >= 0);
	}

	/**
	 * Deze methode kijkt na of de robot kan bewegen.
	 * 
	 * @return	Boolean die true is als de robot voldoende energie heeft om te moven.
	 * 			|(this.getEnergy().getEnergy() - moveCost(this).getEnergy() >= 0)
	 * 
	 * @note	Deze methode houdt enkel rekening met de minimale energie en niet met de positie.
	 */
	@Raw
	public boolean canMove(){
		return (this.getEnergy().getEnergy() - moveCost(this).getEnergy() >= 0);
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
	 * Deze methode laadt een programma in voor de robot.
	 * 
	 * @param	path
	 * 			Het bestandspad van het programma dat moet ingeladen worden.
	 * 
	 * @throws	FileNotFoundException
	 * 			Het pad van het bestand verwijst niet naar een bestand.
	 */
	public void loadProgramFromFile(String path) throws FileNotFoundException {
		this.setProgram(new Program(path));
	}

	/**
	 * Deze methode laadt een gegeven programma in.
	 * 
	 * @param	program
	 * 			Het programma dat moet ingeladen worden.
	 * 
	 * @post	Het gegeven programma is ingeladen.
	 * 			|new.getProgram() == program
	 */
	private void setProgram(Program program) {
		this.program = program;
	}

	/**
	 * Deze methode geeft het ingeladen programma terug.
	 * 
	 * @return	Het ingeladen programma.
	 * 			|program
	 */
	public Program getProgram() {
		return program;
	}
	
	/**
	 * De robot kan een programma laden en uitvoeren.
	 */
	private Program program;
	
	/**
	 * Deze methode slaat het ingeladen programma op in het gegeven pad.
	 * 
	 * @param	path
	 * 			Het pad waar het programma opgeslagen moet worden.
	 * 
	 * @return	Een integer die 0 is als de operatie geslaagd is en -1 indien deze mislukte.
	 * 			|try{
	 * 			|	PrintWriter out = new PrintWriter(new FileWriter(path))
	 * 			|	1
	 * 			|}catch(IOException e){
	 * 			|	-1
	 * 			|}
	 */
	public int saveProgramToFile(String path) {
		try{
			FileWriter outFile = new FileWriter(path);
			PrintWriter out = new PrintWriter(outFile);
			for(String str: getProgram().getOriginal()){
				out.print(str);
			}
			return 0;
		}catch(IOException e){
			return -1;
		}		
	}
	
	/**
	 * 
	 * @param n
	 */
	public void stepn(int n) {
		// TODO Auto-generated method stub
	}

	/*
	 * Deze methode zet het object om naar een String.
	 * 
	 * @return	Een textuele representatie van dit object waarbij duidelijk wordt wat de eigenschappen van dit object zijn.
	 * 			|super.toString() + ", energie: " + getEnergy().toString() + ", oriëntatie: " + getOrientation().toString() + ", gewicht: " + getTotalWeight().toString()
	 * 
	 * @see		roborally.model.Entity#toString()
	 */
	@Override
	public String toString() {
		return super.toString() + ", energie: " + getEnergy().toString() + ", maximale energie: " + getMaxEnergy().toString() + ", oriëntatie: " + getOrientation().toString() + ", gewicht: " + getTotalWeight().toString();
	}
	
	

}