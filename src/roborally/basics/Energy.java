package roborally.basics;

import be.kuleuven.cs.som.annotate.Value;

/**
 * Deze klasse stelt een hoeveelheid energie voor.
 * 
 * @author 	Bavo Goosens (1e bachelor informatica, r0297884), Samuel Debruyn (1e bachelor informatica, r0305472)
 * 
 * @version 1.0
 */
public class Energy {
	
	public final static double MOVE_COST = 500;
	public final static double TURN_COST = 100;
	public final static double MINENERGY = 0;
	public final static double MAXENERGY = 20000;
	private double amount;
	
	/**
	 * Deze enumeratie stelt alle energie-eenheden voor.
	 * 
	 * @author 	Bavo Goosens (1e bachelor informatica, r0297884), Samuel Debruyn (1e bachelor informatica, r0305472)
	 *
	 * @version	1.0
	 */
	@Value
	public enum eUnit{
		WS(1);
		
		private final double factor;
		
		/**
		 * Maakt een energie-eenheid aan met de factor ten opzichte van de standaardeenheid (hier wordt Ws als standaard gebruikt).
		 * 
		 * @param 	double factor
		 * 			De factor ten opzichte van Ws.
		 * 
		 * @post	|new.factor() == factor
		 */
		eUnit(double factor){
			this.factor = factor;
		}
		
		/**
		 * Deze methode geeft de factor terug ten opzichte van Ws.
		 * 
		 * @return	factor
		 * 			|this.factor
		 */
		private double factor() { return factor; }
	}
	
	/**
	 * Deze methode maakt een nieuwe hoeveelheid energie aan.
	 * 
	 * @param 	double amount
	 * 			De hoeveelheid energie
	 * 
	 * @param	eUnit unit
	 * 			De eenheid van de opgegeven energie
	 * 
	 * @pre		De energie is een geldige hoeveelheid.
	 * 			|isValidEnergy(double amount, eUnit unit)
	 */
	public Energy(double amount, eUnit unit){
		this.setEnergy(amount, unit);
	}
	
	/**
	 * Deze methode geeft de huidige hoeveelheid energie terug in de gekozen eenheid.
	 * 
	 * @param 	eUnit unit
	 * 			Eenheid waarin de energie gegeven moet worden.
	 * 
	 * @return	new.amount*unit.factor()
	 */
	public double getAmount(eUnit unit) {
		return amount * unit.factor();
	}
	
	/**
	 * Deze methode wijzigt de energie naar de gegeven waarde in de gegeven eenheid.
	 * 
	 * @param 	double amount
	 * 			De hoeveelheid energie
	 * 
	 * @param	eUnit unit
	 * 			De eenheid van de te controleren hoeveelheid energie
	 * 
	 * @post	new.amount == amount * unit.factor()
	 */
	public void setEnergy(double amount, eUnit unit) {
		this.amount = amount * unit.factor();
	}
	
	/**
	 * Methode die controleert of de opgegeven hoeveelheid energie een geldige hoeveelheid is.
	 * 
	 * @param 	double amount
	 * 			De hoeveelheid energie.
	 * 
	 * @param	eUnit unit
	 * 			De eenheid van de te controleren hoeveelheid energie
	 * 
	 * @return	|(amount*unit.factor() >= MINENERGY) && (amount*unit.factor() <= MAXENERGY)
	 */
	public boolean isValidEnergyAmount(double amount, eUnit unit){
		return (amount*unit.factor() >= MINENERGY) && (amount*unit.factor() <= MAXENERGY);
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
	 * 			Som van de gegeven hoeveelheden.
	 */
	public static Energy energySum(Energy e1, Energy e2){
		return new Energy(e1.getAmount(eUnit.WS) + e2.getAmount(eUnit.WS), eUnit.WS);
	}
	
}