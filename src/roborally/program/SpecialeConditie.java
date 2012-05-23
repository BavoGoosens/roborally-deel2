package roborally.program;

import roborally.model.Robot;

public class SpecialeConditie {
	
	@Override
	public String toString() {
		if (this.conditie == ConditieEnum.NOT ){
			return this.cond1.toString();
		}
		return this.cond1.toString()+this.cond2.toString();
	}

	private ConditieEnum conditie; 

	private Condition cond1;
	
	private Condition cond2;

	public SpecialeConditie(ConditieEnum or, Condition[] c) {
		setConditie(or);
		setCond1(c[0]);
		setCond2(c[1]);
	}

	public SpecialeConditie(ConditieEnum not, Condition notCond) {
		setConditie(not);
		setCond1(notCond);
	}

	public ConditieEnum getConditie() {
		return this.conditie;
	}

	public void setConditie(ConditieEnum conditie) {
		this.conditie = conditie;
	}

	public Condition getCond1() {
		return this.cond1;
	}

	public void setCond1(Condition cond1) {
		this.cond1 = cond1;
	}

	public Condition getCond2() {
		return this.cond2;
	}

	public void setCond2(Condition cond2) {
		this.cond2 = cond2;
	}

	public boolean evaluate(Robot robot) {
		if (getConditie() == ConditieEnum.AND){
			return (getCond1().evaluate(robot) && getCond2().evaluate(robot));
		}else if (getConditie() == ConditieEnum.OR){
			return (getCond1().evaluate(robot) || getCond2().evaluate(robot));
		}else{
			return !getCond1().evaluate(robot);
		}
	}

}
