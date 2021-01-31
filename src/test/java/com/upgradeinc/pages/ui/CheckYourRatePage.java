package com.upgradeinc.pages.ui;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CheckYourRatePage extends BasePage {
	
	///////////////////////////////////elements///////////////////////////////////
	
	@FindBy(name="desiredAmount")
    WebElement desiredAmount;
	
	@FindBy(xpath="//select[@aria-label='Loan Purpose']")
    WebElement loanPurpose;
	
	@FindBy(xpath="//button[@data-auto='CheckYourRate']")
    WebElement checkYourRate;
	
	///////////////////////////////////constructor///////////////////////////////////

	public CheckYourRatePage(
			WebDriver driver, 
			Actions actions, 
			WebDriverWait wait) {
		super(driver, actions, wait);
	}
	
	///////////////////////////////////actions///////////////////////////////////
	
	public void waitAndSetDesiredAmount(String desiredLoanAmount) {
		wait.until(ExpectedConditions.visibilityOf(desiredAmount));
		desiredAmount.sendKeys(desiredLoanAmount);
	}
	
	public void waitAndClickLoanPurposeDropdown() {
		// hard wait for element to get refreshed and active to click
		try {
			Thread.sleep(1000l);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		WebElement dropdown = wait.until(ExpectedConditions.visibilityOf(loanPurpose));
		actions.moveToElement(dropdown).click().perform();
	}
	
	public void selectValueForLoanPurpose(String loanPurposeValue) {
		Select select = new Select(loanPurpose);
		select.selectByValue(loanPurposeValue);
	}
	
	public void clickCheckYourRate() {
		checkYourRate.click();
	}
	

	
}
