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
		
		ArrayList<Email> listOfEmails1 = EmailStream.getListOfEmailsFromFile(null, 
				new File("Files/spam_test_valid.log"), listOfRules, Email.SPAM);
		
		assertTrue(listOfEmails1.size() == 2);
		assertTrue(listOfEmails1.get(0).getID().equals("00938.cdac5333fc78f7128fd8f2905fe4b89b"));
	}

}
