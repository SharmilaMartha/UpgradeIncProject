package com.upgradeinc.ui;

import java.util.Random;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import utils.UiTestCommonUtils;
import utils.Constants;

import static utils.Configuration.UI_DATA;

import com.upgradeinc.pages.ui.AnnualIncomeInformationPage;
import com.upgradeinc.pages.ui.BasicInformationPage;
import com.upgradeinc.pages.ui.CheckYourRatePage;
import com.upgradeinc.pages.ui.CreateAccountPage;
import com.upgradeinc.pages.ui.OffersPage;
import com.upgradeinc.pages.ui.UpgradePortalLoginPage;

public class CredifyAccountCreationTest extends UiTestCommonUtils {

	private Random randomNumGen = new Random();

	private int randomNum = randomNumGen.nextInt(1000);

	private final String emailId = "candidate" + randomNum + "@upgrade-challenge.com";

	String loanAmount, monthlyPayment, loanTerm, interestRate, apr;

	private AnnualIncomeInformationPage annualIncomeInformationPage;
	private BasicInformationPage basicInformationPage;
	private CheckYourRatePage checkYourRatePage;
	private CreateAccountPage createAccountPage;
	private OffersPage offersPage;
	private UpgradePortalLoginPage upgradePortalLoginPage;

	@BeforeClass
	public void init() {
		annualIncomeInformationPage = new AnnualIncomeInformationPage(this.driver, this.actions, this.wait);
		basicInformationPage = new BasicInformationPage(this.driver, this.actions, this.wait);
		checkYourRatePage = new CheckYourRatePage(this.driver, this.actions, this.wait);
		createAccountPage = new CreateAccountPage(this.driver, this.actions, this.wait);
		offersPage = new OffersPage(this.driver, this.actions, this.wait);
		upgradePortalLoginPage = new UpgradePortalLoginPage(this.driver, this.actions, this.wait);
	}

	@Test(priority = 1)
	public void launchBrowserValidateTitleForRateCheck() {
		lauchBrowserValidateTitle(UI_DATA.getProperty(Constants.RATE_CHECK_URL),
				UI_DATA.getProperty(Constants.EXPECTED_TITLE_RATE_CHECK));
	}

	@Test(priority = 2)
	public void checkYourRate() {

		checkYourRatePage.waitAndSetDesiredAmount(UI_DATA.getProperty(Constants.DESIRED_LOAN_AMOUNT));
		checkYourRatePage.waitAndClickLoanPurposeDropdown();
		checkYourRatePage.selectValueForLoanPurpose(UI_DATA.getProperty(Constants.LOAN_PURPOSE_BUSINESS));
		checkYourRatePage.clickCheckYourRate();
	}

	@Test(priority = 3)
	public void basicInfoPage() {
		
		basicInformationPage.waitAndClickIndividualRadioButton();
		basicInformationPage.setFirstName(UI_DATA.getProperty(Constants.FIRST_NAME_VAL));
		basicInformationPage.setLastName(UI_DATA.getProperty(Constants.LAST_NAME_VAL));
		basicInformationPage.setStreet(UI_DATA.getProperty(Constants.STREET_VAL));
		basicInformationPage.setCity(UI_DATA.getProperty(Constants.CITY_VAL));
		basicInformationPage.setState(UI_DATA.getProperty(Constants.STATE_VAL));		
		basicInformationPage.setZipCode(UI_DATA.getProperty(Constants.ZIPCODE_VAL));
		basicInformationPage.setDateOfBirth(UI_DATA.getProperty(Constants.DOB_VAL));		
		basicInformationPage.WaitAndClickContinuePersonalInfo();
	}

	@Test(priority = 4)
	public void annualIncomeInfoPage() throws Exception {
		
		annualIncomeInformationPage.waitAndSetAnnualIncome(UI_DATA.getProperty(Constants.ANNUAL_INCOME_VAL));
		annualIncomeInformationPage.setAdditionalAnnualIncome(UI_DATA.getProperty(Constants.ADDITIONAL_ANNUAL_INCOME_VAL));
		annualIncomeInformationPage.waitAndClickContinueIncomeInfo();
	}

	@Test(priority = 5)
	public void createAccountPage() throws Exception {
		
		createAccountPage.waitAndSetNewUserEmailId(emailId);
		createAccountPage.setNewPassword(UI_DATA.getProperty(Constants.UI_PASSWORD));
		createAccountPage.checkTermsAndAgreements();
		createAccountPage.clickSubmitPersonalInformation();
	}

	@Test(priority = 6)
	public void offersPage() {
		
		loanAmount = offersPage.waitAndGetUserLoanAmountValue();
		monthlyPayment = offersPage.getMonthlyPaymentValue();
		loanTerm = offersPage.getLoanTermValue();
		interestRate = offersPage.getLoanInterestRateValue();
		apr = offersPage.getAprValue();
		
		System.out.println("loanAmount: " + loanAmount + "\n" + "monthlyPayment: " + monthlyPayment + "\n"
				+ "loanTerm: " + loanTerm + "\n" + "interestRate: " + interestRate + "\n" + "apr: " + apr);

		offersPage.waitAndClickMenuNavigator();
		offersPage.waitAndClickSignOut();
	}

	@Test(priority = 7)
	public void launchBrowserValidateTitleForLoginPortal() {

		lauchBrowserValidateTitle(UI_DATA.getProperty(Constants.PORTAL_URL),
				UI_DATA.getProperty(Constants.EXPECTED_TITLE_PORTAL));
	}

	@Test(priority = 8)
	public void upgradePortalLoginPage() {
		
		upgradePortalLoginPage.waitAndEnterUserEmailId(emailId);
		upgradePortalLoginPage.enterPassword(UI_DATA.getProperty(Constants.UI_PASSWORD));
		upgradePortalLoginPage.clickSignIn();
	}

	@Test(priority = 9)
	public void offersPageValidation() {
		
		String loanAmountToValidate = offersPage.waitAndGetUserLoanAmountValue();
		String monthlyPaymentToValidate = offersPage.getMonthlyPaymentValue();
		String loanTermToValidate = offersPage.getLoanTermValue();
		String interestRateToValidate = offersPage.getLoanInterestRateValue();
		String aprToValidate = offersPage.getAprValue();
		
		// validation:
		Assert.assertEquals(loanAmountToValidate, loanAmount);
		Assert.assertEquals(monthlyPaymentToValidate, monthlyPayment);
		Assert.assertEquals(loanTermToValidate, loanTerm);
		Assert.assertEquals(interestRateToValidate, interestRate);
		Assert.assertEquals(aprToValidate, apr);

		offersPage.waitAndClickMenuNavigator();
		offersPage.waitAndClickSignOut();
	}

	@Test(priority = 10)
	public void closeBrowser() {
		quitBrowser();
	}

}
