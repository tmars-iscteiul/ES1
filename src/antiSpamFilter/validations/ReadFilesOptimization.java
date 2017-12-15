package antiSpamFilter.validations;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.ListIterator;
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
	
	int chosenValueIndex;
	ArrayList<Double> FPList= new ArrayList<>();
	ArrayList<Double> FNList= new ArrayList<>();
	
	//find value more smaller from the list
	public <T extends Comparable<Double>> int findMinIndex(ArrayList<Double> valueList){
		int minIndex;
		if (valueList.isEmpty()) {
			minIndex = -1;
		} else {
			final ListIterator<Double> itr = valueList.listIterator();
			Double min = itr.next(); // first element as the current minimum
			minIndex = itr.previousIndex();
			while (itr.hasNext()) {
				final Double curr = itr.next();
				if (curr.compareTo(min) < 0) {
					min = curr;
					minIndex = itr.previousIndex();
				}
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
		} catch (FileNotFoundException e) { }
		chosenValueIndex= findMinIndex(valueList);
	}
	
	//read the file "AntiSpamFilterProblem.NSGAII.rf" and return the line that have the best configuration of the rules
	public ArrayList<String> readFileRS(File f){
		int lineNumber=0;
		ArrayList<String> chosenWeightsList= new ArrayList<>();
		try {
			Scanner s = new Scanner(f);
			while (s.hasNextLine()) {
				int bestLine= chosenValueIndex+1;
				lineNumber=lineNumber+1;
				String nextLine = s.nextLine();
				String [] lineVector= nextLine.split((" "));
				if (lineNumber==bestLine){
					for(int i=0; i<lineVector.length;i++){
						chosenWeightsList.add(lineVector[i]);
					}
				}
			}
			s.close();
		} catch (FileNotFoundException e) { }
		return chosenWeightsList;
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
