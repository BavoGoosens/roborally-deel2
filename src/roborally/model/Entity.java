package roborally.model;

import roborally.basics.Position;
import roborally.model.Board;
import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Raw;

/**
 * Deze klasse houdt een object bij dat op een board kan staan en een positie kan hebben.
 * 
 * @author 	Bavo Goosens (1e bachelor informatica, r0297884), Samuel Debruyn (1e bachelor informatica, r0305472)
 * 
 * @version 1.0
 */
public class Entity {
	
	/**
	 * Positie van dit object (niet noodzakelijk).
	 */
	private Position position;
	
	/**
	 * Indien het object vernietigd is wordt dit true.
	 */
	private boolean isTerminated = false;
	
	/**
	 * Het board waarop dit object staat (niet noodzakelijk).
	 */
	private Board board;
	
	/**
	 * Methode die het board instelt waartoe dit object behoort.
	 * 
	 * @param 	board
	 * 			Het board waarop dit object zich bevindt.
	 * 
	 * @post	new.board == board
	 */
	private void setBoard(Board board){
		this.board = board;
	}
	
	/**
	 * Methode die het board teruggeeft waarop dit object zich bevindt. Deze methode kan ook null teruggeven wat wil zeggen dat het object zich niet op een board bevindt.
	 * 
	 * @return	new.board
	 * 			Het board waarop dit object zich bevindt of null als het object niet op een board staat;
	 */
	@Basic
	public Board getBoard(){
		return this.board;
	}
	/**
	 * Geeft de positie van dit object terug.
	 * 
	 * @return	De positie van het object.
	 * 			|new.position
	 */
	@Basic
	public Position getPosition() {
		return position;
	}

	/**
	 * Wijzigt de positie van dit object naar de nieuwe positie.
	 * 
	 * @param	position
	 * 			De nieuwe positie. Null indien deze buiten het bord is.
	 */
	public void setPosition(Position position) {
		this.position = position;
	}
	
	/**
	 * Deze methode vernietigt het object.
	 * 
	 * @post	Het object heeft geen positie meer.
	 * 			|new.getPosition() == null
	 * @post	Het object is vernietigd.
	 * 			|new.isDestroyed()
	 * @post	Het object staat niet meer op een bord.
	 * 			|new.getBoard() == null
	 */
	public void destroy(){
		//TODO: remove from board
		this.isTerminated = true;
		this.setPosition(null);
		this.setBoard(null);
	}
	
	/**
	 * Deze methode geeft true indien het object vernietigd is, anders false.
	 * 
	 * @return	|new.isTerminated
	 */
	@Basic
	public boolean isDestroyed(){
		return isTerminated;
	}
	
	public void putOnBoard(Board board, Position position){
		//TODO
	}
	
	public void removeFromBoard(){
		//TODO
	}
}
