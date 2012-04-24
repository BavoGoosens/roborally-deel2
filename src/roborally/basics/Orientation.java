package roborally.basics;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Value;

/**
 * Deze klasse stelt een richting voor.
 * 
 * @author 	Bavo Goosens (1e bachelor informatica, r0297884), Samuel Debruyn (1e bachelor informatica, r0305472)
 * 
 * @version 1.0
 */
public class Orientation{
	
	@Value
	public enum orientationValue{
		UP, RIGHT, DOWN, LEFT;
	}
	
	private final orientationValue ov;
	
	/**
	 * Deze methode maakt een nieuwe ori�ntatie aan.
	 * 
	 * @param 	ov
	 * 			De waarde van de ori�ntatie. Mogelijke waarden zijn gedefinieerd in de enumeratie orientationValue als UP, RIGHT, DOWN en LEFT.
	 */
	public Orientation(orientationValue ov){
		this.ov = ov;
	}

	/**
	 * Deze methode geeft de waarde van de ori�ntatie terug.
	 * 
	 * @return 	orientationValue
	 * 			De waarde van de richting.
	 */
	@Basic
	private orientationValue getOv() {
		return ov;
	}
	
	/**
	 * Deze methode geeft de ori�ntatie terug als er 1 keer in wijzerzin gedraaid wordt.
	 * 
	 * @return	Orientation
	 * 			De ori�ntatie als er 1 keer in wijzerzin gedraaid wordt.
	 */
	public Orientation getClockwiseOrientation(){
		Orientation result = null;
		if(this.getOv().equals(Orientation.orientationValue.UP))
			result = new Orientation(Orientation.orientationValue.RIGHT);
		if(this.getOv().equals(Orientation.orientationValue.RIGHT))
			result = new Orientation(Orientation.orientationValue.DOWN);
		if(this.getOv().equals(Orientation.orientationValue.DOWN))
			result = new Orientation(Orientation.orientationValue.LEFT);
		if(this.getOv().equals(Orientation.orientationValue.LEFT))
			result = new Orientation(Orientation.orientationValue.UP);
		return result;	
	}
	
	/**
	 * Deze methode geeft de ori�ntatie terug als er 1 keer in tegenwijzerzin gedraaid wordt.
	 * 
	 * @return	Orientation
	 * 			De ori�ntatie als er 1 keer in tegenwijzerzin gedraaid wordt.
	 */
	public Orientation getCounterClockwiseOrientation(){
		Orientation result = null;
		if(this.getOv().equals(Orientation.orientationValue.UP))
			result = new Orientation(Orientation.orientationValue.LEFT);
		if(this.getOv().equals(Orientation.orientationValue.RIGHT))
			result = new Orientation(Orientation.orientationValue.UP);
		if(this.getOv().equals(Orientation.orientationValue.DOWN))
			result = new Orientation(Orientation.orientationValue.RIGHT);
		if(this.getOv().equals(Orientation.orientationValue.LEFT))
			result = new Orientation(Orientation.orientationValue.DOWN);
		return result;	
	}
}
