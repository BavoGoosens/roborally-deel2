package roborally.program;

import roborally.model.Robot;


/**
 * Een klasse om commandos voor te stellen.
 * 
 * @author 	Bavo Goosens (1e bachelor informatica, r0297884), Samuel Debruyn (1e bachelor informatica, r0305472)
 * 
 * @version 3.0
 */
public class Command {

	/**
	 * 
	 * @param str
	 * @return
	 */
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
					return substr = substr.substring(beginIdx + 1, eindIdx).trim();
				}

			}
		}
		return str;
	}

	/**
	 * 
	 * @param substr
	 * @return
	 */
	protected Command getFirstCommand(String substr){
		String eersteCommand = haalHaakskesWeg(substr);
		String[] words = eersteCommand.split("[^a-z]");
		if (words[0].contains("while")){
			return new While(substr.substring(6, substr.length()));
		} else if (words[0].contains("seq")){
			return new Sequence(substr.substring(4, substr.length()));
		} else if (words[0].contains("if")){
			return new If(substr.substring(3, substr.length()));
		} else {
			if (words[0].contains("shoot")){
				return new Statement(StatementEnum.SHOOT);
			}else if (words[0].contains("move")){
				return new Statement(StatementEnum.MOVE);
			}else if (words[0].contains("turn")){
				String[] word = eersteCommand.split(" ");
				return new Statement(StatementEnum.TURN,word[1]);
			}else{
				return new Statement(StatementEnum.PICK_UP_AND_USE);
			}
		}
	}

	/**
	 * 
	 * @param sbstr
	 * @return
	 */
	protected Condition[] getAndOrCond(String sbstr) {
		String[] condIndeling = sbstr.split("\\) ");
		Condition cond1 = getBasicConditie(condIndeling[0]);
		Condition cond2 = getBasicConditie(condIndeling[1]);
		Condition [] result = new Condition[2];
		result[0] = cond1;
		result[1] = cond2;
		return result;
	}

	/**
	 * 
	 * @param sbstr
	 * @return
	 */
	@SuppressWarnings("boxing")
	protected Double getAmountEnergy(String sbstr) {
		int beginIdx = sbstr.indexOf(' ');
		int eindIdx = sbstr.indexOf(')');
		String amount = sbstr.substring(beginIdx,eindIdx);
		return Double.parseDouble(amount);
	}

	protected Condition getNotCond(String sbstr) {
		String[] condIndeling = sbstr.split("\\) ");
		Condition cond1 = getBasicConditie(condIndeling[0]);
		return cond1;
	}

	/**
	 * 
	 * @param str
	 * @return
	 */
	@SuppressWarnings("boxing")
	public Condition getBasicConditie(String str) {
		String sbstr = str.trim();
		String[] words = sbstr.split("[^a-z]");
		if (words[0].contains("true")){
			return new Condition(ConditionEnum.TRUE);
		}else if (words[0].contains("at")){
			return  new Condition(ConditionEnum.AT_ITEM);
		}else if (words[0].contains("energy")){
			return new Condition(ConditionEnum.ENERGY_AT_LEAST, getAmountEnergy(sbstr));
		}else if (words[0].contains("wall")){
			return new Condition(ConditionEnum.WALL);
		}else{
			return new Condition(ConditionEnum.CAN_HIT_ROBOT);
		}
	}

	/**
	 * 
	 * @param robot
	 */
	public void executeNext(Robot robot){}

	/**
	 * 
	 */
	protected boolean executed = false;
	
	/**
	 * 
	 * @param ex
	 */
	public void setExecuted(boolean ex){
		this.executed = ex;
	}

	/**
	 * 
	 * @return
	 */
	public boolean isExecuted() {
		return this.executed;
	}

	/**
	 * 
	 */
	public void resetExecuted() {
		this.setExecuted(false);
	}
}
