package roborally.program;

import java.util.ArrayList;
import roborally.model.Robot;

public class Sequence extends Command {

	public boolean enterd = false;

	public Sequence(String substring) {
		makeBody(substring);
	}

	private void makeBody(String substring) {
		String cpysubstring = substring.trim();
		while (cpysubstring.length() > 3){
			cpysubstring = cpysubstring.trim();
			cpysubstring = cpysubstring.substring(cpysubstring.indexOf('('));
			String cmd = haalHaakskesWeg(cpysubstring);
			getBody().add(getFirstCommand(cpysubstring));
			cpysubstring = cpysubstring.substring(cmd.length() + 3);
		}
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
