package roborally.model;

import be.kuleuven.cs.som.annotate.Basic;
import roborally.basics.Position;

/**
 * Deze klasse houdt een muur bij. Deze heeft een positie.
 * 
 * @author 	Bavo Goosens (1e bachelor informatica, r0297884), Samuel Debruyn (1e bachelor informatica, r0305472)
 * 
 * @version 1.0
 */
public class Wall {
	
	/**
	 * Positie van deze muur.
	 */
	private final Position position;
	
	/**
	 * Deze methode maakt een nieuwe muur aan op een gegeven positie.
	 * 
	 * @param	position
	 * 			De positie van de nieuwe muur.
	 * 
	 * @post	new.position == position
	 */
	public Wall(Position position){
		this.position = position;
	}
	
	/**
	 * Geeft de positie van de muur terug.
	 * 
	 * @return 	Position position
	 * 			|new.position
	 */
	@Basic
	public Position getPosition() {
		return position;
	}
	
}
