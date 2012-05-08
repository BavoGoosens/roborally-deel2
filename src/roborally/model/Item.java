package roborally.model;

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

	private void setEnergy(Energy energy) {
		this.energy = energy;
	}
}
