package roborally.model;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;

import roborally.basics.Position;

public class Board{
	
	private final long width;
	private final long height;
	private HashMap <Position,HashSet> map;
	
	private static final long UPPER_BOUND_WIDTH = Long.MAX_VALUE;
	private static final long LOWER_BOUND_WIDTH = 0;
	private static final long UPPER_BOUND_HEIGTH = Long.MAX_VALUE;
	private static final long LOWER_BOUND_HEIGTH = 0;
	
	public Board (long height, long width){
		this.height = height;
		this.width = width;
	}
	
	public boolean isValidHeight(long height){
		return (height > Board.LOWER_BOUND_HEIGTH) && (height <= Board.UPPER_BOUND_HEIGTH);
	}
	
	public boolean isValidWidth(long width){
		return (width > Board.LOWER_BOUND_WIDTH) && (width <= Board.UPPER_BOUND_WIDTH);
	}

	public void putBattery(long x, long y, Battery battery) {
		Position key = new Position(x,y);
		if (!map.containsValue(battery)){
			if (isPlacableOnPosition(key,battery)){
				
			}
		}
	}
	
	public HashSet<Object> getObjectsOnPosition(Position pos){
		return this.map.get(pos);
	}
	public boolean isPlacableOnPosition(Position pos , Object obj){
		if (this.map.get(pos).isEmpty())
			return true;
		if (obj instanceof Battery){
			
		}
		if (obj instanceof Robot){
			if(this.map.get(pos).
		}
		return false;
	}

	public void putRobot(long x, long y, Robot robot) {
		// TODO Auto-generated method stub
		
	}

	public void putWall(long x, long y, Wall wall) {
		// TODO Auto-generated method stub
		
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
	
}
