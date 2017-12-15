package antiSpamFilter;

import java.util.ArrayList;

import antiSpamFilter.GUI.AntiSpamFilterConfigurationGUI;
import antiSpamFilter.emails.Email;
import antiSpamFilter.rules.Rule;

/**
 * <p>AntiSpamFilterManualConfiguration - the rules configuration class</br>
 * </br>
 * The AntiSpamFilter Manual Configuration allows the user to manually change
 * and test the list of rules. This class also creates the configuration GUI
 * and interacts with it.</p>
 *
 * @author ES1-2017-LIGE-PL-102
 */

public class AntiSpamFilterManualConfiguration {
	
	//Launch of the global variables
	AntiSpamFilterAutomaticConfiguration main;
	AntiSpamFilterConfigurationGUI gui;
	ArrayList<Rule> mainListOfRules, temporaryListOfRules;
	ArrayList<Email> listOfEmailsSpam, listOfEmailsHam;
	ArrayList<String> listOfNames;
	Boolean initiateGUI = false;
	
	public AntiSpamFilterManualConfiguration (
			AntiSpamFilterAutomaticConfiguration main) {
		this.main = main;	
		gui = new AntiSpamFilterConfigurationGUI(this,false);
	}

	/**Creation of the main list and the temporary list of rules**/
	public void startConfiguration() {
		mainListOfRules = main.getListOfRules();
		temporaryListOfRules = new ArrayList<Rule>();
		listOfEmailsHam = main.getListOfEmailsHam();
		listOfEmailsSpam = main.getListOfEmailsSpam();
		
		for (Rule rule : mainListOfRules)
			temporaryListOfRules.add(new Rule(rule.getName(),rule.getWeight()));
		
		updateListOfNames();
		
		//See if the window was already created before
		if (!initiateGUI) gui.startConfiguration();
		else gui.refreshRulesList();
		
		gui.setVisible(true);
		
		initiateGUI = true;
	}
	
	/** Returns the weight of a rule by the name **/
	public Double getRuleWeight(String name) {
		for (int i = 0; i < temporaryListOfRules.size(); i++)
			if (temporaryListOfRules.get(i).getName().equals(name))
				return temporaryListOfRules.get(i).getWeight();
		
		return 0.0;
	}
	
	/** Returns the weight of a rule by the index **/
	public Double getRuleWeight(int selectedIndex) {
		return temporaryListOfRules.get(selectedIndex).getWeight();
	}
	
	/** Apply a certain weight value to a rule **/
	public void applyWeightValue(String selectedRule, Double value) {
		for (int i = 0; i < temporaryListOfRules.size(); i++)
			if (temporaryListOfRules.get(i).getName().equals(selectedRule))
				temporaryListOfRules.get(i).setWeight(value);
		
		updateListOfNames();
		gui.refreshRulesList();
	}
	
	/** Updates the list of rule's name with the temporary list **/
	public void updateListOfNames() {
		listOfNames = new ArrayList<String>();
		String intro = "";

		for (int i = 0; i < temporaryListOfRules.size(); i++) {
			if (temporaryListOfRules.get(i).getWeight() < 0)
				intro = String.format("%.1f", temporaryListOfRules.get(i).getWeight()) + " | ";
			else intro = String.format("%.2f", temporaryListOfRules.get(i).getWeight()) + " | ";
			
			if (temporaryListOfRules.get(i).getWeight() == mainListOfRules.get(i).getWeight())
				listOfNames.add(intro + mainListOfRules.get(i).getName());
			else listOfNames.add(intro + "> " + mainListOfRules.get(i).getName());
		}
	}
	
	/** Resets a rule value to his original value **/
	public void resetWeightValue(String name) {
		name = name.substring(9);
		
		for (int i = 0; i < temporaryListOfRules.size(); i++)
			if (temporaryListOfRules.get(i).getName().contains(name))
				temporaryListOfRules.get(i).setWeight(mainListOfRules.get(i).getWeight());

		updateListOfNames();
		gui.refreshRulesList();
	}

