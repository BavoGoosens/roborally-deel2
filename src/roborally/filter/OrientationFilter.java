package roborally.filter;

import roborally.property.Orientation;

public class OrientationFilter extends Filter {
	
	Orientation base;

	public OrientationFilter(int filterMethod, Orientation value) {
		super(filterMethod, Orientation.class, value);
		base = (Orientation) value;
	}

	@Override
	public boolean evaluateObject(Object object) {
		Orientation other = (Orientation) object;
		if(filterMethod < 0 && base.compareTo(other) < 0)
			return true;
		if(filterMethod == 0 && base.compareTo(other) == 0)
			return true;
		if(filterMethod > 0 && base.compareTo(other) > 0)
			return true;
		return false;	
	}
	
}