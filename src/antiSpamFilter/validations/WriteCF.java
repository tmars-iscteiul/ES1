package antiSpamFilter.validations;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import antiSpamFilter.rules.Rule;

public class WriteCF {
	
	public static void writeCF (ArrayList<Rule> listOfRules, File rulesCF) {
	
		FileWriter writer;

		try {
		    writer = new FileWriter(rulesCF);
		    
		    for (Rule rule : listOfRules)
		    	writer.write(rule.getName() + '\t' + rule.getWeight() + '\n');
		    
		    writer.close();
		} catch (IOException e) {
		        // TODO Auto-generated catch block
		        e.printStackTrace();
		}           
	

	}
}
