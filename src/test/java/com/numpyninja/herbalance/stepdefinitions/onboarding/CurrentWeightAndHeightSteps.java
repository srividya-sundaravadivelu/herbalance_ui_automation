package com.numpyninja.herbalance.stepdefinitions.onboarding;

import com.fasterxml.jackson.databind.JsonNode;
import com.numpyninja.herbalance.context.TestContext;
import com.numpyninja.herbalance.pages.onboarding.CurrentWeightAndHeightPage;
import com.numpyninja.herbalance.testdata.TestDataManager;
import io.cucumber.java.en.*;
import org.testng.Assert;

public class CurrentWeightAndHeightSteps {

    private CurrentWeightAndHeightPage whPage;

    public CurrentWeightAndHeightSteps(TestContext testContext) {
    	whPage = new CurrentWeightAndHeightPage(testContext);
    }   

    // -------------------------
    // Scenario Outline
    // -------------------------
    @When("the user enters weight and height for {string}")
    public void the_user_enters_weight_and_height_for(String testCase) {

        JsonNode bmiNode = TestDataManager.get()
                .get("onboarding")
                .get("bmiTests")
                .get(testCase);

        int weight = bmiNode.get("weight").asInt();
        String unit = bmiNode.get("unit").asText();
        int height = bmiNode.get("height").asInt();

        whPage.enterWeight(weight, unit);
        whPage.enterHeight(height);
        whPage.triggerBMICalculation();
    }

    @Then("the system should calculate and display the correct BMI for {string}")
    public void the_system_should_calculate_correct_bmi(String testCase) {

        JsonNode bmiTest = TestDataManager.get().get("onboarding").get("bmiTests").get(testCase);

        double expectedBmi = bmiTest.get("expectedBmi").asDouble();

        Assert.assertTrue(
            whPage.isBMIDisplayedCorrectly(expectedBmi),
            "BMI calculation is NOT correct. Expected: " + expectedBmi
        );
    }


    // -------------------------
    // Phase text validation
    // -------------------------
    @When("the user enters a valid weight and height")
    public void the_user_enters_valid_weight_and_height() {
        whPage.enterWeight(60, "kg");
        whPage.enterHeight(165);
        whPage.triggerBMICalculation();
    }

    @Then("the user should see the correct phase and related text")
    public void the_user_should_see_correct_phase_text() {
        Assert.assertTrue(whPage.isPhaseTextDisplayed(), "Phase text NOT displayed");
    }

    // -------------------------
    // BMI feedback
    // -------------------------
    @Then("the user should see the correct feedback message based on the calculated BMI")
    public void the_user_should_see_correct_feedback_message() {
        Assert.assertTrue(whPage.isBMIFeedbackDisplayed(), "BMI feedback NOT displayed");
    }

    // -------------------------
    // Navigation
    // -------------------------
    @When("the user clicks on Continue after entering valid weight and height")
    public void the_user_clicks_continue_after_valid_input() {
        whPage.clickButton("Continue");
    }

    @When("the user clicks on Continue without entering weight and height")
    public void the_user_clicks_continue_without_entering_values() {
        whPage.clickButton("Continue");
    }

    @Then("the user should see validation error for weight and height")
    public void the_user_should_see_validation_error() {
        Assert.assertTrue(whPage.isValidationErrorDisplayed(), "Validation error NOT displayed");
    }

    @When("the user clicks Back Button in current weight and height page")
    public void the_user_clicks_back_button() {
        whPage.clickButton("Back");
    }

   
}
