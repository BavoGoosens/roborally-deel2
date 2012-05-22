package roborally.util;

public class Conditie extends Command {
	
	private ConditieEnum conditie; 

	public Conditie(ConditieEnum cnd) {
		setConditie(cnd);
	}

	public Conditie(ConditieEnum energyAtLeast, double amountEnergy) {
		// TODO Auto-generated constructor stub
	}

	public ConditieEnum getConditie() {
		return conditie;
	}

	public void setConditie(ConditieEnum conditie) {
		this.conditie = conditie;
	}

}
