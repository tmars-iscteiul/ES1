package antiSpamFilter.validations;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ReadLOG {
	
	public static boolean readFile(File f) throws FileNotFoundException {
		
		
		try {
			Scanner s = new Scanner(f);
			
			while (s.hasNextLine()) {
				
				String nextLine = s.nextLine();
				
				//Divisão das diversas colunas do ficheiro
				String []hamFileFields= nextLine.split("\t");
				
				//o ficheiro tem de ter pelo menos o ID de cada email 
				if (hamFileFields.length<1)
					return false;
				
				//caso o ficheiro não seja o ham.log nem o spam.log não é relevante para o software
				if (f.getName()!= "ham.log" || f.getName()!= "spam.log")
					return false;
						
				s.close();
			}
		} catch (FileNotFoundException e) {
			e.getStackTrace();
		}
		return true;
	}
	
}
