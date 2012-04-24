package roborally.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import roborally.basics.Energy;
import roborally.basics.Position;
import roborally.model.Node;
import roborally.model.Robot;

public class Calculators {

	public long calculateManhattan(Position pos1, Position pos2){
		return Math.abs(pos1.getX() - pos2.getX()) + Math.abs(pos1.getY() - pos2.getY());
	} 
	
	public long getHCost(Node a, Node b){
		Energy manHattanCost = new Energy(Energy.MOVE_COST*calculateManhattan(a.getPosition(), b.getPosition()), Energy.eUnit.WS);
		Energy turnCost = new Energy(Energy.TURN_COST*calculateTurnsToPosition(), Energy.eUnit.WS);
		Energy cost = energySum(manHattanCost, turnCost);
		return (Long) cost.getAmountInWs();
	}
	
	public long calculateTurnsToPosition(Robot a, Position pos){
		roborally.basics.Orientation or = a.getOrientation();
		Position aPos = a.getPosition();
		
	}
	
	public HashMap<Position,Node> aStar(Robot a, Robot b){
		HashMap<Position,Node> openSet = new HashMap<Position,Node>(); 
		// de experimentele posities die nog geëvalueerd moeten/kunnen worden
		openSet.put(a.getPosition(), new Node(a.getPosition(), 0 , getHCost(a.getPosition(),a.getOrientation(), b),a.getOrientation(), null));
		// de startPositie aan de open list toevoegen
		HashMap<Position,Node> closedSet = new HashMap<Position, Node>(); 
		// de lijst met al geëvalueerde posities
		HashMap<Position,Node> travelledSet = new HashMap<Position,Node>();
		// de afgelegde weg 
		
		while ( !openSet.isEmpty()){
			Node currentNode = getMinimalFNode(openSet);
			if (b.getPosition().getNeighbours().contains(currentNode.getPosition())){
				return travelledSet;
			}
			openSet.remove(currentNode.getPosition());
	        closedSet.put(currentNode.getPosition(), currentNode);
	        
	        ArrayList<Position> neighbours = currentNode.getPosition().getNeighbours();
	        for (Position pos : neighbours){
	        	if (closedSet.containsKey(pos))
	        		continue;
	        	
	        	if (!openSet.containsKey(pos)){
	        		openSet.put(pos,new Node(pos,getGCost(currentNode, pos),getHCost(pos, getNodeOrientation(currentNode, pos), b), getNodeOrientation(currentNode, pos), currentNode));
	        	}
	        }
			
		}

	}
	
	private long getGCost(Node currentNode, Position pos) {
		long gCost = currentNode.getGCost() + Energy.MOVE_COST + Energy.TURN_COST*getTurns(currentNode.getOrientation(), pos);
		return gCost;
	}

	private Node getNodeOrientation(Node currentNode, Position pos) {
		return null;
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
