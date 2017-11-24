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

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AntiSpamFilterAutomaticConfiguration {
	private static final int INDEPENDENT_RUNS = 5 ;
	private boolean filesAreValidated = false;

	ArrayList<Email> listOfEmails, listOfEmailsSpam, listOfEmailsHam;
	ArrayList<Rule> listOfRules;

	File spamFile, hamFile, rulesFile; 

	public AntiSpamFilterAutomaticConfiguration() {
		new AntiSpamFilterGUI(this);
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

	protected boolean validateFilesAndBuildRulesAndEmails(File spamFile, File hamFile, File rulesFile) {
		this.spamFile = spamFile;
		this.hamFile = hamFile;
		this.rulesFile = rulesFile;
		
		//Validation of the files
		if (!validateFiles()) {filesAreValidated = false; return false;}

		//Creation of the list of rules and emails
		if (!buildRulesAndEmails()) {filesAreValidated = false; return false;}

		filesAreValidated = true;
		return true;
	}

	private boolean validateFiles() {		
		boolean validateFiles=false;
		
		//TODO Validation of the spam and ham log files
		boolean validarHamFile=ReadLOG.readFile(new File("Ficheiros/ham.log"));
		boolean validarSpamFile=ReadLOG.readFile(new File("Ficheiros/spam.log"));
		
		//TODO Validation of the rules file
		boolean validarRulesFile;//adicionar ReadCF.readFile quando o ricardo acabar a validação

		if(validarHamFile==true && validarSpamFile==true){//adicionar validarRulesFile==true quando Ricardo acabar
			validateFiles= true;
		}
		
		return validateFiles;
	}

	private boolean buildRulesAndEmails() {
		//TODO Evoque the static class RuleStream to return the list
		//listOfRules = RuleStream.getListOfRulesFromFile(rulesFile);

		//Creation of the lists of email Spam and email Ham
		listOfEmailsSpam = EmailStream.getListOfEmailsFromFile(spamFile, listOfRules);
		listOfEmailsHam = EmailStream.getListOfEmailsFromFile(hamFile, listOfRules);

		if (listOfEmailsSpam == null || listOfEmailsHam == null) return false;

		listOfEmails.addAll(listOfEmailsSpam);
		listOfEmails.addAll(listOfEmailsHam);

		return true;
	}

}