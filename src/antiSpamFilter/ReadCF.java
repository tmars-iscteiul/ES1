package antiSpamFilter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import org.apache.commons.lang3.StringUtils;

public class ReadCF {

	public static boolean read(File file) throws FileNotFoundException {

		String line = null;
		ArrayList<String> lineList = new ArrayList<String>();
		boolean regraValida = false;

		try {

			Scanner scanner = new Scanner(file);

			while (scanner.hasNextLine()) {
				
			
				line = scanner.nextLine();
				line.split(" ");
				lineList.add(line);
				String[] vetor = line.split("\t");
				
			


				if (vetor.length > 2) 
					return false;


			
				if(vetor.length == 2 && !StringUtils.isNumericSpace(vetor[1]))
					return false;
					
				System.out.println(line);
			
			}
			
			scanner.close();

		} catch (FileNotFoundException e) {
			e.getStackTrace();
		}
		return true;
	}

	public static void main(String[] args) throws FileNotFoundException {

		read(new File("Ficheiros/rules.cf"));
		

	}

}