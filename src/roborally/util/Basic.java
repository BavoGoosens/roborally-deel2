package roborally.util;

public class Basic extends Command {
	
	private int turnDir;

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

}
