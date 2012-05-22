package roborally.utils;

import java.util.Comparator;

import roborally.model.Battery;
import roborally.model.Item;

/**
 * Een comparator om voorwerpen te vergelijken op basis van gewicht.
 * 
 * @author 	Bavo Goosens (1e bachelor informatica, r0297884), Samuel Debruyn (1e bachelor informatica, r0305472)
 * 
 * @version 1.0
 */
public class ItemComparator implements Comparator<Item>{
	
	/**
	 * Methode die de gewichten van 2 batterijen vergelijkt.
	 * 
	 * @param	firstItem
	 * 			Eerste voorwerp om te vergelijken
	 * 
	 * @param	secondItem
	 * 			Tweede voorwerp om te vergelijken
	 * 
	 * @return	|if(firstItem.getWeight().getWeight() > secondItem.getWeight().getWeight()){
	 * 			|	1
	 * 			|}else if(firstItem.getWeight().getWeight() < secondItem.getWeight().getWeight()){
	 * 			|	-1
	 * 			|}else{
	 * 			|	0
	 * 			|}
	 */
	@Override
	public int compare(Item firstItem, Item secondItem) {
		int w1 = firstItem.getWeight().getWeight();
		int w2 = secondItem.getWeight().getWeight();
		
		if(w1 > w2){
			return 1;
		}else if(w1 < w2){
			return -1;
		}else{
			return 0;
		}
	}	

}
