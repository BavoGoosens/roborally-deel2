package roborally.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Immutable;

import roborally.basics.Energy;
import roborally.basics.Orientation;
import roborally.basics.Position;
import roborally.utils.Calculator;

/**
 * Een klasse om borden voor te stellen.
 * 
 * @author 	Bavo Goosens (1e bachelor informatica, r0297884), Samuel Debruyn (1e bachelor informatica, r0305472)
 * 
 * @Invar 	Een board moet ten alle tijden een geldige hoogte en breedte hebben/
 * 			| isValidHeight(getHeight())
 *  		| isValidWidth(getWidth())
 *  
 * @version 1.1
 */
public class Board{
	//TODO: documentatie en annotations
	//TODO:	kan getermineerd worden moet de map dan op null gezet worden (bavo)

	/**
	 * De breedte van het bord.
	 */
	private final long width;
	/**
	 * De hoogte van het bord.
	 */
	private final long height;
	/**
	 * Indien het object vernietigd is wordt dit true.
	 */
	private boolean isTerminated = false;
	/**
	 * Deze HashMap houdt een lijst van HashSets bij per Position met in elke HashSet alle objecten op die plaats.
	 */
	private final HashMap <Position, HashSet<Entity>> map;

	/**
	 * De maximale breedte die een bord kan hebben.
	 */
	private static final long UPPER_BOUND_WIDTH = Long.MAX_VALUE;
	/**
	 * De minimale breedte die een bord kan hebben.
	 */
	private static final long LOWER_BOUND_WIDTH = 0;
	/**
	 * De maximale hoogte die een bord kan hebben.
	 */
	private static final long UPPER_BOUND_HEIGHT = Long.MAX_VALUE;
	/**
	 * De minimale hoogte die een bord kan hebben.
	 */
	private static final long LOWER_BOUND_HEIGHT = 0;

	/**
	 * Deze constructor maakt een nieuw bord aan.
	 * 
	 * @param	height
	 * 			De hoogte van het nieuwe bord.
	 * 
	 * @param 	width
	 * 			De breedte van het nieuwe bord.
	 * 
	 * @post	Het bord heeft een map gekregen.
	 * 			|new.getMap() != null
	 * 
	 * @post	Het bord heeft een hoogte gekregen.
	 * 			|new.getHeight() == height
	 * 
	 * @post	Het bord heeft een breedte gekregen.
	 * 			|new.getWidth() == width
	 */
	public Board (long height, long width){
		this.height = height;
		this.width = width;
		this.map = new HashMap<Position, HashSet<Entity>>();
	}

	/**
	 * Deze methode geeft alle objecten terug op een positie.
	 * 
	 * @param	pos
	 * 			De positie waar gekeken moet worden.
	 * 
	 * @return	De HashSet met alle objecten op deze positie.
	 * 			|this.getMap().get(pos)
	 */
	public HashSet<Entity> getEntityOnPosition(Position pos){
		return this.getMap().get(pos);
	}

	/**
	 * Deze methode verwijdert een object uit het bord.
	 * 
	 * @param 	ent
	 * 			De entity die verwijderd moet worden.
	 * 
	 * @throws	IllegalStateException
	 * 			Het object bevindt zich niet op een juiste positie in dit bord.
	 * 			|ent.getBoard().isValidBoardPosition(ent.getPosition())
	 */
	public void removeEntity(Entity ent) throws IllegalStateException{
		Position pos = ent.getPosition();
		if(!ent.getBoard().isValidBoardPosition(pos)){
			throw new IllegalStateException("Dit object bevindt zich niet op een geldige positie.");
		}
		this.getMap().get(pos).remove(ent);
		this.cleanBoardPosition(pos);
	}

	/**
	 * Deze methode kijkt na of het bord leeg is op deze positie, indien dat zo is wordt een mogelijke opgeslagen lege HashSet verwijderd.
	 * 
	 * @param	pos
	 * 			De positie in het bord die moet nagekeken worden.
	 * 
	 * @post	De positie is leeg indien er geen objecten meer stonden.
	 * 			|if(this.getMap().containsKey(pos) && this.getMap().get(pos).isEmpty())
	 * 			|	new.getMap().containsKey(pos) == false
	 */
	public void cleanBoardPosition(Position pos){
		if(this.getMap().containsKey(pos)){
			if(this.getMap().get(pos).isEmpty()){
				this.getMap().remove(pos);
			}
		}
	}
	
	/**
	 * Deze methode kijkt na of een object op een plaats geplaatst kan worden.
	 * 
	 * @param	pos
	 * 			De positie die moet nagekeken worden.
	 * 
	 * @return	Boolean die true is als de positie niet door een muur ingenomen is.
	 * 			|if(!this.getMap().containsKey(pos))
	 * 			|	true
	 * 			|if(this.getMap().get(pos).isEmpty())
	 * 			
	 */
	public boolean isPlacableOnPosition(Position pos){
		if (!this.getMap().containsKey(pos))
			return true;
		if (this.getMap().get(pos).isEmpty())
			return true;
		if (this.getEntityOnPosition(pos).size() == 1){
			Entity obj = this.getEntityOnPosition(pos).iterator().next();
			if (obj instanceof Wall)
				return false;
		}
		return true;
	}
	
