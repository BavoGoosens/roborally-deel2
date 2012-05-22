package roborally.model;

import roborally.property.Energy;
import roborally.property.Weight;

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
public class Battery extends Item{
	
	/**
	 * Maximale energie die een batterij kan hebben.
	 */
	public final static Energy MAXBATTERYENERGY = new Energy(5000);
	/**
	 * Minimale energie die een batterij kan hebben.
	 */
	public final static Energy MINBATTERYENERGY = new Energy(0);
	/** 
	 * De hoeveelheid energie die een battery bijkrijgt wanneer hij geraakt wordt.
	 */
	private final static Energy HIT_ENERGY = new Energy(500);

	
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
		super(energy, weight);
	}
	
	/**
	 * 
	 */
	//TODO: doc
	@Override
	protected void damage(){
		Energy newEnergy = new Energy(super.getEnergy().getEnergy() + HIT_ENERGY.getEnergy());
		if (newEnergy.getEnergy() > MAXBATTERYENERGY.getEnergy())
			super.setEnergy(MAXBATTERYENERGY);
		super.setEnergy(newEnergy);
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
}