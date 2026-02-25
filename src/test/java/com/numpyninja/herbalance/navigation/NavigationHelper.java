package com.numpyninja.herbalance.navigation;

import com.fasterxml.jackson.databind.JsonNode;
import com.numpyninja.herbalance.context.TestContext;
import com.numpyninja.herbalance.pages.auth.AuthPage;
import com.numpyninja.herbalance.pages.home.HomePage;
import com.numpyninja.herbalance.pages.onboarding.CurrentWeightAndHeightPage;
import com.numpyninja.herbalance.pages.onboarding.DietaryPreferencesPage;
import com.numpyninja.herbalance.pages.onboarding.FileUploadPage;
import com.numpyninja.herbalance.pages.onboarding.FoodAllergiesPage;
import com.numpyninja.herbalance.pages.onboarding.HealthConditionsPage;
import com.numpyninja.herbalance.pages.onboarding.MedicationsAndSupplementsPage;
import com.numpyninja.herbalance.pages.onboarding.MenstrualCycleAwarenessPage;
import com.numpyninja.herbalance.pages.onboarding.MenstrualCyclePhaseCalculationPage;
import com.numpyninja.herbalance.pages.onboarding.OnboardingPage;
import com.numpyninja.herbalance.pages.onboarding.PersonalDetailsPage;
import com.numpyninja.herbalance.pages.onboarding.PhysicalActivityLevelPage;
import com.numpyninja.herbalance.testdata.TestDataManager;

public class NavigationHelper {

	private final TestContext testContext;

	public NavigationHelper(TestContext testContext) {
		this.testContext = testContext;
	}

	// ---------------------------------------------------------
	// HOME PAGE
	// ---------------------------------------------------------
	public HomePage goToHomePage() {
		String url = testContext.getConfigReader().getBaseUrl();
		testContext.getDriverManager().getDriver().get(url);

		HomePage homePage = new HomePage(testContext);

		if (!homePage.isHomePageDisplayed()) {
			throw new IllegalStateException("Home page is NOT displayed.");
		}

		return homePage;
	}

	// ---------------------------------------------------------
	// SIGNUP FORM
	// ---------------------------------------------------------
	public AuthPage goToSignupForm() {
		HomePage homePage = goToHomePage();
		homePage.clickCTA("Sign Up");

		AuthPage authPage = new AuthPage(testContext);
		authPage.clickTab("signup");

		if (!authPage.isFormDisplayed("signup")) {
			throw new IllegalStateException("Signup form is NOT displayed.");
		}

		return authPage;
	}

	// ---------------------------------------------------------
	// LOGIN FORM
	// ---------------------------------------------------------
	public AuthPage goToLoginForm() {
		HomePage homePage = goToHomePage();
		homePage.clickLoginButton();

		AuthPage authPage = new AuthPage(testContext);
		authPage.clickTab("login");

		if (!authPage.isFormDisplayed("login")) {
			throw new IllegalStateException("Login form is NOT displayed.");
		}

		return authPage;
	}

	// ---------------------------------------------------------
	// AUTH PAGE (generic)
	// ---------------------------------------------------------
	public AuthPage goToAuthPage() {
		HomePage homePage = goToHomePage();
		homePage.clickCTA("Sign Up");

		AuthPage authPage = new AuthPage(testContext);

		if (!authPage.isAuthPageDisplayed()) {
			throw new IllegalStateException("Auth page is NOT displayed.");
		}

		return authPage;
	}

	// ---------------------------------------------------------
	// ONBOARDING PAGE (full registration flow)
	// ---------------------------------------------------------
	public FileUploadPage goToFileUploadPage() {

		// 1. Navigate to signup form
		AuthPage authPage = goToSignupForm();

		// 2. Load valid registration data from JSON
		JsonNode reg = TestDataManager.get().get("registration").get("validRegistration");

		String email = reg.get("email").asText();
		String password = reg.get("password").asText();
		String confirmPassword = reg.get("confirmPassword").asText();
		boolean acceptTerms = reg.get("acceptTerms").asBoolean();

		// 3. Register
		authPage.register(email, password, confirmPassword, acceptTerms);

		// 4. Validate onboarding container is displayed
		OnboardingPage onboardingPage = new OnboardingPage(testContext);
		if (!onboardingPage.isOnboardingDisplayed()) {
			throw new IllegalStateException("Onboarding container is NOT displayed after registration.");
		}

		// 5. Validate that File Upload step is displayed
		if (!onboardingPage.isStepDisplayed("file-upload")) {
			throw new IllegalStateException("File Upload step is NOT displayed after registration.");
		}

		// 6. Return FileUploadPage object
		return new FileUploadPage(testContext);
	}

