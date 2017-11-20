package antiSpamFilter;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ReadCF {

	public static void read(File file) {

		try {

			Scanner scanner = new Scanner(file);

			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				System.out.println(line);
			}
			// scanner.close();

		} catch (FileNotFoundException e) {
			e.getStackTrace();
		}
	}

	public static void main(String[] args) {

		read(new File("Ficheiros/rules.cf"));

	}

}
