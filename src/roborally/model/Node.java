package roborally.model;

import roborally.basics.Energy;
import roborally.basics.Position;

import roborally.basics.Orientation;

//mag parent moet ten alle tijden naar een andere node wijzen => geen selfreferenties $
//position zal altyd naar een geldige pos verwijzen 
//de cost moet altijd groter zijn dan 0 
//hoe da combinere me die interpretatie van energie me die omzettingen 

//TODO: Node moet uitbreiding worden van Entity
public class Node{

private Position pos;
private final Energy gCost = new Energy(0);
private final Energy hCost = new Energy(0);
private final Energy fCost = new Energy(0);
private Orientation orientation;
private Node parent;

public Node(Position position, Energy g, Energy h,Orientation orientation, Node parent){
	setPos(position);
	setGCost(g);
	setHCost(h);
	setOrientation(orientation);
	setParent(parent);
}

public Node(Position position, Orientation orientation) {
	setPos(position);
	setOrientation(orientation);
}

public void setParent(Node parent){
	this.parent = parent;
}

private void setOrientation(Orientation or){
	this.orientation = or;
}

private void setPos(Position position){
	this.pos = position;
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

public Position getPosition(){
	return this.pos;
}

public Orientation getOrientation(){
	return this.orientation;
}
}

	
