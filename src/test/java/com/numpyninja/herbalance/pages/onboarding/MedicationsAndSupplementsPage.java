package com.numpyninja.herbalance.pages.onboarding;

import com.numpyninja.herbalance.context.TestContext;
import com.numpyninja.herbalance.pages.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MedicationsAndSupplementsPage extends BasePage {

	@FindBy(css = "div.medications-container")
	private WebElement pageContainer;

	@FindBy(css = "div.error-message")
	private WebElement errorMessage;

	private final boolean stubMode;
	private final TestContext testContext;

	public MedicationsAndSupplementsPage(TestContext testContext) {
		super(testContext.getDriverManager().getDriver(), testContext.getConfigReader());
		this.testContext = testContext;
		this.stubMode = testContext.getConfigReader().isStubMode();
		PageFactory.initElements(driver, this);
	}

	public void goToMedicationSelectionScreen() {
		if (stubMode) {
			System.out.println("[STUB MODE] Navigating to medication selection screen");
			return;
		}

		By locator = By.cssSelector("button[data-testid='open-medication-selection']");
		WebElement button = safeFind(locator, "Open medication selection");
		safeClick(button, "Open medication selection");
	}

	public void selectMedication(String medicationName) {
		if (stubMode) {
			System.out.println("[STUB MODE] Selecting medication: " + medicationName);
			return;
		}

		By locator = By.xpath("//label[contains(.,'" + medicationName + "')]/preceding-sibling::input");
		WebElement option = safeFind(locator, medicationName + " option");
		safeClick(option, medicationName + " option");
	}

	public boolean isInfoMessageDisplayed(String expectedMessage) {
		if (stubMode)
			return true;

		By locator = By.xpath("//*[contains(.,'" + expectedMessage + "')]");
		WebElement msg = safeFind(locator, expectedMessage);
		return safeIsDisplayed(msg, expectedMessage);
	}

	public void addNewMedication() {
		if (stubMode) {
			System.out.println("[STUB MODE] Adding new medication");
			return;
		}
		// UI logic
	}

	public boolean isNewMedicationInfoDisplayed() {
		if (stubMode)
			return true;

		WebElement msg = safeFind(By.cssSelector("div.info-message"), "New medication info");
		return safeIsDisplayed(msg, "New medication info");
	}

	public void selectMoreThanThreeMedications() {
		if (stubMode) {
			System.out.println("[STUB MODE] Selecting more than 3 medications");
			return;
		}

		// Select first 4 checkboxes
		for (int i = 1; i <= 4; i++) {
			By locator = By.cssSelector("div.medication-item:nth-of-type(" + i + ") input");
			WebElement checkbox = safeFind(locator, "Medication item " + i);
			safeClick(checkbox, "Medication item " + i);
		}
	}

	public boolean isSummaryMessageDisplayed() {
		if (stubMode)
			return true;

		WebElement summary = safeFind(By.cssSelector("div.summary-message"), "Summary message");
		return safeIsDisplayed(summary, "Summary message");
	}

	public void clickButton(String buttonName) {
		if (stubMode) {
			System.out.println("[STUB MODE] Clicking: " + buttonName);
			return;
		}

		By locator = By.xpath("//button[contains(.,'" + buttonName + "')]");
		WebElement button = safeFind(locator, buttonName + " button");
		safeClick(button, buttonName + " button");
	}

	public boolean isPlansPageDisplayed() {
		if (stubMode)
			return true;

		return new OnboardingPage(testContext).isStepDisplayed("plans");
	}

	private boolean requiresDetailsStub = false;

	public void setStubRequiresDetails(boolean requiresDetails) {
		this.requiresDetailsStub = requiresDetails;
	}

	public boolean isErrorDisplayed(String expectedError) {
		if (stubMode)
			return true;

		WebElement error = safeFind(By.cssSelector("div.error-message"), "Error message");
		String actual = error.getText().trim();
		return actual.equalsIgnoreCase(expectedError.trim());
	}

	public boolean requiresDetails() {
		return this.requiresDetailsStub;
	}

	public void selectOption(String label) {
		if (stubMode) {
			System.out.println("[STUB MODE] Selecting option: " + label);
			return;
		}

		By locator = By.xpath("//label[contains(.,'" + label + "')]/preceding-sibling::input");
		WebElement option = safeFind(locator, label + " option");
		safeClick(option, label + " option");
	}

}
