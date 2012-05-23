package roborally.program;

import roborally.model.Robot;

public class While extends Command {

	public boolean enterd = false;

	public While(String substring) {
		extractConditionBody(substring);		
	}

	private void extractConditionBody(String substring) {
		String sbstr = substring.trim();
		sbstr = sbstr.substring(1);
		String[] words = sbstr.split("[^a-z]");
		if (words[0].equals("and")){
			sbstr = sbstr.substring(4);
			sbstr = sbstr.trim();
			SpecialCondition cnd = new SpecialCondition(ConditionEnum.AND, getAndOrCond(sbstr));
			this.setCondition(cnd);
			int beginIdx = sbstr.indexOf(cnd.toString())+cnd.toString().length();
			makeBody(sbstr.substring(beginIdx));
		}else if (words[0].equals("or")){
			sbstr = sbstr.substring(4);
			sbstr = sbstr.trim();
			SpecialCondition cnd = new SpecialCondition(ConditionEnum.OR, getAndOrCond(sbstr));
			this.setCondition(cnd);
			int beginIdx = sbstr.indexOf(cnd.toString())+cnd.toString().length();
			makeBody(sbstr.substring(beginIdx));
		}else if (words[0].equals("not")){
			sbstr = sbstr.substring(4);
			sbstr = sbstr.trim();
			SpecialCondition cnd = new SpecialCondition(ConditionEnum.NOT, getNotCond(sbstr));
			this.setCondition(cnd);
			int beginIdx = sbstr.indexOf(cnd.toString())+cnd.toString().length();
			makeBody(sbstr.substring(beginIdx));
		}
		else{
			setCondition(getBasicConditie(sbstr));
			makeBody(sbstr.substring(words[0].length()));
		}
	}

	private void makeBody(String sbstr) {
		String substr =sbstr;
		substr = substr.substring(sbstr.indexOf('('));
		setBody(getFirstCommand(substr));
	}

	private void setCondition(SpecialCondition cnd) {
		this.conditieSpeciaal = cnd;
	}

	private void setCondition(Condition cnd) {
		this.conditie = cnd;	
	}

	public Command getBody() {
		return this.body;
	}

	public void setBody(Command body) {
		this.body = body;
	}

	@Override
	public int execute(int n , Robot robot) {
		if (getConditie() != null){
			while (getConditie().evaluate(robot)){
				return getBody().execute(n , robot);
			}
		}else {
			while(getConditieSpeciaal().evaluate(robot)){

				return getBody().execute(n , robot);
			}
		}
		// niet voldaan aan de conditie
		return 0;
	}

	private Condition conditie;

	public Condition getConditie() {
		return this.conditie;
	}

	private SpecialCondition conditieSpeciaal;


	public SpecialCondition getConditieSpeciaal() {
		return this.conditieSpeciaal;
	}
	
	private Command body;

}
