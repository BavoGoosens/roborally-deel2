package roborally.model;

import roborally.basics.Energy;
import roborally.basics.Weight;

public class RepairKit extends Item{
	
	public final static Energy MAX_ENERGY = new Energy(Double.MAX_VALUE);
	
	public RepairKit(Energy energy, Weight weight){
		super(energy,weight);
	}

}
