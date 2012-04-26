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
	 * De ori�ntatie is naar boven gericht.
	 */
	UP, /**
	 * De ori�ntatie is naar rechts gericht.
	 */
	RIGHT, /**
	 * De ori�ntatie is naar beneden gericht.
	 */
	DOWN, /**
	 * De ori�ntatie is naar links gericht.
	 */
	LEFT;
	
	/**
	 * Deze methode geeft de ori�ntatie terug als er 1 keer in wijzerzin gedraaid wordt.
	 * 
	 * @return	Orientation
	 * 			De ori�ntatie als er 1 keer in wijzerzin gedraaid wordt.
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
	 * Deze methode geeft de ori�ntatie terug als er 1 keer in tegenwijzerzin gedraaid wordt.
	 * 
	 * @return	Orientation
	 * 			De ori�ntatie als er 1 keer in tegenwijzerzin gedraaid wordt.
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
}
