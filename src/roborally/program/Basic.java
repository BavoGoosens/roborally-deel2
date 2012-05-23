package roborally.program;

import java.util.HashSet;

import roborally.model.Entity;
import roborally.model.Item;
import roborally.model.Robot;

public class Basic extends Command {
	
	private int turnDir;

	public int getTurnDir() {
		return this.turnDir;
	}

	public void setTurnDir(int turnDir) {
		this.turnDir = turnDir;
	}

	public Basic(BasicEnum en) {
		setEnumOfThis(en);
	}

	public Basic(BasicEnum turn, String string) {
		setEnumOfThis(turn);
		if(string.contains("counterclockwise")){
			this.turnDir = 1;
		}
		this.turnDir = 0;
	}

	private void setEnumOfThis(BasicEnum en) {
		this.enumOfThis = en;
	}

	private BasicEnum enumOfThis;
	
	public BasicEnum getEnumOfThis() {
		return this.enumOfThis;
	}

	@Override
	public void execute(Robot robot){
		if (this.getEnumOfThis() == BasicEnum.MOVE){
			robot.move();
		}else if (this.getEnumOfThis() == BasicEnum.SHOOT){
			robot.shoot();
		}else if (this.getEnumOfThis() == BasicEnum.TURN){
			if (getTurnDir() == 1){
				robot.turnCounterClockWise();
			}
			robot.turnClockWise();
		}else{
			HashSet<Entity> ents = robot.getBoard().getEntityOnPosition(robot.getPosition());
			for (Entity ent : ents){
				if (ent instanceof Item){
					robot.pickUp((Item) ent);
					robot.use((Item) ent);
					break;
				}
			}
		}
		
	}

}
