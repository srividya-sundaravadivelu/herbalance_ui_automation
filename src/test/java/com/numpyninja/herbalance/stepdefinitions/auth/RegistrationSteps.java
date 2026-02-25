package com.numpyninja.herbalance.stepdefinitions.auth;

import com.fasterxml.jackson.databind.JsonNode;
import com.numpyninja.herbalance.context.TestContext;
import com.numpyninja.herbalance.navigation.NavigationHelper;
import com.numpyninja.herbalance.pages.auth.AuthPage;
import com.numpyninja.herbalance.testdata.TestDataManager;

import io.cucumber.java.en.*;
import org.testng.Assert;

public class RegistrationSteps {

	private final NavigationHelper navigationHelper;
	private AuthPage authPage;
	public RegistrationSteps(TestContext testContext) {
		this.navigationHelper = new NavigationHelper(testContext);
	}

	@Given("the user is on the Sign up form")
	public void the_user_is_on_the_sign_up_form() {
		authPage = navigationHelper.goToSignupForm();
	}

	@When("the user registers with {string} data")
	public void the_user_registers_with_data(String dataKey) {

		JsonNode reg = TestDataManager.get().get("registration").get(dataKey);

		String email = reg.get("email").asText();
		String password = reg.get("password").asText();
		String confirmPassword = reg.get("confirmPassword").asText();
		boolean acceptTerms = reg.get("acceptTerms").asBoolean();

		authPage.register(email, password, confirmPassword, acceptTerms);
	}

	@Then("the user should see the correct result for registration {string}") 
	public void the_user_should_see_the_correct_result(String dataKey) {

	    JsonNode regNode = TestDataManager.get()
	            .get("registration")
	            .get(dataKey);

	    String expected = regNode.get("expectedResult").asText();

	    Assert.assertTrue(
	        authPage.isExpectedResultDisplayed(expected),
	        "Expected result NOT displayed: " + expected
	    );
	}

}
