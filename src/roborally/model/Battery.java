package roborally.model;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Raw;
import roborally.basics.Energy;
import roborally.basics.Position;
import roborally.basics.Weight;

/**
 * Deze klasse houdt een batterij bij. Deze heeft een positie en een hoeveelheid energie.
 * 
 * @invar	De hoeveelheid energie van een batterij moet altijd geldig zijn.
 * 			|isValidBatteryEnergyAmount(getEnergy())
 * 
 * @author 	Bavo Goosens (1e bachelor informatica, r0297884), Samuel Debruyn (1e bachelor informatica, r0305472)
 * 
 * @version 1.0
 */
public class Battery {
	
	/**
	 * Maximale energie die een batterij kan hebben.
	 */
	public final static int MAXBATTERYENERGY = 5000;
	
	/**
	 * Minimale energie die een batterij kan hebben.
	 */
	public final static int MINBATTERYENERGY = 0;
	
	/**
	 * Positie van de batterij (niet noodzakelijk).
	 */
	private Position position;
	/**
	 * Energie in de batterij.
	 */
	private Energy energy;
	/**
	 * Gewicht van de batterij.
	 */
	private Weight weight;
	
	/**
	 * Indien de batterij vernietigd is wordt dit true.
	 */
	private boolean isTerminated = false;

	private Board board;
	
	/**
	 * Maakt een nieuwe batterij aan.
	 * 
	 * @param	energy
	 * 			Energie van de nieuwe batterij.
	 * 
	 * @param	weight
	 * 			Massa van de batterij.
	 * 
	 * @pre		Energie moet geldige hoeveelheid zijn.
	 * 			|isValidBatteryEnergyAmount(energy)
	 */
	public Battery(Energy energy, Weight weight){
		this.setEnergy(energy);
		this.setWeight(weight);
	}

	/**
	 * Methode die het board instelt waartoe deze battery behoort.
	 * 
	 * @param 	board
	 * 			Het board waarop deze battery zich bevindt.
	 */
	public void setBoard(Board board){
		this.board = board;
	}
	
	/**
	 * Methode die het board teruggeeft waarop deze battery zich bevindt. Deze methode kan ook null teruggeven wat wil zeggen dat de battery zich niet op een board bevindt.
	 * 
	 * @return	Board of null
	 * 			Het board waarop de battery zich bevindt of null als de battery niet op een board staat;
	 */
	public Board getBoard(){
		return this.board;
	}
	/**
	 * Geeft de positie van de batterij terug.
	 * 
	 * @return	De positie van de batterij.
	 * 			|new.position
	 */
	@Basic
	public Position getPosition() {
		return position;
	}

	/**
	 * Wijzigt de positie van de robot naar de nieuwe positie.
	 * 
	 * @param	position
	 * 			De nieuwe positie. Null indien deze buiten het bord is.
	 */
	@Basic @Raw
	public void setPosition(Position position) {
		this.position = position;
	}

	/**
	 * Geeft de energie van de batterij.
	 * 
	 * @return	Energie van de batterij.
	 * 			|new.energy
	 */
	@Basic
	public Energy getEnergy() {
		return energy;
	}

	/**
	 * Wijzigt de energie van de batterij.
	 * 
	 * @param	energy
	 * 			Nieuwe energie van de batterij.
	 * 
	 * @post	|new.energy == energy
	 */
	@Basic
	public void setEnergy(Energy energy) {
		this.energy = energy;
	}

	/**
	 * Geeft het gewicht terug van de batterij.
	 * 
	 * @return	Het gewicht van de batterij.
	 * 			|new.weight
	 */
	@Basic
	public Weight getWeight() {
		return weight;
	}

	/**
	 * Wijzigt het gewicht van de batterij.
	 * 
	 * @param	weight
	 * 			Het nieuwe gewicht van de batterij.
	 */
	@Basic
	public void setWeight(Weight weight) {
		this.weight = weight;
	}
	
	/**
	 * Geeft terug of de hoeveelheid energie een geldige hoeveelheid is voor deze batterij.
	 * 
	 * @param	energy
	 * 			De na te kijken hoeveelheid energie.
	 * 
	 * @return	Boolean met het resultaat van de controle.
	 * 			|energyAmount >= MINBATTERYENERGY && energyAmount <= MAXBATTERYENERGY
	 */
	public static boolean isValidBatteryEnergyAmount(Energy energy){
		return (energy.getEnergy() >= MINBATTERYENERGY && energy.getEnergy() <= MAXBATTERYENERGY);
	}
	
	/**
	 * Deze methode vernietigt de batterij.
	 * 
	 * @post	|this.isDestroyed()
	 * @post	|this.getPosition() == null
	 */
	public void destroy(){
		//TODO: remove from board
		this.setPosition(null);
		this.isTerminated = true;
	}
	
	/**
	 * Deze methode geeft true indien de batterij vernietigd is, anders false.
	 * 
	 * @return	|new.isTerminated
	 */
	@Basic
	public boolean isDestroyed(){
		return isTerminated;
	}
	
}
