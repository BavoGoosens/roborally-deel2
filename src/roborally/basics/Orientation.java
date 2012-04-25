package roborally.basics;

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
	 * @return	Orientation
	 * 			De oriëntatie als er 1 keer in wijzerzin gedraaid wordt.
	 *			|if(new.equals(UP))
	 *			|	RIGHT
	 *			|if(new.equals(RIGHT))
	 *			|	DOWN
	 *			|if(new.equals(DOWN))
	 *			|	LEFT
	 *			|if(new.equals(LEFT))
	 *			|	UP
	 *			|null
	 */
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
	 * @return	Orientation
	 * 			De oriëntatie als er 1 keer in tegenwijzerzin gedraaid wordt.
	 *			|if(new.equals(UP))
	 *			|	LEFT
	 *			|if(new.equals(RIGHT))
	 *			|	UP
	 *			|if(new.equals(DOWN))
	 *			|	RIGHT
	 *			|if(new.equals(LEFT))
	 *			|	DOWN
	 *			|null
	 * 			
	 */
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
