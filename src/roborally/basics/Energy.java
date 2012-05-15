package roborally.basics;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Immutable;
import be.kuleuven.cs.som.annotate.Value;

/**
 * Deze klasse stelt een hoeveelheid energie voor.
 * 
 * @invar	De energie mag niet negatief zijn.
 * 			|this.getEnergy() >= 0
 * 
 * @author 	Bavo Goosens (1e bachelor informatica, r0297884), Samuel Debruyn (1e bachelor informatica, r0305472)
 * 
 * @version 1.2
 */
@Value
public class Energy implements Comparable<Energy>{

	/**
	 * Hoeveelheid energie opgeslagen in dit object.
	 */
	private final double amount;

	/**
	 * Deze methode maakt een nieuwe hoeveelheid energie aan.
	 * 
	 * @param 	amount
	 * 			De hoeveelheid energie
	 * 
	 * @pre		De hoeveelheid mag niet negatief zijn.
	 * 			|amount >= 0
	 * 
	 * @post	De nieuwe hoeveelheid energie moet gelijk zijn aan de gegeven hoeveelheid energie.
	 * 			|new.getEnergy() == amount
	 */
	public Energy(double amount){
		assert(amount >= 0);
		this.amount = amount;
	}

	/**
	 * Deze methode maakt een nieuwe hoeveelheid energie aan.
	 * 
	 * @param 	amount
	 * 			De hoeveelheid energie
	 * 
	 * @param	unit
	 * 			De eenheid waarin de energie gegeven wordt.
	 * 
	 * @pre		De hoeveelheid mag niet negatief zijn.
	 * 			|amount >= 0
	 * 
	 * @post	De nieuwe hoeveelheid energie moet gelijk zijn aan de gegeven hoeveelheid energie.
	 * 			|new.getEnergy(unit) == amount
	 */
	public Energy(double amount, EnergyUnit unit){
		assert(amount >= 0);
		this.amount = amount * unit.getFactor();
	}

	/**
	 * Deze methode geeft de huidige hoeveelheid energie terug.
	 * 
	 * @return	De huidige hoeveelheid energie.
	 * 			|this.amount
	 */
	@Basic @Immutable
	public double getEnergy(){
		return this.amount;
	}

	/**
	 * Deze methode geeft de huidige hoeveelheid energie terug in de opgegeven eenheid.
	 * 
	 * @param	unit
	 * 			De eenheid waarin de energie teruggegeven moet worden.
	 * 
	 * @return	De huidige hoeveelheid energie in de opgegeven eenheid.
	 * 			|this.amount * unit.getFactor()
	 */
	@Immutable
	public double getEnergy(EnergyUnit unit){
		return this.getEnergy() * unit.getFactor();
	}

	/**
	 * Deze methode geeft de som van 2 hoeveelheden energie.
	 * 
	 * @param 	e1
	 * 			Eerste hoeveelheid energie.
	 * 
	 * @param 	e2
	 * 			Eerste hoeveelheid energie.
	 * 
	 * @return	De som van de 2 hoeveelheden energie.
	 * 			|new Energy(e1.getEnergy() + e2.getEnergy())
	 */
	public static Energy energySum(Energy e1, Energy e2){
		return new Energy(e1.getEnergy() + e2.getEnergy());
	}

	/**
	 * Deze methode geeft het verschil van 2 hoeveelheden energie.
	 * 
	 * @param	e1
	 * 			De hoeveelheid energie waarvan vertrokken wordt.
	 * 
	 * @param 	e2
	 * 			De hoeveelheid energie die van e1 afgetrokken wordt.
	 * 
	 * @return	Het verschil van de 2 hoeveelheden energie. Dit is 0 indien de uitkomst negatief zou moeten zijn.
	 * 			|if((e1.getEnergy() - e2.getEnergy()) < 0)
	 * 			|	new Energy(0)
	 * 			|new Energy(e1.getEnergy() - e2.getEnergy())
	 */
	public static Energy energyDifference(Energy e1, Energy e2){
		if((e1.getEnergy() - e2.getEnergy()) < 0)
			return new Energy(0);
		return new Energy(e1.getEnergy() - e2.getEnergy());
	}

	/**
	 * Deze methode vergelijkt 2 hoeveelheden energie.
	 * 
	 * @param	other
	 * 			De hoeveelheid energie waarmee this moet vergeleken worden.
	 * 
	 * @throws	IllegalArgumentException
	 * 			De 2de hoeveelheid energie is null.
	 * 			|other == null
	 * 
	 * @return	|(int) (this.getEnergy() - other.getEnergy())	
	 */
	@Override
	public int compareTo(Energy other) throws IllegalArgumentException{
		if(other == null)
			throw new IllegalArgumentException("Andere hoeveelheid energie mag niet null zijn.");
		return (int) (this.getEnergy() - other.getEnergy());
	}

	/*
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object other) {
		if(other == null)
			return false;
		if(this.getClass() != other.getClass())
			return false;
		return (this.getEnergy()) == ((Energy) other).getEnergy();
	}

	/*
	 * @see java.lang.Double#hashCode()
	 */
	@Override
	public int hashCode() {
		return ((Double) this.getEnergy()).hashCode();
	}

	/*
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return Double.toString(this.getEnergy()) + " " + EnergyUnit.WS.toString();
	}

}