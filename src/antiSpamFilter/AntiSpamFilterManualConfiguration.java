package antiSpamFilter;

import java.util.ArrayList;

import antiSpamFilter.GUI.AntiSpamFilterConfigurationGUI;
import antiSpamFilter.rules.Rule;

public class AntiSpamFilterManualConfiguration {

	AntiSpamFilterAutomaticConfiguration main;
	AntiSpamFilterConfigurationGUI gui;
	ArrayList<Rule> mainListOfRules, temporaryListOfRules;
	ArrayList<String> listOfNames;
	
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
		
		gui.startConfiguration();
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

	public void applyWeightValue(int selectedIndex, Double value) {
		temporaryListOfRules.get(selectedIndex).setWeight(value);
		updateListOfNames();
		gui.refreshRulesList();
	}
	
	public void updateListOfNames() {
		listOfNames = new ArrayList<String>();

		for (int i = 0; i < temporaryListOfRules.size(); i++)
			if (temporaryListOfRules.get(i).getWeight() == mainListOfRules.get(i).getWeight())
				listOfNames.add(temporaryListOfRules.get(i).getName());
			else listOfNames.add("> " + temporaryListOfRules.get(i).getName());
	}
}
