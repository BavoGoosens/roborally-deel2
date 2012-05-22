package roborally.util;

/**
 * Deze Exception beschrijft dat een robot niet op een board staat.
 * 
 * @author 	Bavo Goosens (1e bachelor informatica, r0297884), Samuel Debruyn (1e bachelor informatica, r0305472)
 * 
 * @version 1.0
 */
public class EntityNotOnBoardException extends IllegalStateException{

	/**
	 * Constructor om het bericht in te stellen dat teruggeven dient te worden.
	 * 
	 * @post	Het bericht is ingesteld op met meer informatie over de fout.
	 * 			|new.getMessage().equals("Dit object bevindt zich niet op een bord.")
	 */
	public EntityNotOnBoardException(){
		super("Het object waarnaar verwezen werd bevindt zich niet op een bord.");
	}

}
