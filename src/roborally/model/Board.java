package roborally.model;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;

import roborally.basics.Position;

public class Board{
	
	private final long width;
	private final long height;
	private HashMap <Position,HashSet> map = new HashMap<Position,HashSet>();
	
	private static final long UPPER_BOUND_WIDTH = Long.MAX_VALUE;
	private static final long LOWER_BOUND_WIDTH = 0;
	private static final long UPPER_BOUND_HEIGTH = Long.MAX_VALUE;
	private static final long LOWER_BOUND_HEIGTH = 0;
	
	public Board (long height, long width){
		this.height = height;
		this.width = width;
	}

	/**
	 * Deze methode plaats een battery op een gegeven plaats in het bord.
	 * 
	 * @param 	x
	 * 
	 * @param 	y
	 * 
	 * @param 	battery
	 * 
	 */
	public void putBattery(long x, long y, Battery battery) {
		if (battery.getBoard() != null){
			Position key = new Position(x,y);
			if (!map.containsValue(battery)){
				if (isPlacableOnPosition(key,battery)){
					if (map.get(key) == null){
						HashSet<Object> input = new HashSet<Object>();
						input.add(battery);
						map.put(key,input);
					}
					map.get(key).add(battery);
				}
			}
		}
	}
	
	public HashSet<Object> getObjectsOnPosition(Position pos){
		return this.map.get(pos);
	}
	
	public boolean isPlacableOnPosition(Position pos , Object obj){
		if (this.map.get(pos) == null)
			return true;
		if (this.map.get(pos).isEmpty())
			return true;
		if (this.map.get(pos).size() == 1){
			Object derp = this.map.get(pos).iterator().next();
			if (derp instanceof Wall)
				return false;
		}
		return false;
	}

	public void putRobot(long x, long y, Robot robot) {
		Position key = new Position(x,y);
		if (!map.containsValue(robot)){
			if (isPlacableOnPosition(key,robot)){
				if (map.get(key) == null){
					HashSet<Object> input = new HashSet<Object>();
					input.add(robot);
					map.put(key,input);
				}
				map.get(key).add(robot);
			}
		}
	}

	public void putWall(long x, long y, Wall wall) {
		Position key = new Position(x,y);
		if (!map.containsValue(wall)){
			if (isPlacableOnPosition(key,wall)){
				HashSet<Object> input = new HashSet<Object>();
				input.add(wall);
				map.put(key,input);
			}
		}
	}

	public HashSet<Robot> getRobots() {
		// TODO testen en checken
		Collection<HashSet> c = map.values();
		HashSet<Robot> result = new HashSet<Robot>();
		for (HashSet<Object> values : c){
			while (values.iterator().hasNext()){
				Object obj = values.iterator().next();
				if (obj instanceof Robot)
					result.add((Robot) obj);
			}
		}
		return result;
	}

	public HashSet<Battery> getBatteries() {
		// TODO testen en checken
		Collection<HashSet> c = map.values();
		HashSet<Battery> result = new HashSet<Battery>();
		for (HashSet<Object> values : c){
			while (values.iterator().hasNext()){
				Object obj = values.iterator().next();
				if (obj instanceof Battery)
					result.add((Battery) obj);
			}
		}
		return result;
	}

	public HashSet<Wall> getWalls() {
		// TODO testen en checken
		Collection<HashSet> c = map.values();
		HashSet<Wall> result = new HashSet<Wall>();
		for (HashSet<Object> values : c){
			while (values.iterator().hasNext()){
				Object obj = values.iterator().next();
				if (obj instanceof Wall)
					result.add((Wall) obj);
			}
		}
		return result;
	}
	

	public boolean isValidHeight(long height){
		return (height > Board.LOWER_BOUND_HEIGTH) && (height <= Board.UPPER_BOUND_HEIGTH);
	}
	
	public boolean isValidWidth(long width){
		return (width > Board.LOWER_BOUND_WIDTH) && (width <= Board.UPPER_BOUND_WIDTH);
	}

	public long getWidth() {
		return width;
	}

	public long getHeight() {
		return height;
	}
}
