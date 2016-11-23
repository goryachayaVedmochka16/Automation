import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import steps.Steps;

public class Tests {

	private Steps steps;
	private static final String URL = "http://www.4chan.org/";
	private static final String LINKTEXT = "Quests";
	private static final String PAGE_TITLE = "/qst/ - Quests - 4chan";

	@Before
	public void init() {
		steps = new Steps();
		steps.initDriver();
	}

	@Test
	public void testCorrectLinkDestination() {
		steps.openPage(URL);
		steps.clickOnLink(LINKTEXT);
		steps.closePopup();
		assertEquals(PAGE_TITLE, steps.getPageTitle());
	}

	@After
	public void close() {
		steps.closeDriver();
	}

}
