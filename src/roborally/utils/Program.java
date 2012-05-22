package roborally.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;



public class Program {

	private ArrayList<String> program = new ArrayList<>();

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
		String prettyPrintCopy = this.prettyPrintLine;
		while (Pattern.matches("[a-zA-Z]", prettyPrintCopy)){
			
		}
	}

	public String getPrettyPrint(){
		return this.prettyPrintLine;
	}
}
