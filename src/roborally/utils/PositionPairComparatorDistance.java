package roborally.utils;

import java.util.Comparator;

public class PositionPairComparatorDistance implements Comparator<PositionPair>{

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