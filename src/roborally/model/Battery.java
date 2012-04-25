package roborally.model;

import roborally.basics.Energy;
import roborally.basics.Position;
import roborally.basics.Weight;

/**
 * Deze klasse houdt een batterij bij. Deze heeft een positie en een hoeveelheid energie.
 * 
 * @author 	Bavo Goosens (1e bachelor informatica, r0297884), Samuel Debruyn (1e bachelor informatica, r0305472)
 * 
 * @version 1.0
 */
public class Battery {
	
	public final static Energy MAXBATTERYENERGY = new Energy(5000);
	private Position position;
	private Energy energy;
	private Weight weight;
	
	public Battery(Energy energy, Weight weight){
		
	}

	/**
	 * @return the position
	 */
	public Position getPosition() {
		return position;
	}

	/**
	 * @param position the position to set
	 */
	public void setPosition(Position position) {
		this.position = position;
	}

	/**
	 * @return the energy
	 */
	public Energy getEnergy() {
		return energy;
	}

	/**
	 * @param energy the energy to set
	 */
	public void setEnergy(Energy energy) {
		this.energy = energy;
	}

	/**
	 * @return the weight
	 */
	public Weight getWeight() {
		return weight;
	}

	/**
	 * @param weight the weight to set
	 */
	public void setWeight(Weight weight) {
		this.weight = weight;
	}
	
}
