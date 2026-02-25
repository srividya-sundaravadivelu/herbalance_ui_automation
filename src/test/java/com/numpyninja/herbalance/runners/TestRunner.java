package com.numpyninja.herbalance.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/resources/features", 	
	glue = { "com.numpyninja.herbalance.stepdefinitions", "com.numpyninja.herbalance.hooks" }, 
	plugin = { "pretty", "html:target/cucumber-report.html", "json:target/cucumber.json", "rerun:target/failed_scenarios.txt" }, 
	tags = "")
public class TestRunner extends AbstractTestNGCucumberTests {

}
