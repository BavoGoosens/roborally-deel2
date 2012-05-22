package roborally.utils;

public class Basic extends Command {
	
	public Basic(BasicEnum en) {
		setEnumOfThis(en);
	}

	private void setEnumOfThis(BasicEnum en) {
		this.enumOfThis = en;
	}

	private BasicEnum enumOfThis;

}
