package antiSpamFilter.tests;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

import antiSpamFilter.rules.Rule;
import antiSpamFilter.rules.RuleStream;

public class RuleStreamTest {

	@Test
	public void testRuleStream() {
		RuleStream rs = new RuleStream(new File("Files/rules_test_valid.cf"));
		assertEquals(rs.getRuleStream().get(0).getName(), "BAYES_00");
		assertEquals(rs.getRuleStream().get(0).getWeight(), 0.0, 0.0);
		
		RuleStream rs1 = new RuleStream(new File("Files/rules_invalid_double.cf"));
		assertEquals(rs1.getRuleStream().size(), 0);
	}

	@Test
	public void testGetRuleStream() {
		RuleStream rs = new RuleStream(new File("Files/rules_test_valid.cf"));
		assertTrue(rs.getRuleStream().get(0) instanceof Rule);
	}

}
