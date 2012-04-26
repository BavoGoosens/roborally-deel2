package roborally.basics;

import be.kuleuven.cs.som.annotate.Basic;

/**
 * Deze klasse stelt een hoeveelheid energie voor.
 * 
 * @invar	De energie mag niet negatief zijn.
 * 
 * @author 	Bavo Goosens (1e bachelor informatica, r0297884), Samuel Debruyn (1e bachelor informatica, r0305472)
 * 
 * @version 1.0
 */
public class Energy {
	
	/**
	 * Hoeveelheid energie opgeslagen in dit object.
	 */
	private int amount;
	
	/**
	 * Deze methode maakt een nieuwe hoeveelheid energie aan.
	 * 
	 * @param 	amount
	 * 			De hoeveelheid energie
	 * 
	 * @pre		De hoeveelheid mag niet negatief zijn.
	 * 			|amount >= 0
	 */
	public Energy(int amount){
		this.setEnergy(amount);
	}
	
	/**
	 * Deze methode geeft de huidige hoeveelheid energie terug.
	 * 
	 * @return	this.amount
	 */
	@Basic
	public int getEnergy(){
		return amount;
	}
	
	/**
	 * Deze methode wijzigt de energie naar de gegeven waarde.
	 * 
	 * @param 	amount
	 * 			De hoeveelheid energie
	 * 
	 * @pre		De energie is niet negatief.
	 * 			|amount >= 0
	 * 
	 * @post	new.amount == amount
	 */
	public void setEnergy(int amount){
		this.amount = amount;
	}
	
	/**
	 * Deze methode geeft de som van 2 hoeveelheden energie met hun eenheid.
	 * 
	 * @param 	e1
	 * 			Eerste hoeveelheid energie.
	 * 
	 * @param 	e2
	 * 			Eerste hoeveelheid energie.
	 * 
	 * @return	Energy
	 * 			|new Energy(e1.getEnergy() + e2.getEnergy())
	 */
	public static Energy energySum(Energy e1, Energy e2){
		return new Energy(e1.getEnergy() + e2.getEnergy());
	}
	
}