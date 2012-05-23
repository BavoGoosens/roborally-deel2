package roborally.program;

import java.util.ArrayList;

import roborally.model.Robot;

public class Sequentie extends Command {
	
	public Sequentie(String substring) {
		makeBody(substring);
	}

	private void makeBody(String substring) {
		substring = substring.trim();
		while (substring.length() > 3){
			substring = substring.trim();
			substring = substring.substring(substring.indexOf('('));
			String cmd = haalHaakskesWeg(substring);
			getBody().add(getFirstCommand(substring));
			substring = substring.substring(cmd.length() + 3);
		}
	}

	@Override
	public void execute (Robot robot){
		for (int i = 0; i < getBody().size(); i++){
			Command comm = getBody().get(i);
			comm.execute(robot);
		}
	}
	
	private ArrayList<Command> body = new ArrayList<>();

	public ArrayList<Command> getBody() {
		return this.body;
	}
}
