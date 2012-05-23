package roborally.model;

import be.kuleuven.cs.som.annotate.Basic;
import roborally.property.Energy;
import roborally.property.Orientation;
import roborally.property.Position;

/**
 * Klasse om nodes op het spelbord voor te stellen.
 * 
 * @author 	Bavo Goosens (1e bachelor informatica, r0297884), Samuel Debruyn (1e bachelor informatica, r0305472)
 * 
 * @version 1.0
 * 
 * @invar	
 * 			| isValidParent(this.getParent())
 */
public class Node extends Entity{


	private Energy gCost = new Energy(0);
	private Energy hCost = new Energy(0);
	private Energy fCost = new Energy(0);
	private Orientation orientation;
	private Node parent;

	/**
	 * Constructor om een nieuwe node aan te maken met gegeven position,board,gcost,hcost,orientatie en parent.
	 * 
	 * @param 	position
	 * @param 	board
	 * @param 	g
	 * @param 	h
	 * @param 	orientation
	 * @param 	parent
	 */
	public Node(Position position,Board board, Energy g, Energy h,Orientation orientation, Node parent){
		this.setBoard(board);
		this.setPosition(position);
		setGCost(g);
		setHCost(h);
		setOrientation(orientation);
		setParent(parent);
	}

	/**
	 * Constructor om een nieuwe node aan te maken met gegeven position, orientation en board.
	 * 
	 * @param 	position
	 * @param 	orientation
	 * @param 	board
	 */
	public Node(Position position, Orientation orientation, Board board) {
		setOrientation(orientation);
		putOnBoard(board,position);
	}

	/**
	 * Deze methode gaat de parent van deze node instellen.
	 * 
	 * @param 	parent
	 * 
	 * @post 	...
	 * 			|(new this).getParent() == parent
	 */
	public void setParent(Node parent){
		this.parent = parent;
	}

	/**
	 * Deze methode gaat de orientatie van deze node instellen.
	 * 
	 * @param 	orientation
	 * 
	 * @post 	...
	 * 			| (new this).getOrientation() == orientation
	 */
	private void setOrientation(Orientation orientation){
		this.orientation = orientation;
	}

	/**
	 * Deze methode gaat de G kost van deze node instellen.
	 * De G kost is de kost om van de beginpositie tot deze positie te komen.
	 * 
	 * @param 	g
	 * 
	 * @post 	...
	 * 			| (new this).getGCost().getEnergy() == g.getEnergy()
	 * 			| (new this).getFCost().getEnergy() == g.getEnergy() + this.getHCost().getEnergy()
	 */
	public void setGCost(Energy g){
		this.gCost = g;
		this.calcFCost();
	}

	/**
	 * Deze methode gaat de H kost van deze node instellen.
	 * De H kost is de heuristieke kost of nog de geschatte kost om tot aan de eindpositie te geraken.
	 * 
	 * @param  	h
	 * 
	 * @post 	...
	 * 			| (new this).getHCost().getEnergy() == h.getEnergy()
	 * 			| (new this).getFCost().getEnergy() == h.getEnergy() + this.getGCost().getEnergy()
	 */
	public void setHCost(Energy h){
		this.hCost = h;
		this.calcFCost();
	}

	/**
	 * Deze methode gaat de F kost van deze node berekenen.
	 * Deze is gelijk aan de som van de H kost en de G kost.
	 * 
	 * @post 	...
	 * 			| (new this).getFCost.getEnergy() == this.getHCost.getEnergy() + this.getGCost.getEnergy()
	 */
	private void calcFCost(){
		this.setFCost(Energy.energySum(this.getGCost(), this.getHCost()));
	}

	/**
	 * Deze methode gaat de F kost van deze node instellen.
	 * 
	 * @param 	f
	 * 
	 * @post 	...
	 * 			| (new this).getFcost.getEnergy() == f.getEnergy()
	 */
	private void setFCost(Energy f){
		this.fCost = f;
	}

	/**
	 * Deze methode geeft de ouder node van deze node terug.
	 * 
	 * @return	Node result 
	 * 			| result == this.parent
	 */
	@Basic
	public Node getParent(){
		return this.parent;
	}

	/**
	 * Deze methode geeft de F kost van deze node terug.
	 * 
	 *@return	Energy result 
	 *			| result.getEnergy() == this.fCost.getEnergy()
	 */
	@Basic
	public Energy getFCost(){
		return this.fCost;
	}

	/**
	 * Deze methode geeft de H kost van deze node terug.
	 * 
	 * @return	Energy result 
	 * 			| result.getEnergy() == this.hCost.getEnergy()
	 */
	@Basic
	public Energy getHCost(){
		return this.hCost;
	}

	/**
	 * Deze methode geeft de G kost van deze node terug.
	 * 	
	 * @return	Energy result
	 * 			| result.getEnergy() == this.gCost.getEnergy()
	 */
	@Basic
	public Energy getGCost(){
		return this.gCost;
	}

	/**
	 * Deze methode gaat de orientatie van deze node teruggeven.
	 * 
	 * @return	Orientation result 
	 * 			| result == this.orientation
	 */
	@Basic
	public Orientation getOrientation(){
		return this.orientation;
	}

	/**
	 * Methode om na te gaan of een node een toegestane parent is van deze node.
	 * 
	 * @param 	node
	 * 
	 * @return 	boolean result 
	 * 			| if ((node == null) || (node != this ))
	 * 			|	result == true
	 * 			|result == false 
	 */
	public boolean isValidParent(Node node){
		return ((node == null) || (node != this ));
	}
}
