package roborally.filter;

/**
 * Een klasse om een filter voor te stellen.
 * 
 * @author 	Bavo Goosens (1e bachelor informatica, r0297884), Samuel Debruyn (1e bachelor informatica, r0305472)
 * 
 * @version 1.0
 */
public abstract class Filter {
	
	/**
	 * De manier waarop deze filter werkt. Deze integer is negatief, 0 of positief indien compareTo met de standaardwaarde resp. negatief, 0 of positief moet zijn.
	 */
	protected int filterMethod;
	/**
	 * De standaardwaarde waarmee vergeleken moet worden.
	 */
	protected Object value;
	
	/**
	 * Deze methode maakt een nieuwe filter aan.
	 * 
	 * @param	filterMethod
	 * 			De manier waarop deze filter werkt.
	 * 
	 * @param	value
	 * 			De waarde waarmee vergeleken moet worden.
	 * 
	 * @post	|new.filterMethod == filterMethod
	 * @post	|new.value == value
	 */
	protected Filter(int filterMethod, Object value){
		this.filterMethod = filterMethod;
		this.value = value;
	}
	
	public boolean evaluateObject(Object object){
		if(filterMethod == 0){
			return object.equals(value);
		}
		return false;
	}
	
}