package antiSpamFilter.emails;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

import antiSpamFilter.AntiSpamFilterAutomaticConfiguration;
import antiSpamFilter.GUI.AntiSpamFilterStyles;
import antiSpamFilter.GUI.AntiSpamFilterStyles.AOptionPane;
import antiSpamFilter.rules.Rule;

/**
 * <p>EmailStream - the creation of the object Email through the log files</br>
 * </br>
 * The EmailStream class reads the email log files and extracts the information.
 * This class returns the list of emails read from the file. 
 * It is important to assure that the log files are not corrupted and were before
 * validated.</p>
 * 
 * @author ES1-2017-LIGE-PL-102
 *
 */

public class EmailStream {

	public static ArrayList<Email> getListOfEmailsFromFile (
			AntiSpamFilterAutomaticConfiguration main, File file, ArrayList<Rule> listOfRules, int emailType) {
		
		ArrayList<Email> listOfAllEmails = new ArrayList<Email>();
		int rulePosition;
		double finalWeight = 0;
		String[] fileLineList;
		 
		//Implementation of the comparator of objects Rule
		Comparator<Rule> compareName = new Comparator<Rule>() {
			public int compare(Rule r1, Rule r2) {
				return r1.getName().compareTo(r2.getName());
			}
		};
		
		//Start the reading		
		try {
			Scanner scanner = new Scanner(new FileReader(file));

			while (scanner.hasNextLine()) {
				
				//Separation of the line fields
				fileLineList = scanner.nextLine().split("\t");

				ArrayList<Integer> emailRulesList = new ArrayList<Integer>();

				//List of rules if exists
				if (fileLineList.length > 1) {
					for (int i = 1; i < fileLineList.length; i++) {
						//Creation of the list of rules for the e-mail
						rulePosition = Collections.binarySearch(
								listOfRules, new Rule(fileLineList[i],0), compareName);
						
						//check if the rule exists and asks the user what to do
						if (rulePosition < 0) {
							new AntiSpamFilterStyles().new AOptionPane();
							Object[] optionValues = { "Add rule", "Ignore rule", "Cancel" };
							Object selectedValue = AOptionPane.showOptionDialog(null,
							"Rule: " + fileLineList[i], "New rules detected in the emails log",
							AOptionPane.DEFAULT_OPTION, AOptionPane.WARNING_MESSAGE,null,
							optionValues, optionValues[0]);
							
							//if the user chooses to add the rule
							if (selectedValue.equals(0)) {
								main.addRuleToList(fileLineList[i], 0.0);
								listOfRules.add(new Rule(fileLineList[i], 0.0));
								Collections.sort(listOfRules);
								
								System.out.println("Entrei");

								rulePosition = Collections.binarySearch(
										listOfRules, new Rule(fileLineList[i],0), compareName);

								emailRulesList.add(rulePosition);

							}
							//if the user chooses to cancel operation
							else if (selectedValue.equals(2)) {
								scanner.close();
								return null;
							}

						}
						//Increment of the final weight of the email
						else {
							emailRulesList.add(rulePosition);
							finalWeight += listOfRules.get(rulePosition).getWeight();
						}
					}
				}
				
				//Creation of the object Email and the addition to the list
				listOfAllEmails.add(new Email(fileLineList[0], emailRulesList, emailType, finalWeight));
			}

			scanner.close();

		} catch (FileNotFoundException e) {
			AOptionPane.showMessageDialog(
					null, "File not found. Confirm the link.", "Error", AOptionPane.ERROR_MESSAGE);
			return null;
		}
		
		return listOfAllEmails;
	}
	
}
