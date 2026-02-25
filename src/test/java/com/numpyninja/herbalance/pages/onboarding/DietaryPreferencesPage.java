package com.numpyninja.herbalance.pages.onboarding;

import com.numpyninja.herbalance.context.TestContext;
import com.numpyninja.herbalance.pages.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DietaryPreferencesPage extends BasePage {

	@FindBy(css = "div.dietary-preferences-container")
	private WebElement pageContainer;

	@FindBy(css = "button.continue")
	private WebElement continueButton;

	private final boolean stubMode;
	private final TestContext testContext;

	public DietaryPreferencesPage(TestContext testContext) {
		super(testContext.getDriverManager().getDriver(), testContext.getConfigReader());
		this.testContext = testContext;
		this.stubMode = testContext.getConfigReader().isStubMode();
		PageFactory.initElements(driver, this);
	}

	public boolean isDisplayed() {
		if (stubMode)
			return true;
		return safeIsDisplayed(pageContainer, "Dietary Preferences Page");
	}

	// Select based on JSON value (e.g., "Vegetarian")
	public void selectDietType(String dietType) {

		if (stubMode) {
			System.out.println("[STUB MODE] Simulating selecting diet type: " + dietType);
			return;
		}

		// Match label text → click its input
		By locator = By.xpath("//label[contains(.,'" + dietType + "')]/preceding-sibling::input");
		WebElement option = safeFind(locator, dietType + " option");

		safeClick(option, dietType + " option");
	}

	public boolean isContinueEnabled() {
		if (stubMode)
			return true;
		return continueButton.isEnabled();
	}

	public boolean isContinueDisabled() {
		if (stubMode)
			return true;
		return !continueButton.isEnabled();
	}

	public void clickButton(String buttonName) {

		if (stubMode) {
			System.out.println("[STUB MODE] Simulating click on: " + buttonName);
			return;
		}

		By locator = By.xpath("//button[contains(.,'" + buttonName + "')]");
		WebElement button = safeFind(locator, buttonName + " button");
		safeClick(button, buttonName + " button");
	}

	public boolean isStepDisplayed(String stepKey) {

		if (stubMode) {
			System.out.println("[STUB MODE] Simulating step: " + stepKey);
			return true;
		}

		return new OnboardingPage(testContext).isStepDisplayed(stepKey);
	}
}
