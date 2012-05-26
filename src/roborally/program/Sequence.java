package roborally.program;

import java.util.ArrayList;
import roborally.model.Robot;

/**
 * Een klasse om sequenties voor te stellen.
 * 
 * @author 	Bavo Goosens (1e bachelor informatica, r0297884), Samuel Debruyn (1e bachelor informatica, r0305472)
 * 
 * @version 1.0
 */
public class Sequence extends Command {

	public boolean entered = false;

	public Sequence(String substring) {
		makeBody(substring);
	}

	private void makeBody(String substring) {
		String cpysubstring = substring.trim();
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
			getNextCommand().executeNext(robot);
			updateIsDoneAll();
		}
	}


	private void updateIsDoneAll() {
		int elementCount = this.getBody().size(); 
		int executedCount = 0;
		for (Command comm : getBody()){
			if (comm.isExecuted())
				executedCount += 1;
		}
		if (elementCount == executedCount)
			setDoneAll(true);
		
	}

	private Command getNextCommand() {
		for (Command comm : getBody()){
			if (!comm.isExecuted())
				return comm;
		}
		return null;
	}

	

	@Override
	public void resetExecuted() {
		setDoneAll(false);
		for (Command comm : getBody()){
			comm.resetExecuted();
		}
	}



	private boolean doneAll;

	private ArrayList<Command> body = new ArrayList<Command>();

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
