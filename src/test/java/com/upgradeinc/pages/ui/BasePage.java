package com.upgradeinc.pages.ui;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class BasePage {
	protected WebDriver driver;
	protected Actions actions;
	protected WebDriverWait wait;

	public BasePage(
			WebDriver driver, 
			Actions actions, 
			WebDriverWait wait) {
		this.driver = driver;
		this.wait = wait;
		this.actions = actions;
		PageFactory.initElements(driver, this);
	}
}
