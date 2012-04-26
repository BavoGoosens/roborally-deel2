package roborally.basics;

import be.kuleuven.cs.som.annotate.Basic;

/**
 * Deze klasse stelt een gewicht voor.
 * 
 * @author 	Bavo Goosens (1e bachelor informatica, r0297884), Samuel Debruyn (1e bachelor informatica, r0305472)
 * 
 * @version 1.0
 */
public class Weight {

	/**
	 * Het maximale gewicht van een object.
	 */
	public final static int MAXWEIGHT = Integer.MAX_VALUE;
	/**
	 * Het minimale gewicht van een object.
	 */
	public final static int MINWEIGHT = 0;
	/**
	 * Gewicht van dit object.
	 */
	private int weight;

	/**
	 * Deze methode maakt een nieuwe massa aan.
	 * 
	 * @param	newWeight
	 * 			Het opgegeven gewicht.
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
	 * 			|this.weight
	 */
	@Basic
	public int getWeight() {
		return weight;
	}
	
	/**
	 * Deze methode wijzigt het gewicht.
	 * 
	 * @param	newWeight
	 * 			Het nieuwe gewicht.
	 * 
	 * @post	|if(newWeight < MINWEIGHT)
	 * 			|	new.weight == MINWEIGHT
	 * 			|if(newWeight > MAXWEIGHT)
	 * 			|	new.weight == MAXWEIGHT
	 * 			|new.weight == newWeight
	 */
	public void setWeight(int newWeight){
		if(newWeight < MINWEIGHT)
			newWeight = MINWEIGHT;
		if(newWeight > MAXWEIGHT)
			newWeight = MAXWEIGHT;
		weight = newWeight;
	}

}
