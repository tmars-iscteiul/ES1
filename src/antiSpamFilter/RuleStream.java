package antiSpamFilter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import antiSpamFilter.AntiSpamFilterStyles.AOptionPane;

/**
 * 
 * EXPLICAÇÂO DA CLASSE
 * 
 * @author ES1-2017-LIGE-PL-102
 *
 */

public class RuleStream {
	
	/**
	 *Attribute that keeps track of the Rules.cf file from where every Rule is created. 
	 */
	private File filename; //depois tenho de tirar esta parte de dizer o ficheiro
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
		
		//if(reader.read(filename)) {
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
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
				
		}
	//}
	
	public static void main(String[] args) {

		RuleStream r = new RuleStream(new File("Ficheiros/rulesExpRodolfo.cf"));
		for(int i=0; i < r.ruleStream.size(); i++) {
			System.out.println(r.ruleStream.get(i).toString());
		}

	}
	
}
