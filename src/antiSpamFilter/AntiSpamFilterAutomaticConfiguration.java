package antiSpamFilter;

import org.uma.jmetal.algorithm.Algorithm;
import org.uma.jmetal.algorithm.multiobjective.nsgaii.NSGAIIBuilder;
import org.uma.jmetal.operator.impl.crossover.SBXCrossover;
import org.uma.jmetal.operator.impl.mutation.PolynomialMutation;
import org.uma.jmetal.problem.Problem;
import org.uma.jmetal.problem.multiobjective.zdt.*;
import org.uma.jmetal.qualityindicator.impl.*;
import org.uma.jmetal.qualityindicator.impl.hypervolume.PISAHypervolume;
import org.uma.jmetal.solution.DoubleSolution;
import org.uma.jmetal.util.JMetalException;
import org.uma.jmetal.util.experiment.Experiment;
import org.uma.jmetal.util.experiment.ExperimentBuilder;
import org.uma.jmetal.util.experiment.component.*;
import org.uma.jmetal.util.experiment.util.ExperimentAlgorithm;
import org.uma.jmetal.util.experiment.util.ExperimentProblem;

import antiSpamFilter.GUI.AntiSpamFilterGUI;
import antiSpamFilter.emails.Email;
import antiSpamFilter.emails.EmailStream;
import antiSpamFilter.rules.Rule;
import antiSpamFilter.rules.RuleStream;
import antiSpamFilter.validations.ReadCF;
import antiSpamFilter.validations.ReadLOG;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AntiSpamFilterAutomaticConfiguration {
	private static final int INDEPENDENT_RUNS = 5 ;
	private boolean filesAreValidated = false, isRulesChanged = false;
	
	AntiSpamFilterGUI mainGUI;
	AntiSpamFilterManualConfiguration manualConfigure;
	
	ArrayList<Email> listOfEmailsSpam, listOfEmailsHam;
	ArrayList<Rule> listOfRules;

	File spamFile, hamFile, rulesFile; 

	public AntiSpamFilterAutomaticConfiguration() {
		mainGUI = new AntiSpamFilterGUI(this);
		manualConfigure = new AntiSpamFilterManualConfiguration(this);
	}

	public static void main(String[] args) {
		new AntiSpamFilterAutomaticConfiguration();
	}
 
	public void runOptimization() {
		
		if (!filesAreValidated) {
			String experimentBaseDirectory = "experimentBaseDirectory";

			List<ExperimentProblem<DoubleSolution>> problemList = new ArrayList<>();
			problemList.add(new ExperimentProblem<>(new AntiSpamFilterProblem()));

			List<ExperimentAlgorithm<DoubleSolution, List<DoubleSolution>>> algorithmList =
					configureAlgorithmList(problemList);

			Experiment<DoubleSolution, List<DoubleSolution>> experiment =
					new ExperimentBuilder<DoubleSolution, List<DoubleSolution>>("AntiSpamStudy")
					.setAlgorithmList(algorithmList)
					.setProblemList(problemList)
					.setExperimentBaseDirectory(experimentBaseDirectory)
					.setOutputParetoFrontFileName("FUN")
					.setOutputParetoSetFileName("VAR")
					.setReferenceFrontDirectory(experimentBaseDirectory+"/referenceFronts")
					.setIndicatorList(Arrays.asList(new PISAHypervolume<DoubleSolution>()))
					.setIndependentRuns(INDEPENDENT_RUNS)
					.setNumberOfCores(8)
					.build();

			new ExecuteAlgorithms<>(experiment).run();
			try {
				new GenerateReferenceParetoSetAndFrontFromDoubleSolutions(experiment).run();
				new ComputeQualityIndicators<>(experiment).run();
				new GenerateLatexTablesWithStatistics(experiment).run();
				new GenerateBoxplotsWithR<>(experiment).setRows(1).setColumns(1).run();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	static List<ExperimentAlgorithm<DoubleSolution, List<DoubleSolution>>> configureAlgorithmList(
			List<ExperimentProblem<DoubleSolution>> problemList) {
		List<ExperimentAlgorithm<DoubleSolution, List<DoubleSolution>>> algorithms = new ArrayList<>();

		for (int i = 0; i < problemList.size(); i++) {
			Algorithm<List<DoubleSolution>> algorithm = new NSGAIIBuilder<>(
					problemList.get(i).getProblem(),
					new SBXCrossover(1.0, 5),
					new PolynomialMutation(1.0 / problemList.get(i).getProblem().getNumberOfVariables(), 10.0))
					.setMaxEvaluations(25000)
					.setPopulationSize(100)
					.build();
			algorithms.add(new ExperimentAlgorithm<>(algorithm, "NSGAII", problemList.get(i).getTag()));
		}

		return algorithms;
	}

	public boolean validateFilesAndBuildRulesAndEmails(File spamFile, File hamFile, File rulesFile) {
		boolean isSameFiles = false;
		
		//Check if the files are the same since the last optimization
		if (this.spamFile == spamFile && this.hamFile == hamFile && this.rulesFile == rulesFile)
			isSameFiles = true;
		
		//Check if the files were already validated before
		if (!filesAreValidated || !isSameFiles) {
			this.spamFile = spamFile;
			this.hamFile = hamFile;
			this.rulesFile = rulesFile;

			//Validation of the files
			if (!validateFiles()) {
				filesAreValidated = false; 
				return false;
			}

			//Creation of the list of rules and emails
			if (!buildRulesAndEmails()) {
				filesAreValidated = false; 
				return false;
			}

			filesAreValidated = true;
		}
		
		return true;
	}

	private boolean validateFiles() {		
		//Call the validation classes and validate
		if(/*ReadLOG.readFile(hamFile) && ReadLOG.readFile(spamFile)&&*/ ReadCF.read(rulesFile))
			return true;
	
		return false;
	}

	private boolean buildRulesAndEmails() {
		//Creation of the list of rules
		listOfRules = new RuleStream(rulesFile).getRuleStream();
		
		//Creation of the lists of email Spam and email Ham
		listOfEmailsSpam = EmailStream.getListOfEmailsFromFile(spamFile, listOfRules, Email.SPAM);
		listOfEmailsHam = EmailStream.getListOfEmailsFromFile(hamFile, listOfRules, Email.HAM);

		if (listOfEmailsSpam == null || listOfEmailsHam == null) return false;
		if (listOfEmailsSpam.size() == 0 || listOfEmailsHam.size() == 0) return false;

		return true;
	}
	
	public ArrayList<Email> getListOfEmailsSpam() {
		return listOfEmailsSpam;
	}

	public ArrayList<Email> getListOfEmailsHam() {
		return listOfEmailsHam;
	}

	public ArrayList<Rule> getListOfRules() {
		return listOfRules;
	}

	public void setConfigureWindowVisible(boolean b) {
		manualConfigure.startConfiguration();
	}

	public void configureWindowClose() {
		mainGUI.setEnable(true);
	}

	public void saveListOfRules(ArrayList<Rule> mainListOfRules) {
		listOfRules = mainListOfRules;
		isRulesChanged = true;
	}

	public boolean isRulesChanged() {
		return isRulesChanged;
	}
}