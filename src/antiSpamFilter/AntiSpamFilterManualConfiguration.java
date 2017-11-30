package antiSpamFilter;

import java.util.ArrayList;

import antiSpamFilter.GUI.AntiSpamFilterConfigurationGUI;
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

		for (int i = 0; i < temporaryListOfRules.size(); i++)
			if (temporaryListOfRules.get(i).getWeight() == mainListOfRules.get(i).getWeight())
				listOfNames.add(mainListOfRules.get(i).getName());
			else listOfNames.add("> " + mainListOfRules.get(i).getName());
	}
	
	/** Resets a rule value to his original value **/
	public void resetWeightValue(String name) {
		if (name.charAt(0) == '>')
			name = name.substring(2);
		
		for (int i = 0; i < temporaryListOfRules.size(); i++)
			if (temporaryListOfRules.get(i).getName().contains(name))
				temporaryListOfRules.get(i).setWeight(mainListOfRules.get(i).getWeight());

		updateListOfNames();
		gui.refreshRulesList();
	}

	/** Apply a filter to the list of rules based in a text **/
	public void filterRulesList(String text) {
		if (text.isEmpty()) updateListOfNames();
		else {
			listOfNames = new ArrayList<String>();

			for (int i = 0; i < temporaryListOfRules.size(); i++)
				if (temporaryListOfRules.get(i).getName().contains(text))
					if (temporaryListOfRules.get(i).getWeight() == mainListOfRules.get(i).getWeight())
						listOfNames.add(mainListOfRules.get(i).getName());
					else listOfNames.add("> " + mainListOfRules.get(i).getName());
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

	public Double getRuleWeight(int selectedIndex) {
		return temporaryListOfRules.get(selectedIndex).getWeight();
	}
}
