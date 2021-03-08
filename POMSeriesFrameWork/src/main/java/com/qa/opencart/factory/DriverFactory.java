package com.qa.opencart.factory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {
	WebDriver driver;
	Properties prop;
	public static String highlight;
	OptionsManager op;
	
	/**
	 * This method is used to initilizing the driver on the basis of given browsername
	 * @param BrowserName
	 * @return this return WebDriver Driver
	 */
	
	public  WebDriver Init_driver(Properties prop) {
		String BrowserName = prop.getProperty("browser");
		
		System.out.println("Broswer name is: "+ BrowserName);
		highlight = prop.getProperty("highlight");
		 op = new OptionsManager(prop);
		
		if (BrowserName.equals("chrome")) {
			WebDriverManager.chromedriver().setup();
			 driver= new ChromeDriver(op.getChromeOptions()); 
			
		}
		else if (BrowserName.equals("firefox")) {
			WebDriverManager.firefoxdriver().setup();
	 		driver= new FirefoxDriver();
		
		}

		else {
			System.out.println("Please pass correct browser name=====: "+ BrowserName);
			
			
		}
		driver.manage().window().fullscreen();
		driver.manage().deleteAllCookies();
	
		return driver;
		}
	
		/**
		 * This menthod is used to initiliaze the properties from config file
		 * @return returns Properties prop.
		 */
		public Properties init_prop() {
			prop= new Properties();
			try {
				FileInputStream ip = new FileInputStream("./src/test/resources/config/config.properties");
				prop.load(ip);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
					
			return prop;
		}
	
			

		 
			
}
