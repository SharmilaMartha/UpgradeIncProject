package com.upgradeinc.pages.ui;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class CreateAccountPage extends BasePage {

	/////////////////////////////////// elements///////////////////////////////////

	@FindBy(name = "username")
	WebElement newUserNameEmailId;

	@FindBy(name = "password")
	WebElement newPassword;

	@FindBy(xpath = "//*[@type='checkbox' and @name='agreements']//following-sibling::div")
	WebElement termsAgreements;
	
	@FindBy(xpath = "//button[@data-auto='submitPersonalInfo']")
	WebElement submitPersonalInfo;

	/////////////////////////////////// constructor///////////////////////////////////

	public CreateAccountPage(WebDriver driver, Actions actions, WebDriverWait wait) {
		super(driver, actions, wait);
	}

	/////////////////////////////////// actions///////////////////////////////////

	public void waitAndSetNewUserEmailId(String newUserEmailIdValue) {
		wait.until(ExpectedConditions.visibilityOf(newUserNameEmailId));
		newUserNameEmailId.sendKeys(newUserEmailIdValue);
	}

	public void setNewPassword(String newPasswordValue) {
		newPassword.sendKeys(newPasswordValue);
	}

	public void checkTermsAndAgreements() {
		WebElement checkBox = wait.until(ExpectedConditions.elementToBeClickable(termsAgreements));
		actions.moveToElement(checkBox).click().perform();
	}

	public void clickSubmitPersonalInformation() {
		submitPersonalInfo.click();
	}

}
