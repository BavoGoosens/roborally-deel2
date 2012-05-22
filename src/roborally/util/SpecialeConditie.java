package roborally.util;

public class SpecialeConditie {
	
	private ConditieEnum conditie; 

	private Conditie cond1;
	
	private Conditie cond2;

	public SpecialeConditie(ConditieEnum or, String andOrSbStr) {
		setConditie(or);
	}

	public ConditieEnum getConditie() {
		return conditie;
	}

	public void setConditie(ConditieEnum conditie) {
		this.conditie = conditie;
	}

}