	/** Apply a filter to the list of rules based in a text **/
	public void filterRulesList(String text) {
		String intro = "";
		if (text.isEmpty()) updateListOfNames();
		else {
			listOfNames = new ArrayList<String>();

			for (int i = 0; i < temporaryListOfRules.size(); i++) {
				if (temporaryListOfRules.get(i).getName().contains(text)) {
					if (temporaryListOfRules.get(i).getWeight() < 0)
						intro = String.format("%.1f", temporaryListOfRules.get(i).getWeight()) + " | ";
					else intro = String.format("%.2f", temporaryListOfRules.get(i).getWeight()) + " | ";
					
					if (temporaryListOfRules.get(i).getWeight() == mainListOfRules.get(i).getWeight())
						listOfNames.add(intro + mainListOfRules.get(i).getName());
					else listOfNames.add(intro + "> " + mainListOfRules.get(i).getName());
				}
			}
		}
		gui.refreshRulesList();
	}
	
	/** Save the temporary list of rules in the main class **/
	public void saveMainListOfRules() {
		for (int i = 0; i < mainListOfRules.size(); i++)
			mainListOfRules.get(i).setWeight(temporaryListOfRules.get(i).getWeight());
		
		main.saveListOfRules(mainListOfRules);
	}

	/** Verifies if a list was changed **/
	public boolean isListChanged() {
		for (int i = 0; i < temporaryListOfRules.size(); i++)
			if (temporaryListOfRules.get(i).getWeight() != mainListOfRules.get(i).getWeight())
				return true;
			
		return false;
	}
	
	public ArrayList<String> getListOfNames() {
		return listOfNames;
	}

	/** Closes the window **/
	public void setWindowClose() {
		main.configureWindowClose();
	}

	public String[] getResultsList() {
		String[] resultsList = new String[5];

		// FP - ham que foi classificado spam
		int FP = 0, mainFP = 0;
		for (Email email : listOfEmailsHam) {
			double ruleWeight = 0.0, mainRuleWeight = 0.0;
			for (Integer rulePos : email.getRulesList()) {
				ruleWeight += temporaryListOfRules.get(rulePos).getWeight();
				mainRuleWeight += mainListOfRules.get(rulePos).getWeight();
			}

			if (ruleWeight >= 5)
				FP++;
			
			if (mainRuleWeight >= 5)
				mainFP++;
		}

		// FN - spam que foi classificado ham
		int FN = 0, mainFN = 0;
		for (Email email : listOfEmailsSpam) {
			double ruleWeight = 0.0, mainRuleWeight = 0.0;
			for (Integer rulePos : email.getRulesList()) {
				ruleWeight += temporaryListOfRules.get(rulePos).getWeight();
				mainRuleWeight += mainListOfRules.get(rulePos).getWeight();
			}
			
			if (ruleWeight < 5)
				FN++;
			
			if (mainRuleWeight < 5)
				mainFN++;
		}
		
		resultsList[0] = "FP new: " + FP + " / previous: " + mainFP;
		resultsList[1] = "FN new: " + FN + " / previous: " + mainFN;
		resultsList[2] = "Compare results: ";
		
		if (FP < mainFP) resultsList[2] += "FP + / ";
		else resultsList[2] += "FP - / ";
		
		if (FN < mainFN) resultsList[2] += "FN +";
		else resultsList[2] += "FN -";
		
		double efficiency = 1 - (double)(FN+FP)/(temporaryListOfRules.size()+mainListOfRules.size());
		double mainEfficiency = 1 - (double)(mainFN+mainFP)/(temporaryListOfRules.size()+mainListOfRules.size());
		
		resultsList[3] = "Efficiency new: " + String.format("%.2f", efficiency) + 
				" / previous: " + String.format("%.2f", mainEfficiency);
		
		if (efficiency > mainEfficiency) resultsList[4] = "Tip: Save the changes";
		else resultsList[4] = "Tip: Discard the changes";
		
		return resultsList;
	}
}
