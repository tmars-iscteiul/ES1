package antiSpamFilter;

import java.util.ArrayList;

import antiSpamFilter.GUI.AntiSpamFilterConfigurationGUI;
import antiSpamFilter.rules.Rule;

public class AntiSpamFilterManualConfiguration {

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

	public void startConfiguration() {
		mainListOfRules = main.getListOfRules();
		temporaryListOfRules = new ArrayList<Rule>();
		
		for (Rule rule : mainListOfRules)
			temporaryListOfRules.add(new Rule(rule.getName(),rule.getWeight()));
		
		updateListOfNames();
		
		if (!initiateGUI) gui.startConfiguration();
		else gui.refreshRulesList();
		
		gui.setVisible(true);
		
		initiateGUI = true;
	}

	public ArrayList<String> getListOfNames() {
		return listOfNames;
	}

	public void setWindowClose() {
		main.configureWindowClose();
	}

	public Double getRuleWeight(int selectedIndex) {
		return temporaryListOfRules.get(selectedIndex).getWeight();
	}

	public void applyWeightValue(String selectedRule, Double value) {
		for (int i = 0; i < temporaryListOfRules.size(); i++)
			if (temporaryListOfRules.get(i).getName().equals(selectedRule))
				temporaryListOfRules.get(i).setWeight(value);
		
		updateListOfNames();
		gui.refreshRulesList();
	}
	
	public void updateListOfNames() {
		listOfNames = new ArrayList<String>();

		for (int i = 0; i < temporaryListOfRules.size(); i++)
			if (temporaryListOfRules.get(i).getWeight() == mainListOfRules.get(i).getWeight())
				listOfNames.add(mainListOfRules.get(i).getName());
			else listOfNames.add("> " + mainListOfRules.get(i).getName());
	}
	
	public void resetWeightValue(int selectedIndex) {
		temporaryListOfRules.get(selectedIndex).setWeight(
				mainListOfRules.get(selectedIndex).getWeight());
		updateListOfNames();
		gui.refreshRulesList();
	}

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
}
