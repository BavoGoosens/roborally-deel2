package roborally.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

import roborally.model.Board;
import roborally.model.Node;
import roborally.model.Robot;
import roborally.property.Energy;
import roborally.property.Orientation;
import roborally.property.Position;

/**
 * Deze klasse voert berekeningen uit die nodig zijn om het paar posities te vinden bij moveNextTo in Robot.
 * 
 * @author 	Bavo Goosens (1e bachelor informatica, r0297884), Samuel Debruyn (1e bachelor informatica, r0305472)
 * 
 * @version 1.0
 */
public class AStarPath {
	
	public HashMap<Position,Node> path;
	
	public AStarPath(Robot robot, Position pos){
		//deze gaat direct naar de positie die opgegeven wordt

		HashMap<Position, Node> open = new HashMap<Position, Node>(); 
		// de experimentele posities die nog geëvalueerd moeten/kunnen worden
		Node startNode = new Node(robot.getPosition(), robot.getBoard(), new Energy(0) , 
				getHCost(robot.getPosition(), robot.getOrientation(),pos, robot),robot.getOrientation(), null);

		open.put(robot.getPosition(), startNode);
		// de startPositie aan de open list toevoegen
		HashMap<Position, Node> closed = new HashMap<>(); 
		// de lijst met al geëvalueerde posities
		Board board = robot.getBoard();

		while ( !open.isEmpty()){
			Node currentNode = getMinimalFNode(open);
			if (pos.equals(currentNode.getPosition())){
				open.remove(currentNode.getPosition());
				closed.put(currentNode.getPosition(), currentNode);
				path = closed;
				break;
			}

			open.remove(currentNode.getPosition());
			closed.put(currentNode.getPosition(), currentNode);

			ArrayList<Position> neighbours = currentNode.getPosition().getNeighbours(robot.getBoard());
			for (Position neighbour : neighbours){
				double gCostNeighbour = getGCost(currentNode, neighbour, robot).getEnergy();
				if (closed.containsKey(neighbour)){
					if(closed.get(neighbour).getGCost().getEnergy() > gCostNeighbour){
						closed.get(neighbour).setGCost(new Energy(gCostNeighbour));
						closed.get(neighbour).setParent(currentNode);
					}
					continue;
				}
				else if ((open.containsKey(neighbour)) && (open.get(neighbour).getGCost().getEnergy() > gCostNeighbour)){
					open.get(neighbour).setGCost(new Energy(gCostNeighbour));
					open.get(neighbour).setParent(currentNode);
				}
				else if (board.isPlacableOnPosition(neighbour,robot)){
					open.put(neighbour, new Node(neighbour,board, new Energy(gCostNeighbour),
							getHCost(neighbour,getNodeOrientation(currentNode,neighbour) , pos, robot),
							getNodeOrientation(currentNode,neighbour),currentNode));
				} else 
					open.remove(neighbour);
			} 
		}
		path = closed;
	}
	
	private Energy getHCost(Position position, Orientation orientation, Position pos, Robot robot) {
		Energy manHattanCost = new Energy(Robot.moveCost(robot).getEnergy() * (int) calculateManhattan(position, pos));
		Energy turnCost = new Energy(Robot.TURN_COST.getEnergy()*getTurns(new Node(position,orientation,robot.getBoard()),pos));
		return Energy.energySum(manHattanCost, turnCost);
	}
	
	private Energy getGCost(Node currentNode, Position pos, Robot robot) {
		return Energy.energySum(Energy.energySum(currentNode.getGCost(), Robot.moveCost(robot)),
				new Energy(Robot.TURN_COST.getEnergy()*getTurns(currentNode, pos)));
	}
	
	private int getTurns(Node node, Position pos){
		int result = 0;
		if(node.getPosition().getX() == pos.getX() && node.getPosition().getY() == pos.getY())
			return result;
		/*
		 * In dit gedeelte kijken we de verhouding van de huidige robot met zijn bestemming na. Hier worden alle gevallen overlopen.
		 * Om dit visueel voor te stellen staan er letters die de posities voorstellen. De hoekpunten zijn in wijzerszin A, B, C en D.
		 * Vervolgens worden de middens van elke rand voorgesteld met E, F, G en H.
		 */
		if(pos.getX() == node.getPosition().getX() && pos.getY() < node.getPosition().getY()){
			// E
			switch(node.getOrientation()){
			case RIGHT:
			case LEFT:
				result = 1;
				break;
			case DOWN:
				result = 2;
				break;
			}
		}else if(pos.getX() == node.getPosition().getX() && pos.getY() > node.getPosition().getY()){
			// G
			switch(node.getOrientation()){
			case LEFT:
			case RIGHT:
				result = 1;
				break;
			case UP:
				result = 2;
				break;
			}
		}else if(pos.getX() > node.getPosition().getX() && pos.getY() == node.getPosition().getY()){
			// F
			switch(node.getOrientation()){
			case DOWN:
			case UP:
				result = 1;
				break;
			case LEFT:
				result = 2;
				break;
			}
		}else if(pos.getX() > node.getPosition().getX() && pos.getY() < node.getPosition().getY()){
			// B
			switch(node.getOrientation()){
			case RIGHT:
			case UP:
				result = 1;
				break;
			case DOWN:
			case LEFT:
				result = 2;
				break;
			}
		}else if(pos.getX() > node.getPosition().getX() && pos.getY() > node.getPosition().getY()){
			// C
			switch(node.getOrientation()){
			case DOWN:
			case RIGHT:
				result = 1;
				break;
			case LEFT:
			case UP:
				result = 2;	
				break;
			}
		}else if(pos.getX() < node.getPosition().getX() && pos.getY() == node.getPosition().getY()){
			// H
			switch(node.getOrientation()){
			case UP:
			case DOWN:
				result = 1;
				break;
			case RIGHT:
				result = 2;
				break;
			}
		}else if(pos.getX() < node.getPosition().getX() && pos.getY() < node.getPosition().getY()){
			// A
			switch(node.getOrientation()){
			case UP:
			case LEFT:
				result = 1;
				break;
			case RIGHT:
			case DOWN:
				result = 2;
				break;
			}
		}else if(pos.getX() < node.getPosition().getX() && pos.getY() > node.getPosition().getY()){
			// D
			switch(node.getOrientation()){
			case LEFT:
			case DOWN:
				result = 1;
				break;
			case UP:
			case RIGHT:
				result = 2;
				break;
			}
		}
		return result;
	}

