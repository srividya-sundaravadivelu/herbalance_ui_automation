package com.numpyninja.herbalance.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class DriverManager {

	private WebDriver driver;
	private ConfigReader configReader;

	public DriverManager(ConfigReader configReader) {
		this.configReader = configReader;
	}

	public void initDriver() {

		String browser = configReader.getBrowser().toLowerCase();
		boolean headless = configReader.isHeadless();

		switch (browser) {

		case "firefox":
			FirefoxOptions firefoxOptions = new FirefoxOptions();
			if (headless) {
				firefoxOptions.addArguments("-headless");
			}
			driver = new FirefoxDriver(firefoxOptions);
			break;

		case "edge":
			EdgeOptions edgeOptions = new EdgeOptions();
			if (headless) {
				edgeOptions.addArguments("--headless=new");
			}
			driver = new EdgeDriver(edgeOptions);
			break;

		case "chrome":
		default:
			ChromeOptions chromeOptions = new ChromeOptions();
			if (headless) {
				chromeOptions.addArguments("--headless=new");
			}
			driver = new ChromeDriver(chromeOptions);
			break;
		}

		driver.manage().window().maximize();
		driver.get(configReader.getBaseUrl());
	}

	public WebDriver getDriver() {
	    if (driver == null) {
	        initDriver();
	    }
	    return driver;
	}


	public void quitDriver() {
		if (driver != null) {
			driver.quit();
		}
	}
}
