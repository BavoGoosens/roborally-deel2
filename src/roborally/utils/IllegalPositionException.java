package roborally.utils;

import roborally.property.Position;

/**
 * Deze Exception beschrijft een ongeldige positie.
 * 
 * @author 	Bavo Goosens (1e bachelor informatica, r0297884), Samuel Debruyn (1e bachelor informatica, r0305472)
 * 
 * @version 1.0
 */
public class IllegalPositionException extends IllegalArgumentException{

	/**
	 * Deze constructor dient om het bericht in te stellen bij gegeven x- en y-coördinaten.
	 * 
	 * @param	x
	 * 			Het ongeldige x-coördinaat.
	 * 
	 * @param	y
	 * 			Het ongeldige y-coördinaat.
	 * 
	 * @post	Er is een nieuw bericht ingesteld voor deze Exception met de gegeven coördinaten.
	 * 			|new.getMessage() == "De positie met coördinaten " + x + ", " + y + " bestaat niet of is reeds bezet."
	 */
	public IllegalPositionException(long x, long y){
		super("De positie met coördinaten " + x + ", " + y + " is ongeldig.");
	}
	
	/**
	 * Deze constructor dient om het bericht in te stellen bij een gegeven positie.
	 * 
	 * @param	position
	 * 			De ongeldige positie.
	 *  
	 * @post	Er is een nieuw bericht ingesteld voor deze Exception met de gegeven coördinaten.
	 * 			|new.getMessage() == "De positie met coördinaten " + position.toString() + " bestaat niet of is reeds bezet."
	 */
	public IllegalPositionException(Position position) {
		super("De positie met coördinaten " + position.toString() + " bestaat niet in dit bord of is reeds bezet.");
	}
	
}