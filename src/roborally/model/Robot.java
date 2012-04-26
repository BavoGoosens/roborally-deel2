package roborally.model;

import java.util.Set;
import java.util.TreeSet;

import be.kuleuven.cs.som.annotate.Basic;
import roborally.basics.Energy;
import roborally.basics.Orientation;
import roborally.basics.Position;
import roborally.interfaces.IRobot;
import roborally.utils.BatteryComparator;

/**
 * Een klasse om robots voor te stellen.
 * 
 * @author 	Bavo Goosens (1e bachelor informatica, r0297884), Samuel Debruyn (1e bachelor informatica, r0305472)
 * 
 * @version 2.0
 */
public class Robot extends Entity implements IRobot{

	/**
	 * De energiekost van 1 move.
	 */
	public final static int MOVE_COST = 500;
	/**
	 * De energiekost van 1 draai.
	 */
	public final static int TURN_COST = 100;
	/**
	 * De minimale energie van een robot.
	 */
	public final static int MINENERGY = 0;
	/**
	 * De maximale energie van een robot.
	 */
	public final static int MAXENERGY = 20000;

	/**
	 * De energie van de robot.
	 */
	private Energy energy;
	/**
	 * De oriëntatie van de robot.
	 */
	private Orientation orientation;
	
	/**
	 * Een instance van de comparator waarmee batterijen vergeleken kunnen worden op basis van gewicht.
	 */
	private BatteryComparator bc;
	
	/**
	 * De set van batterijen die de robot bezit.
	 */
	private Set<Battery> Possessions = new TreeSet<Battery>(bc);
	
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
	 */
	public Robot(Orientation orientation, Energy energy){
		setEnergy(energy);
		setOrientation(orientation);
	}

	/**
	 * Methode om de oriëntatie van een robot te wijzigen.
	 *  
	 * @param 	or
	 * 			De oriëntatie die de robot moet krijgen.
	 * 
	 * @post	De direction van de robot is gelijk aan de gegeven parameter.
	 * 			|new.getOrientation() == or
	 */
	private void setOrientation(Orientation or) {
		this.orientation = or;
	}

	/**
	 * Methode om de oriëntatie van de robot te verkrijgen.
	 * 
	 * @return 	De oriëntatie van de robot.
	 * 			|new.orientation 			
	 */
	@Basic
	public Orientation getOrientation(){
		return orientation;
	}

	/**
	 * Methode om de energie van een robot te wijzigen.
	 * 
	 * @param	newEnergy
	 * 			De energie die de robot moet krijgen.
	 * 
	 * @post 	De energie van de robot is newEnergy.
	 * 			|new.getEnergy() == newEnergy
	 */
	private void setEnergy(Energy newEnergy) {
		this.energy = newEnergy;
	}

	/**
	 * Methode om de energie van de robot te verkrijgen.
	 * 
	 * @return 	De energie van de robot.
	 * 			|new.energy
	 */
	@Basic
	public Energy getEnergy(){
		return energy;
	}

	/**
	 * Methode die controleert of de opgegeven hoeveelheid energie een geldige hoeveelheid is.
	 * 
	 * @param 	energy
	 * 			De hoeveelheid energie.
	 *
	 * @return	boolean
	 * 			|(amount >= MINENERGY) && (amount <= MAXENERGY)
	 */
	public static boolean isValidRobotEnergyAmount(Energy energy){
		return (energy.getEnergy() >= MINENERGY) && (energy.getEnergy() <= MAXENERGY);
	}

	/**
	 * Deze methode berekent de verhouding tussen de huidige hoeveelheid energie en de maximale hoeveelheid energie.
	 * 
	 * @return	De verhouding tussen de huidige hoeveelheid energie en de maximale hoeveelheid energie.
	 * 			|new.getEnergy().getEnergy()/MAXENERGY
	 */
	public double getEnergyFraction(){
		return this.getEnergy().getEnergy()/MAXENERGY;
	}

