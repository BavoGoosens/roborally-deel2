package roborally.model;

import roborally.basics.Position;

import roborally.basics.Orientation;

//mag parent moet ten alle tijden naar een andere node wijzen => geen selfreferenties $
//position zal altyd naar een geldige pos verwijzen 
//de cost moet altijd groter zijn dan 0 
//hoe da combinere me die interpretatie van energie me die omzettingen 
public class Node{

private Position pos;
private double gCost;
private double hCost;
private double fCost = gCost + hCost;
private Orientation orientation;
private Node parent;

public Node(Position position, double g, double h,Orientation orientation, Node parent){
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

private void setParent(Node parent){
	this.parent = parent;
}

private void setOrientation(Orientation or){
	this.orientation = or;
}

private void setPos(Position position){
	this.pos = position;
}

private void setGCost(double g){
	this.gCost = g;
}

private void setHCost(double h){
	this.hCost = h;
}

public Node getParent(){
	return this.parent;
}

public double getFCost(){
	return this.fCost;
}

public double getHCost(){
	return this.hCost;
}

public double getGCost(){
	return this.gCost;
}

public Position getPosition(){
	return this.pos;
}

public Orientation getOrientation(){
	return this.orientation;
}
}

	
