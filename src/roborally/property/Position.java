package roborally.property;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Immutable;
import be.kuleuven.cs.som.annotate.Value;

import java.util.ArrayList;

import roborally.model.Board;

/**
 * Deze klasse houdt een positie bij. Deze bestaat uit een x- en een y-co�rdinaat.
 * 
 * @invar	Een positie is altijd geldig (de co�rdinaten liggen op of tussen hun minima en maxima).
 * 			|isValidPosition(new.getX(), new.getY())
 * 
 * @author 	Bavo Goosens (1e bachelor informatica, r0297884), Samuel Debruyn (1e bachelor informatica, r0305472)
 * 
 * @version 1.0
 */
@Value
public class Position implements Comparable<Position>{

	/**
	 * X-co�rdinaat van de positie.
	 */
	private final long x;
	/**
	 * Y-co�rdinaat van de positie.
	 */
	private final long y;
	/**
	 * Maximale x-waarde.
	 */
	private final static long UPPER_BOUND_X = Long.MAX_VALUE;
	/**
	 * Minimale x-waarde.
	 */
	private final static long LOWER_BOUND_X = 0;
	/**
	 * Maximale y-waarde
	 */
	private final static long UPPER_BOUND_Y = Long.MAX_VALUE;
	/**
	 * Minimale y-waarde.
	 */
	private final static long LOWER_BOUND_Y = 0;

	/**
	 * Initialiseer een positie.
	 * 
	 * @param 	xpos
	 * 			X-waarde van de in te stellen positie
	 * 
	 * @param 	ypos
	 * 			Y-waarde van de in te stellen positie
	 * 
	 * @throws	IllegalArgumentException
	 * 			De gegeven positie is ongeldig.
	 * 			|!isValidPosition(xpos, ypos)
	 * 
	 * @post	De x-waarde van de nieuwe positie is gelijk aan de gegeven x-waarde.
	 * 			|new.x == xpos
	 * 
	 * @post	De y-waarde van de nieuwe positie is gelijk aan de gegeven y-waarde.
	 * 			|new.y == ypos
	 */
	public Position(long xpos, long ypos) throws IllegalArgumentException{
		if(!isValidPosition(xpos, ypos)) throw new IllegalArgumentException("Gegeven positie is ongeldig!");
		this.x = xpos;
		this.y = ypos;
	}

	/**
	 * Deze methode geeft de x-co�rdinaat van de positie terug.
	 * 
	 * @return 	x-waarde van deze positie
	 * 			|this.x
	 */
	@Basic @Immutable
	public long getX() {
		return this.x;
	}

	/**
	 * Deze methode geeft de y-co�rdinaat van de positie terug.
	 * 
	 * @return 	y-waarde van deze positie
	 * 			|this.y
	 */
	@Basic @Immutable
	public long getY() {
		return this.y;
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
	 * @return	Boolean die true is als de positie geldig is.
	 * 			|if(xpos > UPPER_BOUND_X || xpos < LOWER_BOUND_X || ypos > UPPER_BOUND_Y || ypos < LOWER_BOUND_Y)
	 * 			|	false
	 * 			|true
	 */
	@Basic
	public static boolean isValidPosition(long xpos, long ypos){
		if (xpos > UPPER_BOUND_X || xpos < LOWER_BOUND_X || ypos > UPPER_BOUND_Y || ypos < LOWER_BOUND_Y)
			return false;
		return true;
	}

	/**
	 * Deze methode dient om de posities te vinden naast de huidige positie.
	 * 
	 * @param	board
	 * 			Het bord is nodig om na te kijken of de posities ernaast geldig zijn voor dit bord.
	 * 
	 * @return	Dit is een lijst van posities rond de huidige positie.
	 */
	public ArrayList<Position> getNeighbours(Board board){
		ArrayList<Position> positions = new ArrayList<Position>();
		ArrayList<Position> validPositions = new ArrayList<Position>();
		try{
			positions.add(new Position(this.getX() - 1, this.getY()));
		}catch(IllegalArgumentException e){}
		try{
			positions.add(new Position(this.getX() + 1, this.getY()));
		}catch(IllegalArgumentException e){}
		try{
			positions.add(new Position(this.getX(), this.getY() - 1));
		}catch(IllegalArgumentException e){}
		try{
			positions.add(new Position(this.getX(), this.getY() + 1));
		}catch(IllegalArgumentException e){}
		for(Position pos: positions){
			if(board.isValidBoardPosition(pos)){
				validPositions.add(pos);
			}
		}
		return validPositions;
	}

	/* 
	 * Deze methode maakt een String op basis van een Position.
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return this.getX() + ", " + this.getY();
	}
	
	/**
	 * Deze methode vergelijkt 2 posities.
	 * 
	 * @param	other
	 * 			De positie waarmee vergeleken moet worden.
	 * 
	 * @throws	IllegalArgumentException
	 * 			De 2de positie is null.
	 * 			|other == null
	 * 
	 * @return	|if(this.getX() == other.getX())
	 *			|	(int) (this.getY() - other.getY())
	 *			|(int) (this.getX() - other.getX())
	 */
	@Override
	public int compareTo(Position other) throws	IllegalArgumentException{
		if(other == null)
			throw new IllegalArgumentException("Andere positie mag niet null zijn.");
		if(this.getX() == other.getX())
			return (int) (this.getY() - other.getY());
		return (int) (this.getX() - other.getX()); 
	}	

	/*
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object other){
		if(other == null)
			return false;
		if(this.getClass() != other.getClass())
			return false;
		return (this.getX() == ((Position) other).getX() && this.getY() == ((Position) other).getY());
	}

	/*
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return Integer.parseInt(Long.toString(this.getX()) + Long.toString(this.getY()));
	}
}
