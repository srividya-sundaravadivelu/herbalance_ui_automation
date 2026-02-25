package com.numpyninja.herbalance.stepdefinitions.onboarding;

import com.numpyninja.herbalance.context.TestContext;
import com.numpyninja.herbalance.pages.onboarding.MenstrualCycleAwarenessPage;
import io.cucumber.java.en.*;
import org.testng.Assert;

public class MenstrualCycleAwarenessSteps {

	private MenstrualCycleAwarenessPage menstrualPage;

	public MenstrualCycleAwarenessSteps(TestContext testContext) {
		menstrualPage = new MenstrualCycleAwarenessPage(testContext);
	}

	// -------------------------
	// ACTIONS
	// -------------------------
	@When("the user selects an option in menstrual cycle awareness")
	public void the_user_selects_an_option_in_menstrual_cycle_awareness() {
		menstrualPage.selectAnyOption();
	}

	@When("the user doesn’t select any option and tries to click {string}")
	public void the_user_doesnt_select_any_option_and_tries_to_click(String buttonName) {
		menstrualPage.clickButton(buttonName);
	}

	@When("the user clicks the {string} button on the menstrual cycle awareness page")
	public void the_user_clicks_button_on_menstrual_cycle_awareness_page(String buttonName) {
		menstrualPage.clickButton(buttonName);
	}

	@When("the user clicks {string} after selecting an option in menstrual cycle awareness")
	public void the_user_clicks_button_after_selecting_an_option_in_menstrual_cycle_awareness_page(String buttonName) {
		menstrualPage.selectAnyOption();
		menstrualPage.clickButton(buttonName);
	}

	// -------------------------
	// VALIDATION
	// -------------------------
	@Then("the system should display the correct guidance message for the selected option")
	public void the_system_should_display_the_correct_guidance_message() {
		Assert.assertTrue(menstrualPage.isGuidanceMessageDisplayed(), "Guidance message is NOT displayed");
	}

	@Then("the Continue button should be disabled")
	public void the_continue_button_should_be_disabled() {
		Assert.assertTrue(menstrualPage.isContinueDisabled(), "Continue button is NOT disabled");
	}

}
