package roborally.model;

import java.io.File;
import java.io.FileNotFoundException;
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
			ArrayList<String> lines = new ArrayList<>();
			Scanner scan = new Scanner(file);
			while (scan.hasNextLine()) {
				lines.add(scan.nextLine());
			}
		}catch (FileNotFoundException esc){
			System.err.println(esc.getMessage());
		}
	}
}
