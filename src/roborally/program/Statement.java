package roborally.program;

import java.util.HashSet;

import roborally.model.Entity;
import roborally.model.Item;
import roborally.model.Robot;

public class Statement extends Command {

	@Override
	public String toString() {
		if (getEnumOfThis() == StatementEnum.TURN){
			if (this.turnDir == 1)
				return "("+getEnumOfThis().name()+" counterclockwise)";
			return "("+getEnumOfThis().name()+" clockwise)";
		}
		return "("+getEnumOfThis().name()+")";
	}

	private int turnDir;

	public int getTurnDir() {
		return this.turnDir;
	}

	public void setTurnDir(int turnDir) {
		this.turnDir = turnDir;
	}

	public Statement(StatementEnum en) {
		setEnumOfThis(en);
	}

	public Statement(StatementEnum turn, String string) {
		setEnumOfThis(turn);
		if(string.contains("counterclockwise")){
			this.turnDir = 1;
		}
		this.turnDir = 0;
	}

	private void setEnumOfThis(StatementEnum en) {
		this.enumOfThis = en;
	}

	private StatementEnum enumOfThis;

	public StatementEnum getEnumOfThis() {
		return this.enumOfThis;
	}

	@Override
	public void executeNext(Robot robot){
		if (this.getEnumOfThis() == StatementEnum.MOVE && !this.isExecuted()){
			robot.move();
			setExecuted(true);
		}else if (this.getEnumOfThis() == StatementEnum.SHOOT && !this.isExecuted()){
			robot.shoot();
			setExecuted(true);
		}else if (this.getEnumOfThis() == StatementEnum.TURN && !this.isExecuted()){
			if (getTurnDir() == 1){
				robot.turnCounterClockWise();
				setExecuted(true);
			}
			robot.turnClockWise();
			setExecuted(true);
		}else{
			if (!this.isExecuted()){
				HashSet<Entity> ents = robot.getBoard().getEntityOnPosition(robot.getPosition());
				for (Entity ent : ents){
					if (ent instanceof Item){
						robot.pickUp((Item) ent);
						robot.use((Item) ent);
						setExecuted(true);
					}
				}
			}
		}
	}
}
