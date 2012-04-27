package roborally.model;

import be.kuleuven.cs.som.annotate.Basic;
import roborally.basics.Energy;
import roborally.basics.Weight;

/**
 * Deze klasse houdt een batterij bij. Deze heeft een positie en een hoeveelheid energie.
 * 
 * @invar	De hoeveelheid energie van een batterij moet altijd geldig zijn.
 * 			|isValidBatteryEnergyAmount(getEnergy())
 * 
 * @author 	Bavo Goosens (1e bachelor informatica, r0297884), Samuel Debruyn (1e bachelor informatica, r0305472)
 * 
 * @version 1.1
 */
public class Battery extends Entity{
	
	/**
	 * Maximale energie die een batterij kan hebben.
	 */
	public final static int MAXBATTERYENERGY = 5000;
	
	/**
	 * Minimale energie die een batterij kan hebben.
	 */
	public final static int MINBATTERYENERGY = 0;
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
	 * 
	 * @pre		Energie moet geldige hoeveelheid zijn.
	 * 			|isValidBatteryEnergyAmount(energy)
	 */
	public Battery(Energy energy, Weight weight){
		this.setEnergy(energy);
		this.setWeight(weight);
	}

	/**
	 * Geeft de energie van de batterij.
	 * 
	 * @return	Energie van de batterij.
	 * 			|this.energy
	 */
	@Basic
	public Energy getEnergy() {
		return this.energy;
	}

	/**
	 * Wijzigt de energie van de batterij.
	 * 
	 * @param	energy
	 * 			Nieuwe energie van de batterij.
	 * 
	 * @post	|new.energy == energy
	 */
	@Basic
	public void setEnergy(Energy energy) {
		this.energy = energy;
	}

	/**
	 * Geeft het gewicht terug van de batterij.
	 * 
	 * @return	Het gewicht van de batterij.
	 * 			|this.weight
	 */
	@Basic
	public Weight getWeight() {
		return this.weight;
	}

	/**
	 * Wijzigt het gewicht van de batterij.
	 * 
	 * @param	weight
	 * 			Het nieuwe gewicht van de batterij.
	 */
	@Basic
	public void setWeight(Weight weight) {
		this.weight = weight;
	}
	
	/**
	 * Geeft terug of de hoeveelheid energie een geldige hoeveelheid is voor deze batterij.
	 * 
	 * @param	energy
	 * 			De na te kijken hoeveelheid energie.
	 * 
	 * @return	Boolean met het resultaat van de controle.
	 * 			|energyAmount >= MINBATTERYENERGY && energyAmount <= MAXBATTERYENERGY
	 */
	public static boolean isValidBatteryEnergyAmount(Energy energy){
		return (energy.getEnergy() >= MINBATTERYENERGY && energy.getEnergy() <= MAXBATTERYENERGY);
	}
	
}
