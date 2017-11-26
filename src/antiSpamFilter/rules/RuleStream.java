package antiSpamFilter.rules;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import javax.swing.JOptionPane;

import antiSpamFilter.validations.ReadCF;

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
	private File filename;
	/**
	 * 
	 */
	private ArrayList<Rule> ruleStream = new ArrayList<Rule>();
	
	/**
	 * The constructor method creates an array of Rules to be used in the AntiSpam Filter.
	 * @param filename file with the information of rules to be used
	 */
	public RuleStream(File filename) {
		this.filename = filename;
		ReadCF reader = new ReadCF();
		
		if(reader.read(filename)) {
			//Start the reading		
			Scanner scanner;
			String line;
			String [] tokens;
			try {
				scanner = new Scanner(filename);
				while (scanner.hasNextLine()) {
					line = scanner.nextLine();
					tokens = line.split("\t");
					/*for (int i=0; i<tokens.length; i++) {
					 System.out.println(tokens[i]);
					}*/
					Rule rule = new Rule (tokens[0], 0.0);
					if(tokens.length>1) {
						rule.setWeight(Double.parseDouble(tokens[1]));
					}
					ruleStream.add(rule);
				}
			
			} catch (FileNotFoundException e) {
				JOptionPane.showMessageDialog(null, "Could not read the file.", "Error", JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
				return;
			}
			Collections.sort(ruleStream);
				
		}
		else {
			JOptionPane.showMessageDialog(null, "Could not read the file.", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
	}
	
	/**
	 * Getter with the purpose to obtain the arraylist attribute of the RuleStream
	 * @return returns the arraylist of Rules
	 */
	public ArrayList<Rule> getRuleStream() {
		return ruleStream;
	}
	
	/**
	 * Test method to make sure the array of Rules is constructed correctly.
	 * @param args
	 */
	/*public static void main(String[] args) {

		RuleStream r = new RuleStream(new File("Files/rulesExpRodolfo.cf"));
		
		for(int i=0; i < r.ruleStream.size(); i++) {
			System.out.println(r.ruleStream.get(i).toString());
		}

	}*/



	
	
}
