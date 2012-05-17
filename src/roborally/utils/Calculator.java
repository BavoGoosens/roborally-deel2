package roborally.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

import roborally.basics.Energy;
import roborally.basics.Orientation;
import roborally.basics.Position;
import roborally.model.Board;
import roborally.model.Node;
import roborally.model.Robot;

public class Calculator {

	/**
	 * Deze Methode gaat de manhattandistance van punt A met pos pos1 tot punt B met pos pos2 berekenen.
	 * 
	 * @param 	pos1
	 * 			Het eerste co�rdinatenkoppel.
	 * 
	 * @param 	pos2
	 * 	 		Het tweede co�rdinatenkoppel.
	 * 
	 *@return 	long 
	 *			Een long die de manhattan teruggeeft.
	 *			|return result = (Math.abs(pos1.getX() - pos2.getX()) + Math.abs(pos1.getY() - pos2.getY()))
	 * 
	 */
	public static long calculateManhattan(Position pos1, Position pos2){
		return (Math.abs(pos1.getX() - pos2.getX()) + Math.abs(pos1.getY() - pos2.getY()));
	}

	/**
	 * Deze methode gaat van een string van de vorm long1,long2 een object maken met die longs als positions.
	 * 
	 * @param 	posString
	 * 			De string waarvan je het Positie object wil maken.
	 * 
	 * @return	Position 
	 * 			|result.getX() = Long.parseLong(posString.split(",")[0])
	 * 			|result.getY() = Long.parseLong(posString.split(",")[1])
	 *
	 * @throws IllegalArgumentException
	 */
	public static Position getPositionFromString(String posString) throws IllegalArgumentException{
		if(posString.indexOf(",") == -1)
			throw new IllegalArgumentException("De String is niet geformatteerd als een positie.");
		String[] split = posString.split(",");
		long x, y;
		try{
			x = Long.parseLong(split[0]);
		}catch(NumberFormatException e){
			throw new IllegalArgumentException(e.getMessage());
		}
		try{
			y = Long.parseLong(split[1]);
		}catch(NumberFormatException e){
			throw new IllegalArgumentException(e.getMessage());
		}
		Position result;
		try{
			result = new Position(x, y);
		}catch(IllegalArgumentException e){
			throw new IllegalArgumentException(e.getMessage());
		}
		return result;		
	}

	/**
	 * Deze methode gaat alle nodes teruggeven waar deze robot zou kunnen geraken.
	 * 
	 * @param 	robot
	 * 			De robot waarvan je de haalbare posities wilt weten.
	 * 
	 * @return	HashMap<String,Node> 
	 * 			
	 */
	public static HashMap<String,Node> getReachables(Robot robot){
		Energy upperbound = robot.getEnergy();
		ArrayList<String> explorable = new ArrayList<>();
		explorable.add(robot.getPosition().toString());
		HashMap<String,Node> reachables = new HashMap<>();

		while(!explorable.isEmpty()){
			Position currentPos = Calculator.getPositionFromString(explorable.get(0));
			HashMap<String,Node> pad = aStarOnTo(robot,currentPos);
			Node currentNode = pad.get(currentPos.toString());
			explorable.remove(0);
			if(currentNode.getGCost().getEnergy() <= upperbound.getEnergy()){
				reachables.put(currentPos.toString(),currentNode);
				ArrayList<Position> preNeighbours = currentPos.getNeighbours(robot.getBoard());
				ArrayList<Position> neighbours = removeWalls(preNeighbours,robot.getBoard());
				for(Position neighbour: neighbours){
					if((!reachables.containsKey(neighbour.toString())) && (!explorable.contains(neighbour.toString())))
						explorable.add(neighbour.toString());
				}
			}			
		}
		return reachables;
	}

	/**
	 * Deze methode gaat posities waar een wall op staat uit de ArrayList gaan verwijderen.
	 * 
	 * @param 	neighbours
	 * 
	 * @param 	board
	 * 
	 * @return	
	 * 			
	 */
	private static ArrayList<Position> removeWalls(ArrayList<Position> neighbours, Board board) {
		ArrayList<Position> result = new ArrayList<>();
		for (Position pos : neighbours){
			if (!board.containsWall(pos)){
				result.add(pos);
			}
		}
		return result;
	}
	
