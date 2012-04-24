package roborally.model;

import roborally.basics.Position;

import roborally.basics.Orientation;

//mag parent moet ten alle tijden naar een andere node wijzen => geen selfreferenties $
//position zal altyd naar een geldige pos verwijzen 
//de cost moet altijd groter zijn dan 0 
//hoe da combinere me die interpretatie van energie me die omzettingen 
public class Node{

private Position pos;
private long gCost;
private long hCost;
private long fCost = gCost + hCost;
private Orientation orientation;
private Node parent;

public Node(Position position, long g, long h,Orientation orientation, Node parent){
	setPos(position);
	setGCost(g);
	setHCost(h);
	setOrientation(orientation);
	setParent(parent);
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

private void setGCost(long g){
	this.gCost = g;
}

private void setHCost(long h){
	this.hCost = h;
}

public Node getParent(){
	return this.parent;
}

public long getFCost(){
	return this.fCost;
}

public long getHCost(){
	return this.hCost;
}

public long getGCost(){
	return this.gCost;
}

public Position getPostion(){
	return this.pos;
}

}

	
