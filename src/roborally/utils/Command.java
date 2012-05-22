package roborally.utils;

import java.util.ArrayList;

public class Command {
	
	static final String[] ALLBASIC = {"move","turn","shoot","pick-up-and-use"};

	static final String[] ALLCONDITIONS = {"true","energy-at-least", "at-item","can-hit-robot", "wall"};
	
	static final String[] ALLSPECIAL = {"seq","while","if"};
}
