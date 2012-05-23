package roborally.filter;

import roborally.property.Weight;

public class WeightFilter extends Filter {
	
	Weight base;

	public WeightFilter(int filterMethod, Weight value) {
		super(filterMethod, value);
		base = (Weight) value;
	}

	@Override
	public boolean evaluateObject(Object object) {
		Weight other = (Weight) object;
		if(filterMethod < 0 && base.compareTo(other) < 0)
			return true;
		if(filterMethod == 0 && base.compareTo(other) == 0)
			return true;
		if(filterMethod > 0 && base.compareTo(other) > 0)
			return true;
		return false;	
	}
	
}