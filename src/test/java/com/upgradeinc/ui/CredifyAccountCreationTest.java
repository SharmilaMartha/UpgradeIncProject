package com.upgradeinc.ui;

import java.util.Random;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import utils.UICommonUtils;
import utils.Constants;

import static utils.Configuration.UI_DATA;

import static utils.Configuration.CHECK_YOUR_RATE_PAGE;
import static utils.Configuration.BASIC_INFO_PAGE;
import static utils.Configuration.ANNUAL_INCOME_INFO_PAGE;
import static utils.Configuration.CREATE_ACCOUNT_PAGE;
import static utils.Configuration.OFFERS_PAGE;
import static utils.Configuration.UPGRADE_PORTAL_LOGIN_PAGE;

public class CredifyAccountCreationTest extends UICommonUtils {

	private Random randomNumGen = new Random();

	private int randomNum = randomNumGen.nextInt(1000);

	private final String emailId = "candidate" + randomNum + "@upgrade-challenge.com";

	String loanAmount, monthlyPayment, loanTerm, interestRate, apr;

	@Test(priority = 1)
	public void launchBrowserValidateTitleForRateCheck() {

		lauchBrowserValidateTitle(UI_DATA.getProperty(Constants.RATE_CHECK_URL),
				UI_DATA.getProperty(Constants.EXPECTED_TITLE_RATE_CHECK));
	}

