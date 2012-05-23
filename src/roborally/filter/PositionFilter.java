package roborally.filter;

import roborally.property.Position;

public class PositionFilter extends Filter {
	
	Position base;

	public PositionFilter(int filterMethod, Position value) {
		super(filterMethod, value);
		base = (Position) value;
	}

	@Override
	public boolean evaluateObject(Object object) {
		Position other = (Position) object;
		if(filterMethod < 0 && base.compareTo(other) < 0)
			return true;
		if(filterMethod == 0 && base.compareTo(other) == 0)
			return true;
		if(filterMethod > 0 && base.compareTo(other) > 0)
			return true;
		return false;	
	}
	
}