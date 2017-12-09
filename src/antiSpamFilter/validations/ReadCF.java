package antiSpamFilter.validations;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import antiSpamFilter.GUI.AntiSpamFilterStyles.AOptionPane;

/**
 * <p>ReadCF - the rules file validation class</br>
 * </br>
 * This class tests if a .cf file is a valid rules file,
 * checking if it has only a rule between lines or a rule
 * and a weight.</p>
 * 
 * @author ES1-2017-LIGE-PL-102
 *
 */

public class ReadCF {

	/** Reads the rules file and validates it **/
	public static boolean read(File file) {

		String line;

		try {
			//Opens a scanner to read the file
			Scanner scanner = new Scanner(file);

			while (scanner.hasNextLine()) {
				
				//Splits the line to check its fields
				line = scanner.nextLine();
				String[] vetor = line.split("\t");
				
				//Validates the minimum number of line fields
				if (vetor.length > 2) {
					scanner.close();
					return false;
				}	
				
				//Validates if it has a weight and if it is a valid number
				if (vetor.length == 2) {
					try{
				        Double.parseDouble(vetor[1]);
					} catch( Exception e ){
				    	scanner.close();
				    	return false;
				    }
				}
				
				//Validates if the weight value is between -5 and 5
				if (vetor.length == 2 && Math.abs(Double.parseDouble(vetor[1])) > 5) {
					scanner.close();
					return false;
				}

			}
			
			scanner.close();

		} catch (FileNotFoundException e) {
			AOptionPane.showMessageDialog(
					null, "File not found. Confirm the link.", "Error", AOptionPane.ERROR_MESSAGE);
			return false;
		}

		return true;
	}

}