package antiSpamFilterTests;
import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import antiSpamFilter.Email;
import antiSpamFilter.Rule;

public class EmailTest {

	@Test
	public final void testEmail() {
		Email email = new Email("teste",new ArrayList<Rule>(),0);
		assertEquals(email.getID(),"teste");
		assertEquals(email.getType(),0);
		assertTrue(email.getRulesList().isEmpty());
	}

	@Test
	public final void testGetID() {
		assertEquals(new Email("teste",new ArrayList<Rule>(),0).getID(),"teste");
	}

	@Test
	public final void testGetRulesList() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testGetType() {
		assertEquals(new Email("teste",new ArrayList<Rule>(),0).getType(),0);
		assertEquals(new Email("teste",new ArrayList<Rule>(),1).getType(),1);
	}

	@Test
	public final void testToString() {
		fail("Not yet implemented"); // TODO
	}

}
