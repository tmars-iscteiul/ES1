package antiSpamFilterTests;
import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import antiSpamFilter.Email;

public class EmailTest {

	@Test
	public final void testEmail() {
		Email email = new Email("teste",new ArrayList<Integer>(),0,0);
		assertEquals(email.getID(),"teste");
		assertEquals(email.getType(),0);
		assertTrue(email.getRulesList().isEmpty());
	}

	@Test
	public final void testGetID() {
		assertEquals(new Email("teste",new ArrayList<Integer>(),0,0).getID(),"teste");
	}

	@Test
	public final void testGetRulesList() {
		ArrayList<Integer> list = new ArrayList<Integer>();
		list.add(1);
		list.add(2);
		list.add(3);
		
		assertEquals(new Email("teste",list,0,0).getRulesList(),list);
	}

	@Test
	public final void testGetType() {
		assertEquals(new Email("teste",new ArrayList<Integer>(),0,0).getType(),0);
		assertEquals(new Email("teste",new ArrayList<Integer>(),1,0).getType(),1);
	}
	
	@Test
	public final void testGetFinalWeight() {
		Email email = new Email("teste", null, 1, 1);
		assertEquals(email.getFinalWeight(), 1, 0);
		
		email.setFinalWeight(5);
		assertEquals(email.getFinalWeight(),5, 0);
	}

	@Test
	public final void testToString() {
		ArrayList<Integer> list = new ArrayList<Integer>();
		Email emailSpam = new Email("teste", list, 1, 1);
		Email emailHam = new Email("teste", list, 0, 1);
		
		assertEquals(emailSpam.toString(),
				"teste / SPAM / 1.0 / " + list.toString());
		
		assertEquals(emailHam.toString(),
				"teste / HAM / 1.0 / " + list.toString());
		
	}

}
