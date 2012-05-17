package roborally.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Raw;
import roborally.basics.*;
import roborally.utils.*;

/**
 * Een klasse om robots voor te stellen.
 * 
 * @invar	De hoeveelheid energie van een batterij moet altijd geldig zijn.
 * 			|isValidRobotEnergyAmount(getEnergy())
 * 
 * @author 	Bavo Goosens (1e bachelor informatica, r0297884), Samuel Debruyn (1e bachelor informatica, r0305472)
 * 
 * @version 2.1
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
	 * De minimale energie van een robot.
	 */
	public final static Energy MINENERGY = new Energy(0);
	/**
	 * De maximale bovengrens van de energie van een robot.
	 */
	public final static Energy MAXENERGY = new Energy(20000);
	/**
	 * De kost van 1 schot met zijn laser.
	 */
	public final static Energy SHOOT_COST = new Energy(1000);

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
	 * De lijst van batterijen die de robot bezit.
	 * 
	 * @note	Gebruik altijd Collections.sort met een BatteryComparator() wanneer deze lijst gewijzigd wordt.
	 */
	private ArrayList<Item> possessions = new ArrayList<>();
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
	 * 			|new.getEnergy().equals(energy) == true
	 * 
	 * @post	De oriëntatie van de robot is gelijk aan de opgegeven oriëntatie.
	 * 			|new.getOrientation().equals(orientation) == true
	 */
	public Robot(Orientation orientation, Energy energy){
		this.setEnergy(energy);
		setOrientation(orientation);
		setMaxEnergy(energy);
	}

	/**
	 * Deze methode wijzigt de energie van de robot.
	 * 
	 * @param	energy
	 * 			De nieuwe energie die de robot moet krijgen.
	 * 
	 * @post	De energie van de robot is gelijk aan de gegeven energie.
	 * 			|new.getEnergy().equals(energy) == true
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
		this.orientation = or;
	}

	/**
	 * Methode om de oriëntatie van de robot te verkrijgen.
	 * 
	 * @return 	De oriëntatie van de robot.
	 * 			|this.orientation 			
	 */
	@Basic
	public Orientation getOrientation(){
		return this.orientation;
	}

	/**
	 * Methode om de energie van de robot te verkrijgen.
	 * 
	 * @return 	De energie van de robot.
	 * 			|this.energy
	 */
	@Basic
	public Energy getEnergy(){
		return this.energy;
	}

	/**
	 * 
	 * @return
	 */
	//TODO: doc
	@Basic
	public Energy getMaxEnergy() {
		return this.maxEnergy;
	}

	/**
	 * Deze methode stelt de bovengrens in van de nergie van de robot
	 * 
	 * @param currentMaxEnergy
	 */
	//TODO: doc + hoe uitgewerkt? nom def of tot?
	@Basic
	public void setMaxEnergy(Energy currentMaxEnergy) {
		if (currentMaxEnergy.getEnergy() < Robot.MAXENERGY.getEnergy())
			this.maxEnergy = currentMaxEnergy;
		this.maxEnergy = Robot.MAXENERGY;
	}

	/**
	 * Methode die controleert of de opgegeven hoeveelheid energie een geldige hoeveelheid is.
	 * 
	 * @param 	energy
	 * 			De hoeveelheid energie.
	 *
	 * @return	Boolean die weergeeft of de hoeveelheid energie geldig is.
	 * 			|(energy.getEnergy() >= MINENERGY.getEnergy() && energy.getEnergy() <= MAXENERGY.getEnergy())
	 */
	public static boolean isValidRobotEnergyAmount(Energy energy){
		return (energy.getEnergy() >= MINENERGY.getEnergy() && energy.getEnergy() <= MAXENERGY.getEnergy());
	}

	/**
	 * Deze methode berekent de verhouding tussen de huidige hoeveelheid energie en de maximale hoeveelheid energie.
	 * 
	 * @return	De verhouding tussen de huidige hoeveelheid energie en de maximale hoeveelheid energie.
	 * 			|this.getEnergy().getEnergy()/MAXENERGY.getEnergy()
	 */
	public double getEnergyFraction(){
		return this.getEnergy().getEnergy()/MAXENERGY.getEnergy();
	}

	/**
	 * Draait de robot 1 keer in wijzerzin.
	 * 
	 * @post	De nieuwe oriëntatie van de robot is gelijk aan de volgende oriëntatie in wijzerzin.
	 * 			|if(isValidRobotEnergyAmount(Energy.energyDifference(this.getEnergy(), TURN_COST)))
	 * 			|	new.getOrientation() == this.getOrienation().getClockwiseOrientation()
	 * 
	 * @post	De energie van de robot is verminderd met benodigde energie voor een draai.
	 * 			|if(isValidRobotEnergyAmount(Energy.energyDifference(this.getEnergy(), TURN_COST)))
	 * 			|	new.getEnergy().equals(Energy.energyDifference(this.getEnergy(), TURN_COST))
	 */
	public void turnClockWise(){
		if(this.canTurn()){
			this.setOrientation(this.getOrientation().getClockwiseOrientation());
			this.setEnergy(Energy.energyDifference(this.getEnergy(), TURN_COST));
		}
	}

	/**
	 * Draait de robot 1 keer in tegenwijzerzin.
	 * 
	 * @post	De nieuwe oriëntatie van de robot is gelijk aan de volgende oriëntatie in wijzerzin.
	 * 			|if(isValidRobotEnergyAmount(Energy.energyDifference(this.getEnergy(), TURN_COST)))
	 * 			|	new.getOrientation() == this.getOrienation().getCounterClockwiseOrientation()
	 * 
	 * @post	De energie van de robot is verminderd met benodigde energie voor een draai.
	 * 			|if(isValidRobotEnergyAmount(Energy.energyDifference(this.getEnergy(), TURN_COST)))
	 * 			|	new.getEnergy().equals(Energy.energyDifference(this.getEnergy(), TURN_COST))
	 */
	public void turnCounterClockWise(){
		if(this.canTurn()){
			this.setOrientation(this.getOrientation().getCounterClockwiseOrientation());
			this.setEnergy(Energy.energyDifference(this.getEnergy(), TURN_COST));
		}
	}

	/**
	 * Deze methode beweegt de robot een stap vooruit indien mogelijk.
	 * 
	 * @throws 	IllegalStateException
	 * 			De robot heeft onvoldoende energie om te bewegen of de positie waarnaar bewogen moet worden is ongeldig of reeds bezet.
	 * 			|!this.canMove() || !Position.isValidPosition(this.getOrientation().getNextPosition(this.getPosition())) || !this.getBoard().isPlacableOnPosition(Calculator.getNextPosition(this.getPosition(), this.getOrientation()))
	 * 
	 * @post	De robot staat een plaats verder.
	 * 			|new.getPosition().equals(Calculator.getNextPosition(this.getPosition(), this.getOrientation())) == true
	 * 
	 * @post	De robot heeft energie verbruikt.
	 * 			|new.getEnergy().equals(Energy.energyDifference(this.getEnergy(), moveCost(this))) == true
	 */
	public void move() throws IllegalStateException{
		if(!this.canMove()){
			throw new IllegalStateException("De robot heeft onvoldoende energie om te bewegen.");
		}
		Position destination;
		try{
			destination = Calculator.getNextPosition(this.getPosition(), this.getOrientation());
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
	 * 			|Calculator.aStarOnTo(this, position).get(position.toString()).getGCost()
	 *
	 */
	//TODO: Moet een negatief getal terugeven indien niet bereikbaar of te weinig energie
	public Energy getEnergyRequiredToReach(Position position){
		HashMap<String, Node> resultpad = Calculator.aStarOnTo(this, position);
		Node n = resultpad.get(position.toString());
		return n.getGCost();
	}

	/**
	 * Deze methode verplaatst de robot zo dicht mogelijk bij een andere robot.
	 * 
	 * @param	robot
	 * 			De robot waar naartoe moet bewogen worden.
	 * 
	 * @post	De robot staat zo dicht mogelijk bij de gegeven robot met een zo laag mogelijk energieverbruik.
	 * 			|TODO: formele post doc bij moveNextTo()
	 */
	public void moveNextTo(Robot robot){
		HashMap<String,Node> thisReachables = Calculator.getReachables(this);
		HashMap<String,Node> otherReachables = Calculator.getReachables(robot);
		Set<String> thisKeys = thisReachables.keySet();
		Set<String> otherKeys = otherReachables.keySet();
		ArrayList<PositionPair> posPairs = new ArrayList<>();
		for(String thisPosString: thisKeys){
			for(String otherPosString: otherKeys){
				Node thisNode = thisReachables.get(thisPosString);
				Node otherNode = otherReachables.get(otherPosString);
				PositionPair toAdd = new PositionPair(Calculator.getPositionFromString(thisPosString), Calculator.getPositionFromString(otherPosString),thisNode.getGCost(),
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
			this.setPosition(thePair.getPos1());
			robot.setPosition(thePair.getPos2());
			this.setOrientation(thePair.getOr1());
			robot.setOrientation(thePair.getOr2());
			this.setEnergy(Energy.energyDifference(this.getEnergy(),thePair.getCost1()));
			robot.setEnergy(Energy.energyDifference(robot.getEnergy(),thePair.getCost2()));	
		}
	}

	/**
	 * Deze methode doet een robot schieten met zijn laser.
	 * 
	 * @throws	IllegalStateException
	 * 			De robot staat niet op een bord.
	 * 			|!this.isOnBoard()
	 * 
	 * @post	De robot verliest energie. De hoeveelheid is bepaald in de constante SHOOT_COST.
	 * 			|new.getEnergy().equals(Energy.energyDifference(this.getEnergy(), SHOOT_COST)) == true
	 * 
	 * @post	Mogelijks wordt een object geraakt en vernietigd.
	 * 			|TODO: formele post doc bij shoot()
	 */
	public void shoot() throws IllegalStateException{
		if(this.isOnBoard()){	
			Position beginpos = this.getPosition();
			Orientation beginor = this.getOrientation();
			boolean found = false;
			while(this.getBoard().isValidBoardPosition(Calculator.getNextPosition(beginpos, beginor)) && !found){
				beginpos = Calculator.getNextPosition(beginpos, beginor);
				HashSet<Entity> content = this.getBoard().getEntityOnPosition(beginpos);
				if(content != null){
					Random rndm = new Random();
					((Entity) content.toArray()[rndm.nextInt(content.toArray().length)]).destroy();
					found = true;
				}
			}
			this.setEnergy(Energy.energyDifference(this.getEnergy(), SHOOT_COST));
		}else{
			throw new IllegalStateException("De robot staat niet op een bord.");
		}
	}

	/**
	 * Deze methode laadt een robot op met de opgegeven hoeveelheid energie.
	 * 
	 * @param 	energy
	 * 			Energie waarmee moet opgeladen worden.
	 * 
	 * @post	De robot is opgeladen met de opgegeven hoeveelheid energie.
	 * 			|if(!isValidRobotEnergyAmount(Energy.energySum(this.getEnergy(), energy).getEnergy())){
	 * 			|	new.getEnergy() == new Energy(MAXENERGY)
	 * 			|}else{
	 * 			|	new.getEnergy() == Energy.energySum(this.getEnergy(), energy)
	 * 			|}
	 * 			
	 */
	public void recharge(Energy energy){
		Energy newEnergy = Energy.energySum(this.getEnergy(), energy);
		if(!isValidRobotEnergyAmount(newEnergy))
			newEnergy = MAXENERGY;
		this.setEnergy(newEnergy);
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
	 * Geeft het totale gewicht van alles wat de robot draagt.
	 * 
	 * @return	Het totale gewicht van alles wat de robot draagt.
	 * 			|int totalWeight = 0;
	 * 			|for(Battery batt: this.getPossessions()){
	 * 			|	totalWeight += batt.getWeight().getWeight();
	 * 			|}
	 * 			|new Weight(totalWeight)
	 */
	public Weight getTotalWeight(){
		int totalWeight = 0;
		for(Item item: this.getPossessions()){
			totalWeight += item.getWeight().getWeight();
		}
		return new Weight(totalWeight);
	}

	/**
	 * Geeft een lijst terug van alle items die de robot momenteel draagt.
	 * 
	 * @return	De lijst van batterijen
	 * 			|this.Possessions
	 */
	public ArrayList<Item> getPossessions(){
		return this.possessions;
	}

	/**
	 * Deze methode neemt een batterij op in de bezittingen van de robot.
	 * 
	 * @param 	battery
	 * 			De batterij die moet opgenomen worden.
	 * 
	 * @throws 	IllegalArgumentException
	 * 			De batterij staat niet op dezelfde plaats als de robot of niet op hetzelfde bord.
	 * 			|!this.getBoard().equals(battery.getBoard()) || !this.getPosition().equals(battery.getPosition())
	 * 
	 * @throws 	IllegalStateException
	 * 			De robot staat niet op een bord.
	 * 			|!this.isOnBoard()
	 * 
	 * @post	De batterij zit in de bezittingen van de robot.
	 * 			|new.getPossessions().contains(battery)
	 * 
	 * @post	De batterij staat niet meer op een bord.
	 * 			|!battery.isOnBoard()
	 */
	public void pickUp(Item item) throws IllegalArgumentException, IllegalStateException{
		if(!this.isOnBoard()){
			throw new IllegalStateException("De robot staat niet op een bord.");
		}else if(!this.getBoard().equals(item.getBoard())){
			throw new IllegalArgumentException("De robot staat niet op hetzelfde bord als de batterij.");
		}else if(!this.getPosition().toString().equals(item.getPosition().toString())){
			throw new IllegalArgumentException("De robot staat niet op dezelfde positie als de batterij.");
		}else{
			item.removeFromBoard();
			this.getPossessions().add(item);
			Collections.sort(this.getPossessions(), new ItemComparator());
		}
	}


	/**
	 * Deze methode laadt de robot op met de energie in een batterij die hij bij zich heeft.
	 * 
	 * @param	battery
	 * 			De batterij die gebruikt moet worden.
	 * 
	 * @post	De robot is opgeladen met een hoeveelheid energie uit de batterij (dit kan onveranderd blijven).
	 * 			|new.getEnergy().equals(Energy.energySum(this.getEnergy(), Energy.energyDifference(battery.getEnergy(), Energy.energyDifference(Robot.MAXENERGY, this.getEnergy())))) == true
	 * 
	 * @post	De batterij heeft mogelijks energie verloren.
	 * 			|(new battery).getEnergy().equals(Energy.energyDifference(battery.getEnergy(), Energy.energyDifference(Robot.MAXENERGY, this.getEnergy()))) == true
	 */
	//TODO: neke checke
	public void use(Item item) {
		if (this.getPossessions().contains(item)){
			if(item.isDestroyed()){
				this.getPossessions().remove(item);
			}else{
				if (item instanceof Battery){
					Energy toRecharge = Energy.energyDifference(item.getEnergy(), Energy.energyDifference(Robot.MAXENERGY, this.getEnergy()));
					item.setEnergy(toRecharge);
					this.setEnergy(Energy.energySum(this.getEnergy(), toRecharge));
					if(item.getEnergy().getEnergy() == 0){
						this.getPossessions().remove(item);
						Collections.sort(this.getPossessions(), new ItemComparator());
						item.destroy();
					}
				} else if (item instanceof SurpriseBox){
					getSurpriseBoxAction(item);
				}else {
					// item instanceof Repairkit
				}
			}
		}else{
			throw new IllegalArgumentException("Deze batterij is niet in het bezit van de robot."); 
		}
	}

	public void getSurpriseBoxAction(Item item){
		Random rand = new Random();
		int choice = 1 + rand.nextInt(3);
		if (choice == 1){
			//explosion
			this.setMaxEnergy(new Energy(this.getMaxEnergy().getEnergy()- SHOOT_DAMAGE.getEnergy()));
			if (this.getEnergy().getEnergy() < this.getMaxEnergy().getEnergy()){
				this.setEnergy(this.getMaxEnergy());
			}
			item.destroy();
		}else if (choice == 2){
			//teleport
			Position teleport = getRandomBoardPosition();
			this.setPosition(teleport);
			item.destroy();
		}else{
			//random new item
			choice = 1 + rand.nextInt(3);
			if (choice == 1){
				//SurpriseBox
				SurpriseBox box = new SurpriseBox(item.getWeight());
				this.getPossessions().add(box);
				item.destroy();
			}else if (choice == 2){
				//Battery
				Battery bat = new Battery(new Energy(rand.nextInt((int)Battery.MAXBATTERYENERGY.getEnergy()) + 1),item.getWeight());
				this.getPossessions().add(bat);
				item.destroy();
			}else{
				//Repairkit
				RepairKit kit = new RepairKit(new Energy(rand.nextInt((int)RepairKit.MAX_ENERGY.getEnergy()) + 1) ,item.getWeight());
				this.getPossessions().add(kit);
				item.destroy();
			}
		}
	}

	private Position getRandomBoardPosition() {
		long height = this.getBoard().getHeight();
		long width = this.getBoard().getHeight();
		Random rand = new Random();
		long randomX = 0;
		long randomY = 0;
		boolean xfound = false;
		boolean yfound = false;		
		while (!xfound){
			randomX = rand.nextLong();
			if (randomX >= 0 && randomX <= width){
				xfound = true;
			}
		}
		while (!yfound){
			randomY = rand.nextLong();
			if (randomY >= 0 && randomY <= height){
				xfound = true;
			}
		}
		Position teleport = new Position(randomX, randomY);
		if (this.getBoard().isPlacableOnPosition(teleport, this)){
			return teleport;		
		}
		return getRandomBoardPosition();
	}

	/**
	 * Deze methode plaats een Item uit de bezittingen van de robot op de huidige positie van de robot in het bord van de robot.
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
		if(!this.isOnBoard()){
			throw new IllegalStateException("De robot staat niet op een bord.");
		}else if(!this.getPossessions().contains(item)){
			throw new IllegalArgumentException("De robot heeft dit item niet.");
		}else{
			this.getPossessions().remove(item);
			item.putOnBoard(this.getBoard(), this.getPosition());
			Collections.sort(this.getPossessions(), new ItemComparator());
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

}