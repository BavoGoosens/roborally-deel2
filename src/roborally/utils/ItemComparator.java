package roborally.utils;

import java.util.Comparator;

import roborally.model.Battery;
import roborally.model.Item;

/**
 * Een comparator om batterijen te vergelijken op basis van gewicht.
 * 
 * @author 	Bavo Goosens (1e bachelor informatica, r0297884), Samuel Debruyn (1e bachelor informatica, r0305472)
 * 
 * @version 1.0
 */
public class ItemComparator implements Comparator<Item>{
	
	/**
	 * Methode die de gewichten van 2 batterijen vergelijkt.
	 * 
	 * @param	b1
	 * 			Eerste batterij om te vergelijken
	 * 
	 * @param	b2
	 * 			Tweede batterij om te vergelijken
	 * 
	 * @return	|if(b1.getWeight().getWeight() > b2.getWeight().getWeight()){
	 * 			|	1
	 * 			|}else if(b1.getWeight().getWeight() < b2.getWeight().getWeight()){
	 * 			|	-1
	 * 			|}else{
	 * 			|	0
	 * 			|}
	 */
	@Override
	public int compare(Item b1, Item b2) {
		int w1 = b1.getWeight().getWeight();
		int w2 = b2.getWeight().getWeight();
		
		if(w1 > w2){
			return 1;
		}else if(w1 < w2){
			return -1;
		}else{
			return 0;
		}
	}	

}
