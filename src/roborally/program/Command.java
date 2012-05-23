package roborally.program;

import roborally.model.Robot;


/**
 * Een klasse om commando's voor te stellen.
 * 
 * @author 	Bavo Goosens (1e bachelor informatica, r0297884), Samuel Debruyn (1e bachelor informatica, r0305472)
 * 
 * @version 3.0
 */
public class Command {

	/**
	 * Methode om overeenkomstige haaksjes weg te halen
	 *  en de omvatte string terug te geven.
	 * 
	 * @param 	str
	 * 			De string waarvan je de haakjes wilt verwijderen.
	 * @return	Als er overeenkomstige haakjes gevonden worden zal de omsloten string 
	 * 			terugggeven worden. In het andere geval wordt de beginstring teruggeven.
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
	 * Een methode om het eerste commando uit een opgegeven string terug te vinden.
	 * 
	 * @param 	substr
	 * 			De string waaruit het eerst voorkomende commando moet gevonden worden.
	 * 
	 * @return	Geeft het eerst voorkomende Commando terug.
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
	 * Deze methode gaat 2 basic conditions uit de opgegeven string halen.
	 * 
	 * @param  	sbstr
	 * 			De subtring waarin de 2 condities zich bevinden.
	 * 
	 * @return 	Een 1-dimensionele array van grootte 2 met de beide condities in.
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
	 * Deze methode dient voor bij een energy-at-least conditie de hoeveelheid energie uit de opgegeven string terug te vinden.
	 * 
	 * @param 	sbstr
	 * 			De String waarin moet gezocht worden.
	 * 
	 * @return	Geeft een Double terug gevonden uit de opgegeven string.
	 */
	@SuppressWarnings("boxing")
	protected Double getAmountEnergy(String sbstr) {
		int beginIdx = sbstr.indexOf(' ');
		int eindIdx = sbstr.indexOf(')');
		String amount = sbstr.substring(beginIdx,eindIdx);
		return Double.parseDouble(amount);
	}

	/**
	 * Methode die de basic conditie behorende tot een NOT uit de ogegeven string gaat halen.
	 * 
	 * @param 	sbstr
	 * 			De string waarin de conditie zich bevindt.
	 * 
	 * @return	Condition
	 * 			De eerstgevonden basic conditie behorende tot de not. 
	 */
	protected Condition getNotCond(String sbstr) {
		String[] condIndeling = sbstr.split("\\) ");
		Condition cond1 = getBasicConditie(condIndeling[0]);
		return cond1;
	}

	/**
	 * Methode om de eerste basic conditie te vinden in een opgegeven string.
	 * 
	 * @param 	str
	 * 			De string waarin de conditie zicht bevindt.
	 * 
	 * @return	Condition 
	 * 			De eerst gevonden basic conditie in de opgegeven string.
	 * 
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
	 * Methode om het volgende onuitgevoerde commando uit te voeren.
	 * 
	 * @param	robot
	 * 			De robot die het commando uitvoert. 
	 */
	public void executeNext(Robot robot){}

	/**
	 * boolean die bijhoudt of een commando al uitgevoerd is.
	 */
	protected boolean executed = false;
	
	/**
	 * Methode om de staat van executed in te stellen.
	 * 
	 * @param 	ex
	 * 			De booleaanse waarde die executed dient te krijgen.
	 * 
	 */
	public void setExecuted(boolean ex){
		this.executed = ex;
	}

	/**
	 * Methdode om de staat van executed terug te geven.
	 * 
	 * @return	boolean 
	 * 			Geeft de booleaanse waarde van executed weer. 
	 */
	public boolean isExecuted() {
		return this.executed;
	}

	/**
	 * Deze methode gaat de staat van executed resetten.
	 */
	public void resetExecuted() {
		this.setExecuted(false);
	}
}
