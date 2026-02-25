package com.numpyninja.herbalance.stepdefinitions.home;

import com.numpyninja.herbalance.context.TestContext;
import com.numpyninja.herbalance.navigation.NavigationHelper;
import com.numpyninja.herbalance.pages.auth.AuthPage;
import com.numpyninja.herbalance.pages.home.HomePage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.testng.Assert;

public class HomeSteps {

	private final TestContext testContext;
	private final NavigationHelper navigationHelper;
	private HomePage homePage;
	private AuthPage authPage;

	public HomeSteps(TestContext testContext) {
		this.testContext = testContext;
		this.navigationHelper = new NavigationHelper(testContext);
	}

	@Given("the user is in the home page")
	public void the_user_is_in_the_home_page() {
		homePage = navigationHelper.goToHomePage();
		authPage = new AuthPage(testContext);
		Assert.assertTrue(homePage.isHomePageDisplayed(), "Home Page is NOT displayed");
	}

	@When("the user clicks the {string} button on the home page")
	public void the_user_clicks_the_button(String ctaButton) {
		homePage.clickCTA(ctaButton);
	}

	@Then("the Registration screen should be displayed")
	public void the_registration_screen_should_be_displayed() {
		Assert.assertTrue(authPage.isFormDisplayed("signup"), "Registration screen is NOT displayed");
	}

	@When("the user clicks the Login button")
	public void the_user_clicks_the_login_button() {
		homePage.clickLoginButton();
	}

	@Then("the Login screen should be displayed")
	public void the_login_screen_should_be_displayed() {
		Assert.assertTrue(authPage.isFormDisplayed("login"), "Login screen is NOT displayed");
	}
}
