package com.upgradeinc.pages.ui;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class UpgradePortalLoginPage extends BasePage {
	
	///////////////////////////////////elements///////////////////////////////////
		
	@FindBy(name="username")
	WebElement userEmailId;
	
	@FindBy(name="password")
	WebElement userPassword;
	
	@FindBy(xpath="//button[text()='Sign in to your account']")
	WebElement signIn;
	
	///////////////////////////////////constructor///////////////////////////////////
	
	public UpgradePortalLoginPage(
	WebDriver driver, 
	Actions actions, 
	WebDriverWait wait) {
	super(driver, actions, wait);
	}
	
	///////////////////////////////////actions///////////////////////////////////
	
	public void waitAndEnterUserEmailId(String userEmailIdValue) {
		wait.until(ExpectedConditions.visibilityOf(userEmailId));
		userEmailId.sendKeys(userEmailIdValue);
	}
	
	public void enterPassword(String passwordValue) {
		wait.until(ExpectedConditions.visibilityOf(userPassword));
		userPassword.sendKeys(passwordValue);
	}
	
	public void clickSignIn() {
		signIn.click();
	}
	
	}
