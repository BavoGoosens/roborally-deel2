package roborally.basics;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Immutable;

/**
 * @invar	Een positie is altijd geldig (de coördinaten liggen op of tussen hun minima en maxima).
 * 			|isValidPosition(new.getX(), new.getY())
 * 
 */
public class Position {
	
	private long x;
	private long y;
	private final long UPPER_BOUND_X = Long.MAX_VALUE;
	private final long LOWER_BOUND_X = 0;
	private final long UPPER_BOUND_Y = Long.MAX_VALUE;
	private final long LOWER_BOUND_Y = 0;
	
	/**
	 * Initialiseer een positie.
	 * 
	 * @param 	xpos
	 * 			X-waarde van de in te stellen positie
	 * 
	 * @param 	ypos
	 * 			Y-waarde van de in te stellen positie
	 */
	public Position(long xpos, long ypos) throws IllegalArgumentException{
		if(!isValidPosition(xpos, ypos)) throw new IllegalArgumentException("Gegeven positie is ongeldig!");
		this.setX(xpos);
		this.setY(ypos);
	}

	/**
	 * @return x-waarde van deze positie
	 */
	@Basic @Immutable
	public long getX() {
		return x;
	}

	/**
	 * @param x-waarde om in te stellen voor deze positie
	 */
	@Immutable
	private void setX(long x) {
		this.x = x;
	}

	/**
	 * @return y-waarde van deze positie
	 */
	@Basic @Immutable
	public long getY() {
		return y;
	}

	/**
	 * @param y-waarde om in te stellen voor deze positie
	 */
	@Immutable
	private void setY(long y) {
		this.y = y;
	}
	
	/**
	 * Kijkt na of de positie een geldige positie is.
	 * 
	 * @param	xpos
	 * 			X-waarde van de positie die nagekeken moet worden.
	 * 
	 * @param	ypos
	 * 			Y-waarde van de positie die nagekeken moet worden.
	 * 
	 * @return	boolean
	 * 			True als de positie geldig is, false indien deze ongeldig is.
	 */
	@Basic
	private boolean isValidPosition(long xpos, long ypos){
		if (xpos > UPPER_BOUND_X || xpos < LOWER_BOUND_X || ypos > UPPER_BOUND_Y || ypos < LOWER_BOUND_Y) return false;
		return true;
	}
	
}
