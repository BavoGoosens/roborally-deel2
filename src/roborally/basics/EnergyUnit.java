package roborally.basics;

/**
 * Een enumeratie met alle mogelijke eenheiden voor een hoeveelheid energie. Momenteel Watt-seconde, Joule en KiloJoule.
 * 
 * @author 	Bavo Goosens (1e bachelor informatica, r0297884), Samuel Debruyn (1e bachelor informatica, r0305472)
 * 
 * @version 1.0
 */
public enum EnergyUnit{
	
	WS(1), J(1), KJ(1000);
	
	private final int factor;
	
	EnergyUnit(int factor){
		this.factor = factor;
	}
}
