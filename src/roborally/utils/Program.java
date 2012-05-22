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
		
	}

	public String getPrettyPrint(){
		return this.prettyPrintLine;
	}
}
