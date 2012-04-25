package roborally.model;

import java.util.ArrayList;
import java.util.Collections;
import be.kuleuven.cs.som.annotate.*;
import roborally.basics.Energy;
import roborally.basics.Orientation;
import roborally.basics.Position;
import roborally.interfaces.IRobot;
import roborally.utils.RouteComparator;
/**
 * Een klasse om robots voor te stellen.
 * 
 * @invar	De energie van een robot kan nooit kleiner zijn dan 0 en niet groter dan 20000.
 * 			|isValidEnergy(new.getEnergy())
 * 
 * @invar 	Een robot heeft altijd een geldige oriëntatie.
 * 			|isValidDirection(new.getDirection)
 * 
 * @author 	Bavo Goosens (1e bachelor informatica, r0297884), Samuel Debruyn (1e bachelor informatica, r0305472)
 * 
 * @version 2.0
 */

public class Robot implements IRobot{

	private Position position;
	private Energy energy;
	private Orientation orientation;
	private static final double maxEnergy = 20000;

	/**
	 * Maakt een nieuwe Robot aan
	 * 
	 * @param 	position
	 * 			De positie van de robot
	 * 
	 * @param 	direction
	 * 			De oriëntatie van de robot: 0, 1, 2, 3 stellen respectievelijk boven, rechts, onder en links voor.
	 * 
	 * @param 	energy 
	 * 			De hoeveelheid initiële energie in Ws.
	 * 
	 * @post 	De positie van de nieuwe robot is gelijk aan de gegeven positie.
	 * 			|new.getPosition().getX() == x
	 * 			|new.getPosition().getY() == y
	 * 
	 * @post	De energie van de nieuwe robot is gelijk aan de gegeven energie .
	 * 			|new.getEnergy() == energy
	 * 
	 * @post 	De oriëntatie van de nieuwe robot is gelijk aan de gegeven oriëntatie.
	 * 			|new.getDirection() == direction
	 * 
	 */
	public Robot(Position position, Orientation orientation, Energy energy){
		setPosition(position);
		setEnergy(energy);
		setOrientation(orientation);
	}

	/**
	 * Methode om de oriëntatie van een robot te wijzigen.
	 *  
	 * @param 	direction
	 * 			De integer die de oriëntatie voorstelt die de robot moet krijgen.
	 * 
	 * @post	De direction van de robot is gelijk aan de gegeven parameter.
	 * 			|new.getDirection() == direction
	 * 
	 * @note	Indien de gegeven richting negatief was worden 1 en 3 omgewisseld. Dit is iets logischer wanneer bvb. 1 van de richting wordt afgetrokken en het resultaat -1 is. De implementatie is totaal.
	 */
	private void setOrientation(Orientation or) {
		this.orientation = or;
	}

	/**
	 * Methode om de energie van een robot te wijzigen.
	 * 
	 * @param	newEnergy
	 * 			Integer die de energie voorstelt die de robot moet krijgen.
	 * 
	 * @pre		newEnergy moet tussen 0 en het ingestelde maximum liggen.
	 * 			|isValidEnergy(newEnergy)
	 * 
	 * @post 	De energie van de robot bedraagt newEnergy.
	 * 			|new.getEnergy() == newEnergy
	 */
	private void setEnergy(Energy newEnergy) {
		this.energy = newEnergy;
	}

	/**
	 * Methode om de positie van een robot te wijzigen.
	 * 
	 * @param 	position
	 * 			|Nieuwe positie van de robot
	 * 
	 * @post 	De positie van de robot verandert naar die gegeven als parameters.
	 * 			|new.getPosition().getX() == x
	 * 			|new.getPosition().getY() == y
	 */
	private void setPosition(Position position){
		this.position = position;
	}


	/**
	 * Methode om de oriëntatie van de robot te verkrijgen.
	 * 
	 * @return 	integer
	 * 			De oriëntatie van de robot.
	 */
	@Basic
	public Orientation getOrientation(){
		return this.orientation;
	}

	/**
	 * Methode om de energie van de robot te verkrijgen.
	 * 
	 * @return 	double
	 * 			De energie van de robot.
	 */
	@Basic
	public Energy getEnergy(){
		return this.energy;
	}

