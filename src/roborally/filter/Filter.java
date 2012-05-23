package roborally.filter;

public abstract class Filter {
	
	protected int filterMethod;
	private Class property;
	private Object value;
	
	protected Filter(int filterMethod, Class property, Object value){
		this.filterMethod = filterMethod;
		this.property = property;
		this.value = value;
	}
	
	public boolean evaluateObject(Object object){
		return false;
	}
	
}
