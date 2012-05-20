package roborally.model;

import roborally.property.Energy;
import roborally.property.Weight;

public class RepairKit extends Item{
	
	public final static Energy MAX_ENERGY = new Energy(Double.MAX_VALUE);
	
	public RepairKit(Energy energy, Weight weight){
		super(energy,weight);
	}

}
