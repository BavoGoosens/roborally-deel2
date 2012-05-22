package roborally.util;

import roborally.model.Robot;
import roborally.property.Energy;
import roborally.property.Orientation;
import roborally.property.Position;

/**
 * Deze klasse stelt een paar bestemmingen voor die gebruikt worden door de functie moveNextTo() in de klasse Robot.
 * 
 * @author 	Bavo Goosens (1e bachelor informatica, r0297884), Samuel Debruyn (1e bachelor informatica, r0305472)
 * 
 * @version 1.0
 */
public class PositionPair{
	private final Position pos1;
	private final Position pos2;
	private final long manhattanDistance;
	private final Energy cost1;
	private final Energy cost2;
	private final Energy totalCost;
	private final Orientation or1;
	private final Orientation or2;
	
	public PositionPair(Position pos1, Position pos2, Energy cost1 , Energy cost2, Orientation or1, Orientation or2){
		this.pos1 = pos1;
		this.pos2 = pos2;
		this.manhattanDistance = Robot.calculateManhattan(pos1, pos2);
		this.cost1 = cost1;
		this.cost2 = cost2;
		this.totalCost = Energy.energySum(cost1,cost2);
		this.or1 = or1;
		this.or2 = or2;
	}

	/**
	 * @return the pos1
	 */
	public Position getPos1() {
		return this.pos1;
	}

	/**
	 * @return the pos2
	 */
	public Position getPos2() {
		return this.pos2;
	}

	/**
	 * @return the manhattanDistance
	 */
	public long getManhattanDistance() {
		return this.manhattanDistance;
	}

	public Energy getCost1() {
		return this.cost1;
	}

	public Energy getCost2() {
		return this.cost2;
	}

	public Energy getTotalCost() {
		return this.totalCost;
	}

	public Orientation getOr1() {
		return this.or1;
	}

	public Orientation getOr2() {
		return this.or2;
	}
}
