package roborally.utils;

import roborally.basics.Energy;
import roborally.basics.Position;

public class PositionPair{
	private final Position pos1;
	private final Position pos2;
	private final long manhattanDistance;
	private final Energy cost;
	
	public PositionPair(Position pos1, Position pos2, Energy cost){
		this.pos1 = pos1;
		this.pos2 = pos2;
		this.manhattanDistance = Calculator.calculateManhattan(pos1, pos2);
		this.cost = cost;
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

	public Energy getCost() {
		return this.cost;
	}
}
