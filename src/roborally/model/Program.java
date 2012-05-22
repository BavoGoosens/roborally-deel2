package roborally.model;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;


public class Program {

	private ArrayList<Commands> program = new ArrayList<>();

	public Program(String path){
		File file = new File(path);
		procesFile(file);
	}

	private void procesFile(File file) {
		try{
			ArrayList<String> lines = new ArrayList<>()
			Scanner scan = new Scanner(file);
			while (scan.hasNextLine()) {
				
			}
		}
		ArrayList<String> lines = new ArrayList<>();
		try {
			//
			// Create a new Scanner object which will read the data 
			// from the file passed in. To check if there are more 
			// line to read from it we check by calling the 
			// scanner.hasNextLine() method. We then read line one 
			// by one till all line is read.
			//
			Scanner scanner = new Scanner(file);
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				System.out.println(line);
			}
			27.
		} catch (FileNotFoundException e) {
			28.
			e.printStackTrace();
			29.
		}

	}

}
