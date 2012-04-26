package roborally.basics;

import be.kuleuven.cs.som.annotate.Basic;

/**
 * Een enumeratie met alle mogelijke waarden voor een richting. Momenteel UP, RIGHT, DOWN, LEFT.
 * 
 * @author 	Bavo Goosens (1e bachelor informatica, r0297884), Samuel Debruyn (1e bachelor informatica, r0305472)
 * 
 * @version 1.0
 */
public enum Orientation{

	/**
	 * De oriëntatie is naar boven gericht.
	 */
	UP, /**
	 * De oriëntatie is naar rechts gericht.
	 */
	RIGHT, /**
	 * De oriëntatie is naar beneden gericht.
	 */
	DOWN, /**
	 * De oriëntatie is naar links gericht.
	 */
	LEFT;

	/**
	 * Deze methode geeft de oriëntatie terug als er 1 keer in wijzerzin gedraaid wordt.
	 * 
	 * @return	De oriëntatie als er 1 keer in wijzerzin gedraaid wordt.
	 *			|if(this.equals(UP))
	 *			|	RIGHT
	 *			|if(this.equals(RIGHT))
	 *			|	DOWN
	 *			|if(this.equals(DOWN))
	 *			|	LEFT
	 *			|if(this.equals(LEFT))
	 *			|	UP
	 *			|null
	 */
	@Basic
	public Orientation getClockwiseOrientation(){
		if(this.equals(UP))
			return RIGHT;
		if(this.equals(RIGHT))
			return DOWN;
		if(this.equals(DOWN))
			return LEFT;
		if(this.equals(LEFT))
			return UP;
		return null;	
	}

	/**
	 * Deze methode geeft de oriëntatie terug als er 1 keer in tegenwijzerzin gedraaid wordt.
	 * 
	 * @return	De oriëntatie als er 1 keer in tegenwijzerzin gedraaid wordt.
	 *			|if(this.equals(UP))
	 *			|	LEFT
	 *			|if(this.equals(RIGHT))
	 *			|	UP
	 *			|if(this.equals(DOWN))
	 *			|	RIGHT
	 *			|if(this.equals(LEFT))
	 *			|	DOWN
	 *			|null
	 * 			
	 */
	@Basic
	public Orientation getCounterClockwiseOrientation(){
		if(this.equals(UP))
			return LEFT;
		if(this.equals(RIGHT))
			return UP;
		if(this.equals(DOWN))
			return RIGHT;
		if(this.equals(LEFT))
			return DOWN;
		return null;
	}

	/**
	 * Deze methode geeft de volgende positie weer met de huidige oriëntatie.
	 * 
	 * @param	pos
	 * 			Positie van waaruit vertrokken moet worden.
	 * 
	 * @return	De volgende positie met de huidige oriëntatie
	 * 			|if(this.equals(UP))
	 *			|	new Position(pos.getX(), pos.getY() - 1)
	 *			|if(this.equals(RIGHT))
	 *			|	new Position(pos.getX() + 1, pos.getY())
	 *			|if(this.equals(DOWN))
	 *			|	new Position(pos.getX(), pos.getY() + 1);
	 *			|if(this.equals(LEFT))
	 *			|	new Position(pos.getX() - 1, pos.getY())
	 *			|null
	 *
	 * @throws	IllegalStateException
	 * 			Er bestaat geen verdere positie meer met deze oriëntatie.
	 */
	public Position getNextPosition(Position pos) throws IllegalStateException{
		Position result = null;
		if(this.equals(UP)){
			try {
				result = new Position(pos.getX(), pos.getY() - 1);
			} catch (IllegalArgumentException e) {
				throw new IllegalStateException("Er bestaat geen verdere positie meer met deze oriëntatie.");
			}
		}else if(this.equals(RIGHT)){
			try {
				result = new Position(pos.getX() + 1, pos.getY());
			} catch (IllegalArgumentException e) {
				throw new IllegalStateException("Er bestaat geen verdere positie meer met deze oriëntatie.");
			}
		}else if(this.equals(DOWN)){
			try {
				result = new Position(pos.getX(), pos.getY() + 1);
			} catch (IllegalArgumentException e) {
				throw new IllegalStateException("Er bestaat geen verdere positie meer met deze oriëntatie.");
			}
		}else if(this.equals(LEFT)){
			try {
				result = new Position(pos.getX() - 1, pos.getY());
			} catch (IllegalArgumentException e) {
				throw new IllegalStateException("Er bestaat geen verdere positie meer met deze oriëntatie.");
			}
		}
		return result;
	}

}
