package com.numpyninja.herbalance.stepdefinitions.onboarding;

import com.numpyninja.herbalance.context.TestContext;
import com.numpyninja.herbalance.pages.onboarding.HealthConditionsPage;
import io.cucumber.java.en.*;

public class HealthConditionsSteps {

    private HealthConditionsPage healthConditionsPage;
    public HealthConditionsSteps(TestContext testContext) {
    	healthConditionsPage = new HealthConditionsPage(testContext);
    }
    
    @When("the user clicks the {string} button on the health conditions page after selecting menopause status")
    public void the_user_clicks_continue_after_selecting_menopause(String buttonName) {
        healthConditionsPage.selectMenopauseStatus();
        healthConditionsPage.clickButton(buttonName);
    }

    @When("the user proceeds without selecting any health condition")
    public void the_user_proceeds_without_selecting_any_health_condition() {
        healthConditionsPage.clickContinueWithoutSelection();
    }

    @When("the user clicks the {string} button on the health conditions page after selecting 1 or more health conditions")
    public void the_user_clicks_continue_after_selecting_conditions(String buttonName) {
        healthConditionsPage.selectOneOrMoreConditions();
        healthConditionsPage.clickButton(buttonName);
    }

    @When("the user clicks the {string} button on the health conditions page")
    public void the_user_clicks_button_on_health_conditions_page(String buttonName) {
        healthConditionsPage.clickButton(buttonName);
    }


}
