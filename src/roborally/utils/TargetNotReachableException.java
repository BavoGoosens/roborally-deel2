package roborally.utils;

import roborally.property.Position;

/**
 * Deze Exception beschrijft een exception die optreedt wanneer het doel niet bereikbaar is.
 * 
 * @author 	Bavo Goosens (1e bachelor informatica, r0297884), Samuel Debruyn (1e bachelor informatica, r0305472)
 * 
 * @version 1.0
 */
public class TargetNotReachableException extends IllegalStateException {

	/**
	 * Constructor om het bericht in te stellen dat teruggeven dient te worden.
	 * 
	 * @param	pos
	 * 			De positie die bereikt moet worden.
	 * 
	 * @post	Het bericht is ingesteld op met meer informatie over de fout.
	 * 			|new.getMessage().equals("Het doel met positie " + pos.toString() + " dat je wilt bereiken is niet bereikbaar.")
	 */
	public TargetNotReachableException(Position pos){
		super("Het doel met positie " + pos.toString() + " dat je wilt bereiken is niet bereikbaar.");
	}
	
}