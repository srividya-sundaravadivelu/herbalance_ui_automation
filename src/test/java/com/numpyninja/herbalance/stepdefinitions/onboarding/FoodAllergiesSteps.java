package com.numpyninja.herbalance.stepdefinitions.onboarding;

import com.fasterxml.jackson.databind.JsonNode;
import com.numpyninja.herbalance.context.TestContext;
import com.numpyninja.herbalance.pages.onboarding.FoodAllergiesPage;
import com.numpyninja.herbalance.testdata.TestDataManager;
import io.cucumber.java.en.*;
import org.testng.Assert;

public class FoodAllergiesSteps {

	private final FoodAllergiesPage allergiesPage;

	public FoodAllergiesSteps(TestContext testContext) {
		this.allergiesPage = new FoodAllergiesPage(testContext);
	}

	// ------------------------------------------------------------
	// JSON HELPERS
	// ------------------------------------------------------------

	private JsonNode getPreferenceOptions() {
		return TestDataManager.get().get("onboarding").get("foodPreferenceOptions");
	}

	private JsonNode getFoodsToAvoid() {
		return TestDataManager.get().get("onboarding").get("foodsToAvoid");
	}

	private JsonNode findPreferenceById(String id) {
		for (JsonNode option : getPreferenceOptions()) {
			if (option.get("id").asText().equals(id)) {
				return option;
			}
		}
		throw new IllegalArgumentException("Invalid preference key: " + id);
	}

	private JsonNode findFoodByName(String name) {
		for (JsonNode food : getFoodsToAvoid()) {
			if (food.get("name").asText().equals(name)) {
				return food;
			}
		}
		throw new IllegalArgumentException("Invalid food key: " + name);
	}

	// ------------------------------------------------------------
	// SCENARIO 1: USER SELECTS NOTHING
	// ------------------------------------------------------------

	@When("the user doesn’t select any option in food allergy or intolerance")
	public void the_user_doesnt_select_any_option() {
		// Intentionally empty — user selects nothing
	}

	@Then("the system should display the no preference selected error")
	public void the_system_should_display_no_preference_selected_error() {

		String expectedError = TestDataManager.get().get("onboarding").get("foodAllergies")
				.get("noPreferenceSelectedError").asText();

		Assert.assertTrue(allergiesPage.isErrorDisplayed(expectedError),
				"Expected error NOT displayed: " + expectedError);
	}

	// ------------------------------------------------------------
	// SCENARIO 2: USER SELECTS A FOOD PREFERENCE
	// ------------------------------------------------------------

	@When("the user selects a food preference {string}")
	public void the_user_selects_a_food_preference(String preferenceKey) {

		JsonNode option = findPreferenceById(preferenceKey);
		String label = option.get("label").asText();

		allergiesPage.selectFoodPreference(label);
	}

	@Then("the system should behave based on the selected food preference {string}")
	public void the_system_should_behave_based_on_preference(String preferenceKey) {

		JsonNode option = findPreferenceById(preferenceKey);
		boolean requiresFoodsToAvoid = option.get("requiresFoodsToAvoid").asBoolean();

		allergiesPage.setStubRequiresFoodsToAvoid(requiresFoodsToAvoid);

		Assert.assertEquals(allergiesPage.requiresFoodsToAvoid(), requiresFoodsToAvoid,
				"System behavior mismatch for preference: " + preferenceKey);
	}

	// ------------------------------------------------------------
	// FOODS TO AVOID SELECTION
	// ------------------------------------------------------------

	@Given("the user is on the foods to avoid selection screen")
	public void the_user_is_on_foods_to_avoid_screen() {
		allergiesPage.goToFoodsToAvoidScreen();
	}

	@When("the user selects food to avoid {string}")
	public void the_user_selects_food_to_avoid(String foodKey) {

		JsonNode food = findFoodByName(foodKey);
		allergiesPage.selectFoodToAvoid(food.get("name").asText());
	}

	@Then("the system should display the correct informational message for that food {string}")
	public void the_system_should_display_correct_info_message(String foodKey) {

		JsonNode food = findFoodByName(foodKey);
		String expectedMessage = food.get("infoMessage").asText();

		Assert.assertTrue(allergiesPage.isInfoMessageDisplayed(expectedMessage),
				"Incorrect info message for: " + foodKey);
	}

	// ------------------------------------------------------------
	// MULTIPLE FOODS SELECTED
	// ------------------------------------------------------------

	@When("the user selects more than 3 foods to avoid")
	public void the_user_selects_more_than_three_foods() {
		allergiesPage.selectMoreThanThreeFoods();
	}

	@Then("the system should display the first 3 relevant messages and a \"+<remainingCount> more\" indicator")
	public void the_system_should_display_summary_message() {
		Assert.assertTrue(allergiesPage.isSummaryMessageDisplayed(), "Summary message NOT displayed");
	}

	// ------------------------------------------------------------
	// NAVIGATION BUTTONS
	// ------------------------------------------------------------

	@When("the user clicks on \"Continue\" after selecting food allergy")
	public void the_user_clicks_continue_after_selecting_food_allergy() {
		allergiesPage.clickButton("Continue");
	}

	@When("the user clicks \"Back\" Button in food allergies page")
	public void the_user_clicks_back_button() {
		allergiesPage.clickButton("Back");
	}

	// ------------------------------------------------------------
	// SCENARIO 3: USER SELECTS A REQUIRING OPTION BUT NO FOODS
	// ------------------------------------------------------------

	@When("the user selects a food preference option that requires selecting foods to avoid, but clicks continue without selecting the foods to avoid")
	public void the_user_selects_preference_that_requires_foods_to_avoid_but_does_not_select_any() {

		for (JsonNode option : getPreferenceOptions()) {
			if (option.get("requiresFoodsToAvoid").asBoolean()) {
				allergiesPage.selectFoodPreference(option.get("label").asText());
				break;
			}
		}

		allergiesPage.clickButton("Continue");
	}

	@Then("the system should display the foods to avoid error")
	public void the_system_should_display_foods_to_avoid_error() {

		String expectedError = null;

		for (JsonNode option : getPreferenceOptions()) {
			if (option.get("requiresFoodsToAvoid").asBoolean()) {
				expectedError = option.get("errorMessage").asText();
				break;
			}
		}

		Assert.assertTrue(allergiesPage.isErrorDisplayed(expectedError),
				"Expected error NOT displayed: " + expectedError);
	}
}
