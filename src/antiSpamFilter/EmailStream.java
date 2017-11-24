package antiSpamFilter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import antiSpamFilter.AntiSpamFilterStyles.AOptionPane;

/**
 * @author ES1-2017-LIGE-PL-102
 *
 */
public class EmailStream {

	public static ArrayList<Email> getListOfEmailsFromFile (File file, ArrayList<Rule> listOfRules) {
		ArrayList<Email> listOfAllEmails = new ArrayList<Email>();
		String fileLine;
		int emailType, rulePosition;
		double finalWeight = 0;
		String[] fileLineList, emailAtributesList;
		
		//Implementation of the comparator of objects Rule
		Comparator<Rule> compareName = new Comparator<Rule>() {
			public int compare(Rule r1, Rule r2) {
				return r1.getName().compareTo(r2.getName());
			}
		};
		
		//Start the reading		
		try {
			BufferedReader bufferedReader = new BufferedReader(new FileReader(file));

			try {
				while ((fileLine = bufferedReader.readLine()) != null) {
					//Separation of the line fields
					fileLineList = fileLine.split("\t");

					//emailAtributesList[2] = Email.type | emailAtributesList[3] = Email.Id
					emailAtributesList = fileLineList[0].split("/");
					ArrayList<Integer> emailRulesList = new ArrayList<Integer>();

					//List of rules if exist
					if (fileLineList.length > 1) {
						for (int i = 1; i < fileLineList.length; i++) {
							//Creation of the list of rules for the e-mail
							rulePosition = Collections.binarySearch(
									listOfRules, new Rule(fileLineList[i],0), compareName);

							emailRulesList.add(rulePosition);

							//Increment of the final weight of the email
							finalWeight += listOfRules.get(rulePosition).getWeight();
						}
					}
					
					//Conversion of the type of email
					if (emailAtributesList[2].equals("_SPAM_")) emailType = Email.SPAM;
					else emailType = Email.HAM;

					//Creation of the object Email and the addition to the list
					listOfAllEmails.add(new Email(emailAtributesList[3], emailRulesList, emailType, finalWeight));
				}
				
			} catch (IOException e) {
				AOptionPane.showMessageDialog(
						null, "Could not read the file", "Error", AOptionPane.ERROR_MESSAGE);
				return null;
			}

			try {
				bufferedReader.close();
			} catch (IOException e) {
				AOptionPane.showMessageDialog(
						null, "Could not read the file.", "Error", AOptionPane.ERROR_MESSAGE);
				return null;
			}

		} catch (FileNotFoundException e) {
			AOptionPane.showMessageDialog(
					null, "File not found. Confirm the link.", "Error", AOptionPane.ERROR_MESSAGE);
			return null;
		}
		
		return listOfAllEmails;
	}
	
	public static void writeListOfEmailsToFile (File file, ArrayList<Email> list) {
		//TODO Implementation of the write function
	}
}
