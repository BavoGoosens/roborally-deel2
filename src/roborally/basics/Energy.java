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
	private eUnit unit;
	
	@Value
	public enum eUnit{
		WS(1);
		
		private final double factor;
		
		eUnit(double factor){
			this.factor = factor;
		}
		
		private double factor() { return factor; }
	}
	
	/**
	 * Deze methode maakt een nieuwe hoeveelheid energie aan.
	 * 
	 * 
	 * 
	 * @param 	double amount
	 * 			De hoeveelheid energie
	 */
	public Energy(double amount, eUnit unit){
		this.setEnergy(amount, unit);
	}
	
	/**
	 * 
	 * @return the amount
	 */
	public double getAmountInWs() {
		return amount;
	}

	/**
	 * @param amount the amount to set
	 */
	public void setEnergy(double amount, eUnit unit) {
		this.amount = amount * unit.factor();
	}
	
	public boolean isValidEnergyAmount(double energy){
		return (energy >= MINENERGY) && (energy <= MAXENERGY);
	}
}