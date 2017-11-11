package antiSpamFilter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import antiSpamFilter.AntiSpamFilterStyles.AOptionPane;

/**
 * @author ES1-2017-LIGE-PL-102
 *
 */
public class EmailStream {

	public static ArrayList<Email> getListOfEmailFromFile (File file, ArrayList<Rule> listOfRules) {
		ArrayList<Email> list = new ArrayList<Email>();
		String fileLine;
		int emailType;
				
		try {
			BufferedReader bufferedReader = new BufferedReader(new FileReader(file));

			try {
				while ((fileLine = bufferedReader.readLine()) != null) {
					//Separation of the line fields
					String[] fileLineList = fileLine.split("\t");

					//emailAtributesList[2] = Email.type
					//emailAtributesList[3] = Email.Id
					String[] emailAtributesList = fileLineList[0].split("/");
					ArrayList<Rule> emailRulesList = new ArrayList<Rule>();

					//List of rules
					for (int i = 1; i < fileLineList.length; i++) {
						//Creation of the list of rules for the e-mail
						//TODO creation of the object Rule
						Rule rule = new Rule();
						emailRulesList.add(rule);
					}

					//Conversion of the type of email
					if (emailAtributesList[2].equals("_SPAM_")) emailType = Email.SPAM;
					else emailType = Email.HAM;

					//Creation of the object Email and the addition to the list
					list.add(new Email(emailAtributesList[3], emailRulesList, emailType));
				}
			} catch (IOException e) {
				AOptionPane.showMessageDialog(null, "Could not read the file", "Error", AOptionPane.ERROR_MESSAGE);
			}

			//Creation of the list of e-mails

			/*fileLine = "xval_initial/9/_spam_/00195.0a543c2780491168160570bb6708af86	"
					+ "BAYES_99	FORGED_OUTLOOK_HTML	FORGED_OUTLOOK_TAGS	FREEMAIL_ENVFROM_END_DIGIT	"
					+ "FREEMAIL_FROM	FREEMAIL_REPLYTO	HTML_MESSAGE	MIME_HTML_ONLY	"
					+ "MISSING_MIMEOLE	MSGID_OUTLOOK_INVALID	NO_RDNS_DOTCOM_HELO	RATWARE_MS_HASH	"
					+ "RCVD_FAKE_HELO_DOTCOM	SPF_FAIL"; */

			try {
				bufferedReader.close();
			} catch (IOException e) {
				AOptionPane.showMessageDialog(null, "Could not read the file.", "Error", AOptionPane.ERROR_MESSAGE);
			}

		} catch (FileNotFoundException e) {
			AOptionPane.showMessageDialog(null, "File not found. Confirm the link.", "Error", AOptionPane.ERROR_MESSAGE);
		}
		
		return list;
	}
	
	public static void writeListOfEmailToFile (File file, ArrayList<Email> list) {
		
	}
}
