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
import roborally.exception.IllegalPositionException;
import roborally.filter.EnergyFilter;
import roborally.filter.Filter;
import roborally.filter.OrientationFilter;
import roborally.filter.PositionFilter;
import roborally.filter.WeightFilter;

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
	protected final HashMap <Position, HashSet<Entity>> map;
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
	 * @param	entity
	 * 			Het object waarvoor dit nagekeken moet worden.
	 * 
	 * @return	Boolean die true is als de positie niet door een muur of een robot ingenomen is.
	 * 			|if(!isValidPosition(pos))
	 * 			|	false
	 * 			|if(getEntityOnPosition(pos) == null)
	 * 			|	true
	 * 			|if(getEntityOnPosition(pos).isEmpty())
	 * 			|	true
	 * 			|Iterator<Entity> itr = place.iterator();
	 * 			|while(itr.hasNext()){
	 * 			|	Entity ent = itr.next();
	 * 			|	if (ent instanceof Wall)
	 * 			|		false
	 * 			|	if(ent instanceof Robot && entity instanceof Robot)
	 * 			|		false
	 * 			|}
	 * 			|true
	 */
	public boolean isPlacableOnPosition(Position pos, Entity entity){
		if (!isValidPosition(pos))
			return false;
		HashSet<Entity> place = getEntityOnPosition(pos);
		if(place == null)
			return true;
		if (place.isEmpty())
			return true;
		Iterator<Entity> itr = place.iterator();
		while(itr.hasNext()){
			Entity ent = itr.next();
			if (ent instanceof Wall)
				return false;
			if(ent instanceof Robot && entity instanceof Robot)
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
	 * 			Het object dat op het bord geplaatst moet worden.
	 * 
	 * @throws 	IllegalPositionException
	 * 			De plaats in het bord is niet beschikbaar.
	 * 			|!isPlacableOnPosition(key, entity)
	 * 
	 * @post	Als de positie op het bord beschikbaar was staat het object op deze positie.
	 * 			|new.getEntityOnPosition.contains(entity)
	 * 			
	 */
	public void putEntity(Position key, Entity entity) throws IllegalPositionException{
		if (!isPlacableOnPosition(key, entity))
			throw new IllegalPositionException(key);
		if(getEntityOnPosition(key) == null)
			map.put(key, new HashSet<Entity>());
		getEntityOnPosition(key).add(entity);
	}

	/**
	 * Deze methode geeft alle objecten van een bepaale klasse op het bord terug.
	 * 
	 * @param	type
	 * 			De klasse waarvan objecten gezocht moeten worden.
	 * 
	 * @return	Een set met alle objecten van de gegeven klasse op het bord.
	 * 			|Collection<HashSet<Entity>> coll = map.values();
	 * 			|Set<Entity> result = new HashSet<Entity>();
	 * 			|for(HashSet<Entity> place: coll){
	 * 			|	Iterator<Entity> itr = place.iterator();
	 * 			|	while(itr.hasNext()){
	 * 			|		Entity current = itr.next();
	 * 			|		if(current.getClass() == type)
	 * 			|			result.add(current);
	 * 			|	}
	 * 			|}
	 * 			|result
	 */
	public Set<Entity> getEntitiesOfType(Class type){
		Collection<HashSet<Entity>> coll = map.values();
		Set<Entity> result = new HashSet<Entity>();
		for(HashSet<Entity> place: coll){
			Iterator<Entity> itr = place.iterator();
			while(itr.hasNext()){
				Entity current = itr.next();
				if(current.getClass() == type)
					result.add(current);
			}
		}
		return result;
	}

	/**
	 * Deze inspector geeft een boolean die true is wanneer de hoogte toegestaan is en in het andere geval false.
	 * 
	 * @param 	height
	 * 			De hoogte die nagekeken moet worden.
	 * 	
	 * @return	Boolean die true is wanneer de hoogte toegestaan is en in het andere geval false.
	 * 			|(height > Board.LOWER_BOUND_HEIGHT) && (height < Board.UPPER_BOUND_HEIGHT)
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
	 * 			|(width > Board.LOWER_BOUND_WIDTH) && (width < Board.UPPER_BOUND_WIDTH)
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
	 * 			|if(position == null)
	 * 			|	false
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
	 * 			|(new board2).isTerminated()
	 */
	public void merge(Board board2) {
		Set<Position> keys2 = board2.map.keySet();
		for(Position pos2: keys2){
			if(this.isValidPosition(pos2)){
				HashSet<Entity> ents2 = board2.getEntityOnPosition(pos2);
				for(Entity ent2: ents2){
					if(isPlacableOnPosition(pos2, ent2)){
						ent2.removeFromBoard();
						ent2.putOnBoard(this, pos2);
					}else{
						for(Position neighbour: pos2.getNeighbours(this)){
							if(isPlacableOnPosition(neighbour, ent2)){
								ent2.removeFromBoard();
								ent2.putOnBoard(this, neighbour);
								break;
							}
						}
					}
				}
			}
		}
		board2.terminate();
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
		isTerminated = true;
	}

	/**
	 * Deze methode geeft een willekeurige positie terug die geldig is voor het bord van de robot.
	 * 
	 * @param	robot
	 * 			De robot waarvoor een willekeurige positie moet teruggegeven worden.
	 * 
	 * @return	|Random rand = new Random();
	 * 			|Position teleport = new Position(nextLong(rand, robot.getBoard().getWidth()), nextLong(rand, robot.getBoard().getHeight()));
	 * 			|if (robot.getBoard().isPlacableOnPosition(teleport, robot))
	 * 			|	teleport
	 * 			|getRandomPosition(robot)
	 */
	protected static Position getRandomPosition(Robot robot) {
		Random rand = new Random();
		Position teleport = new Position(nextLong(rand, robot.getBoard().getWidth()), nextLong(rand, robot.getBoard().getHeight()));
		if (robot.getBoard().isPlacableOnPosition(teleport, robot))
			return teleport;		
		return getRandomPosition(robot);
	}

	/**
	 * Deze methode geeft een willekeurige long terug op basis van een random en een bereik.
	 * 
	 * @param	rand
	 * 			De random waarmee een willekeurige long gemaakt moet worden.
	 * 
	 * @param	n
	 * 			De maximale waarde van het bereik waarin de long gegenereerd moet worden.
	 * 
	 * @see		java.util.Random#nextInt(int n)
	 */
	private static long nextLong(Random rand, long n) {
		long bits, val;
		do {
			bits = (rand.nextLong() << 1) >>> 1;
			val = bits % n;
		} while (bits-val+(n-1) < 0L);
		return val;
	}

	/**
	 * Deze methode geeft een iterator terug met een bepaalde conditie (Filter).
	 * 
	 * @param	filter
	 * 			Deze filter bepaalt de conditie voor de iterator. 
	 * 
	 * @return	Een nieuwe iterator met alle objecten op het bord die voldoen aan de gegeven filter.
	 */
	public Iterator<Entity> getEntitiesWithFilter(Filter filter){

		Collection<HashSet<Entity>> coll = map.values();
		final Set<Entity> entities = new HashSet<Entity>();
		for(HashSet<Entity> place: coll){
			Iterator<Entity> itr = place.iterator();
			while(itr.hasNext()){
				Entity current = itr.next();
				if(filter instanceof PositionFilter){
					if(filter.evaluateObject(current.getPosition()))
						entities.add(current);
				}
				if(filter instanceof OrientationFilter && current instanceof Robot){
					if(filter.evaluateObject(((Robot) current).getEnergy()))
						entities.add(current);
				}
				if(filter instanceof WeightFilter){
					if(current instanceof Robot){						
						if(filter.evaluateObject(((Robot) current).getTotalWeight()))
							entities.add(current);
					}
					if(current instanceof Item){						
						if(filter.evaluateObject(((Item) current).getWeight()))
							entities.add(current);
					}
				}
				if(filter instanceof EnergyFilter){
					if(current instanceof Robot){
						if(filter.evaluateObject(((Robot) current).getEnergy()))
							entities.add(current);
					}
					if(current instanceof Battery){
						if(filter.evaluateObject(((Battery) current).getEnergy()))
							entities.add(current);
					}
					if(current instanceof RepairKit){
						if(filter.evaluateObject(((RepairKit) current).getEnergy()))
							entities.add(current);
					}
				}				
			}
		}

		return new Iterator<Entity>(){

			@Override
			public boolean hasNext() {
				return entItr.hasNext();
			}
			@Override
			public Entity next() {
				last = entItr.next();
				return last;
			}
			@Override
			public void remove() {
				last.removeFromBoard();
			}
			
			private Iterator<Entity> entItr = entities.iterator();
			private Entity last;

		};
	}

}