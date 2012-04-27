package roborally.model;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import roborally.basics.Position;

public class Board{
	
	//TODO: documentatie en annotations
	
	private final long width;
	private final long height;
	private HashMap <Position, Set> map;
	
	private static final long UPPER_BOUND_WIDTH = Long.MAX_VALUE;
	private static final long LOWER_BOUND_WIDTH = 0;
	private static final long UPPER_BOUND_HEIGTH = Long.MAX_VALUE;
	private static final long LOWER_BOUND_HEIGTH = 0;
	
	public Board (long height, long width){
		this.height = height;
		this.width = width;
	}

	
	public Set getEntityOnPosition(Position pos){
		return this.map.get(pos);
	}
	
	public void removeEntity(Entity ent){
		//TODO
		
	}
	
	public boolean isPlacableOnPosition(Position pos){
		if (this.map.get(pos) == null)
			return true;
		if (this.map.get(pos).isEmpty())
			return true;
		if (this.map.get(pos).size() == 1){
			Object obj = this.map.get(pos).iterator().next();
			if (obj instanceof Wall)
				return false;
		}
		return false;
	}

	public void putEntity(long x ,long y, Entity entity){
		Position key = new Position(x,y);
		if (entity instanceof Battery || entity instanceof Robot){
			if (entity.getBoard() == null){
				if (isPlacableOnPosition(key)){
					if (map == null){
						map = new HashMap<Position,Set>();
						Set input = new HashSet<Entity>();
						input.add(entity);
						map.put(key, input);
					}else{
						Set set = map.get(key);
						if (set == null){
							Set input = new HashSet<Entity>();
							input.add(entity);
							map.put(key, input);
						}else{
							set.add(entity);
						}
					}
				}
			}
		}
		if(entity instanceof Wall){
			if (entity.getBoard() == null){
				if (isPlacableOnPosition(key)){
					if (map == null){
						map = new HashMap<Position,Set>();
						Set input = new HashSet<Entity>();
						input.add(entity);
						map.put(key, input);
					}else{
						Set set = map.get(key);
						Set input = new HashSet<Entity>();
						input.add(entity);
						map.put(key, input);
					}
				}
			}
		}
			
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
		putEntity(x,y,battery);
	}
	
	public void putRobot(long x, long y, Robot robot) {
		putEntity(x,y,robot);
	}

	public void putWall(long x, long y, Wall wall) {
		putEntity(x,y,wall);
	}

	public Set<Robot> getRobots() {
		Collection c = map.values();
		
	}

	public Set<Battery> getBatteries() {
		Collection<Set> c = map.values();
		HashSet<Battery> bat = new HashSet<Battery>();
		for (Set values : c ){
			while (values.iterator().hasNext()){
				Object obj = values.iterator().next();
				if (obj instanceof Battery){
					bat.add((Battery) obj);
				}
			}
			
		}
		return bat;
	}

	public Set<Wall> getWalls() {
		
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
