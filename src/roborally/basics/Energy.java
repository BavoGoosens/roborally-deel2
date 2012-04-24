package roborally.basics;

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
	public double amount;
	
	/**
	 * @return the amount
	 */
	public double getAmount() {
		return amount;
	}

	/**
	 * @param amount the amount to set
	 */
	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Energy(double amount){
		
	}
	
	public boolean isValidEnergy(double energy){
		return (energy >= MINENERGY) && (energy <= MAXENERGY);
	}
}