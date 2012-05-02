package roborally.model;

import roborally.basics.Energy;
import roborally.basics.Position;

import roborally.basics.Orientation;

//mag parent moet ten alle tijden naar een andere node wijzen => geen selfreferenties 
//niet altyd de eerste node heeft gewoon geen parent u startnode heeft dus null als parent
//position zal altyd naar een geldige pos verwijzen 
//de cost moet altijd groter zijn dan 0 
//hoe da combinere me die interpretatie van energie me die omzettingen 

public class Node extends Entity{


private final Energy gCost = new Energy(0);
private final Energy hCost = new Energy(0);
private final Energy fCost = new Energy(0);
private Orientation orientation;
private Node parent;

public Node(Position position,Board board, Energy g, Energy h,Orientation orientation, Node parent){
	putOnBoard(board,position);
	setGCost(g);
	setHCost(h);
	setOrientation(orientation);
	setParent(parent);
}

public Node(Position position, Orientation orientation, Board board) {
	setOrientation(orientation);
	putOnBoard(board,position);
}

public void setParent(Node parent){
	this.parent = parent;
}

private void setOrientation(Orientation or){
	this.orientation = or;
}

public void setGCost(Energy g){
	this.gCost.setEnergy(g.getEnergy());
	this.calcFCost();
}

public void setHCost(Energy h){
	this.hCost.setEnergy(h.getEnergy());
	this.calcFCost();
}

private void calcFCost(){
	this.setFCost(Energy.energySum(this.getGCost(), this.getHCost()));
}

private void setFCost(Energy f){
	this.fCost.setEnergy(f.getEnergy());
}

public Node getParent(){
	return this.parent;
}

public Energy getFCost(){
	return this.fCost;
}

public Energy getHCost(){
	return this.hCost;
}

public Energy getGCost(){
	return this.gCost;
}

public Orientation getOrientation(){
	return this.orientation;
}

}

	
