package roborally.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import roborally.basics.Energy;
import roborally.basics.Orientation;
import roborally.basics.Position;
import roborally.utils.Calculator;

public class Board{
	
	//TODO: documentatie en annotations & Helaas veel meer dan enkel da de puts en gets werken niet... Die rare structuur snap ik ni van hashmap
	
	private final long width;
	private final long height;
	private HashMap <Position, HashSet<Entity>> map = new HashMap<Position, HashSet<Entity>>();
	
	private static final long UPPER_BOUND_WIDTH = Long.MAX_VALUE;
	private static final long LOWER_BOUND_WIDTH = 0;
	private static final long UPPER_BOUND_HEIGTH = Long.MAX_VALUE;
	private static final long LOWER_BOUND_HEIGTH = 0;
	
	public Board (long height, long width){
		this.height = height;
		this.width = width;
	}

	
	public HashSet<Entity> getEntityOnPosition(Position pos){
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
			Entity obj = this.map.get(pos).iterator().next();
			if (obj instanceof Wall)
				return false;
		}
		return true;
	}

	public void putEntity(Position key, Entity entity){
		if (entity instanceof Battery || entity instanceof Robot){
			if (!entity.isOnBoard()){
				if (isPlacableOnPosition(key)){
					HashSet<Entity> set = map.get(key);
					if (set == null){
						HashSet<Entity> input = new HashSet<Entity>();
						input.add(entity);
						map.put(key, input);
					}else{
						set.add(entity);
					}
				}
			}else{
				//TODO
			}
		}
		if(entity instanceof Wall){
			if (!entity.isOnBoard()){
				if (isPlacableOnPosition(key)){
					HashSet<Entity> set = map.get(key);
					HashSet<Entity> input = new HashSet<Entity>();
					input.add(entity);
					map.put(key, input);
				}
			}else{
				//TODO
			}

		}

	}

	public Set<Robot> getRobots() {
		Collection<HashSet<Entity>> c = map.values();
		HashSet<Robot> rob = new HashSet<Robot>();
		for (Set<Entity> values : c ){
			while (values.iterator().hasNext()){
				Entity obj = values.iterator().next();
				if (obj instanceof Robot){
					rob.add((Robot) obj);
				}
			}
		}
		return rob;
	}

	public Set<Battery> getBatteries() {
		Collection<HashSet<Entity>> c = map.values();
		HashSet<Battery> bat = new HashSet<Battery>();
		for (Set<Entity> values : c ){
			while (values.iterator().hasNext()){
				Entity obj = values.iterator().next();
				if (obj instanceof Battery){
					bat.add((Battery) obj);
				}
			}
			
		}
		return bat;
	}

	public Set<Wall> getWalls() {
		Collection<HashSet<Entity>> c = map.values();
		HashSet<Wall> wall = new HashSet<Wall>();
		for (Set<Entity> values : c ){
			while (values.iterator().hasNext()){
				Entity obj = values.iterator().next();
				if (obj instanceof Wall){
					wall.add((Wall) obj);
				}
			}
			
		}
		return wall;
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


	public Entity getFirstHit(Robot robot) {
		// bepaalt welke entity door een laser gaat geraakt worden 
		Position pos = Calculator.getNextPosition(robot.getPosition(), robot.getOrientation());
		if (map.containsKey(pos)){
			Set hits = map.get(pos);
			Random rndm = new Random();
			Object hit = hits.toArray()[rndm.nextInt(hits.toArray().length)];
			return (Entity) hit;
		}else{
			//dummy Robot maken met de nieuwe positie en dez orientatie en dan dez methode oproepen tot wnn we result hebben
			Robot dummy = new Robot(robot.getOrientation(), new Energy(0));
			dummy.setPosition(pos);
			return getFirstHit(dummy);
		}
	}
}
