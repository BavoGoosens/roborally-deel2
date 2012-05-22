package roborally.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;



public class Program {

	private ArrayList<Command> program = new ArrayList<>();

	private String prettyPrintLine = "";

	public Program(String path) throws FileNotFoundException{
		File file = new File(path);
		procesFile(file);
	}

	private void procesFile(File file) throws FileNotFoundException {
		try{
			Scanner scan = new Scanner(file);
			while (scan.hasNextLine()) {
				this.prettyPrintLine.concat(scan.nextLine());
			}
			readProgram();
		}catch (FileNotFoundException esc){
			throw new FileNotFoundException();
		}
	}

	private void readProgram() {
		for(int i = 0; i < this.getPrettyPrint().length();i++){
			char currChar = this.getPrettyPrint().charAt(i);
			
		}
	}

	public String getPrettyPrint(){
		return this.prettyPrintLine;
	}
}
