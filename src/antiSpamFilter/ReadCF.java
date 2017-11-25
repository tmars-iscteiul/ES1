package antiSpamFilter;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.apache.commons.lang3.StringUtils;

import antiSpamFilter.AntiSpamFilterStyles.AOptionPane;

public class ReadCF {

	public static boolean read(File file) {

		String line;

		try {

			Scanner scanner = new Scanner(file);

			while (scanner.hasNextLine()) {
							
				line = scanner.nextLine();
				String[] vetor = line.split("\t");
				
				if (vetor.length > 2) {
					scanner.close();
					return false;
				}	
				
				if(vetor.length == 2 && !StringUtils.isNumericSpace(vetor[1])) {
					scanner.close();
					return false;
				}
	
			}
			
			scanner.close();

		} catch (FileNotFoundException e) {
			AOptionPane.showMessageDialog(
					null, "File not found. Confirm the link.", "Error", AOptionPane.ERROR_MESSAGE);
			return false;
		}

		return true;
	}

}