	private Orientation getNodeOrientation(Node currentNode, Position pos) {
		Position previousPosition = currentNode.getPosition();
		if (previousPosition.getX() == pos.getX()){
			if (previousPosition.getY() < pos.getY())
				return Orientation.DOWN;
			return Orientation.UP;
		}
		if (previousPosition.getX() > pos.getX())
			return Orientation.LEFT;
		return Orientation.RIGHT;
	}

	private static ArrayList<Position> removeOccupied(ArrayList<Position> neighbours, Board board, Robot robot) {
		ArrayList<Position> result = new ArrayList<>();
		for (Position pos : neighbours){
			if (board.isPlacableOnPosition(pos, robot)){
				result.add(pos);
			}
		}
		return result;
	}
	
	private Node getMinimalFNode(HashMap<Position, Node> map){
		Collection<Node> c = map.values();
		Iterator<Node> itr = c.iterator();
		Node minimalNode = itr.next();
		for (Node node : c){
			if (node.getFCost().getEnergy() < minimalNode.getFCost().getEnergy())
				minimalNode = node;
		}
		return minimalNode;
	}
	
	public static long calculateManhattan(Position pos1, Position pos2){
		return (Math.abs(pos1.getX() - pos2.getX()) + Math.abs(pos1.getY() - pos2.getY()));
	}
	
	public static HashMap<Position, Node> getReachables(Robot robot){
		Energy upperbound = robot.getEnergy();
		ArrayList<Position> explorable = new ArrayList<>();
		explorable.add(robot.getPosition());
		HashMap<Position, Node> reachables = new HashMap<Position, Node>();

		while(!explorable.isEmpty()){
			Position currentPos = explorable.get(0);
			AStarPath aSPath = new AStarPath(robot,currentPos);
			HashMap<Position, Node> pad = aSPath.path;
			Node currentNode = pad.get(currentPos);
			explorable.remove(0);
			if(currentNode.getGCost().getEnergy() <= upperbound.getEnergy()){
				reachables.put(currentPos, currentNode);
				ArrayList<Position> preNeighbours = currentPos.getNeighbours(robot.getBoard());
				ArrayList<Position> neighbours = removeOccupied(preNeighbours,robot.getBoard(), robot);
				for(Position neighbour: neighbours){
					if((!reachables.containsKey(neighbour)) && (!explorable.contains(neighbour)))
						explorable.add(neighbour);
				}
			}			
		}
		return reachables;
	}
	
	/**
	 * Deze methode geeft de volgende positie weer met de huidige oriëntatie.
	 * 
	 * @param	pos
	 * 			Positie van waaruit vertrokken moet worden.
	 * 
	 * @param	or
	 * 			De huidige oriëntatie.
	 * 
	 * @return	De volgende positie met de huidige oriëntatie
	 * 			|if(or.equals(Orientation.UP))
	 *			|	new Position(pos.getX(), pos.getY() - 1)
	 *			|if(or.equals(Orientation.RIGHT))
	 *			|	new Position(pos.getX() + 1, pos.getY())
	 *			|if(or.equals(Orientation.DOWN))
	 *			|	new Position(pos.getX(), pos.getY() + 1);
	 *			|if(or.equals(Orientation.LEFT))
	 *			|	new Position(pos.getX() - 1, pos.getY())
	 *			|null
	 *
	 */
	public static Position getNextPosition(Position pos, Orientation or){
		Position result = null;
		switch(or){
		case UP:
			try {
				result = new Position(pos.getX(), pos.getY() - 1);
			} catch (IllegalPositionException e) {
				// NOP
			}
			break;
		case RIGHT:
			try {
				result = new Position(pos.getX() + 1, pos.getY());
			} catch (IllegalPositionException e) {
				// NOP
			}
			break;
		case DOWN:
			try {
				result = new Position(pos.getX(), pos.getY() + 1);
			} catch (IllegalPositionException e) {
				// NOP
			}
			break;
		case LEFT:
			try {
				result = new Position(pos.getX() - 1, pos.getY());
			} catch (IllegalPositionException e) {
				// NOP
			}
			break;
		}
		return result;
	}
	
}