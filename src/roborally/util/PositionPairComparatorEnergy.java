package roborally.util;

import java.util.Comparator;
/**
 * Klasse met een speciale comperator in om positieparen te kunnen sorteren op de totale energe kost.
 * 
 * @author 	Bavo Goosens (1e bachelor informatica, r0297884), Samuel Debruyn (1e bachelor informatica, r0305472)
 *
 * @version 1.0
 *  
 */
public class PositionPairComparatorEnergy implements Comparator<PositionPair>{

	/**
	 * Methode die een integer teruggeeft die aangeeft of positionpair1 zijn totale kost groter(1), kleiner(-1) of gelijk(0) is dan een tweede positiepaar.
	 * 
	 * @return 	int result 
	 * 			|if(pp1.getTotalCost().getEnergy() > pp2.getTotalCost().getEnergy()){
	 *				result ==  1;
	 *			}else if(pp1.getTotalCost().getEnergy() < pp2.getTotalCost().getEnergy()){
	 *				result == -1;
	 *			}else{
	 *				result ==  0;
	 *	}
	 */
	@Override
	public int compare(PositionPair pp1, PositionPair pp2) {
		if(pp1.getTotalCost().getEnergy() > pp2.getTotalCost().getEnergy()){
			return 1;
		}else if(pp1.getTotalCost().getEnergy() < pp2.getTotalCost().getEnergy()){
			return -1;
		}else{
			return 0;
		}
	}

}