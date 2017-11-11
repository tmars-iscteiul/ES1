/**
 * 
 */
package antiSpamFilter;

/**
 * @author ES1-2017-LIGE-PL-102
 *
 */
public class Email {
	
	final int ID;
	final int[] rulesList;
	final int type;
	
	protected final static int HAM = 0;
	protected final static int SPAM = 1;
	
	public Email(int Id, int[] rulesList, int type) {
		this.ID = Id;
		this.rulesList = rulesList;
		this.type = type;
	}

	public int getID() {
		return ID;
	}

	public int[] getRulesList() {
		return rulesList;
	}

	public int getType() {
		return type;
	}	
}
