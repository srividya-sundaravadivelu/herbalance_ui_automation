package com.numpyninja.herbalance.hooks;

import com.numpyninja.herbalance.context.TestContext;
import com.numpyninja.herbalance.navigation.NavigationHelper;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class Hooks {

	private TestContext context;
	private final NavigationHelper navigationHelper;

	public Hooks(TestContext context) {
		this.context = context;
		this.navigationHelper = new NavigationHelper(context);
	}

	@After
	public void tearDown(Scenario scenario) {
		try {
			if (scenario.isFailed()) {
				byte[] screenshot = ((TakesScreenshot) context.getDriverManager().getDriver())
						.getScreenshotAs(OutputType.BYTES);

				Allure.getLifecycle().addAttachment("Failure Screenshot", "image/png", "png", screenshot);
			}
		} catch (Exception e) {
			System.out.println("Screenshot capture failed: " + e.getMessage());
		} finally {
			context.getDriverManager().quitDriver();
		}
	}

	@Before("@fileUpload or @continueWithoutReport")
	public void goToFileUpload() {
		navigationHelper.goToFileUploadPage();
	}

	@Before("@healthConditions")
	public void goToHealthConditions() {
		navigationHelper.goToHealthConditionsPage();
	}

	@Before("@personalDetails")
	public void goToPersonalDetails() {
		navigationHelper.goToPersonalDetailsPage();
	}

	@Before("@menstrualCycleAwareness")
	public void goToMenstrualCycleAwareness() {
		navigationHelper.goToMenstrualCycleAwarenessPage();
	}

	@Before("@menstrualCyclePhaseCalculation")
	public void goToMenstrualCyclePhaseCalculation() {
		navigationHelper.goToMenstrualCyclePhaseCalculationPage();
	}

	@Before("@currentWeightAndHeight")
	public void goToCurrentWeightAndHeight() {
		navigationHelper.goToCurrentWeightAndHeightPage();
	}

	@Before("@dietaryPreferences")
	public void goToDietaryPreferences() {
		navigationHelper.goToDietaryPreferencesPage();
	}

	@Before("@physicalActivity")
	public void goToPhysicalActivity() {
		navigationHelper.goToPhysicalActivityLevelPage();
	}

	@Before("@foodAllergies")
	public void goToFoodAllergies() {
		navigationHelper.goToFoodAllergiesPage();
	}

	@Before("@medicationsAndSupplements")
	public void goToMedicationsAndSupplements() {
		navigationHelper.goToMedicationsAndSupplementsPage();
	}

}
