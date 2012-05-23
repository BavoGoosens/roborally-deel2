package roborally.program;

public class SpecialeConditie {
	
	@Override
	public String toString() {
		if (this.conditie == ConditieEnum.NOT ){
			return this.cond1.toString();
		}
		return this.cond1.toString()+this.cond2.toString();
	}

	private ConditieEnum conditie; 

	private Conditie cond1;
	
	private Conditie cond2;

	public SpecialeConditie(ConditieEnum or, Conditie[] c) {
		setConditie(or);
		setCond1(c[0]);
		setCond2(c[1]);
	}

	public SpecialeConditie(ConditieEnum not, Conditie notCond) {
		setConditie(not);
		setCond1(notCond);
	}

	public ConditieEnum getConditie() {
		return this.conditie;
	}

	public void setConditie(ConditieEnum conditie) {
		this.conditie = conditie;
	}

	public Conditie getCond1() {
		return this.cond1;
	}

	public void setCond1(Conditie cond1) {
		this.cond1 = cond1;
	}

	public Conditie getCond2() {
		return this.cond2;
	}

	public void setCond2(Conditie cond2) {
		this.cond2 = cond2;
	}

}
