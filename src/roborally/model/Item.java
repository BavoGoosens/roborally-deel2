package roborally.model;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Model;

import roborally.property.Energy;
import roborally.property.Weight;

/**
 * Deze klasse houdt een object bij dat op een bord kan staan en een positie en een gewicht kan hebben. Daarnaast kan dit object ook door een robot gedragen worden.
 * 
 * @author 	Bavo Goosens (1e bachelor informatica, r0297884), Samuel Debruyn (1e bachelor informatica, r0305472)
 * 
 * @version 1.0
 */
public abstract class Item extends Entity{

	/**
	 * Deze constructor maakt een nieuw item aan.
	 * 
	 * @param	weight
	 * 			Het gewicht dat het nieuwe item moet krijgen.
	 * 
	 * @post	Het nieuwe item heeft het gegeven gewicht.
	 * 			|new.getWeight() == weight
	 */
	@Model
	protected Item (Weight weight){
		this.weight = weight;
	}

	/**
	 * Geeft het gewicht terug van het item.
	 * 
	 * @return	Het gewicht van het item.
	 * 			|weight
	 */
	@Basic
	public Weight getWeight() {
		return weight;
	}
	
	/**
	 * Gewicht van het item.
	 */
	private final Weight weight;
	
	@Override
	public String toString() {
		return "Position: " + this.getPosition().toString() + " Energy: " + this.getEnergy().toString() + 
				" Weight: " + this.getWeight().toString();
	}
}
