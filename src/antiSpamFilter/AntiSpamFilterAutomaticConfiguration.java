package antiSpamFilter;

import antiSpamFilter.GUI.AntiSpamFilterGUI;
import antiSpamFilter.emails.Email;
import antiSpamFilter.emails.EmailStream;
import antiSpamFilter.rules.Rule;
import antiSpamFilter.rules.RuleStream;
import antiSpamFilter.validations.ReadCF;
import antiSpamFilter.validations.ReadLOG;

import java.io.File;
import java.util.ArrayList;

/**
 * <p>AntiSpamFilterAutomaticConfiguration - the main class</br>
 * </br>
 * The AntiSpamFilter Automatic Configuration creates the main environment
 * to automatically configure and optimize the spam classification. </p>
 *
 * @author ES1-2017-LIGE-PL-102
 */

public class AntiSpamFilterAutomaticConfiguration {
	private boolean filesAreValidated = false, isRulesChanged = false;
	
	AntiSpamFilterGUI mainGUI;
	AntiSpamFilterManualConfiguration manualConfigure;
	
	ArrayList<Email> listOfEmailsSpam, listOfEmailsHam;
	ArrayList<Rule> listOfRules;

	File spamFile, hamFile, rulesFile; 

	public AntiSpamFilterAutomaticConfiguration() {
		mainGUI = new AntiSpamFilterGUI(this);
		manualConfigure = new AntiSpamFilterManualConfiguration(this);
	}

	public static void main(String[] args) {
		new AntiSpamFilterAutomaticConfiguration();
	}
 
	/** Confirms if the log and rules files are validated and calls the optimization class **/
	public void runOptimization() {
		if (!filesAreValidated)
			AntiSpamFilterOptimization.runOptimization();
	}

	/** Validates the three files that represent the spam log, ham log and rules list **/
	public boolean validateFilesAndBuildRulesAndEmails(File spamFile, File hamFile, File rulesFile) {
		boolean isSameFiles = false;
		
		//Check if the files are the same since the last optimization
		if (this.spamFile == spamFile && this.hamFile == hamFile && this.rulesFile == rulesFile)
			isSameFiles = true;
		
		//Check if the files were already validated before
		if (!filesAreValidated || !isSameFiles) {
			this.spamFile = spamFile;
			this.hamFile = hamFile;
			this.rulesFile = rulesFile;

			//Validation of the files
			if (!validateFiles()) {
				filesAreValidated = false; 
				return false;
			}

			//Creation of the list of rules and emails
			if (!buildRulesAndEmails()) {
				filesAreValidated = false; 
				return false;
			}

			filesAreValidated = true;
		}
		
		return true;
	}

	/** Calls the reading and validation classes **/
	private boolean validateFiles() {		
		//Call the validation classes and validate
		if(ReadLOG.readFile(hamFile) && ReadLOG.readFile(spamFile)&& ReadCF.read(rulesFile))
			return true;
	
		return false;
	}

	/** Builds the list of rules and the list of emails **/
	private boolean buildRulesAndEmails() {
		//Creation of the list of rules
		listOfRules = new RuleStream(rulesFile).getRuleStream();
		
		//Creation of the lists of email Spam and email Ham
		listOfEmailsSpam = EmailStream.getListOfEmailsFromFile(spamFile, listOfRules, Email.SPAM);
		listOfEmailsHam = EmailStream.getListOfEmailsFromFile(hamFile, listOfRules, Email.HAM);

		if (listOfEmailsSpam == null || listOfEmailsHam == null) return false;
		if (listOfEmailsSpam.size() == 0 || listOfEmailsHam.size() == 0) return false;

		return true;
	}
	
	public ArrayList<Email> getListOfEmailsSpam() {
		return listOfEmailsSpam;
	}

	public ArrayList<Email> getListOfEmailsHam() {
		return listOfEmailsHam;
	}

	public ArrayList<Rule> getListOfRules() {
		return listOfRules;
	}

	public void setConfigureWindowVisible(boolean b) {
		manualConfigure.startConfiguration();
	}

	public void configureWindowClose() {
		mainGUI.setEnable(true);
	}

	public void saveListOfRules(ArrayList<Rule> mainListOfRules) {
		listOfRules = mainListOfRules;
		isRulesChanged = true;
	}

	public boolean isRulesChanged() {
		return isRulesChanged;
	}
}