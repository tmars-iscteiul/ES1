package antiSpamFilter.validations;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class ReadFilesOptimization {
	/**
	 * <p>ReadFilesOptimization -  </br>
	 * </br>
	 * 
	 * Check the variables of the files "AntiSpamFilterProblem.NSGAII.rf" and "AntiSpamFilterProblem.NSGAII.rs" 
	 * relatively to the optimization, choose the results that are better for mixed-use email boxes and after 
	 * show that results on results window.
	 * </p>
	 * 
	 * @author ES1-2017-LIGE-PL-102
	 *
	 */
	
	public static boolean readFile(File f){
		
		
		try {
			Scanner s = new Scanner(f);
			
			while (s.hasNextLine()) {
				
				String nextLine = s.nextLine();
				
				
						
			}
			s.close();
			
		} catch (FileNotFoundException e) {
			
		}
		return true;
	}
	
	
}
