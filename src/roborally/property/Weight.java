package roborally.property;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Immutable;
import be.kuleuven.cs.som.annotate.Value;

/**
 * Deze klasse stelt een gewicht voor.
 * 
 * @author 	Bavo Goosens (1e bachelor informatica, r0297884), Samuel Debruyn (1e bachelor informatica, r0305472)
 * 
 * @version 1.0
 */
@Value
public class Weight implements Comparable<Weight>{

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
		weight = tmp;
	}
	
	/**
	 * Het maximale gewicht van een object.
	 */
	public final static int MAXWEIGHT = Integer.MAX_VALUE;
	/**
	 * Het minimale gewicht van een object.
	 */
	public final static int MINWEIGHT = 0;

	/**
	 * Geeft het gewicht terug.
	 * 
	 * @return 	Het gewicht dat in dit object opgeslagen is.
	 * 			|this.weight
	 */
	@Basic @Immutable
	public int getWeight() {
		return this.weight;
	}
	
	/**
	 * Gewicht van dit object.
	 */
	private final int weight;
	
	/**
	 * Deze methode vergelijkt 2 gewichten.
	 * 
	 * @param	other
	 * 			Het gewicht waarmee vergeleken moet worden.
	 * 
	 * @throws	IllegalArgumentException
	 * 			Het 2de gewicht is null.
	 * 			|other == null
	 * 
	 * @return	|this.getWeight() - other.getWeight()
	 */
	@Override
	public int compareTo(Weight other) throws IllegalArgumentException{
		if(other == null)
			throw new IllegalArgumentException("Ander gewicht mag niet null zijn.");
		return getWeight() - other.getWeight();
	}
	
	/*
	 * Kijk na of het gewicht gelijk is aan een ander gewicht.
	 * 
	 * @param	other
	 * 			Het gewicht waarmee vergeleken moet worden.
	 * 
	 * @return	Dit is true wanneer de 2 objecten hetzelfde gewicht bevatten.
	 * 			|if(other == null)
	 * 			|	false
	 * 			|if(getClass() != other.getClass())
	 * 			|	false
	 * 			|(getWeight() == ((Weight) other).getWeight())
	 */
	@Override
	public boolean equals(Object other) {
		if(other == null)
			return false;
		if(getClass() != other.getClass())
			return false;
		return (getWeight() == ((Weight) other).getWeight());
	}

	/*
	 * Deze methode berekent de hashcode van een object van deze klasse.
	 * 
	 * @return	De hashcode van dit object.
	 * 			|getWeight()
	 */
	@Override
	public int hashCode() {
		return getWeight();
	}

	/*
	 * Deze methode zet het object om naar een String.
	 * 
	 * @return	Een textuele representatie van dit object waarbij duidelijk wordt welk gewicht dit object bevat.
	 * 			|Integer.toString(getWeight())
	 */
	@Override
	public String toString() {
		return Integer.toString(getWeight()) + " gr";
	}
	
}