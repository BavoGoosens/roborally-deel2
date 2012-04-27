package roborally.model;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;

import roborally.basics.Position;

public class Board{
	
	//TODO: documentatie en annotations
	
	private final long width;
	private final long height;
	private HashMap <Position, HashSet<Entity>> map = new HashMap<Position,HashSet<Entity>>();
	
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
			if (!this.map.containsValue(battery)){
				if (isPlacableOnPosition(key,battery)){
					if (this.map.get(key) == null){
						HashSet<Entity> input = new HashSet<Entity>();
						input.add(battery);
						this.map.put(key,input);
					}
					this.map.get(key).add(battery);
				}
			}
		}
	}
	
	public HashSet<Entity> getEntityOnPosition(Position pos){
		return this.map.get(pos);
	}
	
	public void removeEntity(Entity ent){
		//TODO
		
	}
	
	public boolean isPlacableOnPosition(Position pos , Object obj){
		//TODO: parameter obj wordt niet gebruikt
		if (this.map.get(pos) == null)
			return true;
		if (this.map.get(pos).isEmpty())
			return true;
		if (this.map.get(pos).size() == 1){
			//TODO: Naam = What The Fridge?
			Object derp = this.map.get(pos).iterator().next();
			if (derp instanceof Wall)
				return false;
		}
		return false;
	}

	public void putRobot(long x, long y, Robot robot) {
		Position key = new Position(x,y);
		if (!this.map.containsValue(robot)){
			if (isPlacableOnPosition(key,robot)){
				if (this.map.get(key) == null){
					HashSet<Entity> input = new HashSet<Entity>();
					input.add(robot);
					this.map.put(key,input);
				}
				this.map.get(key).add(robot);
			}
		}
	}

	public void putWall(long x, long y, Wall wall) {
		Position key = new Position(x,y);
		if (!this.map.containsValue(wall)){
			if (isPlacableOnPosition(key,wall)){
				HashSet<Entity> input = new HashSet<Entity>();
				input.add(wall);
				this.map.put(key,input);
			}
		}
	}

	public HashSet<Robot> getRobots() {
		// TODO testen en checken
		Collection<HashSet<Entity>> c = this.map.values();
		HashSet<Robot> result = new HashSet<Robot>();
		for (HashSet<Entity> values : c){
			while (values.iterator().hasNext()){
				Entity obj = values.iterator().next();
				if (obj instanceof Robot)
					result.add((Robot) obj);
			}
		}
		return result;
	}

	public HashSet<Battery> getBatteries() {
		// TODO testen en checken
		Collection<HashSet<Entity>> c = this.map.values();
		HashSet<Battery> result = new HashSet<Battery>();
		for (HashSet<Entity> values : c){
			while (values.iterator().hasNext()){
				Entity obj = values.iterator().next();
				if (obj instanceof Battery)
					result.add((Battery) obj);
			}
		}
		return result;
	}

	public HashSet<Wall> getWalls() {
		// TODO testen en checken
		Collection<HashSet<Entity>> c = this.map.values();
		HashSet<Wall> result = new HashSet<Wall>();
		for (HashSet<Entity> values : c){
			while (values.iterator().hasNext()){
				Entity obj = values.iterator().next();
				if (obj instanceof Wall)
					result.add((Wall) obj);
			}
		}
		return result;
	}
	

	public static boolean isValidHeight(long height){
		return (height > LOWER_BOUND_HEIGTH) && (height <= UPPER_BOUND_HEIGTH);
	}
	
	public static boolean isValidWidth(long width){
		return (width > LOWER_BOUND_WIDTH) && (width <= UPPER_BOUND_WIDTH);
	}

	public long getWidth() {
		return this.width;
	}

	public long getHeight() {
		return this.height;
	}
	
	public boolean isValidBoardPosition(Position position){
		if (position.getX() > UPPER_BOUND_WIDTH || position.getX() < LOWER_BOUND_WIDTH || position.getY() > this.getWidth() || position.getY() < this.getHeight())
			return false;
		return true;
	}
}