	/**
	 * Methode om de positie van de robot te verkrijgen.
	 * 
	 * @return	Position
	 * 			De positie van de robot.
	 */
	@Basic
	public Position getPosition(){
		return this.position;
	}

	/**
	 * Methode om een robot 90 graden te draaien in wijzerszin.
	 * 
	 * @pre		De robot heeft een geldige richting en voldoende energie om te draaien.
	 * 			|this.getEnergy() >= 100
	 * 
	 * @post	De robot is 90 gedraaid in wijzerszin op het vlak en de waarde van direction is met 1 gestegen (of 0 geworden indien deze 3 was). De energie is met 100 gedaald.
	 * 			|new.getEnergy() == this.getEnergy() - 100
	 * 			|if(this.getDirection() == 0){
	 * 			|	new.getDirection() == 1
	 * 			|if(this.getDirection() == 1){
	 * 			|	new.getDirection() == 2
	 * 			|if(this.getDirection() == 2){
	 * 			|	new.getDirection() == 3
	 * 			|if(this.getDirection() == 3){
	 * 			|	new.getDirection() == 0
	 */
	public void turnClockWise(){
		this.setOrientation(this.getOrientation().getClockwiseOrientation());
		this.getEnergy().setEnergy(this.getEnergy().getEnergy() - Energy.TURN_COST);
	}

	/**
	 * Methode om een robot 90 graden te draaien in tegenwijzerszin.
	 * 
	 * @pre		De robot heeft een geldige richting en voldoende energie om te draaien.
	 * 			|new.getEnergy() >= 100
	 * 
	 * @post	De robot is 90 gedraaid in tegenwijzerszin op het vlak en de waarde van direction is met 1 gedaald (of 3 geworden indien deze 0 was). De energie is met 100 gedaald.
	 * 			|new.getEnergy() == this.getEnergy() - 100
	 * 			|if(this.getDirection() == 0){
	 * 			|	new.getDirection() == 3
	 * 			|if(this.getDirection() == 1){
	 * 			|	new.getDirection() == 0
	 * 			|if(this.getDirection() == 2){
	 * 			|	new.getDirection() == 1
	 * 			|if(this.getDirection() == 3){
	 * 			|	new.getDirection() == 2
	 */
	public void turnCounterClockWise(){
		this.setOrientation(this.getOrientation().getCounterClockwiseOrientation());
		this.getEnergy().setEnergy(this.getEnergy().getEnergy() - Energy.TURN_COST);
	}

	

	/**
	 * Deze methode verplaatst een robot zo dicht mogelijk bij een andere robot op een zo efficiënt mogelijke manier.
	 * 
	 * @param	target
	 * 			De robot waarnaar de huidige robot verplaatst moet worden.
	 * 
	 * @effect	De huidige robot staat zo dicht mogelijk bij de gegeven robot zonder een inefficiënte beweging te maken.
	 */
	public void moveNextTo(Robot target){
		
	}

	
	private void moveRobotsOnSamePlace(Robot other){
		
	}


