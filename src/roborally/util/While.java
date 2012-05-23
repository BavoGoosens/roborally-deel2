package roborally.util;

public class While extends Command {

	public While(String substring) {
		extractConditionBody(substring);		
	}

	@SuppressWarnings("boxing")
	public Conditie getBasicConditie(String str){
		String sbstr = str.trim();
		sbstr = sbstr.substring(1);
		String[] words = sbstr.split("[^a-z]");
		if (words[0].equals("true")){
			return new Conditie(ConditieEnum.TRUE);
		}else if (words[0].equals("at")){
			return  new Conditie(ConditieEnum.AT_ITEM);
		}else if (words[0].equals("energy")){
			return new Conditie(ConditieEnum.ENERGY_AT_LEAST, getAmountEnergy(sbstr));
		}else if (words[0].equals("wall")){
			return new Conditie(ConditieEnum.WALL);
		}else{
			return new Conditie(ConditieEnum.CAN_HIT_ROBOT);
			}
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
			While whl = new While(substr.substring(5, substr.length()));
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

	@SuppressWarnings("boxing")
	private Double getAmountEnergy(String sbstr) {
		int beginIdx = sbstr.indexOf(' ');
		int eindIdx = sbstr.indexOf(')');
		String amount = sbstr.substring(beginIdx,eindIdx);
		return Double.parseDouble(amount);
	}

	private Conditie getNotCond(String sbstr) {
		// TODO Auto-generated method stub
		return null;
	}

	private void setCondition(SpecialeConditie cnd) {
		this.conditieSpeciaal = cnd;
	}

	private Conditie[] getAndOrCond(String sbstr) {
		String[] condIndeling = sbstr.split("\\) ");
		Conditie cond1 = getBasicConditie(condIndeling[0]);
		Conditie cond2 = getBasicConditie(condIndeling[1]);
		Conditie [] result = new Conditie[2];
		result[0] = cond1;
		result[1] = cond2;
		return result;
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
