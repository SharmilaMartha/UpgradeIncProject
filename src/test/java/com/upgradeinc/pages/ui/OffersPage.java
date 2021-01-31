package com.upgradeinc.pages.ui;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class OffersPage extends BasePage {
	
	///////////////////////////////////elements///////////////////////////////////
	
	@FindBy(xpath="//*[@data-auto='userLoanAmount']//self::span")
    WebElement userLoanAmount;
	
	@FindBy(xpath="//span[@data-auto='defaultMonthlyPayment']")
    WebElement monthlyPayment;
	
	@FindBy(xpath="//div[@data-auto='defaultLoanTerm']")
    WebElement loanTerm;
	
	@FindBy(xpath="//div[@data-auto='defaultLoanInterestRate']")
    WebElement loanInterestRate;
	
	@FindBy(xpath="//div[@data-auto='defaultMoreInfoAPR']")
    WebElement apr;
	
	@FindBy(xpath="//*[@class='header-nav']/label")
    WebElement menuNavigator;
	
	@FindBy(xpath="//a[text()='Sign Out']")
    WebElement signOut;
	
	///////////////////////////////////constructor///////////////////////////////////

	public OffersPage(
			WebDriver driver, 
			Actions actions, 
			WebDriverWait wait) {
		super(driver, actions, wait);
	}

	///////////////////////////////////actions///////////////////////////////////
	
	
	public String waitAndGetUserLoanAmountValue() {
		wait.until(ExpectedConditions.visibilityOf(userLoanAmount));
		return userLoanAmount.getText();
	}
	
	public String getMonthlyPaymentValue() {
		return monthlyPayment.getText();
	}
	
	public String getLoanTermValue() {
		return loanTerm.getText();
	}
	
	public String getLoanInterestRateValue() {
		return loanInterestRate.getText();
	}
	
	public String getAprValue() {
		return apr.getText();
	}
	
	public void waitAndClickMenuNavigator() {
		// hard wait for element to get refreshed and active to click
			try {
					Thread.sleep(1000l);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			WebElement navigator = wait.until(ExpectedConditions.elementToBeClickable(menuNavigator));
			actions.moveToElement(navigator).click().build().perform();
	}
	
	public void waitAndClickSignOut() {
		// hard wait for element to get refreshed and active to click
			try {
					Thread.sleep(1000l);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			signOut.click();
	}

}
