package roborally.model;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Raw;
import roborally.basics.Energy;
import roborally.basics.Weight;

public class Item extends Entity{

	/**
	 * Energie in het item.
	 */
	private Energy energy;
	
	/**
	 * Gewicht van het item.
	 */
	private final Weight weight;
	
	public Item (Energy energy , Weight weight){
		this.setEnergy(energy);
		this.weight = weight;
	}

	public void setEnergy(Energy energy) {
		this.energy = energy;
	}
	
	/**
	 * Geeft de energie van het item.
	 * 
	 * @return	Energie van het item.
	 * 			|this.energy
	 */
	@Basic @Raw
	public Energy getEnergy() {
		return this.energy;
	}
	
	/**
	 * Geeft het gewicht terug van het item.
	 * 
	 * @return	Het gewicht van het item.
	 * 			|this.weight
	 */
	@Basic @Raw
	public Weight getWeight() {
		return this.weight;
	}
}
