package roborally.filter;

import roborally.property.Energy;

public class EnergyFilter extends Filter {
	
	Energy base;
	
	public EnergyFilter(int filterMethod, Energy value) {
		super(filterMethod, value);
		base = (Energy) value;
	}

	@Override
	public boolean evaluateObject(Object object) {
		Energy other = (Energy) object;
		if(filterMethod < 0 && base.compareTo(other) < 0)
			return true;
		if(filterMethod == 0 && base.compareTo(other) == 0)
			return true;
		if(filterMethod > 0 && base.compareTo(other) > 0)
			return true;
		return false;		
	}

}