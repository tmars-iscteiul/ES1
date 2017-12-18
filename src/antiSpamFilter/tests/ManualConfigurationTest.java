package antiSpamFilter.tests;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

import antiSpamFilter.AntiSpamFilterAutomaticConfiguration;
import antiSpamFilter.AntiSpamFilterManualConfiguration;

public class ManualConfigurationTest {

	@Test
	public final void testGetRuleWeightString() {		
		AntiSpamFilterAutomaticConfiguration main = new AntiSpamFilterAutomaticConfiguration();
		main.validateFilesAndBuildRulesAndEmails(
				new File("Files/spam.log"), new File("Files/ham.log"), new File("Files/rules.cf"));
		
		AntiSpamFilterManualConfiguration manual = new AntiSpamFilterManualConfiguration(main);
		manual.startConfiguration();
		manual.startConfiguration();
		
		manual.applyWeightValue("BAYES_00", -4.0);;
		
		manual.getRuleWeight("TEST");
		manual.filterRulesList("BA");
		manual.saveMainListOfRules();
		
		manual.getResultsList();
		manual.setWindowClose();
	}

	@Test
	public final void testApplyWeightValue() {
		AntiSpamFilterAutomaticConfiguration main = new AntiSpamFilterAutomaticConfiguration();
		main.validateFilesAndBuildRulesAndEmails(
				new File("Files/spam.log"), new File("Files/ham.log"), new File("Files/rules.cf"));
		
		AntiSpamFilterManualConfiguration manual = new AntiSpamFilterManualConfiguration(main);
		manual.startConfiguration();
		manual.applyWeightValue("BAYES_00", 5.0);
		assertTrue(manual.getRuleWeight("BAYES_00") == 5.0);
	}

	@Test
	public final void testResetWeightValue() {
		AntiSpamFilterAutomaticConfiguration main = new AntiSpamFilterAutomaticConfiguration();
		main.validateFilesAndBuildRulesAndEmails(
				new File("Files/spam.log"), new File("Files/ham.log"), new File("Files/rules.cf"));
		
		AntiSpamFilterManualConfiguration manual = new AntiSpamFilterManualConfiguration(main);
		manual.startConfiguration();
		manual.applyWeightValue("BAYES_00", 5.0);
		assertTrue(manual.getRuleWeight("BAYES_00") == 5.0);
		
		manual.resetWeightValue("> BAYES_00");
		assertTrue(manual.getRuleWeight("BAYES_00") == 0.0);
	}

	@Test
	public final void testFilterRulesList() {
		AntiSpamFilterAutomaticConfiguration main = new AntiSpamFilterAutomaticConfiguration();
		main.validateFilesAndBuildRulesAndEmails(
				new File("Files/spam.log"), new File("Files/ham.log"), new File("Files/rules.cf"));
		
		AntiSpamFilterManualConfiguration manual = new AntiSpamFilterManualConfiguration(main);
		manual.startConfiguration();
		
		manual.filterRulesList("BAYES");
		assertTrue(manual.getListOfNames().size() == 9);
		
		manual.filterRulesList("Z");
		assertTrue(manual.getListOfNames().size() == 13);
	}

	@Test
	public final void testIsListChanged() {
		AntiSpamFilterAutomaticConfiguration main = new AntiSpamFilterAutomaticConfiguration();
		main.validateFilesAndBuildRulesAndEmails(
				new File("Files/spam.log"), new File("Files/ham.log"), new File("Files/rules.cf"));
		
		AntiSpamFilterManualConfiguration manual = new AntiSpamFilterManualConfiguration(main);
		manual.startConfiguration();
		assertFalse(manual.isListChanged());
		
		manual.applyWeightValue("BAYES_00", 5.0);
		
		assertTrue(manual.isListChanged());
	}

	@Test
	public final void testGetRuleWeightInt() {
		AntiSpamFilterAutomaticConfiguration main = new AntiSpamFilterAutomaticConfiguration();
		main.validateFilesAndBuildRulesAndEmails(
				new File("Files/spam.log"), new File("Files/ham.log"), new File("Files/rules.cf"));
		
		AntiSpamFilterManualConfiguration manual = new AntiSpamFilterManualConfiguration(main);
		manual.startConfiguration();
		manual.startConfiguration();
		
		assertTrue(manual.getRuleWeight(0) == 0);
		assertFalse(manual.getRuleWeight(6) != 0);
	}

}
