package com.numpyninja.herbalance.context;

import com.numpyninja.herbalance.utils.DriverManager;
import com.numpyninja.herbalance.utils.TestDataReader;
import com.numpyninja.herbalance.utils.ConfigReader;

public class TestContext {

	private DriverManager driverManager;
	private TestDataReader testDataReader;
	private ConfigReader configReader;

	public TestContext() {
		configReader = new ConfigReader();
		driverManager = new DriverManager(configReader);
		testDataReader = new TestDataReader();
	}

	public DriverManager getDriverManager() {
		return driverManager;
	}

	public TestDataReader getTestDataReader() {
		return testDataReader;
	}

	public ConfigReader getConfigReader() {
		return configReader;
	}	
}
