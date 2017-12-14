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
	
	static int chosenValueIndex;
	
	//find value more smaller from the list
	public static <T extends Comparable<Double>> int findMinIndex(ArrayList<Double> valueList){
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
	public static void readFileRF(File f){
		ArrayList<Double> valueList= new ArrayList<>() ;
		try {
			Scanner s = new Scanner(f);
			while (s.hasNextLine()) {
				String nextLine = s.nextLine();
				String [] line= nextLine.split(" ");
				double fp= Double.parseDouble(line[0]);
				double fn= Double.parseDouble(line[1]);
				
				//value of the point closest to the center in order to fulfill the requirements of the mixed mail box
				double value= Math.sqrt(Math.pow(fp, 2)+Math.pow(fn, 2) );
				valueList.add(value);
				
			}
			s.close();
		} catch (FileNotFoundException e) { }
		
		System.out.println(valueList);
		chosenValueIndex= findMinIndex(valueList);
		System.out.println("Indice escolhido: "+chosenValueIndex);
		
	}
	
	//read the file "AntiSpamFilterProblem.NSGAII.rf" and return the line that have the best configuration of the rules
	public static String readFileRS(File f){
		int lineNumber=0;
		String pesosEscolhidos = null;
		try {
			Scanner s = new Scanner(f);
			while (s.hasNextLine()) {
				int bestLine= chosenValueIndex+1;
				lineNumber=lineNumber+1;
				String nextLine = s.nextLine();
				if (lineNumber==bestLine){
					pesosEscolhidos=nextLine;
				}
			}
			s.close();
		} catch (FileNotFoundException e) { }
		System.out.println("nº da linha escolhida: " + lineNumber);
		System.out.println(pesosEscolhidos);
		return pesosEscolhidos;
		
	}
	
}
