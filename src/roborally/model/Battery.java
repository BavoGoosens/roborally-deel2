package roborally.model;

import be.kuleuven.cs.som.annotate.Basic;
import roborally.property.Energy;
import roborally.property.Weight;

/**
 * Deze klasse houdt een batterij bij. Deze heeft een positie, een gewicht en een hoeveelheid energie. Deze kan door een robot gedragen worden.
 * 
 * @invar	De hoeveelheid energie van een batterij moet altijd geldig zijn.
 * 			|isValidBatteryEnergyAmount(getEnergy())
 * 
 * @author 	Bavo Goosens (1e bachelor informatica, r0297884), Samuel Debruyn (1e bachelor informatica, r0305472)
 * 
 * @version 2.1
 */
public class Battery extends Item{
	
	/**
	 * Maakt een nieuwe batterij aan.
	 * 
	 * @param	energy
	 * 			Energie van de nieuwe batterij.
	 * 
	 * @param	weight
	 * 			Massa van de batterij.
	 * 
	 * @post	De energie van de nieuwe batterij is gelijk aan de gegeven energie.
	 * 			|new.getEnergy().equals(energy)
	 * 
	 * @post	Het gewicht van de nieuwe batterij is gelijk aan het gegeven gewicht.
	 * 			|new.getWeight().equals(weight)
	 */
	public Battery(Energy energy, Weight weight){
		super(weight);
		setEnergy(energy);
	}
		
	/**
	 * Deze methode wijzigt de energie van de batterij.
	 * 
	 * @param	energy
	 * 			De nieuwe energie van het item.
	 * 
	 * @pre		Energie moet geldige hoeveelheid zijn.
	 * 			|isValidBatteryEnergy(energy)
	 * 
	 * @post	De energie van het item is gelijk aan de gegeven energie.
	 * 			|new.getEnergy().equals(energy)
	 */
	public void setEnergy(Energy energy) {
		this.energy = energy;
	}
	
	/**
	 * Geeft de energie van de batterij.
	 * 
	 * @return	Energie van de batterij.
	 * 			|energy
	 */
	@Basic
	public Energy getEnergy() {
		return energy;
	}
	
	/**
	 * Energie in de batterij.
	 */
	private Energy energy;
	
	/**
	 * Deze methode kijkt na of de gegeven energie geldig is voor een batterij.
	 * 
	 * @param	energy
	 * 			De energie die nagekeken moet worden.
	 * 
	 * @return	Een boolean die true is als de energie geldig is.
	 * 			|if(!Energy.isValidEnergyAmount(energy.getEnergy()))
	 * 			|	false
	 * 			|(energy.getEnergy() <= MAX_ENERGY.getEnergy())
	 */
	public static boolean isValidBatteryEnergy(Energy energy){
		if(!Energy.isValidEnergyAmount(energy.getEnergy()))
				return false;
		return (energy.getEnergy() <= MAX_ENERGY.getEnergy());
	}
	
	/**
	 * De maximale energie van een batterij.
	 */
	public final static Energy MAX_ENERGY = new Energy(5000);
	
	/**
	 * Deze methode wordt opgeroepen wanneer de batterij geraakt wordt door een laser of een surprise box.
	 * 
	 * @post	De nieuwe energie van de batterij is gelijk aan het maximum of aan 500 meer dan wat hij ervoor had.
	 * 			|new.getEnergy().equals(MAX_ENERGY) || new.getEnergy().equals(Energy.energySum(getEnergy(), HIT_ENERGY))
	 */
	@Override
	protected void damage(){
		Energy newEnergy = Energy.energySum(getEnergy(), HIT_ENERGY);
		if (newEnergy.getEnergy() > MAX_ENERGY.getEnergy())
			setEnergy(MAX_ENERGY);
		setEnergy(newEnergy);
	}
	
	/** 
	 * De hoeveelheid energie die een battery bij krijgt wanneer hij geraakt wordt.
	 */
	private final static Energy HIT_ENERGY = new Energy(500);

	/*
	 * Deze methode zet het object om naar een String.
	 * 
	 * @return	Een textuele representatie van dit object waarbij duidelijk wordt wat de eigenschappen van dit object zijn.
	 * 			|super.toString() + ", energie: " + getEnergy().toString()
	 * 
	 * @see		roborally.model.Item#toString()
	 */
	@Override
	public String toString() {
		return super.toString() + ", energie: " + getEnergy().toString();
	}
	
}