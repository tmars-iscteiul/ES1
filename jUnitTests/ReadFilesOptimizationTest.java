package antiSpamFilter.tests;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

import antiSpamFilter.validations.ReadFilesOptimization;

public class ReadFilesOptimizationTest {

	@Test
	public final void testReadFileRF() {
		ReadFilesOptimization read1 = new ReadFilesOptimization();
		read1.readFileRF(new File("./Files/AntiSpamFilterProblem.NSGAII.rf"));
		read1.readFileRS(new File("./Files/AntiSpamFilterProblem.NSGAII.rs"));
		
		assertEquals(read1.getBestFN(),9.3679122E-16,0.01,0.01);
		assertEquals(read1.getBestFP(),9.9611213E-16,0.01,0.01);
		
		ReadFilesOptimization read2 = new ReadFilesOptimization();
		read2.readFileRF(new File("./Files/AntiSpamFilterProblem.NSGAII_empty.rf"));
		read2.readFileRS(new File("./Files/test_fake.rs"));
		read2.readFileRF(new File("./Files/test_fake.rf"));
		
		ReadFilesOptimization read3 = new ReadFilesOptimization();
		read3.readFileRF(new File("./Files/AntiSpamFilterProblem.NSGAII.rf"));
		read3.readFileRS(new File("./Files/AntiSpamFilterProblem.NSGAII_without.rs"));
	}

}
