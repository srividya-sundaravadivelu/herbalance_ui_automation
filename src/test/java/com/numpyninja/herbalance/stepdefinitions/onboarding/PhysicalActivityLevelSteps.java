package com.numpyninja.herbalance.stepdefinitions.onboarding;

import com.fasterxml.jackson.databind.JsonNode;
import com.numpyninja.herbalance.context.TestContext;
import com.numpyninja.herbalance.pages.onboarding.PhysicalActivityLevelPage;
import com.numpyninja.herbalance.testdata.TestDataManager;
import io.cucumber.java.en.*;
import org.testng.Assert;

public class PhysicalActivityLevelSteps {

	private PhysicalActivityLevelPage activityPage;

	public PhysicalActivityLevelSteps(TestContext testContext) {
		activityPage = new PhysicalActivityLevelPage(testContext);
	}	

	@When("the user doesn’t select any physical activity")
	public void the_user_doesnt_select_any_physical_activity() {
		// Do nothing intentionally
	}

	@Then("the Continue button should be disabled on the physical activity level page")
	public void the_continue_button_should_be_disabled() {
		Assert.assertTrue(activityPage.isContinueDisabled(), "Continue button is NOT disabled");
	}
	
	@When("the user selects a physical activity level {string}")
	public void the_user_selects_a_physical_activity_level(String key) {

	    JsonNode activityNode = TestDataManager.get()
	            .get("onboarding")
	            .get("physicalActivity")
	            .get(key);

	    String activityLabel = activityNode.get("activity").asText();

	    activityPage.selectActivity(activityLabel);
	}
	
	@Then("the correct message should be displayed for the selected activity level {string}")
	public void the_correct_message_should_be_displayed(String key) {

	    JsonNode activityNode = TestDataManager.get()
	            .get("onboarding")
	            .get("physicalActivity")
	            .get(key);

	    String expectedMessage = activityNode.get("message").asText();

	    Assert.assertTrue(
	            activityPage.isMessageDisplayed(expectedMessage),
	            "Expected message NOT displayed for: " + key
	    );
	}
	

	@When("the user clicks {string} on the physical activity level page")
	public void the_user_clicks_on_the_physical_activity_level_page(String buttonName) {
		activityPage.clickButton(buttonName);
	}

}
