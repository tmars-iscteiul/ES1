package antiSpamFilter;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class ReadCF {

	public static void read(File file) {

		String line = null;
		ArrayList<String> lineList = new ArrayList<String>();
		boolean regraValida = false;

		try {

			Scanner scanner = new Scanner(file);

			while (scanner.hasNextLine()) {

				line = scanner.nextLine();
				line.split(" ");
				lineList.add(line);
				String[] vetor = line.split(" ");


				if (vetor.length == 1) 
					regraValida = true;

					
				System.out.println("regra valida: " + line + "---->"+ regraValida);

				regraValida = false;
			}
			
			scanner.close();

		} catch (FileNotFoundException e) {
			e.getStackTrace();
		}
	}
	
	
	

	public static void main(String[] args) {

		read(new File("Ficheiros/rules.cf"));

	}

}