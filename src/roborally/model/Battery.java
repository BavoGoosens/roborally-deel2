package roborally.model;

import be.kuleuven.cs.som.annotate.Basic;
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
	
	/**
	 * Maximale energie die een batterij kan hebben.
	 */
	public final static Energy MAXBATTERYENERGY = new Energy(5000);
	/**
	 * Positie van de batterij (niet noodzakelijk).
	 */
	private Position position;
	/**
	 * Energie in de batterij.
	 */
	private Energy energy;
	/**
	 * Gewicht van de batterij.
	 */
	private Weight weight;
	
	/**
	 * Maakt een nieuwe batterij aan.
	 * 
	 * @param	energy
	 * 			Energie van de nieuwe batterij.
	 * 
	 * @param	weight
	 * 			Massa van de batterij.
	 */
	public Battery(Energy energy, Weight weight){
		
	}

	/**
	 * Geeft de positie van de batterij terug.
	 * 
	 * @return	De positie van de batterij.
	 * 			|new.position
	 */
	@Basic
	public Position getPosition() {
		return position;
	}

	/**
	 * Wijzigt de positie van de robot naar de nieuwe positie.
	 * 
	 * @param	position
	 * 			De nieuwe positie.
	 */
	@Basic
	public void setPosition(Position position) {
		this.position = position;
	}

	/**
	 * Geeft de energie van de batterij.
	 * 
	 * @return	Energie van de batterij.
	 * 			|new.energy
	 */
	@Basic
	public Energy getEnergy() {
		return energy;
	}

	/**
	 * Wijzigt de energie van de batterij.
	 * 
	 * @param	energy
	 * 			
	 */
	@Basic
	public void setEnergy(Energy energy) {
		this.energy = energy;
	}

	/**
	 * @return the weight
	 */
	@Basic
	public Weight getWeight() {
		return weight;
	}

	/**
	 * @param weight the weight to set
	 */
	@Basic
	public void setWeight(Weight weight) {
		this.weight = weight;
	}
	
}
