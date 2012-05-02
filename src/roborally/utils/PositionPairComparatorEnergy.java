package roborally.utils;

import java.util.Comparator;

public class PositionPairComparatorEnergy implements Comparator<PositionPair>{

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