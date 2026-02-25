package com.numpyninja.herbalance.stepdefinitions.onboarding;

import com.fasterxml.jackson.databind.JsonNode;
import com.numpyninja.herbalance.context.TestContext;
import com.numpyninja.herbalance.pages.onboarding.DietaryPreferencesPage;
import com.numpyninja.herbalance.testdata.TestDataManager;
import io.cucumber.java.en.*;
import org.testng.Assert;

public class DietaryPreferencesSteps {

    private DietaryPreferencesPage dietaryPage;

    public DietaryPreferencesSteps(TestContext testContext) {
    	dietaryPage = new DietaryPreferencesPage(testContext);
    }

    // -------------------------
    // ACTIONS
    // -------------------------
    @When("the user selects a dietary preference")
    public void the_user_selects_a_dietary_preference() {

        JsonNode data = TestDataManager.get().get("onboarding");
        String dietType = data.get("dietType").asText(); // e.g., "Vegetarian"

        dietaryPage.selectDietType(dietType);
    }

    @When("the user doesn’t select any dietary preference")
    public void the_user_doesnt_select_any_dietary_preference() {
        // Intentionally do nothing
    }

    @When("the user clicks on Continue after selecting dietary preference")
    public void the_user_clicks_on_continue_after_selecting_dietary_preference() {

        String dietType = TestDataManager.get().get("onboarding").get("dietType").asText();
        dietaryPage.selectDietType(dietType);

        dietaryPage.clickButton("Continue");
    }

    @When("the user clicks {string} Button on the dietary preferences page")
    public void the_user_clicks_button_on_the_dietary_preferences_page(String buttonName) {
        dietaryPage.clickButton(buttonName);
    }

    // -------------------------
    // VALIDATION
    // -------------------------
    @Then("the \"Continue\" button should be enabled on the dietary preferences page")
    public void the_continue_button_should_be_enabled() {
        Assert.assertTrue(dietaryPage.isContinueEnabled(),
                "Continue button is NOT enabled");
    }

    @Then("the Continue button should be disabled on the dietary preferences page")
    public void the_continue_button_should_be_disabled() {
        Assert.assertTrue(dietaryPage.isContinueDisabled(),
                "Continue button is NOT disabled");
    }

  
}
