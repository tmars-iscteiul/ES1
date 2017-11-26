package antiSpamFilter.tests;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

import antiSpamFilter.validations.ReadCF;

public class ReadCFTest {

	@Test
	public final void testValidRead() {
		assertTrue(ReadCF.read(new File("Ficheiros/rules_test_valid.cf")));
	}
	
	@Test
	public final void testInvalidDoubleRead() {
		assertFalse(ReadCF.read(new File("Ficheiros/rules_test_invalid_double.cf")));
	}
	
	@Test
	public final void testInvalidFieldsRead() {
		assertFalse(ReadCF.read(new File("Ficheiros/rules_test_invalid_fields.cf")));
	}
	
	@Test
	public final void testInvalidWeightRead() {
		assertFalse(ReadCF.read(new File("Ficheiros/rules_test_invalid_weight.cf")));
	}
	
	@Test
	public final void testInvalidFile() {
		assertFalse(ReadCF.read(new File("Ficheiros/test.cf")));
	}

}
