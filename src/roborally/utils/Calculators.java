package roborally.utils;

import java.util.ArrayList;
import java.util.HashMap;

import com.sun.java.swing.plaf.gtk.GTKConstants.Orientation;

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
		or = a.getOrientation();
		
	}
	
	public ArrayList<Node> aStar(Robot a, Robot b){
		ArrayList<Node> openSet = new ArrayList<Node>(); 
		//de experimentele posities die nog geëvalueerd moeten/kunnen worden
		openSet.add(new Node(a.getPosition(), 0, calculateHCost(a, b);
		Map<Position, F, G, H> closedSet = new HashMap<Position, F, G, H>(); 
		// de lijst met al geëvalueerde posities
		Map<> traveledMap = new HashMap<>();  
		// de kaart van al geteste nodes

	}
}
