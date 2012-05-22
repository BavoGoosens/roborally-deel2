package roborally.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Set;

import roborally.model.Node;
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
		this.manhattanDistance = AStarPath.calculateManhattan(pos1, pos2);
		this.cost1 = cost1;
		this.cost2 = cost2;
		this.totalCost = Energy.energySum(cost1,cost2);
		this.or1 = or1;
		this.or2 = or2;
	}
	
	public Position getPos1() {
		return this.pos1;
	}

	public Position getPos2() {
		return this.pos2;
	}

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
	
	public static PositionPair getRobotsPositionpair(Robot robot1, Robot robot2){
		PositionPair thePair = null;
		HashMap<Position,Node> robot1Reachables = AStarPath.getReachables(robot1);
		HashMap<Position,Node> robot2Reachables = AStarPath.getReachables(robot2);
		Set<Position> robot1Keys = robot1Reachables.keySet();
		Set<Position> robot2Keys = robot2Reachables.keySet();
		ArrayList<PositionPair> posPairs = new ArrayList<>();
		for(Position robot1Pos: robot1Keys){
			for(Position robot2Pos: robot2Keys){
				Node robot1Node = robot1Reachables.get(robot1Pos);
				Node robot2Node = robot2Reachables.get(robot2Pos);
				PositionPair toAdd = new PositionPair(robot1Pos, robot2Pos,robot1Node.getGCost(),
						robot2Node.getGCost(), robot1Node.getOrientation(),robot2Node.getOrientation());
				posPairs.add(toAdd);
			}
		}
		ArrayList<PositionPair> semiValidPosPairs = new ArrayList<>();
		for(PositionPair pp: posPairs){
			if((pp.getManhattanDistance() != 0) && !(pp.getPos1().equals(pp.getPos2())))
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
			thePair = validPosPairs.get(0);
		}
		return thePair;
	}
	
}
