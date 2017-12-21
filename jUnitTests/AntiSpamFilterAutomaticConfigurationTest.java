package antiSpamFilter.tests;

import java.io.File;
import java.util.ArrayList;

import org.junit.Test;

import antiSpamFilter.AntiSpamFilterAutomaticConfiguration;
import antiSpamFilter.rules.Rule;

public class AntiSpamFilterAutomaticConfigurationTest {

	@Test
	public final void testAntiSpamFilterAutomaticConfiguration() {
		File spamFile = new File("./Files/spam.log");
		File hamFile = new File("./Files/ham.log");
		File rulesFile = new File("./Files/rules.cf");
		
		AntiSpamFilterAutomaticConfiguration main1 = new AntiSpamFilterAutomaticConfiguration();
		main1.runOptimization();
		main1.validateFilesAndBuildRulesAndEmails(spamFile, hamFile, rulesFile);
		main1.validateFilesAndBuildRulesAndEmails(spamFile, hamFile, rulesFile);
		
		main1.runOptimization();
		main1.addRuleToList("TEST",	0.0);
		main1.setConfigureWindowVisible(true);
		main1.getListOfRules();
		main1.isRulesChanged();
		main1.getBestFN();
		main1.getBestFP();
		main1.saveListOfRules(new ArrayList<Rule>());
		
		main1.configureWindowClose();
		
		AntiSpamFilterAutomaticConfiguration main2 = new AntiSpamFilterAutomaticConfiguration();
		main2.validateFilesAndBuildRulesAndEmails(
				new File("./Files/spam.log"), new File("./Files/ham.log"), new File("fake.cf"));
		main2.validateFilesAndBuildRulesAndEmails(
				new File("./Files/spam.log"), new File("fake.log"), new File("rules.cf"));
		main2.validateFilesAndBuildRulesAndEmails(
				new File("fake.log"), new File("./Files/ham.log"), new File("rules.cf"));
		
	}

}
