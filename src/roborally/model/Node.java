package roborally.model;

import javax.swing.text.Position;

public class Node{

private Position pos;
private long gCost;
private long hCost;
private long fCost = gCost + hCost;
private Node parent;

public Node(Position pos, long g, long h, Node parent){
	setPos(pos);
	setGCost(g);
	setHCost(h);
	setParent(parent);
}

private void setParent(Node parent){
	this.parent = parent;
}

private void setPos(Position pos){
	this.pos = pos;
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

}
	
