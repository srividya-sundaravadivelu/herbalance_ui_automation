package com.numpyninja.herbalance.runners;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.testng.SkipException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(glue = { "com.numpyninja.herbalance.stepdefinitions" }, plugin = { "pretty",
		"html:target/cucumber-reports/rerun.html" })
public class ReRunFailedScenarios extends AbstractTestNGCucumberTests {

	@Override
	@DataProvider(parallel = true)
	public Object[][] scenarios() {
		return super.scenarios();
	}

	@BeforeClass(alwaysRun = true)
	public void setUpClass() throws IOException {
		Path rerunFile = Paths.get("target/failed_scenarios.txt");

		if (!Files.exists(rerunFile) || Files.size(rerunFile) == 0) {
			throw new SkipException("No failed scenarios to rerun.");
		}

		System.setProperty("cucumber.features", "@" + rerunFile.toString());
	}
}