	public HealthConditionsPage goToHealthConditionsPage() {

		FileUploadPage fileUploadPage = goToFileUploadPage();

		fileUploadPage.clickButton("Continue");
		fileUploadPage.clickButton("Continue"); // TODO

		HealthConditionsPage healthConditionsPage = new HealthConditionsPage(testContext);

		if (!healthConditionsPage.isDisplayed()) {
			throw new IllegalStateException("Health Conditions page is NOT displayed.");
		}

		return healthConditionsPage;
	}

	public PersonalDetailsPage goToPersonalDetailsPage() {

		HealthConditionsPage healthPage = goToHealthConditionsPage();

		healthPage.clickButton("Continue");

		PersonalDetailsPage personalDetailsPage = new PersonalDetailsPage(testContext);

		if (!personalDetailsPage.isDisplayed()) {
			throw new IllegalStateException("Personal Details page is NOT displayed.");
		}

		return personalDetailsPage;
	}

	public MenstrualCycleAwarenessPage goToMenstrualCycleAwarenessPage() {

		PersonalDetailsPage detailsPage = goToPersonalDetailsPage();

		// MUST click Continue on Personal Details page
		detailsPage.clickButton("Continue");

		OnboardingPage onboardingPage = new OnboardingPage(testContext);

		if (!onboardingPage.isStepDisplayed("menstrual-cycle-awareness")) {
			throw new IllegalStateException("Menstrual Cycle Awareness step is NOT displayed.");
		}

		return new MenstrualCycleAwarenessPage(testContext);
	}

	public MenstrualCyclePhaseCalculationPage goToMenstrualCyclePhaseCalculationPage() {

		MenstrualCycleAwarenessPage awarenessPage = goToMenstrualCycleAwarenessPage();

		awarenessPage.clickButton("Continue");

		OnboardingPage onboardingPage = new OnboardingPage(testContext);

		if (!onboardingPage.isStepDisplayed("menstrual-cycle-phase-calculation")) {
			throw new IllegalStateException("Menstrual Cycle Phase Calculation step is NOT displayed.");
		}

		return new MenstrualCyclePhaseCalculationPage(testContext);
	}	

	public CurrentWeightAndHeightPage goToCurrentWeightAndHeightPage() {

		MenstrualCyclePhaseCalculationPage phasePage = goToMenstrualCyclePhaseCalculationPage();

		phasePage.clickButton("Continue");

		OnboardingPage onboardingPage = new OnboardingPage(testContext);

		if (!onboardingPage.isStepDisplayed("current-weight-and-height")) {
			throw new IllegalStateException("Current Weight & Height step is NOT displayed.");
		}

		return new CurrentWeightAndHeightPage(testContext);
	}

	public DietaryPreferencesPage goToDietaryPreferencesPage() {

		CurrentWeightAndHeightPage whPage = goToCurrentWeightAndHeightPage();

		whPage.clickButton("Continue");

		OnboardingPage onboardingPage = new OnboardingPage(testContext);

		if (!onboardingPage.isStepDisplayed("dietary-preferences")) {
			throw new IllegalStateException("Dietary Preferences step is NOT displayed.");
		}

		return new DietaryPreferencesPage(testContext);
	}

	public PhysicalActivityLevelPage goToPhysicalActivityLevelPage() {

		DietaryPreferencesPage dietaryPage = goToDietaryPreferencesPage();

		dietaryPage.clickButton("Continue");

		OnboardingPage onboardingPage = new OnboardingPage(testContext);

		if (!onboardingPage.isStepDisplayed("physical-activity-level")) {
			throw new IllegalStateException("Physical Activity Level step is NOT displayed.");
		}

		return new PhysicalActivityLevelPage(testContext);
	}
	
	public FoodAllergiesPage goToFoodAllergiesPage() {

	    PhysicalActivityLevelPage activityPage = goToPhysicalActivityLevelPage();

	    activityPage.clickButton("Continue");

	    OnboardingPage onboardingPage = new OnboardingPage(testContext);

	    if (!onboardingPage.isStepDisplayed("food-allergies")) {
	        throw new IllegalStateException("Food Allergies step is NOT displayed.");
	    }

	    return new FoodAllergiesPage(testContext);
	}

	
	public MedicationsAndSupplementsPage goToMedicationsAndSupplementsPage() {

	    FoodAllergiesPage allergiesPage = goToFoodAllergiesPage();

	    allergiesPage.clickButton("Continue");

	    OnboardingPage onboardingPage = new OnboardingPage(testContext);

	    if (!onboardingPage.isStepDisplayed("medications-and-supplements")) {
	        throw new IllegalStateException("Medications & Supplements step is NOT displayed.");
	    }

	    return new MedicationsAndSupplementsPage(testContext);
	}


}
