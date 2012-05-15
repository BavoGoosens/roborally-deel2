package roborally.basics;

import be.kuleuven.cs.som.annotate.Basic;

/**
 * Deze klasse stelt een gewicht voor.
 * 
 * @author 	Bavo Goosens (1e bachelor informatica, r0297884), Samuel Debruyn (1e bachelor informatica, r0305472)
 * 
 * @version 1.0
 */
public class Weight implements Comparable<Weight>{

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
	private final int weight;

	/**
	 * Deze methode maakt een nieuwe massa aan.
	 * 
	 * @param	newWeight
	 * 			Het opgegeven gewicht.
	 * 
	 * @post	Het nieuwe gewicht is gelijk aan het opgegeven gewicht mits foutverwerking.
	 * 			|if(newWeight < MINWEIGHT)
	 * 			|	new.getWeight() == MINWEIGHT
	 * 			|if(newWeight > MAXWEIGHT)
	 * 			|	new.getWeight() == MAXWEIGHT
	 * 			|new.getWeight() == newWeight
	 */
	public Weight(int newWeight){
		int tmp = Math.abs(newWeight);
		if(newWeight < MINWEIGHT)
			tmp = MINWEIGHT;
		if(newWeight > MAXWEIGHT)
			tmp = MAXWEIGHT;
		this.weight = tmp;
	}

	/**
	 * Geeft het gewicht terug.
	 * 
	 * @return 	Het gewicht dat in dit object opgeslagen is.
	 * 			|this.weight
	 */
	@Basic
	public int getWeight() {
		return this.weight;
	}

	@Override
	public int compareTo(Weight o) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	/*
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object other) {
		return false;
		//TODO: trolololo
	}

	/*
	 * @see java.lang.Double#hashCode()
	 */
	@Override
	public int hashCode() {
		return weight;
		//TODO: trolololo
	}
	
}