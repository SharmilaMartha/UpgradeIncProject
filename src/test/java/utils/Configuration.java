package utils;

import java.io.IOException;
import java.util.Properties;

public class Configuration {
	public static Properties CHECK_YOUR_RATE_PAGE = loadProperties("checkYourRatePage.properties");
	public static Properties BASIC_INFO_PAGE = loadProperties("basicInfoPage.properties");
	public static Properties ANNUAL_INCOME_INFO_PAGE = loadProperties("annualIncomeInfoPage.properties");
	public static Properties CREATE_ACCOUNT_PAGE = loadProperties("createAccountPage.properties");
	public static Properties OFFERS_PAGE = loadProperties("offersPage.properties");
	public static Properties UPGRADE_PORTAL_LOGIN_PAGE = loadProperties("upgradePortalLoginPage.properties");
	public static Properties UI_DATA = loadProperties("ui_Datafile.properties");
	public static Properties API_DATA = loadProperties("api_Datafile.properties");
	
	private Configuration () {
	}
	
	private static Properties loadProperties(String filePath) {
		Properties properties = new Properties();
		try {
		    //load a properties file from class path, inside static method
			properties.load(Configuration.class.getClassLoader().getResourceAsStream(filePath));
		} 
		catch (IOException ex) {
		    ex.printStackTrace();
		}
		return properties;
	}
}
