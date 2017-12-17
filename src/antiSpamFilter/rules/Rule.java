package antiSpamFilter.rules;

/**
 * 
 * Class Rule is the primary element in order to determine the right classification of an email as Spam or Ham.
 * 
 * @author ES1-2017-LIGE-PL-102
 *
 */

public class Rule implements Comparable<Rule>{
	
	/**
	 * The Name attribute of the class is a String to be compared in every email.
	 */
	private String name;
	/**
	 * The Weight attribute determines by how much the email scores changes when the present rule is verified in an email.
	 */
	private double weight;
	
	/**
	 * The constructor method receives two parameters in order to generate a rule.
	 * 
	 * @param name string of the preposition to be compared in every email
	 * @param weight amount of change in the score of an email who triggers the respective rule
	 */
	public Rule(String name, double weight) {
		this.name = name;
		this.weight = weight;
	}

	public String getName() {
		return name;
	}

	public double getWeight() {
		return weight;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	@Override
	public String toString() {
		return ("Rule name: "+name+" Peso: "+weight);
	}

	@Override
	public int compareTo(Rule other) {
		return this.getName().compareTo(other.getName());
	}
	
	

}