	/**
	 * Deze methode gaat op basis van het A* algorithme het optimale pad naar een gegeven positie teruggeven.
	 * 
	 * @param 	a
	 * 
	 * @param 	pos
	 * 
	 * @return
	 * 
	 * 	
	 */
	public static HashMap<String,Node> aStarOnTo(Robot a, Position pos){
		//deze gaat direct naar de positie die opgegeven wordt

		HashMap<String,Node> open = new HashMap<>(); 
		// de experimentele posities die nog ge�valueerd moeten/kunnen worden
		Node startNode = new Node(a.getPosition(), a.getBoard(), new Energy(0) , 
				getHCost(a.getPosition(), a.getOrientation(),pos, a),a.getOrientation(), null);

		open.put(a.getPosition().toString(), startNode);
		// de startPositie aan de open list toevoegen
		HashMap<String,Node> closed = new HashMap<>(); 
		// de lijst met al ge�valueerde posities
		Board board = a.getBoard();

		while ( !open.isEmpty()){
			Node currentNode = getMinimalFNode(open);
			if (pos.toString().equals(currentNode.getPosition().toString())){
				open.remove(currentNode.getPosition().toString());
				closed.put(currentNode.getPosition().toString(), currentNode);
				return closed;
			}

			open.remove(currentNode.getPosition().toString());
			closed.put(currentNode.getPosition().toString(), currentNode);

			ArrayList<Position> neighbours = currentNode.getPosition().getNeighbours(a.getBoard());
			for (Position neighbour : neighbours){
				double gCostNeighbour = getGCost(currentNode, neighbour, a ).getEnergy();
				if (closed.containsKey(neighbour.toString())){
					if(closed.get(neighbour.toString()).getGCost().getEnergy() > gCostNeighbour){
						closed.get(neighbour.toString()).setGCost(new Energy(gCostNeighbour));
						closed.get(neighbour.toString()).setParent(currentNode);
					}
					continue;
				}
				else if ((open.containsKey(neighbour.toString())) && (open.get(neighbour.toString()).getGCost().getEnergy() > gCostNeighbour)){
					open.get(neighbour.toString()).setGCost(new Energy(gCostNeighbour));
					open.get(neighbour.toString()).setParent(currentNode);
				}
				else{
					if (board.isPlacableOnPosition(neighbour,a)){
						open.put(neighbour.toString(), new Node(neighbour,board, new Energy(gCostNeighbour),
								getHCost(neighbour,getNodeOrientation(currentNode,neighbour) , pos, a),
								getNodeOrientation(currentNode,neighbour),currentNode));
					}
				}
			}

		}
		return closed;

	}

	/**
	 * Deze methode gaat de orientatie bepalen die een node op een positie volgend op de currentnode zou krijgen.
	 *   
	 * @param 	currentNode
	 * 
	 * @param 	pos
	 * 
	 * @return
	 * 
	 */
	private static Orientation getNodeOrientation(Node currentNode, Position pos) {
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

	/**
	 * Deze methode gaat de heuristieke kost naar een gegeven positie vanuit de meegegeven node berekenen.
	 * 
	 * @param 	position
	 * 			
	 * @param 	orientation
	 * 			
	 * @param 	pos
	 * 			
	 * @param 	robot
	 * 			
	 * @return	Energy
	 * 		
	 * 			
	 */
	private static Energy getHCost(Position position, Orientation orientation, Position pos, Robot robot) {
		//de manhattan kost om met de robot van de beginpositie tot de eindpos te geraken.
		Energy manHattanCost = new Energy(Robot.moveCost(robot).getEnergy() * (int) calculateManhattan(position, pos));
		Energy turnCost = new Energy(Robot.TURN_COST.getEnergy()*getTurns(new Node(position,orientation,robot.getBoard()),pos));
		return Energy.energySum(manHattanCost, turnCost);
	}

	/**
	 * 
	 * @param 	currentNode
	 * 
	 * @param 	pos
	 * 
	 * @param 	robot
	 * 
	 * @return	
	 * 
	 */
	private static Energy getGCost(Node currentNode, Position pos, Robot robot) {
		return Energy.energySum(Energy.energySum(currentNode.getGCost(), Robot.moveCost(robot)),
				new Energy(Robot.TURN_COST.getEnergy()*getTurns(currentNode, pos)));
	}
	/**
	 * methode voor het aantal turns terug te geven om van een node met orientatie m naar een nabijgelegen node te 
	 * bewegen (vlak naast)
	 *  
	 * @param 	node
	 * 
	 * @param 	pos 
	 * 	
	 * @return
	 */
	private static int getTurns(Node node, Position pos){
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

	/**
	 * 
	 * @param map
	 * @return
	 */
	public static Node getMinimalFNode(HashMap<String, Node> map){
		Collection<Node> c = map.values();
		Iterator<Node> itr = c.iterator();
		Node minimalNode = itr.next();
		for (Node node : c){
			if (node.getFCost().getEnergy() < minimalNode.getFCost().getEnergy())
				minimalNode = node;
		}
		return minimalNode;
	}

	/**
	 * Deze methode geeft de volgende positie weer met de huidige ori�ntatie.
	 * 
	 * @param	pos
	 * 			Positie van waaruit vertrokken moet worden.
	 * 
	 * @param	or
	 * 			De huidige ori�ntatie.
	 * 
	 * @return	De volgende positie met de huidige ori�ntatie
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
	 * @throws	IllegalStateException
	 * 			Er bestaat geen verdere positie meer met deze ori�ntatie.
	 */
	public static Position getNextPosition(Position pos, Orientation or) throws IllegalStateException{
		Position result = null;
		switch(or){
		case UP:
			try {
				result = new Position(pos.getX(), pos.getY() - 1);
			} catch (IllegalArgumentException e) {
				throw new IllegalStateException("Er bestaat geen verdere positie meer met deze ori�ntatie.");
			}
			break;
		case RIGHT:
			try {
				result = new Position(pos.getX() + 1, pos.getY());
			} catch (IllegalArgumentException e) {
				throw new IllegalStateException("Er bestaat geen verdere positie meer met deze ori�ntatie.");
			}
			break;
		case DOWN:
			try {
				result = new Position(pos.getX(), pos.getY() + 1);
			} catch (IllegalArgumentException e) {
				throw new IllegalStateException("Er bestaat geen verdere positie meer met deze ori�ntatie.");
			}
			break;
		case LEFT:
			try {
				result = new Position(pos.getX() - 1, pos.getY());
			} catch (IllegalArgumentException e) {
				throw new IllegalStateException("Er bestaat geen verdere positie meer met deze ori�ntatie.");
			}
			break;
		}
		return result;
	}

}
