package roborally.util;


public class Command {
	
	public static String haalHaakskesWeg(String str){
		String substr = str;
		int openHaakskesCount = 0;
		int closedHaakskesCount = 0;
		int beginIdx = 0;
		int eindIdx = 0;
		for (int i = 0; i < substr.length(); i++){
			char currChar = substr.charAt(i);
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
		return substr = substr.substring(beginIdx + 1, eindIdx).trim();
	}
}
