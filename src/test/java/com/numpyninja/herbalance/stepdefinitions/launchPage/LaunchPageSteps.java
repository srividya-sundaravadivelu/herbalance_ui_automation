package com.numpyninja.herbalance.stepdefinitions.launchPage;

import com.numpyninja.herbalance.context.TestContext;
import com.numpyninja.herbalance.navigation.NavigationHelper;
import com.numpyninja.herbalance.pages.home.HomePage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.testng.Assert;

public class LaunchPageSteps {

	private final NavigationHelper navigationHelper;
	private HomePage homePage;

	public LaunchPageSteps(TestContext testContext) {
		this.navigationHelper = new NavigationHelper(testContext);
	}

	@Given("the user launches the application")
	public void the_user_launches_the_application() {
		homePage = navigationHelper.goToHomePage();		
	}

	@Then("the Home page should be displayed")
	public void the_home_page_should_be_displayed() {
		Assert.assertTrue(homePage.isHomePageDisplayed(), "Home page is NOT displayed");
	}
}
