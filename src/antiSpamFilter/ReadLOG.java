package antiSpamFilter;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ReadLOG {

	public static void readFile(File f) {

		try {
			Scanner s = new Scanner(f);

			while (s.hasNextLine()) {
				String nextLine = s.nextLine();

				System.out.println(nextLine);
				
			}

		} catch (FileNotFoundException e) {
			e.getStackTrace();
		}
	}

	public static void main(String[] args) {
		readFile(new File("Ficheiros/ham.log"));
	}

}
