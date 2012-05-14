package roborally.basics;

/**
 * Een enumeratie met alle mogelijke eenheden voor een hoeveelheid energie. Momenteel Watt-seconde, Joule en KiloJoule.
 * 
 * @author 	Bavo Goosens (1e bachelor informatica, r0297884), Samuel Debruyn (1e bachelor informatica, r0305472)
 * 
 * @version 1.0
 */
public enum EnergyUnit{
	
	/**
	 * Watt-seconde, de standaardeenheid.
	 */
	WS(1), /**
	 * Joule, gelijk aan 1 x de standaardeenheid.
	 */
	J(1), /**
	 * KiloJoule, gelijk aan 1000 x de standaardeenheid.
	 */
	KJ(1000);
	
	/**
	 * In deze integer wordt bijgehouden wat de factor is van de huidige eenheid ten opzichte van de standaardeenheid.
	 */
	private final double factor;
	
	/**
	 * Met deze constructor wordt een nieuwe energie-eenheid aangemaakt.
	 * 
	 * @param	factor
	 * 			De factor ten opzichte van de standaardeenheid.
	 * 
	 * @post	De factor ten opzichte van de standaardeenheid is nu gelijk aan de gegeven parameter.
	 * 			|new.factor == factor
	 */
	EnergyUnit(double factor){
		this.factor = factor;
	}
	
	/**
	 * Deze methode geeft de factor terug van de huidige energie-eenheid ten opzichte van de standaardeenheid.
	 * 
	 * @return	De factor ten opzichte van de standaardeenheid.
	 * 			|this.factor
	 */
	public double getFactor(){
		return this.factor;
	}
}
