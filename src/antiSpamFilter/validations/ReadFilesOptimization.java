package antiSpamFilter.validations;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import antiSpamFilter.GUI.AntiSpamFilterStyles.AOptionPane;


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
	
	int chosenValueIndex;
	ArrayList<Double> FPList= new ArrayList<>();
	ArrayList<Double> FNList= new ArrayList<>();
	
	//find value more smaller from the list
	public int findMinIndex(ArrayList<Double> valueList){
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
	
	//read the file "AntiSpamFilterProblem.NSGAII.rf" and find the index that which corresponds to the best set of FP and FN
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
	
	//read the file "AntiSpamFilterProblem.NSGAII.rf" and return the line that have the best configuration of the rules
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
	
	public String getBestFP(){
		String bestFP = null;
		
		for(int i =0; i<FPList.size(); i++){
			if (chosenValueIndex==i)
				bestFP= FPList.get(i).toString();
		}
		
		return bestFP;
	}
	public String getBestFN(){
		String bestFN = null ;
		for(int i =0; i<FNList.size(); i++){
			if (chosenValueIndex==i)
				bestFN= FNList.get(i).toString();
		}
		return bestFN;
		
	}
	
}
