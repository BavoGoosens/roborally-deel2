package roborally.model;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Model;

import roborally.property.Energy;
import roborally.property.Weight;

/**
 * Deze klasse houdt een object bij dat op een bord kan staan en een positie kan hebben. Daarnaast kan dit object ook door een robot gedragen worden en heeft het energie en een gewicht.
 * 
 * @author 	Bavo Goosens (1e bachelor informatica, r0297884), Samuel Debruyn (1e bachelor informatica, r0305472)
 * 
 * @version 1.0
 */
public abstract class Item extends Entity{

	/**
	 * Deze constructor maakt een nieuw item aan.
	 * 
	 * @param	energy
	 * 			De energie die het nieuwe item moet krijgen.
	 * 
	 * @param	weight
	 * 			Het gewicht dat het nieuwe item moet krijgen.
	 * 
	 * @post	Het nieuwe item heeft de gegeven energie.
	 * 			|new.getEnergy() == energy
	 * 
	 * @post	Het nieuwe item heeft het gegeven gewicht.
	 * 			|new.getWeight() == weight
	 */
	@Model
	protected Item (Energy energy , Weight weight){
		setEnergy(energy);
		this.weight = weight;
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
	protected void setEnergy(Energy energy) {
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
}
