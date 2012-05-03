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
	public final static Energy MAXBATTERYENERGY = new Energy(5000);
	/**
	 * Minimale energie die een batterij kan hebben.
	 */
	public final static Energy MINBATTERYENERGY = new Energy(0);
	/**
	 * Energie in de batterij.
	 */
	private Energy energy;
	/**
	 * Gewicht van de batterij.
	 */
	private final Weight weight;
	
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
	 * 
	 * @post	De energie van de nieuwe batterij is gelijk aan de gegeven energie.
	 * 			|new.getEnergy() == energy
	 * 
	 * @post	Het gewicht van de nieuwe batterij is gelijk aan het gegeven gewicht.
	 * 			|new.getWeight() == weight
	 */
	public Battery(Energy energy, Weight weight){
		this.weight = weight;
		this.setEnergy(energy);
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
	 * Geeft terug of de hoeveelheid energie een geldige hoeveelheid is voor deze batterij.
	 * 
	 * @param	energy
	 * 			De na te kijken hoeveelheid energie.
	 * 
	 * @return	Boolean met het resultaat van de controle.
	 * 			|energyAmount >= MINBATTERYENERGY && energyAmount <= MAXBATTERYENERGY
	 */
	public static boolean isValidBatteryEnergyAmount(Energy energy){
		return (energy.getEnergy() >= MINBATTERYENERGY.getEnergy() && energy.getEnergy() <= MAXBATTERYENERGY.getEnergy());
	}

	/**
	 * Deze methode wijzigt de energie van de batterij.
	 * 
	 * @param	energy
	 * 			De nieuwe energie die de batterij moet krijgen.
	 * 
	 * @post	De batterij heeft nu de nieuwe hoeveelheid energie.
	 * 			|new.getEnergy().equals(energy) == true
	 */
	public void setEnergy(Energy energy) {
		this.energy = energy;
		
	}
}