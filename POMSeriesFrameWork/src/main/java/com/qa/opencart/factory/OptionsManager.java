package com.qa.opencart.factory;

import java.util.Properties;

import org.openqa.selenium.chrome.ChromeOptions;

public class OptionsManager {

	private Properties prop;
	private ChromeOptions co;
	public OptionsManager(Properties prop) {
		this.prop= prop;
	}
	
	public ChromeOptions getChromeOptions() {
		co = new ChromeOptions();
		if(Boolean.parseBoolean(prop.getProperty("headless").trim())) co.addArguments("--headless");
			return co;
		}
	}

