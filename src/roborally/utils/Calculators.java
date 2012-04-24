package roborally.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

import roborally.basics.Position;
import roborally.model.Node;
import roborally.model.Robot;

public class Calculators {
	public final long MOVE_COST = 500;
	public final long TURN_COST = 100;

	public long calculateManhattan(Position pos1, Position pos2){
		return Math.abs(pos1.getX() - pos2.getX()) + Math.abs(pos1.getY() - pos2.getY());
	} 
	
	public long calculateHCost(Robot a, Robot b){
		long manHattanCost = MOVE_COST*calculateManhattan(a.getPosition(), b.getPosition());
		long turnCost = TURN_COST*calculateTurnsToPosition();
		long cost = manHattanCost + turnCost;
		return cost;
	}
	
	public long calculateTurnsToPosition(Robot a, Position pos){
		roborally.basics.Orientation or = a.getOrientation();
		Position aPos = a.getPosition();
		
	}
	
	public HashMap<Position,Node> aStar(Robot a, Robot b){
		HashMap<Position,Node> openSet = new HashMap<Position,Node>(); 
		// de experimentele posities die nog geëvalueerd moeten/kunnen worden
		openSet.put(a.getPosition(), new Node(a.getPosition(), 0 , calculateHCost(a, b),a.getOrientation(), null));
		// de startPositie aan de open list toevoegen
		HashMap<Position,Node> closedSet = new HashMap<Position, Node>(); 
		// de lijst met al geëvalueerde posities
		while ( !openSet.isEmpty()){
			Node currentNode = getMinimalFNode(openSet);
			if (b.getPosition().getNeighBours().contains(currentNode.getPosition())){
				troleoeleooeoel
			}
			
		}

	}
	
	public Node getMinimalFNode(HashMap<Position, Node> map){
		Collection<Node>c = map.values();
		Iterator<Node> itr = c.iterator();
		Node minimalNode = itr.next();
		for (Node node : c){
			if (node.getFCost() < minimalNode.getFCost())
				minimalNode = node;
		}
		return minimalNode;
	}
	
}
