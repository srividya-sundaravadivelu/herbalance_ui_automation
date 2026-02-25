package com.numpyninja.herbalance.stepdefinitions.onboarding;

import com.fasterxml.jackson.databind.JsonNode;
import com.numpyninja.herbalance.context.TestContext;
import com.numpyninja.herbalance.pages.onboarding.MenstrualCyclePhaseCalculationPage;
import com.numpyninja.herbalance.testdata.TestDataManager;
import io.cucumber.java.en.*;
import org.testng.Assert;

public class MenstrualCyclePhaseCalculationSteps {

	private MenstrualCyclePhaseCalculationPage phasePage;

	public MenstrualCyclePhaseCalculationSteps(TestContext testContext) {
		phasePage = new MenstrualCyclePhaseCalculationPage(testContext);
	}

	@When("the user enters a valid last period date and cycle length")
	public void the_user_enters_valid_last_period_date_and_cycle_length() {

		JsonNode cycleInfo = TestDataManager.get().get("onboarding").get("menstrualCycleInfo");

		String lastPeriodDate = cycleInfo.get("lastPeriodDate").asText();
		int cycleLength = cycleInfo.get("cycleLengthDays").asInt();

		phasePage.enterLastPeriodDate(lastPeriodDate);
		phasePage.enterCycleLength(String.valueOf(cycleLength));
	}

	@Then("the system should display {string} for menstrual cycle phase calculation")
	public void the_system_should_display_expected_output(String expectedKey) {

		JsonNode cycleInfo = TestDataManager.get().get("onboarding").get("menstrualCycleInfo");

		switch (expectedKey) {

		case "correct phase and day":
			String expectedPhase = cycleInfo.get("calculatedPhase").asText();
			int currentDay = cycleInfo.get("currentCycleDay").asInt();
			Assert.assertTrue(phasePage.isPhaseAndDayDisplayed(expectedPhase, currentDay),
					"Phase and day NOT displayed correctly");
			break;

		case "next 3 expected period start dates":
			JsonNode nextPeriods = cycleInfo.get("nextExpectedPeriods");
			Assert.assertTrue(phasePage.areNextPeriodsDisplayed(nextPeriods), "Next expected periods NOT displayed");
			break;

		case "current and next month calendar":
			JsonNode months = cycleInfo.get("calendarDisplay").get("monthsShown");
			Assert.assertTrue(phasePage.areMonthsDisplayed(months), "Calendar months NOT displayed");
			break;

		case "calendar with phase indicators":
			Assert.assertTrue(phasePage.arePhaseIndicatorsDisplayed(), "Phase indicators NOT displayed");
			break;

		default:
			throw new IllegalArgumentException("Unknown expected key: " + expectedKey);
		}
	}

	@When("the user clicks {string} without selecting last period date")
	public void the_user_clicks_without_last_period_date(String buttonName) {
		phasePage.clickButton(buttonName);
	}

	@Then("the user should see validation error for last period date")
	public void the_user_should_see_validation_error_for_last_period_date() {
		Assert.assertTrue(phasePage.isLastPeriodDateErrorDisplayed(),
				"Validation error for last period date is NOT displayed");
	}

	@When("the user clicks {string} after entering a valid last period date and cycle length")
	public void the_user_clicks_after_entering_valid_data(String buttonName) {

		JsonNode cycleInfo = TestDataManager.get().get("onboarding").get("menstrualCycleInfo");

		phasePage.enterLastPeriodDate(cycleInfo.get("lastPeriodDate").asText());
		phasePage.enterCycleLength(String.valueOf(cycleInfo.get("cycleLengthDays").asInt()));

		phasePage.clickButton(buttonName);
	}

	@When("the user clicks {string} button on the menstrual cycle phase calculation step")
	public void the_user_clicks_button_on_the_menstrual_cycle_phase_calculation_step(String buttonName) {
		phasePage.clickButton(buttonName);
	}

}
