package steps;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Steps {
	private static final String LINK_URL = "a[href='%s']";
	private static final String ANY_TEXT_LOCATOR = "//*[text()='%s']";
	private WebDriver driver = null;

	public void initDriver() {
		System.setProperty("webdriver.chrome.driver", "chromedriver_win32/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}

	public void closeDriver() {
		driver.quit();
	}

	public void openPage(String url) {
		driver.get(url);
	}

	public WebElement getLinkByUrl(String url) {
		String locator = String.format(LINK_URL, url);
		return driver.findElement(By.cssSelector(locator));
	}

	public void clickOnLink(String linkText) {
		WebElement link = driver.findElement(By.linkText(linkText));
		link.click();
	}

	public void closePopup() {
		List<WebElement> popupButtons = driver.findElements(By.cssSelector("button[data-cmd='ok-disc']"));
		if (!popupButtons.isEmpty()) {
			popupButtons.get(0).click();
		}
	}

	public String getPageTitle() {
		return driver.getTitle();
	}

	public ArrayList<String> getCopyrightLinks() {
		List<WebElement> links = driver.findElements(By.cssSelector("div[id='copyright'] a"));
		ArrayList<String> linkTexts = new ArrayList<String>();
		for (WebElement link : links) {
			linkTexts.add(link.getText());
		}
		return linkTexts;
	}

	public String getTooltip(WebElement el) {
		return el.getAttribute("title");
	}

	public void search(String query) {
		WebElement search = driver.findElement(By.id("search-box"));
		search.sendKeys(query);
		search.sendKeys(Keys.ENTER);
	}

	public boolean isTextPresent(String text) {
		List<WebElement> results = driver.findElements(By.xpath(String.format(ANY_TEXT_LOCATOR, text)));
		return !results.isEmpty();
	}
}
