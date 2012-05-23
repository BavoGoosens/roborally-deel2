package roborally.util;

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
		substr = haalHaakskesWeg(substr);
		String[] words = substr.split("[^a-z]");
		if (words[0].equals("while")){
			Command whl = new While(substr.substring(5, substr.length()));
			this.setBody(whl);
		} else if (words[0].equals("seq")){
			Sequentie seq = new Sequentie(substr.substring(3, substr.length()));
			this.setBody(seq);
		} else if (words[0].equals("if")){
			If ifs = new If(substr.substring(2, substr.length()));
			this.setBody(ifs);
		} else {
			if (words[0].equals("shoot")){
				Basic bas = new Basic(BasicEnum.SHOOT);
				this.setBody(bas);
			}else if (words[0].equals("move")){
				Basic bas = new Basic(BasicEnum.MOVE);
				this.setBody(bas);
			}else if (words[0].equals("turn")){
				Basic bas = new Basic(BasicEnum.TURN);
				this.setBody(bas);
			}else{
				Basic bas = new Basic(BasicEnum.PICK_UP_AND_USE);
				this.setBody(bas);
			}
		}
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
