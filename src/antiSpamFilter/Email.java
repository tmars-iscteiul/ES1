package antiSpamFilter;

import java.util.ArrayList;

/**
 * @author ES1-2017-LIGE-PL-102
 *
 */
public class Email {
	
	final String ID;
	final ArrayList<Rule> rulesList;
	final int type;
	
	protected final static int HAM = 0;
	protected final static int SPAM = 1;
	
	public Email(String Id, ArrayList<Rule> rulesList, int type) {
		this.ID = Id;
		this.rulesList = rulesList;
		this.type = type;
	}

	public String getID() {
		return ID;
	}

	public ArrayList<Rule> getRulesList() {
		return rulesList;
	}

	public int getType() {
		return type;
	}

	@Override
	public String toString() {
		if (type == SPAM) return (ID + " / SPAM / " + rulesList);
		else return (ID + " / HAM / " + rulesList);
	}
	
	
}
