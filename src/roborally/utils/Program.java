package roborally.utils;

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

	private String[] extractBody(String prettyPrintLine) {
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
		String result [] = new String[2];
		result[0] = prettyPrintLine.substring(beginIdx + 1, eindIdx);
		result[1] = prettyPrintLine.substring(eindIdx,prettyPrintLine.length());
		return result;
	}

	public String getPrettyPrint(){
		return this.prettyPrintLine;
	}
}
