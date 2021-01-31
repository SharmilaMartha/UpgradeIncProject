package com.upgradeinc.pages.ui;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class BasicInformationPage extends BasePage {
	
	///////////////////////////////////elements///////////////////////////////////
	
	@FindBy(xpath="//div[text()=\"Individual\"]")
    WebElement individual;
	
	@FindBy(name="borrowerFirstName")
    WebElement firstName;
	
	@FindBy(name="borrowerLastName")
    WebElement lastName;
	
	@FindBy(name="borrowerStreet")
    WebElement street;
	
	@FindBy(name="borrowerCity")
    WebElement city;
	
	@FindBy(name="borrowerState")
    WebElement state;
	
	@FindBy(name="borrowerZipCode")
    WebElement zipCode;
	
	@FindBy(name="borrowerDateOfBirth")
    WebElement dateOfBirth;
	
	@FindBy(xpath="//button[@data-auto='continuePersonalInfo']")
    WebElement continuePersonalInfo;
	
	///////////////////////////////////constructor///////////////////////////////////
	
	public BasicInformationPage(
			WebDriver driver, 
			Actions actions, 
			WebDriverWait wait) {
		super(driver, actions, wait);
	}
	
	///////////////////////////////////actions///////////////////////////////////
	
	public void waitAndClickIndividualRadioButton() {
		wait.until(ExpectedConditions.visibilityOf(individual));
		individual.click();
	}
	
	public void setFirstName(String firstNameValue) {
		firstName.sendKeys(firstNameValue);
	}
	
	public void setLastName(String lastNameValue) {
		lastName.sendKeys(lastNameValue);
	}
	
	public void setStreet(String streetValue) {
		street.sendKeys(streetValue);
	}
	
	public void setCity(String cityValue) {
		city.sendKeys(cityValue);
	}
	
	public void setState(String stateValue) {
		state.sendKeys(stateValue);
	}
	
	public void setZipCode(String zipCodeValue) {
		zipCode.sendKeys(zipCodeValue);
	}
	
	public void setDateOfBirth(String dateOfBirthValue) {
		dateOfBirth.sendKeys(dateOfBirthValue);
	}
	
	public void WaitAndClickContinuePersonalInfo() {
		wait.until(ExpectedConditions.visibilityOf(continuePersonalInfo));
		continuePersonalInfo.click();
	}
	
}
