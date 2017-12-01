package antiSpamFilter.tests;

import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;

import org.junit.Test;

import antiSpamFilter.emails.Email;
import antiSpamFilter.emails.EmailStream;
import antiSpamFilter.rules.Rule;
import antiSpamFilter.rules.RuleStream;

public class EmailStreamTest {

	@Test
	public final void testGetListOfEmailsFromFile() {
		ArrayList<Rule> listOfRules = new RuleStream(new File("Files/rules.cf")).getRuleStream();
		
		ArrayList<Email> listOfEmails1 = EmailStream.getListOfEmailsFromFile(
				new File("Files/spam_test_valid.log"), listOfRules, Email.SPAM);
		
		ArrayList<Email> listOfEmails2 = EmailStream.getListOfEmailsFromFile(
				new File("Files/test.log"), listOfRules, Email.SPAM);
		
		ArrayList<Email> listOfEmails3 = EmailStream.getListOfEmailsFromFile(
				new File("Files/spam_test_invalid_rule.log"), listOfRules, Email.SPAM);
				
	}

}
