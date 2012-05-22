package roborally.model;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Immutable;

import roborally.property.Position;
import roborally.util.IllegalPositionException;

/**
 * Een klasse om een bord voor te stellen.
 * 
 * @author 	Bavo Goosens (1e bachelor informatica, r0297884), Samuel Debruyn (1e bachelor informatica, r0305472)
 * 
 * @invar 	Een bord moet ten alle tijden een geldige hoogte en breedte hebben.
 * 			|isValidHeight(getHeight())
 *  		|isValidWidth(getWidth())
 *  
 * @version 1.1
 */
public class Board{

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
	 * @pre 	...
	 * 			|isValidWidth(width)
	 * 
	 * @pre 	...
	 * 			|isValidHeight(height) 
	 * 
	 * @param	height
	 * 			De hoogte van het nieuwe bord.
	 * 
	 * @param 	width
	 * 			De breedte van het nieuwe bord.
	 * 
	 * @post	Het bord heeft een map gekregen.
	 * 			|new.map != null
	 * 
	 * @post	Het bord heeft een hoogte gekregen.
	 * 			|new.getHeight() == height
	 * 
	 * @post	Het bord heeft een breedte gekregen.
	 * 			|new.getWidth() == width
	 */
	public Board (long width , long height){
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
	 * 			|map.get(pos)
	 */
	public HashSet<Entity> getEntityOnPosition(Position pos){
		return map.get(pos);
	}

	/**
	 * Deze methode verwijdert een object uit het bord.
	 * 
	 * @param 	ent
	 * 			De entity die verwijderd moet worden.
	 * 
	 * @throws	IllegalPositionException
	 * 			Het object bevindt zich niet op een juiste positie in dit bord.
	 * 			|ent.getBoard().isValidPosition(ent.getPosition())
	 * 
	 * @post	De gegeven entity bevindt zich niet langer op een bord.
	 * 			|new.getEntityOnPosition(ent.getPosition()) == null || new.map.containsKey(pos) == false
	 */
	public void removeEntity(Entity ent) throws IllegalPositionException{
		Position pos = ent.getPosition();
		if(!ent.getBoard().isValidPosition(pos)){
			throw new IllegalPositionException(pos);
		}
		map.get(pos).remove(ent);
		cleanBoardPosition(pos);
	}

	/**
	 * Deze methode kijkt na of het bord leeg is op deze positie, indien dat zo is wordt een mogelijke opgeslagen lege HashSet verwijderd.
	 * 
	 * @param	pos
	 * 			De positie in het bord die moet nagekeken worden.
	 * 
	 * @post	De positie is leeg indien er geen objecten meer stonden.
	 * 			|if(map.containsKey(pos) && !map.get(pos).isEmpty() && map.get(pos) != null)
	 * 			|	new.map.containsKey(pos) == false
	 */
	public void cleanBoardPosition(Position pos){
		if(map.containsKey(pos)){
			if(map.get(pos) == null){
				map.remove(pos);
			}else if(map.get(pos).isEmpty()){
				map.remove(pos);
			}
		}
	}

	/**
	 * Deze methode kijkt na of een object op een plaats geplaatst kan worden.
	 * 
	 * @param	pos
	 * 			De positie die moet nagekeken worden.
	 * 
	 * @return	Boolean die true is als de positie niet door een muur of een robot ingenomen is.
	 * 			|if(!isValidPosition(pos))
	 * 			|	false
	 * 			|if(!map.containsKey(pos))
	 * 			|	true
	 * 			|if(map.get(pos).isEmpty())
	 * 			|	true
	 * 			|if (getEntityOnPosition(pos).size() == 1){
	 * 			|	if(getEntityOnPosition(pos).iterator().next() instanceof Wall || 
	 * 			|											getEntityOnPosition(pos).iterator().next() instanceof Robot)
	 * 			|		false
	 * 			|true
	 * 			
	 */
	public boolean isPlacableOnPosition(Position pos, Entity entity){
		if (!isValidPosition(pos))
			return false;
		if (entity instanceof Wall){
			try{
				HashSet<Entity> place = getEntityOnPosition(pos);
				if (place.isEmpty())
					return true;
				return false;
			}catch (NullPointerException esc){
				return true;
			}
		}
		if (entity instanceof Robot){
			try {
				HashSet<Entity> place = getEntityOnPosition(pos);
				if(place.size() == 1){
					Entity ent = place.iterator().next();
					if (ent instanceof Robot || ent instanceof Wall)
						return false;
					return true;
				}
				return true;
			}catch (NullPointerException esc){
				return true;
			}
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
	 * 			Het object dat op het bord geplaatst moet worden.
	 * 
	 * @throws 	IllegalArgumentException
	 * 			De plaats in het bord is niet beschikbaar.
	 * 			|!isPlacableOnPosition(key)
	 * 
	 * @post	Als de positie op het bord beschikbaar was staat het object op deze positie.
	 * 			|if(isPlacableOnPosition(key))
	 * 			|	new.get(key) == entity
	 * 			
	 */
	public void putEntity(Position key, Entity entity) throws IllegalArgumentException{
		if (isPlacableOnPosition(key, entity)){
			if (entity instanceof Battery || entity instanceof Robot){
				HashSet<Entity> set = map.get(key);
				if (set == null){
					HashSet<Entity> input = new HashSet<>();
					input.add(entity);
					map.put(key, input);
				}else{
					set.add(entity);
					}
			}else if(entity instanceof Wall){
				HashSet<Entity> input = new HashSet<>();
				input.add(entity);
				map.put(key, input);
			}
		}else{
			throw new IllegalArgumentException("Op deze plaats in het bord kan geen object meer geplaatst worden.");
		}
	}

	/**
	 * Deze methode geeft alle robots terug die op dit bord staan.
	 * 
	 * @return	Set<Robot>
	 * 			Voor iedere robot die een element is van map geldt dat deze op het einde van deze methode in de set zal zitten die teruggegeven wordt. Deze set is null als er geen robots op het bord staan.
	 * 			
	 */
	public Set<Robot> getRobots() {
		Collection<HashSet<Entity>> c = map.values();
		HashSet<Robot> rob = new HashSet<>();
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
	 * Deze methode geeft alle batterijen terug die op dit bord staan.
	 * 
	 * @return	Set<Battery>
	 * 			Voor iedere batterij die een element is van map geldt dat deze op het einde van deze methode in de set zal zitten die teruggegeven wordt. Deze set is null als er geen batterijen op het bord staan.
	 * 			
	 */
	public Set<Battery> getBatteries() {
		Collection<HashSet<Entity>> c = map.values();
		HashSet<Battery> bat = new HashSet<>();
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
	 * Deze methode geeft alle muren terug die op dit bord staan.
	 * 
	 * @return	Set<Wall>
	 * 			Voor iedere muur die een element is van map geldt dat deze op het einde van deze methode in de set zal zitten die teruggegeven wordt. Deze set is null als er geen muren op het bord staan.
	 * 			
	 */
	public Set<Wall> getWalls() {
		Collection<HashSet<Entity>> c = map.values();
		HashSet<Wall> wall = new HashSet<>();
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
	 * 			De hoogte die nagekeken moet worden.
	 * 	
	 * @return	Boolean die true is wanneer de hoogte toegestaan is en in het andere geval false.
	 * 			|if((height > Board.LOWER_BOUND_HEIGHT) && (height < Board.UPPER_BOUND_HEIGHT))
	 * 			|	true
	 */
	@Basic
	public static boolean isValidHeight(long height){
		return (height > LOWER_BOUND_HEIGHT) && (height <= UPPER_BOUND_HEIGHT);
	}

	/**
	 * Inspector om na te gaan of een gegeven breedte width een toegestane waarde is.
	 * 
	 * @param 	width
	 * 			De breedte die nagekeken moet worden.
	 * 		
	 * @return	Boolean die true is wanneer de breedte toegestaan is en in het andere geval false.
	 * 			|if((width > Board.LOWER_BOUND_WIDTH) && (width < Board.UPPER_BOUND_WIDTH))
	 * 			|	true
	 */
	@Basic
	public static boolean isValidWidth(long width){
		return (width > LOWER_BOUND_WIDTH) && (width <= UPPER_BOUND_WIDTH);
	}

	/**
	 * Inspector die de breedte van dit bord teruggeeft.
	 * 
	 * @return	De breedte van dit bord.
	 * 			|width
	 */
	@Basic @Immutable
	public long getWidth() {
		return width;
	}

	/**
	 * Inspector die de hoogte van dit bord teruggeeft.
	 * 
	 * @return	De hoogte van dit bord.
	 * 			|height
	 */
	@Basic @Immutable
	public long getHeight() {
		return height;
	}

	/**
	 * Deze methode kijkt na of een positie op het bord geldig is.
	 * 
	 * @param	position
	 * 			De positie waarop nagekeken moet worden.
	 * 
	 * @return	Boolean die true is als de positie geldig is, false indien niet.
	 * 			|if (position.getX() > getWidth() || position.getX() < LOWER_BOUND_WIDTH || position.getY() > getHeight() || position.getY() < LOWER_BOUND_HEIGHT)
	 * 			|	false
	 * 			|true
	 */
	public boolean isValidPosition(Position position){
		if(position == null)
			return false;
		if (position.getX() > getWidth() || position.getX() < LOWER_BOUND_WIDTH || position.getY() > getHeight() || position.getY() < LOWER_BOUND_HEIGHT)
			return false;
		return true;
	}

	/**
	 * Deze methode voegt 2 borden samen. Elementen uit het 2de bord die niet op dit bord geplaatst kunnen worden, worden getermineerd.
	 * 
	 * @param	board2
	 * 			Het bord dat moet samengevoegd worden met dit bord.
	 * 
	 * @post	Het 2de bord is getermineerd.
	 * 			|(new board2).isTerminated() == true
	 */
	//TODO: hier moeten we alles opnieuw doen aangezien ze nu naast elkaar moeten uitkomen.
	
	public void merge(Board board2) {
		/*
		Set<String> b2PosStrings = board2.getMap().keySet();
		for (String b2PosString : b2PosStrings){
			Position b2Pos = Robot.getPositionFromString(b2PosString);
			if(this.isValidPosition(b2Pos)){
				HashSet<Entity> b2EntitiesOnPos = board2.getEntityOnPosition(b2Pos);
				for(Entity ent: b2EntitiesOnPos){
					if(this.isPlacableOnPosition(b2Pos, ent)){
						ent.removeFromBoard();
						ent.putOnBoard(this, b2Pos);
					}
				}
			}
		}
		board2.terminate();
		*/
	}

	/**
	 * Deze methode kijkt na of het bord getermineerd is of niet.
	 * 
	 * @return	Boolean die true is als het bord getermineerd is.
	 * 			|isTerminated
	 */
	public boolean isTerminated(){
		return isTerminated;
	}

	/**
	 * Deze methode termineert het bord en alle objecten op het bord.
	 * 
	 * @post	Het bord is getermineerd.
	 * 			|new.isTerminated() == true
	 * 
	 * @post	Het bord is leeg.
	 * 			|new.map.values() == null
	 */
	public void terminate(){
		isTerminated = true;
		Collection<HashSet<Entity>> c = map.values();
		if(c != null){
			for (HashSet<Entity> ents : c){
				if(ents != null){
					Position pos = ents.iterator().next().getPosition();
					for (Entity ent : ents){
						ent.destroy();
					}
					cleanBoardPosition(pos);
				}
			}
		}
	}

	public boolean containsWall(Position pos) {
		try{
			HashSet<Entity> ents = getEntityOnPosition(pos);
			boolean containsRobotsOrWalls = false;
			for (Entity ent : ents){
				if (ent instanceof Robot || ent instanceof Wall)
					containsRobotsOrWalls = true;
			}
			return containsRobotsOrWalls;
		}catch (NullPointerException esc){
			return false;
		}
	}

	public Set<SurpriseBox> getSurpriseBoxes() {
		Collection<HashSet<Entity>> c = map.values();
		HashSet<SurpriseBox> surp = new HashSet<>();
		for (Set<Entity> values : c ){
			Iterator<Entity> i = values.iterator();
			while (i.hasNext()){
				Entity obj = i.next();
				if (obj instanceof SurpriseBox){
					surp.add((SurpriseBox)obj);
				}
			}
		}
		return surp;
	}

	public Set<RepairKit> getRepairkits() {
		Collection<HashSet<Entity>> c = map.values();
		HashSet<RepairKit> rep = new HashSet<>();
		for (Set<Entity> values : c ){
			Iterator<Entity> i = values.iterator();
			while (i.hasNext()){
				Entity obj = i.next();
				if (obj instanceof RepairKit){
					rep.add((RepairKit)obj);
				}
			}
		}
		return rep;
	}
	
	protected Position getRandomPosition(Robot robot) {
		long height = getHeight();
		long width = getHeight();
		Random rand = new Random();
		long randomX = 0;
		long randomY = 0;
		boolean xfound = false;
		boolean yfound = false;		
		while (!xfound){
			randomX = rand.nextLong();
			if (randomX >= 0 && randomX <= width){
				xfound = true;
			}
		}
		while (!yfound){
			randomY = rand.nextLong();
			if (randomY >= 0 && randomY <= height){
				xfound = true;
			}
		}
		Position teleport = new Position(randomX, randomY);
		if (isPlacableOnPosition(teleport, robot)){
			return teleport;		
		}
		return getRandomPosition(robot);
	}
	
}
