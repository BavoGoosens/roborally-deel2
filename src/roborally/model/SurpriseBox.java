package roborally.model;

import java.util.ArrayList;
import java.util.HashSet;

import roborally.property.Energy;
import roborally.property.Position;
import roborally.property.Weight;
/**
 * 
 * @author bavo
 *
 */
public class SurpriseBox extends Item{

	/**
	 * 
	 * @param weight
	 */
	public SurpriseBox(Weight weight) {
		super(new Energy(0),weight);
	}

	/**
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

	@Override
	public String toString() {
		return "Position: " + this.getPosition().toString() + " Weight: " + this.getWeight().toString();
	}
	

}