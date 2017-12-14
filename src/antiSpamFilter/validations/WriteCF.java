package antiSpamFilter.validations;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

import antiSpamFilter.AntiSpamFilterAutomaticConfiguration;

public class WriteCF {


	public static void writeCF() throws FileNotFoundException {
	
		ReadFilesOptimization a = new ReadFilesOptimization();
		File file = new File("experimentBaseDirectory/referenceFronts/AntiSpamFilterProblem.NSGAII.rs");
		
		ArrayList<Double> pelDouble = new ArrayList<Double>();
		
		ArrayList<String> pesosEscolhidosLista = a.readFileRS(file);
		
		for(String foo: pesosEscolhidosLista) {
			double foo1= Double.parseDouble(foo);
			pelDouble.add(foo1);
		}

		System.out.println(pelDouble);

	}

	public static void main(String[] args) {
		try {
			writeCF();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
