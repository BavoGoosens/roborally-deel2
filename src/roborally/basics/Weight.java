package roborally.basics;

import be.kuleuven.cs.som.annotate.Basic;

/**
 * Deze klasse stelt een massa voor.
 * 
 * @author 	Bavo Goosens (1e bachelor informatica, r0297884), Samuel Debruyn (1e bachelor informatica, r0305472)
 * 
 * @version 1.0
 */
public class Weight {

	public final static int MAXWEIGHT = Integer.MAX_VALUE;
	public final static int MINWEIGHT = 0;
	private int weight;

	/**
	 * Deze methode maakt een nieuwe massa aan.
	 * 
	 * @param	int newWeight
	 * 			De opgegeven massa.
	 * 
	 * @post	|if(newWeight < MINWEIGHT)
	 * 			|	new.weight == MINWEIGHT
	 * 			|if(newWeight > MAXWEIGHT)
	 * 			|	new.weight == MAXWEIGHT
	 * 			|new.weight == newWeight
	 */
	public Weight(int newWeight){
		this.setWeight(newWeight);
	}

	/**
	 * Geeft het gewicht terug.
	 * 
	 * @return 	int weight
	 * 			|new.weight
	 */
	@Basic
	public int getWeight() {
		return weight;
	}
	
	/**
	 * Deze methode wijzigt de massa.
	 * 
	 * @param	int newWeight
	 * 			De nieuwe massa.
	 * 
	 * @post	|if(newWeight < MINWEIGHT)
	 * 			|	new.weight == MINWEIGHT
	 * 			|if(newWeight > MAXWEIGHT)
	 * 			|	new.weight == MAXWEIGHT
	 * 			|new.weight == newWeight
	 */
	@Basic
	public void setWeight(int newWeight){
		if(newWeight < MINWEIGHT)
			newWeight = MINWEIGHT;
		if(newWeight > MAXWEIGHT)
			newWeight = MAXWEIGHT;
		weight = newWeight;
	}

}
