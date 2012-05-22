package roborally.util;

import roborally.property.Energy;

/**
 * Deze Exception beschrijft dat een robot onvoldoende energie heeft om een actie uit te voeren.
 * 
 * @author 	Bavo Goosens (1e bachelor informatica, r0297884), Samuel Debruyn (1e bachelor informatica, r0305472)
 * 
 * @version 1.0
 */
public class NotEnoughEnergyException extends IllegalStateException {

	/**
	 * Deze constructor dient om het bericht in te stellen bij gegeven energie.
	 * 
	 * @param	energy
	 * 			De huidige energie van de robot.
	 * 
	 * @post	Er is een nieuw bericht ingesteld voor deze Exception met de gegeven coördinaten.
	 * 			|new.getMessage().equals("De robot heeft onvoldoende energie om deze actie uit te voeren. De robot heeft slechts " + energy.toString() + " energie.")
	 */
	public NotEnoughEnergyException(Energy energy) {
		super("De robot heeft onvoldoende energie om deze actie uit te voeren. De robot heeft slechts " + energy.toString() + " energie.");
	}

}