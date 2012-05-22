package roborally.model;

import be.kuleuven.cs.som.annotate.Basic;
import roborally.property.Energy;
import roborally.property.Weight;

/**
 * Deze klasse houdt een repair kit bij dat op een bord kan staan. Dit kan een positie, een gewicht, energie en een positie kan hebben. Daarnaast kan dit object ook door een robot gedragen worden.
 * 
 * @author 	Bavo Goosens (1e bachelor informatica, r0297884), Samuel Debruyn (1e bachelor informatica, r0305472)
 * 
 * @version 1.0
 */
public class RepairKit extends Item{
	
	/**
	 * Deze constructor maakt een nieuwe repair kit aan met de gegeven energie en gewicht.
	 * 
	 * @param 	energy
	 * 			De energie die de repair kit moet krijgen.
	 * 
	 * @param	weight
	 * 			Het gewicht van de nieuwe repair kit.
	 * 
	 * @pre		De gegeven energie moet geldig zijn voor een repair kit.
	 * 			|isValidRepairKitEnergy(energy)
	 * 
	 * @post	De nieuwe energie moet gelijk zijn aan de gegeven energie.
	 * 			|new.getEnergy() == energy
	 * 
	 * @post	Het gewicht van de nieuwe repair kit moet gelijk zijn aan het gegeven gewicht.
	 * 			|new.getWeight == weight
	 */
	public RepairKit(Energy energy, Weight weight){
		super(weight);
		setEnergy(energy);
	}

	/**
	 * Deze methode wijzigt de energie van het item.
	 * 
	 * @param	energy
	 * 			De nieuwe energie van het item.
	 * 
	 * @post	De energie van het item is gelijk aan de gegeven energie.
	 * 			|new.getEnergy() == energy
	 */
	private void setEnergy(Energy energy) {
		this.energy = energy;
	}
	
	/**
	 * Geeft de energie van het item.
	 * 
	 * @return	Energie van het item.
	 * 			|energy
	 */
	@Basic
	public Energy getEnergy() {
		return energy;
	}
	
	/**
	 * Energie in het item.
	 */
	private Energy energy;
	
	/**
	 * Deze methode kijkt na of de gegeven energie geldig is voor een repair kit.
	 * 
	 * @param	energy
	 * 			De energie die nagekeken moet worden.
	 * 
	 * @return	Een boolean die true is als de energie geldig is.
	 * 			|(energy.getEnergy() <= MAX_ENERGY.getEnergy())
	 */
	public static boolean isValidRepairKitEnergy(Energy energy){
		return (energy.getEnergy() <= MAX_ENERGY.getEnergy());
	}
	
	/**
	 * De maximale energie van een repair kit.
	 */
	public final static Energy MAX_ENERGY = new Energy(Double.MAX_VALUE);

	@Override
	protected void damage(){
		Energy newEnergy = new Energy(getEnergy().getEnergy() + HIT_ENERGY.getEnergy());
		if (newEnergy.getEnergy() > MAX_ENERGY.getEnergy())
			setEnergy(MAX_ENERGY);
		setEnergy(newEnergy);
	}
	
	/**
	 * De energie die de repair kit bij krijgt wanneer hij geraakt wordt.
	 */
	public final static Energy HIT_ENERGY = new Energy(500);
	
}
