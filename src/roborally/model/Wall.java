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
	
	/**
	 * Deze methode maakt een nieuwe muur aan.
	 */
	public Wall(){}
	
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
