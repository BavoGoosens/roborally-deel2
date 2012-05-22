package roborally.model;

import java.util.ArrayList;
import java.util.HashSet;

import roborally.property.Energy;
import roborally.property.Position;
import roborally.property.Weight;

/**
 * Deze klasse houdt een surprise box bij. Deze heeft een positie en een gewicht.
 * 
 * @author 	Bavo Goosens (1e bachelor informatica, r0297884), Samuel Debruyn (1e bachelor informatica, r0305472)
 * 
 * @version 1.0
 */
public class SurpriseBox extends Item{

	/**
	 * Deze constructor maakt een nieuwe surprise box aan met het gegeven gewicht.
	 * 
	 * @param 	weight
	 * 			Het gewicht dat de surprise box moet krijgen.
	 * 
	 * @post	De nieuwe surprise box heeft het opgegeven gewicht.
	 * 			|new.getWeight() == weight
	 */
	public SurpriseBox(Weight weight) {
		super(weight);
	}

	/**
	 * Deze methode wordt opgeroepen wanneer de surprise box geraakt wordt door een laser of een surprise box.
	 * 
	 * @post	De surprise box is getermineerd.
	 * 			|new.isTerminated()
	 * 
	 * @post	De buren zijn geraakt.
	 * 			|for(Position position: neighbours){
	 * 			|		HashSet<Entity> ents = this.getBoard().getEntityOnPosition(position);
	 * 			|		if (ents != null)
	 * 			|			for (Entity ent : ents)
	 * 			|				ent.damage();
	 * 			|}
	 * 			
	 */
	@Override
	protected void damage(){
		ArrayList<Position> neighbours = this.getPosition().getNeighbours(this.getBoard());
		for(Position position: neighbours){
			HashSet<Entity> ents = this.getBoard().getEntityOnPosition(position);
			if (ents != null)
				for (Entity ent : ents)
					ent.damage();
		}
		this.destroy();
	}	

}