import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebElement;

import steps.Steps;

public class Tests {

	private Steps steps;
	private static final String URL = "http://www.4chan.org/";
	private static final String LOGO_URL = "//www.4chan.org/";
	private static final String LINKTEXT = "Rules";
	private static final String THEME = "Quests";
	private static final String VALID_QUERY = "hello";
	private static final String INVALID_QUERY = "HEH&853e";
	private static final String ERROR_MESSAGE = "Nothing Found";
	private static final String TOOLTIP = "Home";
	private static final String PAGE_TITLE = "Rules - 4chan";
	private static final String[] LINKS = { "About", "Feedback", "Legal", "Contact" };

	@Before
	public void init() {
		steps = new Steps();
		steps.initDriver();
	}

	@Test
	public void testVerifyCorrectCopyright() {
		steps.openPage(URL);
		String[] actualLinks = steps.getCopyrightLinks().toArray(new String[0]);
		assertArrayEquals(LINKS, actualLinks);
	}

	@Test
	public void testVerifyHomeToolip() {
		steps.openPage(URL);
		WebElement link = steps.getLinkByUrl(LOGO_URL);
		assertEquals(TOOLTIP, steps.getTooltip(link));
	}

	@Test
	public void testCorrectLinkDestination() {
		steps.openPage(URL);
		steps.clickOnLink(LINKTEXT);
		steps.closePopup();
		assertEquals(PAGE_TITLE, steps.getPageTitle());
	}

	@Test
	public void testSearchByValidText() {
		steps.openPage(URL);
		steps.clickOnLink(THEME);
		steps.closePopup();
		steps.search(VALID_QUERY);
		assertTrue(steps.isTextPresent(VALID_QUERY));
	}

	@Test
	public void testSearchByInvalidText() {
		steps.openPage(URL);
		steps.clickOnLink(THEME);
		steps.closePopup();
		steps.search(INVALID_QUERY);
		assertTrue(steps.isTextPresent(ERROR_MESSAGE));
	}

	@After
	public void close() {
		steps.closeDriver();
	}
}
