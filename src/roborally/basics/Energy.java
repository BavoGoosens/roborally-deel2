package roborally.basics;

import be.kuleuven.cs.som.annotate.Basic;

/**
 * Deze klasse stelt een hoeveelheid energie voor.
 * 
 * @invar	De energie mag niet negatief zijn.
 * 			|this.getEnergy() >= 0
 * 
 * @author 	Bavo Goosens (1e bachelor informatica, r0297884), Samuel Debruyn (1e bachelor informatica, r0305472)
 * 
 * @version 1.0
 */
public class Energy {
	
	/**
	 * Hoeveelheid energie opgeslagen in dit object.
	 */
	private double amount;
	
	/**
	 * Deze methode maakt een nieuwe hoeveelheid energie aan.
	 * 
	 * @param 	amount
	 * 			De hoeveelheid energie
	 * 
	 * @pre		De hoeveelheid mag niet negatief zijn.
	 * 			|amount >= 0
	 * 
	 * @post	De nieuwe hoeveelheid energie moet gelijk zijn aan de gegeven hoeveelheid energie.
	 * 			|new.getEnergy() == amount
	 */
	public Energy(double amount){
		this.setEnergy(amount);
	}
	
	/**
	 * Deze methode geeft de huidige hoeveelheid energie terug.
	 * 
	 * @return	De huidige hoeveelheid energie.
	 * 			|this.amount
	 */
	@Basic
	public double getEnergy(){
		return this.amount;
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
	 * @post	De nieuwe hoeveelheid energie is gelijk aan die in de parameter.
	 * 			|new.amount == amount
	 */
	public void setEnergy(double amount){
		this.amount = amount;
	}
	
	/**
	 * Deze methode geeft de som van 2 hoeveelheden energie.
	 * 
	 * @param 	e1
	 * 			Eerste hoeveelheid energie.
	 * 
	 * @param 	e2
	 * 			Eerste hoeveelheid energie.
	 * 
	 * @return	De som van de 2 hoeveelheden energie.
	 * 			|new Energy(e1.getEnergy() + e2.getEnergy())
	 */
	public static Energy energySum(Energy e1, Energy e2){
		return new Energy(e1.getEnergy() + e2.getEnergy());
	}
	
	/**
	 * Deze methode geeft het verschil van 2 hoeveelheden energie.
	 * 
	 * @param	e1
	 * 			De hoeveelheid energie waarvan vertrokken wordt.
	 * 
	 * @param 	e2
	 * 			De hoeveelheid energie die van e1 afgetrokken wordt.
	 * 
	 * @return	Het verschil van de 2 hoeveelheden energie. Dit is 0 indien de uitkomst negatief zou moeten zijn.
	 * 			|if((e1.getEnergy() - e2.getEnergy()) < 0)
	 * 			|	new Energy(0)
	 * 			|new Energy(e1.getEnergy() - e2.getEnergy())
	 */
	public static Energy energyDifference(Energy e1, Energy e2){
		if((e1.getEnergy() - e2.getEnergy()) < 0)
			return new Energy(0);
		return new Energy(e1.getEnergy() - e2.getEnergy());
	}
	
}