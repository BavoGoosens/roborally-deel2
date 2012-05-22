package roborally.util;

import roborally.property.Energy;

public class Conditie extends Command {
	
	@Override
	public String toString() {
		if (this.energyContained == null)
			return this.conditie.toString();
		return this.conditie.toString()+" " + this.energyContained.getEnergy();
	}

	private ConditieEnum conditie; 
	
	private Energy energyContained;

	public Conditie(ConditieEnum cnd) {
		setConditie(cnd);
	}

	public Conditie(ConditieEnum energyAtLeast, double amountEnergy) {
		setConditie(energyAtLeast);
		setEnergyContained(new Energy(amountEnergy));
	}

	public ConditieEnum getConditie() {
		return this.conditie;
	}

	public void setConditie(ConditieEnum conditie) {
		this.conditie = conditie;
	}

	public Energy getEnergyContained() {
		return this.energyContained;
	}

	public void setEnergyContained(Energy energyContained) {
		this.energyContained = energyContained;
	}

}
