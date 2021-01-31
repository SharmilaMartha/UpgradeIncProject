package com.upgradeinc.pages.ui;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class AnnualIncomeInformationPage extends BasePage {
	
	///////////////////////////////////elements///////////////////////////////////
	
	@FindBy(name="borrowerIncome")
    WebElement annualIncome;
	
	@FindBy(name="borrowerAdditionalIncome")
    WebElement additionalAnnualIncome;
	
	@FindBy(xpath="//button[@data-auto='continuePersonalInfo']")
    WebElement continueIncomeInfo;
	
	///////////////////////////////////constructor///////////////////////////////////
	
	public AnnualIncomeInformationPage(
			WebDriver driver, 
			Actions actions, 
			WebDriverWait wait) {
		super(driver, actions, wait);
	}
	
	///////////////////////////////////actions///////////////////////////////////
	
	public void waitAndSetAnnualIncome(String annualIncomeValue) {
		wait.until(ExpectedConditions.visibilityOf(annualIncome));
		annualIncome.sendKeys(annualIncomeValue);
	}
	
	public void setAdditionalAnnualIncome(String additionalAnnualIncomeValue) {
		additionalAnnualIncome.sendKeys(additionalAnnualIncomeValue);
	}
	
	public void waitAndClickContinueIncomeInfo() {
		WebElement button = wait.until(ExpectedConditions.visibilityOf(continueIncomeInfo));
		button.isDisplayed();
		button.sendKeys(Keys.TAB);
		button.sendKeys(Keys.ENTER);
	}
	

}
