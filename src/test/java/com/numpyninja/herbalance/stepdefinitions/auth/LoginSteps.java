package com.numpyninja.herbalance.stepdefinitions.auth;

import com.fasterxml.jackson.databind.JsonNode;
import com.numpyninja.herbalance.context.TestContext;
import com.numpyninja.herbalance.navigation.NavigationHelper;
import com.numpyninja.herbalance.pages.auth.AuthPage;
import com.numpyninja.herbalance.pages.dashboard.DashboardPage;
import com.numpyninja.herbalance.testdata.TestDataManager;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.testng.Assert;

public class LoginSteps {

	private final TestContext testContext;
	private final NavigationHelper navigationHelper;
	private AuthPage authPage;
	private DashboardPage dashboardPage;

	public LoginSteps(TestContext testContext) {
		this.testContext = testContext;
		this.navigationHelper = new NavigationHelper(testContext);
	}

	@Given("the user is on the auth page")
	public void the_user_is_on_the_auth_page() {
		authPage = navigationHelper.goToAuthPage();
	}

	@When("the user clicks on the {string} tab")
	public void the_user_clicks_on_the_tab(String tabName) {
		authPage.clickTab(tabName);
	}

	@Then("the {string} form should be displayed")
	public void the_form_should_be_displayed(String formName) {
		Assert.assertTrue(authPage.isFormDisplayed(formName), formName + " form is NOT displayed");
	}

	@Then("the Login form should be displayed by default")
	public void the_login_form_should_be_displayed_by_default() {
		Assert.assertTrue(authPage.isFormDisplayed("login"), "Login" + " form is NOT displayed");
	}

	@Given("the user is on the Login form")
	public void the_user_is_on_the_login_form() {
		authPage = navigationHelper.goToLoginForm();
	}

	@When("the user logs in with {string} credentials")
	public void the_user_logs_in_with_valid_credentials(String datakey) {

		JsonNode loginData = TestDataManager.get().get("login").get(datakey);

		String email = loginData.get("email").asText();
		String password = loginData.get("password").asText();

		authPage.login(email, password);
	}

	@Then("the user should see the dashboard page")
	public void the_user_should_see_the_dashboard_page() {
		dashboardPage = new DashboardPage(testContext);
		Assert.assertTrue(dashboardPage.isDashboardDisplayed(), "Dashboard is NOT displayed");
	}	

	@Then("the user should see error message")
	public void the_user_should_see_error_message() {
		Assert.assertTrue(authPage.isLoginErrorDisplayed(), "Error message NOT displayed");
	}

}
