package com.numpyninja.herbalance.stepdefinitions.onboarding;

import com.fasterxml.jackson.databind.JsonNode;
import com.numpyninja.herbalance.context.TestContext;
import com.numpyninja.herbalance.pages.onboarding.PersonalDetailsPage;
import com.numpyninja.herbalance.testdata.TestDataManager;
import io.cucumber.java.en.*;
import org.testng.Assert;

public class PersonalDetailsSteps {

	private PersonalDetailsPage personalDetailsPage;

	public PersonalDetailsSteps(TestContext testContext) {
		personalDetailsPage = new PersonalDetailsPage(testContext);
	}

	// -------------------------
	// VALID PERSONAL DETAILS
	// -------------------------
	@When("the user enters valid personal details and clicks continue")
	public void enter_valid_personal_details() {

		JsonNode data = TestDataManager.get().get("onboarding").get("personalDetails").get("valid");

		personalDetailsPage.enterFirstName(data.get("firstName").asText());
		personalDetailsPage.enterAge(String.valueOf(data.get("age").asInt()));
		personalDetailsPage.selectBloodPressureStatus(data.get("bloodPressure").asText());

		personalDetailsPage.clickContinue();
	}	

	// -------------------------
	// EMPTY FORM SUBMISSION
	// -------------------------
	@When("the user clicks on Continue without filling any fields in personal details page")
	public void continue_without_filling_fields() {
		personalDetailsPage.clickContinue();
	}

	@Then("the user should see validation errors for first name, age, and blood pressure status")
	public void validation_errors_for_all_fields() {
		Assert.assertTrue(personalDetailsPage.isFirstNameErrorDisplayed());
		Assert.assertTrue(personalDetailsPage.isAgeErrorDisplayed());
		Assert.assertTrue(personalDetailsPage.isBloodPressureErrorDisplayed());
	}

	// -------------------------
	// ONLY FIRST NAME FILLED
	// -------------------------
	@When("the user enters only first name and clicks Continue in personal details page")
	public void enter_only_first_name() {
		JsonNode data = TestDataManager.get().get("onboarding").get("personalDetails").get("valid");
		personalDetailsPage.enterFirstName(data.get("firstName").asText());
		personalDetailsPage.clickContinue();
	}

	@Then("the user should see validation errors for age, and blood pressure status")
	public void validation_errors_for_age_and_bp() {
		Assert.assertTrue(personalDetailsPage.isAgeErrorDisplayed());
		Assert.assertTrue(personalDetailsPage.isBloodPressureErrorDisplayed());
	}

	// -------------------------
	// BACK BUTTON
	// -------------------------
	@When("the user clicks Back Button in personal details page")
	public void click_back_button() {
		personalDetailsPage.clickBack();
	}
	
}
