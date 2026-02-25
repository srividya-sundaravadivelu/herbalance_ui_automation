package com.numpyninja.herbalance.utils;

import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {

	private Properties properties;

	public ConfigReader() {
		loadProperties();
	}

	private void loadProperties() {
		try {
			properties = new Properties();

			InputStream inputStream = getClass().getClassLoader().getResourceAsStream("config/config.properties");

			if (inputStream == null) {
				throw new RuntimeException("config.properties not found in classpath");
			}

			properties.load(inputStream);

		} catch (Exception e) {
			throw new RuntimeException("Failed to load config.properties", e);
		}
	}

	public String getBrowser() {
		return System.getProperty("browser", properties.getProperty("browser", "chrome"));
	}

	public boolean isHeadless() {
		return Boolean.parseBoolean(System.getProperty("headless", properties.getProperty("headless", "false")));
	}

	public String getBaseUrl() {
		return properties.getProperty("base.url");
	}

	public int getImplicitWait() {
		return Integer.parseInt(properties.getProperty("implicit.wait", "10"));
	}

	public boolean isStubMode() {
		return Boolean.parseBoolean(properties.getProperty("stubMode", "false"));
	}

}
