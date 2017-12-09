package antiSpamFilter.validations;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import antiSpamFilter.GUI.AntiSpamFilterStyles.AOptionPane;

/**
 * <p>ReadLOG - the ham file and spam file validation class</br>
 * </br>
 * This class tests if a .log file is a valid ham file or spam file,
 * checking if it has at least the ID of the email .</p>
 * 
 * @author ES1-2017-LIGE-PL-102
 *
 */

public class ReadLOG {
	
	/** Reads the email log file and validates it **/
	public static boolean readFile(File f){
		
		
		try {
			Scanner s = new Scanner(f);
			
			while (s.hasNextLine()) {
				
				String nextLine = s.nextLine();
				
				//Split the fields in tabs
				String []hamFileFields= nextLine.split("\t");
				
				//Validates the minimum number of line fields
				if (hamFileFields.length<=1){
					s.close();
					return false;
				}
						
			}
			s.close();
			
		} catch (FileNotFoundException e) {
			AOptionPane.showMessageDialog(
					null, "File not found. Confirm the link.", "Error", AOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}
	
}