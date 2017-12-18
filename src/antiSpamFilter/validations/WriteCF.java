package antiSpamFilter.validations;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import antiSpamFilter.GUI.AntiSpamFilterStyles.AOptionPane;
import antiSpamFilter.rules.Rule;

/**
 * <p>WriteCF - the rules file write class</br>
 * </br>
 * This class records the list of rules of the program in
 * the file rules.cf. If the file already exists, it will
 * overwrite it.</p>
 * 
 * @author ES1-2017-LIGE-PL-102
 *
 */

public class WriteCF {
	
	/** Writes a list of rules in a file
	 * 
	 * @param ArrayList<Rule> listOfRules
	 * @param File rulesCF
	 */
	public static void writeCF (ArrayList<Rule> listOfRules, File rulesCF) {
	
		FileWriter writer;

		try {
			//creation of the folder where the rules.cf will be saved
			rulesCF.getParentFile().mkdirs();
			
			//creation of the rules.cf file
			if (!rulesCF.exists()) rulesCF.createNewFile();

			writer = new FileWriter(rulesCF);

			//write the list of rules
			for (Rule rule : listOfRules)
				writer.write(rule.getName() + '\t' + rule.getWeight() + '\n');

			writer.close();
		} catch (IOException e) {
			AOptionPane.showMessageDialog(
					null, "File not found. Confirm the link.", "Error", AOptionPane.ERROR_MESSAGE);
		}           
	

	}
}
