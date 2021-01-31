package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class UiTestCommonUtils {
	protected WebDriver driver;
	protected Actions actions;
	protected WebDriverWait wait;

	public UiTestCommonUtils() {
		driver = new FirefoxDriver();
		wait = new WebDriverWait(driver, 15);
		actions = new Actions(driver);
		PageFactory.initElements(driver, this);
	}

	public void lauchBrowserValidateTitle(String url, String expectedTitle) {
		driver.get(url);
		driver.manage().window().maximize();
		String actualTitle = driver.getTitle();
		Assert.assertEquals(actualTitle, expectedTitle);
	}

	public void quitBrowser() {
		driver.quit();
	}
}
