package roborally.program;

import java.util.ArrayList;
import roborally.model.Robot;

public class Sequence extends Command {

	public boolean entered = false;

	public Sequence(String substring) {
		makeBody(substring);
	}

	private void makeBody(String substring) {
		String cpysubstring = substring.trim();
		cpysubstring = cpysubstring.trim();
		cpysubstring = cpysubstring.substring(cpysubstring.indexOf('('));
		cpysubstring = haalHaakskesWeg(cpysubstring);
		while (cpysubstring.length() > 3){
			cpysubstring = cpysubstring.trim();
			cpysubstring = cpysubstring.substring(cpysubstring.indexOf('('));
			String cmd = haalHaakskesWeg(cpysubstring);
			Command toBeAdded = getFirstCommand(cmd);
			getBody().add(toBeAdded);
			cpysubstring = cpysubstring.substring(toBeAdded.toString().length());
		}
	}

	@Override
	public String toString() {
		String result = "";
		for (Command cmd : getBody()){
			result = result + cmd.toString();
		}
		return result;
	}

	@Override
	public void executeNext(Robot robot){
		if (!isDoneAll()){
			for (int i = 0 ; i < getBody().size(); i ++ ){
				Command cmd = getBody().get(i);
				if (!cmd.isExecuted()){
					cmd.executeNext(robot);
					if (i == getBody().size()-1){
						setDoneAll(true);
					}
					break;
				}
			}
		}
	}
	
	@Override
	public void resetExecuted() {
		setDoneAll(false);
		for (Command comm : getBody()){
			comm.resetExecuted();
		}
	}



	private boolean doneAll;

	private ArrayList<Command> body = new ArrayList<>();

	public ArrayList<Command> getBody() {
		return this.body;
	}

	public boolean isDoneAll() {
		return this.doneAll;
	}

	public void setDoneAll(boolean doneAll) {
		this.doneAll = doneAll;
	}
}
