package antiSpamFilter.rules;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import antiSpamFilter.GUI.AntiSpamFilterStyles.AOptionPane;

/**
 * 
 * Class RuleStream represents a series of Rules to be used in the AntiSpam Filter in order to determine if an email must be classified as Spam or Ham.
 * 
 * @author ES1-2017-LIGE-PL-102
 *
 */

public class RuleStream {
	
	/**
	 *Attribute that keeps track of the Rules.cf file from where every Rule is created. 
	 */
	private ArrayList<Rule> ruleStream = new ArrayList<Rule>();
	
	/**
	 * The constructor method creates an array of Rules to be used in the AntiSpam Filter.
	 * @param filename file with the information of rules to be used
	 */
	public RuleStream(File filename) {
		//Start the reading		
		Scanner scanner;
		String line;
		String [] tokens;
		try {
			scanner = new Scanner(filename);
			
			while (scanner.hasNextLine()) {
				line = scanner.nextLine();
				tokens = line.split("\t");
				Rule rule = new Rule (tokens[0], 0.0);
				
				if(tokens.length>1) {
					rule.setWeight(Double.parseDouble(tokens[1]));
				}
				ruleStream.add(rule);
			}

		} catch (FileNotFoundException e) {
			AOptionPane.showMessageDialog(null, "Could not read the file.", "Error", AOptionPane.ERROR_MESSAGE);
			return;
		}
		Collections.sort(ruleStream);
	}

	/**
	 * Getter with the purpose to obtain the arraylist attribute of the RuleStream
	 * @return returns the arraylist of Rules
	 */
	public ArrayList<Rule> getRuleStream() {
		return ruleStream;
	}



	
	
}
