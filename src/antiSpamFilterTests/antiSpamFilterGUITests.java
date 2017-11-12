/**
 * 
 */
package antiSpamFilterTests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import antiSpamFilter.AntiSpamFilterGUI;

/**
 * @author Rodolfo Arnaldo
 *
 */
public class antiSpamFilterGUITests {

	private AntiSpamFilterGUI gui = new AntiSpamFilterGUI(null);
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link antiSpamFilter.AntiSpamFilterGUI#getSerialversionuid()}.
	 */
	@Test
	public void testGetSerialversionuid() {
		assertEquals(1L, AntiSpamFilterGUI.getSerialversionuid());
	}

	/**
	 * Test method for {@link antiSpamFilter.AntiSpamFilterGUI#AntiSpamFilterGUI()}.
	 */
	@Test
	public void testAntiSpamFilterGUI() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link antiSpamFilter.AntiSpamFilterGUI#setEnable(boolean)}.
	 */
	@Test
	public void testSetEnable() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link antiSpamFilter.AntiSpamFilterGUI#getWINDOW_HSIZE()}.
	 */
	@Test
	public void testGetWINDOW_HSIZE() {
		assertEquals(500, gui.getWINDOW_HSIZE());
	}

	/**
	 * Test method for {@link antiSpamFilter.AntiSpamFilterGUI#getWINDOW_VSIZE()}.
	 */
	@Test
	public void testGetWINDOW_VSIZE() {
		assertEquals(600, gui.getWINDOW_VSIZE());
	}

	/**
	 * Test method for {@link antiSpamFilter.AntiSpamFilterGUI#getCOMPONENT_GAP()}.
	 */
	@Test
	public void testGetCOMPONENT_GAP() {
		assertEquals(20, gui.getCOMPONENT_GAP());
	}

	/**
	 * Test method for {@link antiSpamFilter.AntiSpamFilterGUI#getCOMPONENT_MAX_WIDTH()}.
	 */
	@Test
	public void testGetCOMPONENT_MAX_WIDTH() {
		assertEquals(gui.getWINDOW_HSIZE()-(2*gui.getCOMPONENT_GAP()), gui.getCOMPONENT_MAX_WIDTH());
	}

	/**
	 * Test method for {@link antiSpamFilter.AntiSpamFilterGUI#getSPAM_FILENAME()}.
	 */
	@Test
	public void testGetSPAM_FILENAME() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link antiSpamFilter.AntiSpamFilterGUI#getSPAM_DIRECTORY()}.
	 */
	@Test
	public void testGetSPAM_DIRECTORY() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link antiSpamFilter.AntiSpamFilterGUI#getSPAM_FILE()}.
	 */
	@Test
	public void testGetSPAM_FILE() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link antiSpamFilter.AntiSpamFilterGUI#getHAM_FILENAME()}.
	 */
	@Test
	public void testGetHAM_FILENAME() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link antiSpamFilter.AntiSpamFilterGUI#getHAM_DIRECTORY()}.
	 */
	@Test
	public void testGetHAM_DIRECTORY() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link antiSpamFilter.AntiSpamFilterGUI#getHAM_FILE()}.
	 */
	@Test
	public void testGetHAM_FILE() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link antiSpamFilter.AntiSpamFilterGUI#getRULES_FILENAME()}.
	 */
	@Test
	public void testGetRULES_FILENAME() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link antiSpamFilter.AntiSpamFilterGUI#getRULES_DIRECTORY()}.
	 */
	@Test
	public void testGetRULES_DIRECTORY() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link antiSpamFilter.AntiSpamFilterGUI#getRULES_FILE()}.
	 */
	@Test
	public void testGetRULES_FILE() {
		fail("Not yet implemented");
	}

	
}
