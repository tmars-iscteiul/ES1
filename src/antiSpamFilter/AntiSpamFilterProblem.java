package antiSpamFilter;

import java.util.ArrayList;
import java.util.List;

import org.uma.jmetal.problem.impl.AbstractDoubleProblem;
import org.uma.jmetal.solution.DoubleSolution;

import antiSpamFilter.emails.Email;

public class AntiSpamFilterProblem extends AbstractDoubleProblem {

	/**
	 * <p>AntiSpamFilterProblem - the definition of the optimization class</br>
	 * </br>
	 * The AntiSpamFilterProblem defines the parameters and settings to run
	 * the optimizer framework.</p>
	 *
	 * @author ES1-2017-LIGE-PL-102
	 */
	
	private static final long serialVersionUID = 1L;
	private ArrayList<Email> listOfEmailsSpam, listOfEmailsHam;

	public AntiSpamFilterProblem() {
		// 10 variables (anti-spam filter rules) by default
		this(10);
	}

	public AntiSpamFilterProblem(Integer numberOfVariables) {
		setNumberOfVariables(numberOfVariables);
		setNumberOfObjectives(2);
		setName("AntiSpamFilterProblem");

		List<Double> lowerLimit = new ArrayList<>(getNumberOfVariables());
		List<Double> upperLimit = new ArrayList<>(getNumberOfVariables());

		for (int i = 0; i < getNumberOfVariables(); i++) {
			lowerLimit.add(-5.0);
			upperLimit.add(5.0);
		}

		setLowerLimit(lowerLimit);
		setUpperLimit(upperLimit);
	}

	/** Evaluates the result parameters and sets the new objective for the solution */
	public void evaluate(DoubleSolution solution) {
		double[] fx = new double[getNumberOfObjectives()];
		double[] x = new double[getNumberOfVariables()];

		for (int i = 0; i < solution.getNumberOfVariables(); i++) {
			x[i] = solution.getVariableValue(i);
		}

		//calculation of the False Positives
		fx[0] = 0.0;
		for (Email email : listOfEmailsHam) {
			double ruleWeight = 0.0;
			for (Integer rulePos : email.getRulesList())
				ruleWeight += x[rulePos];

			if (ruleWeight >= 5)
				fx[0]++;
		}

		//calculation of the False Negatives
		fx[1] = 0.0;
		for (Email email : listOfEmailsSpam) {
			double ruleWeight = 0.0;
			for (Integer rulePos : email.getRulesList())
				ruleWeight += x[rulePos];

			if (ruleWeight < 5)
				fx[1]++;
		}

		solution.setObjective(0, fx[0]);
		solution.setObjective(1, fx[1]);
	}

	public void setListOfHam(ArrayList<Email> listOfEmailsHam) {
		this.listOfEmailsHam = listOfEmailsHam;
	}

	public void setListOfSpam(ArrayList<Email> listOfEmailsSpam) {
		this.listOfEmailsSpam = listOfEmailsSpam;
	}
}
