package roborally.utils;
/**
 * Deze Exception beschrijft dat een robot niet op een board staat.
 * 
 * @author 	Bavo Goosens (1e bachelor informatica, r0297884), Samuel Debruyn (1e bachelor informatica, r0305472)
 * 
 * @version 1.0
 */
public class EntityNotOnBoardException extends IllegalStateException{

	/**
	 * Constructor om de default message in te stellen die teruggeven dient te worden.
	 * 
	 */
	public EntityNotOnBoardException(){
		super("Dit object bevindt zich niet op een bord.");
	}
	
	/**
	 * Constructor om een message in te stellen die teruggeven dient te worden.
	 * 
	 * @param 	message
	 * 			De tekst die teruggeven moet worden bij deze error.
	 * 
	 * @post	Het bericht is ingesteld op het gegeven bericht.
	 * 			|new.getMessage() = message
	 */
	public EntityNotOnBoardException(String message){
		super(message);
	}
}
