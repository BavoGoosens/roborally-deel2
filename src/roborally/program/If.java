package roborally.program;

import roborally.model.Robot;

public class If extends Command{
	
	@Override
	public String toString() {
		return "(if" + this.conditie.toString() + this.conditieSpeciaal.toString()+ this.getIf_clause().toString()+
				this.getElse_clause().toString() + ")";
	}

	public boolean enterd = false;

	public If(String substring) {
		extractCondition(substring);
	}

	private void extractCondition(String substring) {
		String sbstr = substring.trim();
		sbstr = sbstr.substring(1);
		String[] words = sbstr.split("[^a-z]");
		if (words[0].contains("and")){
			sbstr = sbstr.substring(4);
			sbstr = sbstr.trim();
			SpecialCondition cnd = new SpecialCondition(ConditionEnum.AND, getAndOrCond(sbstr));
			this.setCondition(cnd);
			int beginIdx = sbstr.indexOf(cnd.toString())+cnd.toString().length();
			sbstr = sbstr.substring(beginIdx);
			sbstr = sbstr.substring(sbstr.indexOf('('));
			getIfElseClause(sbstr);
		}else if (words[0].contains("or")){
			sbstr = sbstr.substring(4);
			sbstr = sbstr.trim();
			SpecialCondition cnd = new SpecialCondition(ConditionEnum.OR, getAndOrCond(sbstr));
			this.setCondition(cnd);
			int beginIdx = sbstr.indexOf(cnd.toString())+cnd.toString().length();
			sbstr = sbstr.substring(beginIdx);
			sbstr = sbstr.substring(sbstr.indexOf('('));
			getIfElseClause(sbstr);
		}else if (words[0].contains("not")){
			sbstr = sbstr.substring(4);
			sbstr = sbstr.trim();
			SpecialCondition cnd = new SpecialCondition(ConditionEnum.NOT, getNotCond(sbstr));
			this.setCondition(cnd);
			int beginIdx = sbstr.indexOf(cnd.toString())+cnd.toString().length();
			sbstr = sbstr.substring(beginIdx);
			sbstr = sbstr.substring(sbstr.indexOf('('));
			getIfElseClause(sbstr);
		}
		else{
			setCondition(getBasicConditie(sbstr));
			sbstr = sbstr.substring(getCondition().getConditie().name().length());
			sbstr = sbstr.substring(sbstr.indexOf('('));
			getIfElseClause(sbstr);
		}
	}

	private void getIfElseClause(String sbstr) {
		String ifstring = haalHaakskesWeg(sbstr);
		String rest = sbstr.substring(ifstring.length() + 2);
		setIfClause(getFirstCommand(sbstr));
		setElseClause(getFirstCommand(rest));
	}

	private void setElseClause(Command firstCommand) {
		this.setElse_clause(firstCommand);
	}

	private void setIfClause(Command firstCommand) {
		this.setIf_clause(firstCommand);
	}

	private Condition getCondition() {
		return this.conditie;
	}

	private void setCondition(SpecialCondition cnd) {
		this.setConditieSpeciaal(cnd);
	}

	private void setCondition(Condition conditie) {
		this.conditie = conditie;		
	}

	public SpecialCondition getConditieSpeciaal() {
		return this.conditieSpeciaal;
	}

	public void setConditieSpeciaal(SpecialCondition conditieSpeciaal) {
		this.conditieSpeciaal = conditieSpeciaal;
	}

	public Command getIf_clause() {
		return this.if_clause;
	}

	public void setIf_clause(Command if_clause) {
		this.if_clause = if_clause;
	}

	public Command getElse_clause() {
		return this.else_clause;
	}

	public void setElse_clause(Command else_clause) {
		this.else_clause = else_clause;
	}
	
	@Override
	public void executeNext(Robot robot){
		if (!getEntered()){
			if (getCondition() != null){
				if (getCondition().evaluate(robot)){
					setEntered(true);
					getIf_clause().executeNext(robot);
				}
				setEntered(true);
				getElse_clause().executeNext(robot);
			}else if (getConditieSpeciaal().evaluate(robot)){
				setEntered(true);
				getIf_clause().executeNext(robot);
			}
			setEntered(true);
			getElse_clause().executeNext(robot);
		}
	}
	
	private boolean entered;

	public boolean getEntered() {
		return this.entered;
	}

	public void setEntered(boolean entered) {
		this.entered = entered;
	}

	private Condition conditie;

	private SpecialCondition conditieSpeciaal;

	private Command if_clause;

	private Command else_clause;

}
