package roborally.util;

import java.util.ArrayList;

public class While extends Command {

	public While(String substring) {
		extractConditionBody(substring);		
	}

	private void extractConditionBody(String substring) {
		String sbstr = substring.trim();
		sbstr = sbstr.substring(1);
		String[] words = sbstr.split("[^a-z]");
		if (words[0].equals("true")){
			Conditie cnd = new Conditie(ConditieEnum.TRUE);
			this.setCondition(cnd);
			sbstr = sbstr.substring(5);
			sbstr = sbstr.trim();
			makeBody(sbstr);
		}else if (words[0].equals("at-item")){
			Conditie cnd = new Conditie(ConditieEnum.AT_ITEM);
			this.setCondition(cnd);
			sbstr = sbstr.substring(8);
			sbstr = sbstr.trim();
			makeBody(sbstr);
		}else if (words[0].equals("energy-at-least")){
			sbstr = sbstr.substring(16);
			sbstr = sbstr.trim();
			@SuppressWarnings("boxing")
			Conditie cnd = new Conditie(ConditieEnum.ENERGY_AT_LEAST, getAmountEnergy(sbstr));
			this.setCondition(cnd);
		}else if (words[0].equals("wall")){
			Conditie cnd = new Conditie(ConditieEnum.WALL);
			this.setCondition(cnd);
			sbstr = sbstr.substring(5);
			sbstr = sbstr.trim();
		}else if (words[0].equals("and")){
			sbstr = sbstr.substring(4);
			sbstr = sbstr.trim();
			SpecialeConditie cnd = new SpecialeConditie(ConditieEnum.AND, getAndOrSbStr(sbstr));
			this.setCondition(cnd);
		}else if (words[0].equals("or")){
			sbstr = sbstr.substring(4);
			sbstr = sbstr.trim();
			SpecialeConditie cnd = new SpecialeConditie(ConditieEnum.OR, getAndOrSbStr(sbstr));
			this.setCondition(cnd);
		}else if (words[0].equals("not")){
			sbstr = sbstr.substring(4);
			sbstr = sbstr.trim();
			SpecialeConditie cnd = new SpecialeConditie(ConditieEnum.NOT, getNotSbStr(sbstr));
			this.setCondition(cnd);
		}
		else{
			Conditie cnd = new Conditie(ConditieEnum.CAN_HIT_ROBOT);
			this.setCondition(cnd);
			sbstr = sbstr.substring(14);
			sbstr = sbstr.trim();
		}
	}

	private void makeBody(String sbstr) {
		// TODO Auto-generated method stub
		
	}

	@SuppressWarnings("boxing")
	private Double getAmountEnergy(String sbstr) {
		int eindIdx = sbstr.indexOf(')');
		String amount = sbstr.substring(0, eindIdx-1);
		makeBody(sbstr.substring(eindIdx));
		return Double.parseDouble(amount);
	}

	private String getNotSbStr(String sbstr) {
		// TODO Auto-generated method stub
		return null;
	}

	private void setCondition(SpecialeConditie cnd) {
		// TODO Auto-generated method stub
		
	}

	private String getAndOrSbStr(String sbstr) {
		// TODO Auto-generated method stub
		return null;
	}

	private void setCondition(Conditie cnd) {
		this.conditie = cnd;	
	}

	private Conditie conditie;

	private ArrayList<Command> body = new ArrayList<>();

}
