package antiSpamFilter;

import java.util.ArrayList;

/**
 * <p>Email - the main email class</br>
 * </br>
 * The email class includes all the information of an email, including its type,
 * its ID and the list of rules that were detected.</p>
 * 
 * @author ES1-2017-LIGE-PL-102
 *
 */

public class Email {
	
	final String ID;
	final ArrayList<Integer> rulesPositionList;
	final int type;
	double finalWeight;
	
	protected final static int HAM = 0;
	protected final static int SPAM = 1;
	
	public Email(String Id, ArrayList<Integer> rulesPositionList, int type, double finalWeight) {
		this.ID = Id;
		this.rulesPositionList = rulesPositionList;
		this.type = type;
		this.finalWeight = finalWeight;
	}

	public String getID() {
		return ID;
	}

	public ArrayList<Integer> getRulesList() {
		return rulesPositionList;
	}

	public int getType() {
		return type;
	}
	
	public double getFinalWeight() {
		return finalWeight;
	}

	public void setFinalWeight(double finalWeight) {
		this.finalWeight = finalWeight;
	}

	@Override
	public String toString() {
		if (type == SPAM) return (ID + " / SPAM / " + finalWeight + " / " + rulesPositionList);
		else return (ID + " / HAM / " + finalWeight + " / " + rulesPositionList);
	}
	
	
}
