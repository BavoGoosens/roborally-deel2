package roborally.program;

import java.util.HashSet;

import roborally.model.Entity;
import roborally.model.Item;
import roborally.model.Robot;

public class Statement extends Command {

	public boolean enterd = false;

	public boolean isEnterd() {
		return this.enterd;
	}

	public void setEnterd(boolean enterd) {
		this.enterd = enterd;
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
	public int execute(int n , Robot robot){
		if (n > 0){
			//er kan nog een command uitgevoerd worden.
			if (this.getEnumOfThis() == StatementEnum.MOVE){
				robot.move();
				this.setEnterd(true);
				//teruggeven hoev basics er nog uitgevoerd diene te worden;
				return n - 1;
			}else if (this.getEnumOfThis() == StatementEnum.SHOOT){
				robot.shoot();
				this.setEnterd(true);
				return n - 1;
			}else if (this.getEnumOfThis() == StatementEnum.TURN){
				if (getTurnDir() == 1){
					robot.turnCounterClockWise();
					this.setEnterd(true);
					return n - 1;
				}
				robot.turnClockWise();
				this.setEnterd(true);
				return n - 1;
			}else{
				HashSet<Entity> ents = robot.getBoard().getEntityOnPosition(robot.getPosition());
				for (Entity ent : ents){
					if (ent instanceof Item){
						robot.pickUp((Item) ent);
						robot.use((Item) ent);
						this.setEnterd(true);
						return n - 1;
					}
				}
			}
		}
		//wrd niets uitgevoerd
		return 0;
	}

}
