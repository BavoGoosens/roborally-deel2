package roborally.program;

import java.util.ArrayList;

public class Sequentie extends Command {
	
	public Sequentie(String substring) {
		makeBody(substring);
	}

	private void makeBody(String substring) {
		substring = substring.trim();
		while (substring.length() > 3){
			String cmd = haalHaakskesWeg(substring);
			body.add(getFirstCommand(substring));
			substring = substring.trim();
			substring = substring.substring(cmd.length() + 2);
		}
	}

	private ArrayList<Command> body = new ArrayList<>();

}
