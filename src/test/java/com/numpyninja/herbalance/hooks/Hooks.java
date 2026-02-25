package com.numpyninja.herbalance.hooks;

import com.numpyninja.herbalance.context.TestContext;
import com.numpyninja.herbalance.navigation.NavigationHelper;

import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hooks {

	private TestContext context;
	private final NavigationHelper navigationHelper;

	public Hooks(TestContext context) {
		this.context = context;
		this.navigationHelper = new NavigationHelper(context);
	}

	@After
	public void tearDown() {
		context.getDriverManager().quitDriver();
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
