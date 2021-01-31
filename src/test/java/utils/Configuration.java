package utils;

import java.io.IOException;
import java.util.Properties;

public class Configuration {
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
