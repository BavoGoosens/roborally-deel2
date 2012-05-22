package roborally.util;

import java.util.Comparator;

/**
 * Klasse met een speciale comperator in om te kunnen sorteren op de manhattanDistance van een positiepaar.
 * 
 * @author 	Bavo Goosens (1e bachelor informatica, r0297884), Samuel Debruyn (1e bachelor informatica, r0305472)
 *
 * @version 1.0
 *  
 */
public class PositionPairComparatorDistance implements Comparator<PositionPair>{

	/**
	 * Methode die een integer teruggeeft die aangeeft of positionpair1 zijn manahattandistance groter(1), kleiner(-1) of gelijk(0) is dan een tweed positiepaar.
	 * 
	 * @return 	int result 
	 * 			|if(pp1.getManhattanDistance() > pp2.getManhattanDistance()){
	 *				result ==  1;
	 *			}else if(pp1.getManhattanDistance() < pp2.getManhattanDistance()){
	 *				result == -1;
	 *			}else{
	 *				result ==  0;
	 *	}
	 */
	@Override
	public int compare(PositionPair pp1, PositionPair pp2) {
		if(pp1.getManhattanDistance() > pp2.getManhattanDistance()){
			return 1;
		}else if(pp1.getManhattanDistance() < pp2.getManhattanDistance()){
			return -1;
		}else{
			return 0;
		}
	}

}