	/**
	 * Draait de robot 1 keer in wijzerzin.
	 * 
	 * @post	De nieuwe oriëntatie van de robot is gelijk aan de volgende oriëntatie in wijzerzin.
	 * 			|if(isValidRobotEnergyAmount(new Energy(this.getEnergy().getEnergy() - TURN_COST)))
	 * 			|	new.getOrientation() == this.getOrienation().getClockwiseOrientation()
	 * @post	De energie van de robot is verminderd met benodigde energie voor een draai.
	 * 			|if(isValidRobotEnergyAmount(new Energy(this.getEnergy().getEnergy() - TURN_COST)))
	 * 			|	new.getEnergy().getEnergy().equals(this.getEnergy().getEnergy() - TURN_COST)
	 */
	public void turnClockWise(){
		//TODO
		if(isValidRobotEnergyAmount(new Energy(this.getEnergy().getEnergy() - TURN_COST))){
			this.setOrientation(this.getOrientation().getClockwiseOrientation());
			this.getEnergy().setEnergy(this.getEnergy().getEnergy() - TURN_COST);
		}
	}

	/**
	 * Draait de robot 1 keer in tegenwijzerzin.
	 * 
	 * @post	De nieuwe oriëntatie van de robot is gelijk aan de volgende oriëntatie in wijzerzin.
	 * 			|if(isValidRobotEnergyAmount(new Energy(this.getEnergy().getEnergy() - TURN_COST)))
	 * 			|	new.getOrientation() == this.getOrienation().getCounterClockwiseOrientation()
	 * @post	De energie van de robot is verminderd met benodigde energie voor een draai.
	 * 			|if(isValidRobotEnergyAmount(new Energy(this.getEnergy().getEnergy() - TURN_COST)))
	 * 			|	new.getEnergy().getEnergy().equals(this.getEnergy().getEnergy() - TURN_COST)
	 */
	public void turnCounterClockWise(){
		//TODO
		if(isValidRobotEnergyAmount(new Energy(this.getEnergy().getEnergy() - TURN_COST))){
			this.setOrientation(this.getOrientation().getCounterClockwiseOrientation());
			this.getEnergy().setEnergy(this.getEnergy().getEnergy() - TURN_COST);
		}
	}
	
	/**
	 * Deze methode beweegt de robot een stap vooruit indien mogelijk.
	 */
	public void move(){
		//TODO
	}
	
	/**
	 * Deze methode geeft de energie terug die nodig is om een bepaalde plaats te bereiken.
	 * 
	 * @param 	position
	 * 			De plaats die bereikt moet worden.
	 * 
	 * @return	De energie die nodig is om de plaats te bereiken.
	 */
	public Energy getEnergyRequiredToReach(Position position){
		//TODO
		return null;
	}
	
	/**
	 * Deze methode verplaatst de robot zo dicht mogelijk bij een andere robot.
	 * 
	 * @param	robot
	 * 			De robot waar naartoe moet bewogen worden.
	 */
	public void moveNextTo(Robot robot){
		//TODO
	}
	
	/**
	 * Deze methode doet een robot schieten met zijn laser.
	 * 
	 * @effect	Mogelijks wordt een object op het bord geraakt en verwijderd.
	 */
	public void shoot(){
		if(this.getPosition() != null){
			//TODO
		}else{
			System.err.println("Robot staat niet op een bord!");
		}
	}

	public void recharge(){
		//TODO
	}
	
	public int moveCost(){
		//TODO
		return MOVE_COST;
	}
	
	public Set<Battery> getPossessions(){
		return this.Possessions;
	}

	public void pickUp(Battery battery) {
		// TODO Auto-generated method stub
		
		
	}

	public void use(Battery battery) {
		// TODO Auto-generated method stub
		
	}

	public void drop(Battery battery) {
		// TODO Auto-generated method stub
		
	}
	
	

}