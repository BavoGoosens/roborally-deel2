package roborally.program;

public class While extends Command {

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
			SpecialeConditie cnd = new SpecialeConditie(ConditieEnum.AND, getAndOrCond(sbstr));
			this.setCondition(cnd);
			int beginIdx = sbstr.indexOf(cnd.toString())+cnd.toString().length();
			makeBody(sbstr.substring(beginIdx));
		}else if (words[0].equals("or")){
			sbstr = sbstr.substring(4);
			sbstr = sbstr.trim();
			SpecialeConditie cnd = new SpecialeConditie(ConditieEnum.OR, getAndOrCond(sbstr));
			this.setCondition(cnd);
			int beginIdx = sbstr.indexOf(cnd.toString())+cnd.toString().length();
			makeBody(sbstr.substring(beginIdx));
		}else if (words[0].equals("not")){
			sbstr = sbstr.substring(4);
			sbstr = sbstr.trim();
			SpecialeConditie cnd = new SpecialeConditie(ConditieEnum.NOT, getNotCond(sbstr));
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
		substr = substr.substring(sbstr.indexOf("("));
		setBody(getFirstCommand(substr));
	}

	private void setCondition(SpecialeConditie cnd) {
		this.conditieSpeciaal = cnd;
	}

	private void setCondition(Conditie cnd) {
		this.conditie = cnd;	
	}

	public Command getBody() {
		return body;
	}

	public void setBody(Command body) {
		this.body = body;
	}
	
	
	private Conditie conditie;
	
	private SpecialeConditie conditieSpeciaal;

	private Command body;

}
