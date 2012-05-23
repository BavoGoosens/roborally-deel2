package roborally.program;

import roborally.model.Robot;


public class Command {

	public static String haalHaakskesWeg(String str){
		String substr = str;
		int openHaakskesCount = 0;
		int closedHaakskesCount = 0;
		int beginIdx = 0;
		int eindIdx = 0;
		for (int i = 0; i < substr.length(); i++){
			char currChar = substr.charAt(i);
			if (currChar == '(' && openHaakskesCount == 0){
				beginIdx = i;
				openHaakskesCount++;
			} else if (currChar == '('){
				openHaakskesCount++;
			}else if (currChar == ')'){
				closedHaakskesCount++;
				if (openHaakskesCount == closedHaakskesCount){
					eindIdx = i;
					break;
				}

			}
		}
		return substr = substr.substring(beginIdx + 1, eindIdx).trim();
	}

	protected Command getFirstCommand(String substr){
		String eersteCommand = haalHaakskesWeg(substr);
		String[] words = eersteCommand.split("[^a-z]");
		if (words[0].contains("while")){
			return new While(substr.substring(6, substr.length()));
		} else if (words[0].contains("seq")){
			return new Sequentie(substr.substring(4, substr.length()));
		} else if (words[0].contains("if")){
			return new If(substr.substring(3, substr.length()));
		} else {
			if (words[0].contains("shoot")){
				return new Basic(BasicEnum.SHOOT);
			}else if (words[0].contains("move")){
				return new Basic(BasicEnum.MOVE);
			}else if (words[0].contains("turn")){
				String[] word = eersteCommand.split(" ");
				return new Basic(BasicEnum.TURN,word[1]);
			}else{
				return new Basic(BasicEnum.PICK_UP_AND_USE);
			}
		}
	}

	protected Conditie[] getAndOrCond(String sbstr) {
		String[] condIndeling = sbstr.split("\\) ");
		Conditie cond1 = getBasicConditie(condIndeling[0]);
		Conditie cond2 = getBasicConditie(condIndeling[1]);
		Conditie [] result = new Conditie[2];
		result[0] = cond1;
		result[1] = cond2;
		return result;
	}

	@SuppressWarnings("boxing")
	protected Double getAmountEnergy(String sbstr) {
		int beginIdx = sbstr.indexOf(' ');
		int eindIdx = sbstr.indexOf(')');
		String amount = sbstr.substring(beginIdx,eindIdx);
		return Double.parseDouble(amount);
	}

	protected Conditie getNotCond(String sbstr) {
		String[] condIndeling = sbstr.split("\\) ");
		Conditie cond1 = getBasicConditie(condIndeling[0]);
		return cond1;
	}

	@SuppressWarnings("boxing")
	public Conditie getBasicConditie(String str) {
		String sbstr = str.trim();
		String[] words = sbstr.split("[^a-z]");
		if (words[0].contains("true")){
			return new Conditie(ConditieEnum.TRUE);
		}else if (words[0].contains("at")){
			return  new Conditie(ConditieEnum.AT_ITEM);
		}else if (words[0].contains("energy")){
			return new Conditie(ConditieEnum.ENERGY_AT_LEAST, getAmountEnergy(sbstr));
		}else if (words[0].contains("wall")){
			return new Conditie(ConditieEnum.WALL);
		}else{
			return new Conditie(ConditieEnum.CAN_HIT_ROBOT);
		}
	}

	public void execute(Robot robot) {}
}