	/**
	 * Deze methode plaatst een object op het bord.
	 * 
	 * @param	key
	 * 			De positie waar het object geplaatst moet worden.
	 * 			
	 * @param 	entity
	 * 
	 * 			
	 */
	public void putEntity(Position key, Entity entity){
		if (entity instanceof Battery || entity instanceof Robot){
			if (isPlacableOnPosition(key)){
				HashSet<Entity> set = this.map.get(key);
				if (set == null){
					HashSet<Entity> input = new HashSet<Entity>();
					input.add(entity);
					map.put(key, input);
				}else{
					set.add(entity);
				}
			}
		}else if(entity instanceof Wall){
			if (isPlacableOnPosition(key)){
				HashSet<Entity> set = map.get(key);
				HashSet<Entity> input = new HashSet<Entity>();
				input.add(entity);
				map.put(key, input);
			}

		}
	}

	/**
	 * Deze methode geeft alle robots terug die op dit board staan.
	 * 
	 * @return	Set<Robot>
	 * 			voor iedere robot die een element is van this.map geldt dat deze op het einde van deze methode 
	 * 			in de set zal zitten die gereturned zal worden. tenzij de map geen robots bevat dan geeft deze null weer
	 * 			
	 */
	public Set<Robot> getRobots() {
		Collection<HashSet<Entity>> c = map.values();
		HashSet<Robot> rob = new HashSet<Robot>();
		for (Set<Entity> values : c ){
			Iterator<Entity> i = values.iterator();
			while (i.hasNext()){
				Entity obj = i.next();
				if (obj instanceof Robot){
					rob.add((Robot) obj);
				}
			}
		}
		return rob;
	}

	/**
	 * Deze methode geeft alle Batteries terug die op dit board staan.
	 * 
	 * @return	Set<Battery>
	 * 			voor iedere Battery die een element is van this.map geldt dat deze op het einde van deze methode 
	 * 			in de set zal zitten die gereturned zal worden. tenzij de map geen Batteries bevat dan geeft deze null weer.
	 * 			
	 */
	public Set<Battery> getBatteries() {
		Collection<HashSet<Entity>> c = map.values();
		HashSet<Battery> bat = new HashSet<Battery>();
		for (Set<Entity> values : c ){
			Iterator<Entity> i = values.iterator();
			while (i.hasNext()){
				Entity obj = i.next();
				if (obj instanceof Battery){
					bat.add((Battery) obj);
				}
			}

		}
		return bat;
	}

	/**
	 * Deze methode geeft alle Walls terug die op dit board staan.
	 * 
	 * @return	Set<Battery>
	 * 			voor iedere Wall die een element is van this.map geldt dat deze op het einde van deze methode 
	 * 			in de set zal zitten die gereturned zal worden. tenzij de map geen Walls bevat dan geeft deze null weer.
	 * 			
	 */
	public Set<Wall> getWalls() {
		Collection<HashSet<Entity>> c = map.values();
		HashSet<Wall> wall = new HashSet<Wall>();
		for (Set<Entity> values : c ){
			Iterator<Entity> i = values.iterator();
			while (i.hasNext()){
				Entity obj = i.next();
				if (obj instanceof Wall){
					wall.add((Wall) obj);
				}
			}

		}
		return wall;
	}

	/**
	 * Deze inspector geeft een boolean die true is wanneer de hoogte toegestaan is en in het andere geval false.
	 * 
	 * @param 	height
	 * 	
	 * @return	boolean 
	 * 			| result == true if ((height > Board.LOWER_BOUND_HEIGHT) && (height < Board.UPPER_BOUND_HEIGHT))
	 */
	@Basic
	public static boolean isValidHeight(long height){
		return (height > LOWER_BOUND_HEIGHT) && (height <= UPPER_BOUND_HEIGHT);
	}

	/**
	 * Inspector om na te gaan of een gegeven breedte width een toegstane waarde is.
	 * 
	 * @param 	width
	 * 		
	 * @return	boolean
	 * 			| result == true if ((width > Board.LOWER_BOUND_WIDTH) && (width < Board.UPPER_BOUND_WIDTH))
	 */
	@Basic
	public static boolean isValidWidth(long width){
		return (width > LOWER_BOUND_WIDTH) && (width <= UPPER_BOUND_WIDTH);
	}

	/**
	 * Inspector die de breedte van dit board teruggeeft.
	 * 
	 * @return	long
	 */
	@Basic
	@Immutable
	public long getWidth() {
		return this.width;
	}

	/**
	 * Inspector die de hoogte van dit board teruggeeft.
	 * 
	 * @return	long
	 */
	@Basic
	@Immutable
	public long getHeight() {
		return this.height;
	}

	/**
	 * 
	 * @param position
	 * @return
	 */
	@Basic
	public boolean isValidBoardPosition(Position position){
		if (position.getX() > this.getWidth() || position.getX() < LOWER_BOUND_WIDTH || position.getY() > this.getHeight() || position.getY() < LOWER_BOUND_HEIGHT)
			return false;
		return true;
	}


	public static Entity getFirstHit(Robot robot) {
		// bepaalt welke entity door een laser gaat geraakt worden 
		Position pos = Calculator.getNextPosition(robot.getPosition(), robot.getOrientation());
		if (robot.getBoard().getMap().containsKey(pos)){
			Set hits = robot.getBoard().getMap().get(pos);
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


	public HashMap<Position, HashSet<Entity>> getMap() {
		return this.map;
	}

	public void merge(Board board2) {
		// TODO methode om 2 borden samen te voegen
		
	}
	
	public boolean isTerminated(){
		return this.isTerminated;
	}
	public void terminate(){
		this.isTerminated = true;
		//this.map = null;
	}
}
