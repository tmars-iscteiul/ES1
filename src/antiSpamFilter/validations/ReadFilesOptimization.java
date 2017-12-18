package antiSpamFilter.validations;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import antiSpamFilter.GUI.AntiSpamFilterStyles.AOptionPane;

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

public class ReadFilesOptimization {	
	int chosenValueIndex;
	ArrayList<Double> FPList= new ArrayList<>();
	ArrayList<Double> FNList= new ArrayList<>();
	
	/**Find value more smaller from the list*/
	private int findMinIndex(ArrayList<Double> valueList){
		int minIndex = 0;
		double value = valueList.get(0);
		
		if (valueList.isEmpty()) {
			minIndex = -1;
		} else {
			for (int i = 0; i < valueList.size(); i++)
				if (value > valueList.get(i)) {
					value = valueList.get(i);
					minIndex = i;
				}
		}
		return minIndex;
	}
	
	/**
	 * Read the file "AntiSpamFilterProblem.NSGAII.rf" 
	 * and find the index that which corresponds to the best set of FP and FN
	 */
	public void readFileRF(File f){
		ArrayList<Double> valueList= new ArrayList<>() ;
		try {
			Scanner s = new Scanner(f);
			while (s.hasNextLine()) {
				String nextLine = s.nextLine();
				String [] line= nextLine.split(" ");
				double fp= Double.parseDouble(line[0]);
				double fn= Double.parseDouble(line[1]);
				FPList.add(fp);
				FNList.add(fn);

				//value of the point closest to the center in order to fulfill the requirements of the mixed mail box
				double value= Math.sqrt(Math.pow(fp, 2)+Math.pow(fn, 2) );
				valueList.add(value);
				
			}
			s.close();
		} catch (FileNotFoundException e) {
			AOptionPane.showMessageDialog(
					null, "File not found. Confirm the optimizer file.", "Error", AOptionPane.ERROR_MESSAGE);
		}
		
		chosenValueIndex= findMinIndex(valueList);
	}
	
	/**Read the file "AntiSpamFilterProblem.NSGAII.rf" and return the line
	 * that have the best configuration of the rules
	 */
	public String[] readFileRS(File f){
		int lineNumber=0;
		
		try {
			Scanner s = new Scanner(f);
			while (s.hasNextLine()) {
				String nextLine = s.nextLine();
				
				if (lineNumber == chosenValueIndex) {
					s.close();
					return nextLine.split((" "));
				}
				
				lineNumber++;
			}
			s.close();
			
		} catch (FileNotFoundException e) {
			AOptionPane.showMessageDialog(
					null, "File not found. Confirm the optimizer file.", "Error", AOptionPane.ERROR_MESSAGE);
		}
		
		return null;
	}
	
	/** Returns the best FP */
	public String getBestFP(){
		return FPList.get(chosenValueIndex).toString();
	}
	
	/** Returns the best FN */
	public String getBestFN(){
		return FNList.get(chosenValueIndex).toString();
	}
	
}
