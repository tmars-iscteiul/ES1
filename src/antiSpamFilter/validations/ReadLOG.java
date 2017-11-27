package antiSpamFilter.validations;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import antiSpamFilter.GUI.AntiSpamFilterStyles.AOptionPane;

public class ReadLOG {
	
	public static boolean readFile(File f){
		
		
		try {
			Scanner s = new Scanner(f);
			
			while (s.hasNextLine()) {
				
				String nextLine = s.nextLine();
				
				//Split the fields in tabs
				String []hamFileFields= nextLine.split("\t");
				
				//Validates the minimum number of line fields
				if (hamFileFields.length<1){
					s.close();
					return false;
				}
					
				
				//Validates if the file is ham.log or spam.log
				if (f.getName()!= "ham.log" || f.getName()!= "spam.log"){
					s.close();
					return false;
				}
						
				s.close();
			}
		} catch (FileNotFoundException e) {
			AOptionPane.showMessageDialog(
					null, "File not found. Confirm the link.", "Error", AOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}
	
}
