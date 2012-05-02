package roborally.utils;

import roborally.basics.Position;

public class PositionPair {
	private final Position pos1;
	private final Position pos2;
	private final long manhattanDistance;
	
	public PositionPair(Position pos1, Position pos2, long manhattanDistance){
		this.pos1 = pos1;
		this.pos2 = pos2;
		this.manhattanDistance = manhattanDistance;
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
}
