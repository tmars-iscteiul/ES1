package antiSpamFilter;

import java.io.File;

/**
 * 
 * EXPLICAÇÂO DA CLASSE
 * 
 * @author ES1-2017-LIGE-PL-102
 *
 */

public class Rule {
	
	private String name;
	private double weight;
	
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
		return ("Rule name: "+name+" Peso:"+weight);
	}
	
	

}
