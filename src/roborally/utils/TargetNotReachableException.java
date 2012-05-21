package roborally.utils;
/**
 * Deze Exception beschrijft een exception die optreedt wanneer het doel niet bereikbaar is.
 * 
 * @author 	Bavo Goosens (1e bachelor informatica, r0297884), Samuel Debruyn (1e bachelor informatica, r0305472)
 * 
 * @version 1.0
 */
public class TargetNotReachableException extends Exception {

	/**
	 * Constructor om de default message in te stellen die teruggeven dient te worden.
	 */
	public TargetNotReachableException(){
		super("Het doel dat je wilt bereiken is niet bereikbaar");
	}
	
	/**
	 * Constructor om een message in te stellen die teruggeven dient te worden.
	 * 
	 * @param 	msg
	 * 			De tekst die teruggeven moet worden bij deze error.
	 */
	public TargetNotReachableException(String msg){
		super(msg);
	}
	
}
