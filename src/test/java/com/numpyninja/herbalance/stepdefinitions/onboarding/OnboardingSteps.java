package com.numpyninja.herbalance.stepdefinitions.onboarding;

import org.testng.Assert;

import com.numpyninja.herbalance.context.TestContext;
import com.numpyninja.herbalance.pages.onboarding.OnboardingPage;

import io.cucumber.java.en.Then;

public class OnboardingSteps {

	private OnboardingPage onboardingPage;

	public OnboardingSteps(TestContext testContext) {
        this.onboardingPage = new OnboardingPage(testContext);
	}

	@Then("the user should navigate to the {string} onboarding step")
	public void the_user_should_navigate_to_the_onboarding_step(String stepKey) {
		Assert.assertTrue(onboardingPage.isStepDisplayed(stepKey), "Onboarding step '" + stepKey + "' is NOT displayed");
	}
	
	


}
