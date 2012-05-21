package roborally.utils;
/**
 * Deze Exception beschrijft dat een robot niet op een board staat.
 * 
 * @author 	Bavo Goosens (1e bachelor informatica, r0297884), Samuel Debruyn (1e bachelor informatica, r0305472)
 * 
 * @version 1.0
 */
public class EntityNotOnBoardException extends Exception{

	/**
	 * Constructor om de default message in te stellen die teruggeven dient te worden.
	 * 
	 */
	public EntityNotOnBoardException(){
		super("Deze Robot bevindt zich niet op een Board");
	}
	
	/**
	 * Constructor om een message in te stellen die teruggeven dient te worden.
	 * 
	 * @param 	msg
	 * 			De tekst die teruggeven moet worden bij deze error.
	 */
	public EntityNotOnBoardException(String msg){
		super(msg);
	}
}