	@Test(priority = 2)
	public void checkYourRate() {

		WebElement desiredAmontField = wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.name(CHECK_YOUR_RATE_PAGE.getProperty(Constants.DESIRED_AMOUNT))));
		desiredAmontField.sendKeys(UI_DATA.getProperty(Constants.DESIRED_LOAN_AMOUNT));

		WebElement dropdown = wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath(CHECK_YOUR_RATE_PAGE.getProperty(Constants.LOAN_PURPOSE))));
		actions.moveToElement(dropdown).click().perform();
		Select loanPurpose = new Select(
				driver.findElement(By.xpath(CHECK_YOUR_RATE_PAGE.getProperty(Constants.LOAN_PURPOSE))));
		loanPurpose.selectByValue(UI_DATA.getProperty(Constants.LOAN_PURPOSE_BUSINESS));

		driver.findElement(By.xpath(CHECK_YOUR_RATE_PAGE.getProperty(Constants.CHECK_YOUR_RATE))).click();
	}

	@Test(priority = 3)
	public void basicInfoPage() {

		WebElement individualRadioButton = wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath(BASIC_INFO_PAGE.getProperty(Constants.INDIVIDUAL))));
		individualRadioButton.click();

		driver.findElement(By.name(BASIC_INFO_PAGE.getProperty(Constants.FIRST_NAME)))
				.sendKeys(UI_DATA.getProperty(Constants.FIRST_NAME_VAL));

		driver.findElement(By.name(BASIC_INFO_PAGE.getProperty(Constants.LAST_NAME)))
				.sendKeys(UI_DATA.getProperty(Constants.LAST_NAME_VAL));

		driver.findElement(By.name(BASIC_INFO_PAGE.getProperty(Constants.STREET)))
				.sendKeys(UI_DATA.getProperty(Constants.STREET_VAL));

		driver.findElement(By.name(BASIC_INFO_PAGE.getProperty(Constants.CITY)))
				.sendKeys(UI_DATA.getProperty(Constants.CITY_VAL));

		driver.findElement(By.name(BASIC_INFO_PAGE.getProperty(Constants.STATE)))
				.sendKeys(UI_DATA.getProperty(Constants.STATE_VAL));

		driver.findElement(By.name(BASIC_INFO_PAGE.getProperty(Constants.ZIPCODE)))
				.sendKeys(UI_DATA.getProperty(Constants.ZIPCODE_VAL));

		driver.findElement(By.name(BASIC_INFO_PAGE.getProperty(Constants.DATE_OF_BIRTH)))
				.sendKeys(UI_DATA.getProperty(Constants.DOB_VAL));

		WebElement button = wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath(BASIC_INFO_PAGE.getProperty(Constants.CONTINUE_PERSONAL_INFO))));
		button.click();
	}

	@Test(priority = 4)
	public void annualIncomeInfoPage() throws Exception {

		WebElement annualIncomeTextBox = wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.name(ANNUAL_INCOME_INFO_PAGE.getProperty(Constants.ANNUAL_INCOME))));
		annualIncomeTextBox.sendKeys(UI_DATA.getProperty(Constants.ANNUAL_INCOME_VAL));

		driver.findElement(By.name(ANNUAL_INCOME_INFO_PAGE.getProperty(Constants.ADDITIONAL_ANNUAL_INCOME)))
				.sendKeys(UI_DATA.getProperty(Constants.ADDITIONAL_ANNUAL_INCOME_VAL));

		WebElement button = wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath(ANNUAL_INCOME_INFO_PAGE.getProperty(Constants.CONTINUE_INCOME_INFO))));
		button.isDisplayed();
		button.sendKeys(Keys.TAB);
		button.sendKeys(Keys.ENTER);

		wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.name(CREATE_ACCOUNT_PAGE.getProperty(Constants.NEW_USER_NAME_EMAIL_ID))));
	}

	@Test(priority = 5)
	public void createAccountPage() throws Exception {

		driver.findElement(By.name(CREATE_ACCOUNT_PAGE.getProperty(Constants.NEW_USER_NAME_EMAIL_ID)))
				.sendKeys(emailId);

		driver.findElement(By.name(CREATE_ACCOUNT_PAGE.getProperty(Constants.NEW_PASSWORD)))
				.sendKeys(UI_DATA.getProperty(Constants.UI_PASSWORD));

		WebElement checkBox = driver.findElement(By.xpath(CREATE_ACCOUNT_PAGE.getProperty(Constants.TERMS_AGREEMENTS)));
		actions.moveToElement(checkBox).click().perform();

		driver.findElement(By.xpath(CREATE_ACCOUNT_PAGE.getProperty(Constants.SUBMIT_PERSONAL_INFO))).click();
	}

	@Test(priority = 6)
	public void offersPage() {

		WebElement loanAmountElement = wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath(OFFERS_PAGE.getProperty(Constants.USER_LOAN_AMOUNT))));
		loanAmount = loanAmountElement.getText();

		monthlyPayment = driver.findElement(By.xpath(OFFERS_PAGE.getProperty(Constants.MONTHLY_PAYMENT))).getText();

		loanTerm = driver.findElement(By.xpath(OFFERS_PAGE.getProperty(Constants.LOAN_TERM))).getText();

		interestRate = driver.findElement(By.xpath(OFFERS_PAGE.getProperty(Constants.LOAN_INTEREST_RATE))).getText();

		apr = driver.findElement(By.xpath(OFFERS_PAGE.getProperty(Constants.APR))).getText();

		System.out.println("loanAmount: " + loanAmount + "\n" + "monthlyPayment: " + monthlyPayment + "\n"
				+ "loanTerm: " + loanTerm + "\n" + "interestRate: " + interestRate + "\n" + "apr: " + apr);

		// hard wait for element to get refreshed and active to click
		try {
			Thread.sleep(1000l);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		WebElement element = wait.until(
				ExpectedConditions.elementToBeClickable(By.xpath(OFFERS_PAGE.getProperty(Constants.MENU_NAVIGATOR))));
		actions.moveToElement(element).click().build().perform();
		// hard wait for element to get refreshed and active to click
		try {
			Thread.sleep(1000l);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.findElement(By.xpath(OFFERS_PAGE.getProperty(Constants.SIGNOUT))).click();
	}

	@Test(priority = 7)
	public void launchBrowserValidateTitleForLoginPortal() {

		lauchBrowserValidateTitle(UI_DATA.getProperty(Constants.PORTAL_URL),
				UI_DATA.getProperty(Constants.EXPECTED_TITLE_PORTAL));
	}

	@Test(priority = 8)
	public void upgradePortalLoginPage() {

		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.name(UPGRADE_PORTAL_LOGIN_PAGE.getProperty(Constants.USER_NAME_EMAIL_ID))));
		element.sendKeys(emailId);

		driver.findElement(By.name(UPGRADE_PORTAL_LOGIN_PAGE.getProperty(Constants.USER_PASSWORD)))
				.sendKeys(UI_DATA.getProperty(Constants.UI_PASSWORD));

		driver.findElement(By.xpath(UPGRADE_PORTAL_LOGIN_PAGE.getProperty(Constants.SIGNIN))).click();
	}

	@Test(priority = 9)
	public void offersPageValidation() {

		WebElement loanAmountElementToValidate = wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath(OFFERS_PAGE.getProperty(Constants.USER_LOAN_AMOUNT))));
		String loanAmountToValidate = loanAmountElementToValidate.getText();

		String monthlyPaymentToValidate = driver
				.findElement(By.xpath(OFFERS_PAGE.getProperty(Constants.MONTHLY_PAYMENT))).getText();

		String loanTermToValidate = driver.findElement(By.xpath(OFFERS_PAGE.getProperty(Constants.LOAN_TERM)))
				.getText();

		String interestRateToValidate = driver
				.findElement(By.xpath(OFFERS_PAGE.getProperty(Constants.LOAN_INTEREST_RATE))).getText();

		String aprToValidate = driver.findElement(By.xpath(OFFERS_PAGE.getProperty(Constants.APR))).getText();
		// validation:

		Assert.assertEquals(loanAmountToValidate, loanAmount);
		Assert.assertEquals(monthlyPaymentToValidate, monthlyPayment);
		Assert.assertEquals(loanTermToValidate, loanTerm);
		Assert.assertEquals(interestRateToValidate, interestRate);
		Assert.assertEquals(aprToValidate, apr);

		// hard wait for element to get refreshed and active to click
		try {
			Thread.sleep(1000l);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		WebElement element = wait.until(
				ExpectedConditions.elementToBeClickable(By.xpath(OFFERS_PAGE.getProperty(Constants.MENU_NAVIGATOR))));
		actions.moveToElement(element).click().build().perform();
		// hard wait for element to get refreshed and active to click
		try {
			Thread.sleep(1000l);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.findElement(By.xpath(OFFERS_PAGE.getProperty(Constants.SIGNOUT))).click();
	}

	@Test(priority = 10)
	public void closeBrowser() {
		quitBrowser();
	}

}
