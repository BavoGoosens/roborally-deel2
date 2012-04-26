package roborally.model;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Raw;
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
	private Position position;
	
	/**
	 * Indien de muur vernietigd is wordt dit true.
	 */
	private boolean isTerminated = false;

	private Board board;
	
	/**
	 * Deze methode maakt een nieuwe muur aan.
	 */
	public Wall(){}
	
	/**
	 * Methode die het board instelt waartoe deze Wall behoort.
	 * 
	 * @param 	board
	 * 			Het board waarop deze Wall zich bevindt.
	 */
	public void setBoard(Board board){
		this.board = board;
	}
	/**
	 * Methode die het board teruggeeft waarop deze Wall zich bevindt. Deze methode kan ook null teruggeven wat wil zeggen dat de Wall zich niet op een board bevindt.
	 * 
	 * @return	Board of null
	 * 			Het board waarop de wall zich bevindt of null als de wall niet op een board staat;
	 */
	public Board getBoard(){
		return this.board;
	}
	/**
	 * Wijzigt de positie van de muur.
	 * 
	 * @param	position
	 * 			Nieuwe positie van de muur. Null indien de positie buiten het bord is.
	 */
	@Raw @Basic
	public void setPosition(Position position){
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
	
	/**
	 * Deze methode vernietigt de muur.
	 * 
	 * @post	|this.isDestroyed()
	 * @post	|this.getPosition() == null
	 */
	public void destroy(){
		//TODO: remove from board
		this.setPosition(null);
		this.isTerminated = true;
	}
	
	/**
	 * Deze methode geeft true indien de muur vernietigd is, anders false.
	 * 
	 * @return	|new.isTerminated
	 */
	@Basic
	public boolean isDestroyed(){
		return isTerminated;
	}
	
}
