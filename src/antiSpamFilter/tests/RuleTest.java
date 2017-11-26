package antiSpamFilter.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import antiSpamFilter.rules.Rule;

public class RuleTest {

	@Test
	public void testRule() {
		Rule test = new Rule("teste",2.0);
		assertEquals(test.getName(), "teste");
		assertEquals(test.getWeight(), 2.0, 0.0);		
	}

	@Test
	public void testGetName() {
		Rule test = new Rule("teste",2.0);
		assertEquals(test.getName(), "teste");
	}

	@Test
	public void testGetWeight() {
		Rule test = new Rule("teste",2.0);
		assertEquals(test.getWeight(), 2.0, 0.0);
	}

	@Test
	public void testSetName() {
		Rule test = new Rule("teste",2.0);
		test.setName("teste2");
		assertEquals(test.getName(), "teste2");
	}

	@Test
	public void testSetWeight() {
		Rule test = new Rule("teste",2.0);
		test.setWeight(-1.8);
		assertEquals(test.getWeight(), -1.8, 0.0);
	}

	@Test
	public void testToString() {
		Rule test = new Rule("teste",2.0);
		assertEquals(test.toString(), "Rule name: teste Peso: 2.0");
	}

}
