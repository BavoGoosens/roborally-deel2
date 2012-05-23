package roborally.program;

import java.util.HashSet;
import roborally.model.Entity;
import roborally.model.Robot;
import roborally.model.Wall;
import roborally.property.Energy;
import roborally.property.Position;
import roborally.util.AStarPath;

public class Condition extends Command {
	
	@Override
	public String toString() {
		if (this.energyContained == null)
			return this.conditie.toString();
		return this.conditie.toString()+ " " + this.energyContained.getEnergy();
	}

	private ConditieEnum conditie; 
	
	private Energy energyContained;

	public Condition(ConditieEnum cnd) {
		setConditie(cnd);
	}

	public Condition(ConditieEnum energyAtLeast, double amountEnergy) {
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
	
	public boolean evaluate(Robot robot){
		if (getConditie() == ConditieEnum.AT_ITEM){
			if (robot.getBoard().getEntityOnPosition(robot.getPosition()).size() > 2 ){
				return true;
			}
			return false;
		}else if (getConditie() == ConditieEnum.CAN_HIT_ROBOT){
			Position beginpos = robot.getPosition();
			while(robot.getBoard().isValidPosition(AStarPath.getNextPosition(beginpos, robot.getOrientation()))){
				beginpos = AStarPath.getNextPosition(beginpos, robot.getOrientation());
				HashSet<Entity> content = robot.getBoard().getEntityOnPosition(beginpos);
				if(content != null){
					for (Entity ent : content){
						if (ent instanceof Robot){
							return true;
						}
					}
				}
			}
			return false;
		}else if (getConditie() == ConditieEnum.ENERGY_AT_LEAST){
			if (robot.getEnergy().getEnergy() >= this.getEnergyContained().getEnergy()){
				return true;
			}
			return false;
		}else if (getConditie() == ConditieEnum.WALL){
			Position pos = AStarPath.getNextPosition(robot.getPosition(),robot.getOrientation().getClockwiseOrientation());
			HashSet<Entity> content = robot.getBoard().getEntityOnPosition(pos);
			if(content != null){
				for (Entity ent : content){
					if (ent instanceof Wall){
						return true;
					}
				}
			}
			return false;
		}else
			return true;
	}

}
