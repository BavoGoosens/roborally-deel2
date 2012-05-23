package roborally.program;

import roborally.model.Robot;

/**
 * Een klasse om if constructies voor te stellen.
 * 
 * @author 	Bavo Goosens (1e bachelor informatica, r0297884), Samuel Debruyn (1e bachelor informatica, r0305472)
 * 
 * @version 1.0
 */
public class If extends Command{
	
	/**
	 * Deze methode geeft een string terug die het object voorstelt.
	 * 
	 * @return 	String
	 * 			De String die dit object voorstelt.
	 */
	@Override
	public String toString() {
		return "(if" + this.conditie.toString() + this.conditieSpeciaal.toString()+ this.getIf_clause().toString()+
				this.getElse_clause().toString() + ")";
	}

	/**
	 * Boolean die bijhoudt of deze if constructie al bezocht is.
	 */
	public boolean enterd = false;

	/**
	 * Constructor om een nieuwe if constructie aan te maken.
	 * 
	 * @param 	substring
	 * 			De string met de conditie en de if en else clausule van deze if constructie.
	 */
	public If(String substring) {
		extractCondition(substring);
	}

	/**
	 * Methode om de conditie uit een string te isoleren.
	 * 
	 * @param 	substring
	 * 			De substring die de conditie bevat.
	 */
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

	/**
	 * Methode om de if en de else clausule uit een gegeven string te isoleren.
	 * 
	 * @param 	sbstr
	 * 			De string die de if en else codeblokken bevat.
	 */
	private void getIfElseClause(String sbstr) {
		String ifstring = haalHaakskesWeg(sbstr);
		String rest = sbstr.substring(ifstring.length() + 2);
		setIfClause(getFirstCommand(sbstr));
		setElseClause(getFirstCommand(rest));
	}

	/**
	 * Deze methode stelt de else clausule in om een gegeven commando.
	 * 
	 * @param 	firstCommand
	 * 			Het commando om het else veld mee in te stellen.
	 */
	private void setElseClause(Command firstCommand) {
		this.setElse_clause(firstCommand);
	}

	/**
	 * Deze methode stelt de if clausule in om een gegeven commando.
	 * 
	 * @param 	firstCommand
	 * 			Het commando om het if veld mee in te stellen.
	 */
	private void setIfClause(Command firstCommand) {
		this.setIf_clause(firstCommand);
	}

	/**
	 * Methode om de conditie van deze if constructie terug te geven.
	 * 	
	 * @return	Condition 
	 * 			De conditie van deze if constructie.
	 */
	private Condition getCondition() {
		return this.conditie;
	}

	/**
	 * Methode om de conditie van deze if constructie in te stellen.
	 * 
	 * @param	cnd
	 * 			De Condition waarop de conditie van deze if constructie dient ingesteld te worden.
	 */
	private void setCondition(SpecialCondition cnd) {
		this.setConditieSpeciaal(cnd);
	}

	/**
	 * Methode om de conditie van deze if constructie in te stellen.
	 * 
	 * @param	cnd
	 * 			De speciale Condition waarop de conditie van deze if constructie dient ingesteld te worden.
	 */
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
	
	@Override
	public void resetExecuted() {
		setEntered(false);
		getIf_clause().resetExecuted();
		getElse_clause().resetExecuted();
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
