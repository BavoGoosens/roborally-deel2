package roborally.program;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import roborally.model.Robot;



public class Program {

	private ArrayList<Command> program = new ArrayList<>();

	public ArrayList<Command> getProgram() {
		return this.program;
	}

	public void setProgram(ArrayList<Command> program) {
		this.program = program;
	}

	private ArrayList<String> original = new ArrayList<>();
	
	private String prettyPrintLine;

	public Program(String path) throws FileNotFoundException{
		File file = new File(path);
		procesFile(file);
	} 

	private void procesFile(File file) throws FileNotFoundException {
		try{
			String result = "";
			Scanner scan = new Scanner(file);
			while (scan.hasNextLine()) {
				String str = scan.nextLine();
				result = result + str;
				this.original.add(str);
			}
			
			this.prettyPrintLine = result;
			readProgram();
		}catch (FileNotFoundException esc){
			throw new FileNotFoundException();
		}
	}

	private void readProgram(){
		extractBody(this.prettyPrintLine);
	}

	private void extractBody(String prettyPrintLine) {
		int openHaakskesCount = 0;
		int closedHaakskesCount = 0;
		int beginIdx = 0;
		int eindIdx = 0;
		for (int i = 0; i < prettyPrintLine.length(); i++){
			char currChar = prettyPrintLine.charAt(i);
			if (currChar == '(' && openHaakskesCount == 0){
				beginIdx = i;
				openHaakskesCount++;
			} else if (currChar == '('){
				openHaakskesCount++;
			}else if (currChar == ')'){
				closedHaakskesCount++;
				if (openHaakskesCount == closedHaakskesCount){
					eindIdx = i;
					break;
				}

			}
		}
		String result = prettyPrintLine.substring(beginIdx + 1, eindIdx);
		String[] words = result.split("[^a-z]");
		if (words[0].equals("while")){
			Command cmd = new While(result.substring(5, result.length()));
			this.program.add(cmd);
		} else if (words[0].equals("seq")){
			Sequence seq = new Sequence(result.substring(4, result.length()));
			this.program.add(seq);
		} else if (words[0].equals("if")){
			If ifs = new If(result.substring(2, result.length()));
			this.program.add(ifs);
		} else {
			if (words[0].equals("shoot")){
				Statement bas = new Statement(BasicEnum.SHOOT);
				this.program.add(bas);
			}else if (words[0].equals("move")){
				Statement bas = new Statement(BasicEnum.MOVE);
				this.program.add(bas);
			}else if (words[0].equals("turn")){
				Statement bas = new Statement(BasicEnum.TURN);
				this.program.add(bas);
			}else{
				Statement bas = new Statement(BasicEnum.PICK_UP_AND_USE);
				this.program.add(bas);
			}
		}
		if (prettyPrintLine.substring(eindIdx,prettyPrintLine.length()) != null && 
				prettyPrintLine.substring(eindIdx,prettyPrintLine.length()).length() > 1){
			extractBody( prettyPrintLine.substring(eindIdx,prettyPrintLine.length()));
		}

	}

	public ArrayList<String> getOriginal(){
		return this.original;
	}
	
	public String getPrettyPrint(){
		return this.prettyPrintLine;
	}

	public void execute(Robot robot) {
		for (int i = 0; i < program.size(); i++){
			Command comm = program.get(i);
			comm.execute(robot);
		}
		
	}
}