	/**
	 * Methode die een robot 1 stap in de richting waarnaar hij kijkt beweegt.
	 * 
	 * @pre 	De robot moet voldoende energie hebben (500 of meer).
	 * 			|this.getEnergy() >= 500
	 * 
	 * @effect 	De robot staat 1 stap verder (in de richting waarnaar hij keek) en verliest 500Ws energie.
	 * 
	 * @post 	De robot staat 1 stap verder (in de richting waarnaar hij keek).
	 * 			|if (getDirection() == O)
	 * 			|				(new this).getY = this.getPosition().getY() - 1 
	 * 			|if (getDirection() == 1)
	 * 			|				(new this).getX = this.getPosition().getX() + 1
	 * 			|if (getDirection() == 2)
	 * 			|				(new this).getY = this.getPosition().getY() + 1
	 * 			|if (getDirection() == 3)
	 * 			|				(new this).getX = this.getPosition().getX() - 1
	 * 
	 * @post 	De robot verliest 500Ws energie.
	 * 			| (new this).getEnergy() = this.getEnergy() - 500
	 * 
	 * @throws	IllegalStateException
	 * 			De robot kan niet buiten het bord bewegen.
	 * 			|this.getDirection() == 0 && !Position.isValidPosition(getPosition().getX(), getPosition().getY() - 1)
	 * 			|this.getDirection() == 1 && !Position.isValidPosition(getPosition().getX() + 1, getPosition().getY())
	 * 			|this.getDirection() == 2 && !Position.isValidPosition(getPosition().getX(), getPosition().getY() + 1)
	 * 			|this.getDirection() == 3 && !Position.isValidPosition(getPosition().getX() - 1, getPosition().getY())
	 * 			
	 */
	public void move() throws IllegalStateException{
		if(this.getEnergy().getEnergy() >= 500){
			Position destination = null;
			try{
				switch (this.getOrientation()){
				case UP:
					destination = new Position(getPosition().getX(), getPosition().getY() - 1);
					break;
				case LEFT:
					destination = new Position(getPosition().getX() + 1, getPosition().getY());
					break;
				case DOWN:
					destination = new Position(getPosition().getX(), getPosition().getY() + 1);
					break;
				case RIGHT:
					destination = new Position(getPosition().getX() - 1, getPosition().getY());
					break;
				}
			}
			catch (IllegalArgumentException e){
				throw new IllegalStateException("De robot kan niet buiten het bord bewegen.");
			}
			this.setPosition(destination);
			this.setEnergy(new Energy(this.getEnergy().getEnergy() - Energy.MOVE_COST));
		}
	}

	/**
	 * Methode die een robot zijn energie herlaadt met een expliciete waarde.
	 * 
	 * @pre		Charge moet zelf een geldige hoeveelheid energie zijn.
	 * 			|isValidEnergy(charge)
	 * 
	 * @param 	charge
	 * 			Integer die het aantal watt energie waarmee herladen wordt voorstelt.
	 * 
	 * @post 	De energie van de robot is verhoogd met charge of tot aan het maximum.
	 * 			|(new this).getEnergy() = this.getEnergy() + charge
	 * 
	 */
	public void recharge(int charge){
		assert isValidEnergy(charge);
		assert isValidEnergy(this.getEnergy().getEnergy());
		if(isValidEnergy(charge)){
			int afterCharge = this.getEnergy().getEnergy() + charge;
			if(afterCharge <= Energy.MAXENERGY){
				this.setEnergy(new Energy(afterCharge));
			}else{
				this.setEnergy(new Energy(Energy.MAXENERGY));
			}
		}
	}

	/**
	 * Checkt of de gegeven orientatie toegestaan is.
	 * 
	 * @param 	dir
	 * 		Integer die de oriëntatie van een robot voorstelt.
	 * 
	 * @return boolean 
	 * 		Deze geeft true terug als een bepaalde oriëntatie toegestaan is.
	 */
	public boolean isValidDirection(int dir){
		return ((dir >= 0) && (dir <= 3));
	}

	/**
	 * Checkt of de gegeven energy een toegestane waarde is.
	 * 
	 * @param  	energy
	 * 			De gegeven energy die nagekeken moet worden.
	 * 
	 * @return 	boolean 
	 * 			Deze geeft true terug wanneer energy een toegestane waarde is.
	 */
	public boolean isValidEnergy(double energy){
		return (energy >= 0) && (energy <= this.maxEnergy);
	}


	/**
	 * Methode die nagaat of 2 robots naast elkaar staan.
	 * 
	 * @param 	robot
	 * 			De andere robot wiens positie met de huidige wordt vergeleken. 
	 * 
	 * @return	boolean
	 * 			Deze geeft true terug als de gegeven robot naast de huidige staat.
	 */
	private boolean isNextTo(Robot robot){
		if ((this.getPosition().getX() == robot.getPosition().getX()) && ((this.getPosition().getY() + 1 == robot.getPosition().getY())||(this.getPosition().getY() == robot.getPosition().getY()+ 1)))
			return true;
		else if ((this.getPosition().getY() == robot.getPosition().getY()) && ((this.getPosition().getX() + 1 == robot.getPosition().getX())|| (this.getPosition().getX() == robot.getPosition().getX() + 1)))
			return true;
		return false; 
	}
}