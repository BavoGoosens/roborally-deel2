package roborally.basics;

import be.kuleuven.cs.som.annotate.Basic;

/**
 * Deze klasse stelt een hoeveelheid energie voor.
 * 
 * @author 	Bavo Goosens (1e bachelor informatica, r0297884), Samuel Debruyn (1e bachelor informatica, r0305472)
 * 
 * @version 1.0
 */
public class Energy {
	
	public final static int MOVE_COST = 500;
	public final static int TURN_COST = 100;
	public final static int MINENERGY = 0;
	public final static int MAXENERGY = 20000;
	private int amount;
	
	/**
	 * Deze methode maakt een nieuwe hoeveelheid energie aan.
	 * 
	 * @param 	int amount
	 * 			De hoeveelheid energie
	 * 
	 * @pre		De energie is een geldige hoeveelheid.
	 * 			|isValidEnergy(int amount)
	 */
	public Energy(int amount){
		this.setEnergy(amount);
	}
	
	/**
	 * Deze methode geeft de huidige hoeveelheid energie terug.
	 * 
	 * @return	new.amount
	 */
	@Basic
	public int getEnergy(){
		return amount;
	}
	
	/**
	 * Deze methode wijzigt de energie naar de gegeven waarde.
	 * 
	 * @param 	double amount
	 * 			De hoeveelheid energie
	 * 
	 * @pre		De energie is een geldige hoeveelheid.
	 * 			|isValidEnergy(int amount)
	 * 
	 * @post	new.amount == amount
	 */
	@Basic
	public void setEnergy(int amount){
		this.amount = amount;
	}
	
	/**
	 * Methode die controleert of de opgegeven hoeveelheid energie een geldige hoeveelheid is.
	 * 
	 * @param 	int amount
	 * 			De hoeveelheid energie.
	 *
	 * @return	boolean
	 * 			|(amount >= MINENERGY) && (amount <= MAXENERGY)
	 */
	public boolean isValidEnergyAmount(int amount){
		return (amount >= MINENERGY) && (amount <= MAXENERGY);
	}
	
	/**
	 * Deze methode geeft de som van 2 hoeveelheden energie met hun eenheid.
	 * 
	 * @param 	Energy e1
	 * 			Eerste hoeveelheid energie.
	 * 
	 * @param 	Energy e2
	 * 			Eerste hoeveelheid energie.
	 * 
	 * @return	Energy
	 * 			|new Energy(e1.getAmount(eUnit.WS) + e2.getAmount(eUnit.WS), eUnit.WS)
	 */
	public static Energy energySum(Energy e1, Energy e2){
		return new Energy(e1.getEnergy() + e2.getEnergy());
	}
	
}