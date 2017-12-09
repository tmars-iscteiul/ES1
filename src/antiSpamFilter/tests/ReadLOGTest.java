package antiSpamFilter.tests;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

import antiSpamFilter.validations.ReadLOG;

public class ReadLOGTest {

	@Test
	public final void testValidRead() {
		assertTrue(ReadLOG.readFile(new File("Files/spam_test_valid.log")));
	}
	
	@Test
	public final void testInvalidFields() {
		assertFalse(ReadLOG.readFile(new File("Files/spam_test_invalid_fields.log")));
	}
	
	@Test
	public final void testInvalidFile() {
		assertFalse(ReadLOG.readFile(new File("Files/invalid_file.log")));
	}

}
