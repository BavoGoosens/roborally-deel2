package roborally.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;



public class Program {

	private ArrayList<Command> program = new ArrayList<>();

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
				result = result + scan.nextLine();
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
			While cmd = new While(result.substring(5, result.length()));
			this.program.add(cmd);
		} else if (words[0].equals("seq")){
			Sequentie seq = new Sequentie(result.substring(3, result.length()));
			this.program.add(seq);
		} else if (words[0].equals("if")){
			If ifs = new If(result.substring(2, result.length()));
			this.program.add(ifs);
		} else {
			if (words[0].equals("shoot")){
				Basic bas = new Basic(BasicEnum.SHOOT);
				this.program.add(bas);
			}else if (words[0].equals("move")){
				Basic bas = new Basic(BasicEnum.MOVE);
				this.program.add(bas);
			}else if (words[0].equals("turn")){
				Basic bas = new Basic(BasicEnum.TURN);
				this.program.add(bas);
			}else{
				Basic bas = new Basic(BasicEnum.PICK_UP_AND_USE);
				this.program.add(bas);
			}
		}
		if (prettyPrintLine.substring(eindIdx,prettyPrintLine.length()) != null && 
				prettyPrintLine.substring(eindIdx,prettyPrintLine.length()).length() > 1){
			extractBody( prettyPrintLine.substring(eindIdx,prettyPrintLine.length()));
		}

	}

	public String getPrettyPrint(){
		return this.prettyPrintLine;
	}
}
