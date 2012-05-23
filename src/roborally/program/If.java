package roborally.program;

import roborally.model.Robot;

public class If extends Command{
	
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
	public int execute(int n , Robot robot){
		if (getCondition() != null){
			if (getCondition().evaluate(robot)){
				return getIf_clause().execute(n , robot);
			}
			return getElse_clause().execute(n, robot);
		}
		if (getConditieSpeciaal().evaluate(robot)){
			return getIf_clause().execute(n , robot);
		}
		return getElse_clause().execute(n , robot);
	}

	private Condition conditie;

	private SpecialCondition conditieSpeciaal;

	private Command if_clause;

	private Command else_clause;

}
