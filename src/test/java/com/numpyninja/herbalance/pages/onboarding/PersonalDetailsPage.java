package com.numpyninja.herbalance.pages.onboarding;

import com.numpyninja.herbalance.context.TestContext;
import com.numpyninja.herbalance.pages.base.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PersonalDetailsPage extends BasePage {

	@FindBy(css = "div.onboarding-personal-details")
	private WebElement personalDetailsContainer;

	@FindBy(id = "first-name-input")
	private WebElement firstNameInput;

	@FindBy(id = "age-input")
	private WebElement ageInput;

	@FindBy(id = "blood-pressure-status")
	private WebElement bloodPressureDropdown;

	@FindBy(css = "div.error-first-name")
	private WebElement firstNameError;

	@FindBy(css = "div.error-age")
	private WebElement ageError;

	@FindBy(css = "div.error-blood-pressure")
	private WebElement bloodPressureError;

	private final boolean stubMode;
	public PersonalDetailsPage(TestContext testContext) {
		super(testContext.getDriverManager().getDriver(), testContext.getConfigReader());
		this.stubMode = testContext.getConfigReader().isStubMode();
		PageFactory.initElements(driver, this);
	}

	// -------------------------
	// PAGE DISPLAY
	// -------------------------
	public boolean isDisplayed() {
		if (stubMode)
			return true;
		return safeIsDisplayed(personalDetailsContainer, "Personal Details Page");
	}

	// -------------------------
	// FORM INPUTS
	// -------------------------
	public void enterFirstName(String firstName) {
		if (stubMode)
			return;
		safeSendKeys(firstNameInput, firstName, "First Name");
	}

	public void enterAge(String age) {
		if (stubMode)
			return;
		safeSendKeys(ageInput, age, "Age");
	}

	public void selectBloodPressureStatus(String status) {
		if (stubMode)
			return;
		safeSelectDropdownByVisibleText(bloodPressureDropdown, status, "Blood Pressure Status");
	}

	// -------------------------
	// BUTTONS
	// -------------------------
	public void clickContinue() {
		clickButton("Continue");
	}

	public void clickBack() {
		clickButton("Back");
	}

	public void clickButton(String name) {
		if (stubMode)
			return;
		safeClick(findButton(name), name + " button");
	}

	private WebElement findButton(String name) {
		return safeFind(org.openqa.selenium.By.xpath("//button[contains(text(),'" + name + "')]"), name + " button");
	}

	// -------------------------
	// VALIDATION
	// -------------------------
	public boolean isFirstNameErrorDisplayed() {
		if (stubMode)
			return true;
		return safeIsDisplayed(firstNameError, "First Name Error");
	}

	public boolean isAgeErrorDisplayed() {
		if (stubMode)
			return true;
		return safeIsDisplayed(ageError, "Age Error");
	}

	public boolean isBloodPressureErrorDisplayed() {
		if (stubMode)
			return true;
		return safeIsDisplayed(bloodPressureError, "Blood Pressure Error");
	}
}
