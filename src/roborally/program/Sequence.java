package roborally.program;

import java.util.ArrayList;

import roborally.model.Robot;

public class Sequence extends Command {

	public boolean enterd = false;

	public Sequence(String substring) {
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
	public int execute( int n ,Robot robot){
		if (n != 0){
			//er ken ng command uitgevoerd worden.
			for (int i = 0; i < getBody().size(); i++){
				Command comm = getBody().get(i);
				return comm.execute(n , robot);
			}
		}
		//n heeft 0 bereikt en de stappen zijn allemaal overlopen.
		return 0;
	}

	private ArrayList<Command> body = new ArrayList<>();

	public ArrayList<Command> getBody() {
		return this.body;
	}
}
