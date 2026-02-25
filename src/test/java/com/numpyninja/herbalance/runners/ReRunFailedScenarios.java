package com.numpyninja.herbalance.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "@target/failed_scenarios.txt", glue = { "com.numpyninja.herbalance.stepdefinitions",
		"com.numpyninja.herbalance.hooks" })
public class ReRunFailedScenarios extends AbstractTestNGCucumberTests {
}
