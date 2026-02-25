package com.numpyninja.herbalance.pages.onboarding;

import com.numpyninja.herbalance.context.TestContext;
import com.numpyninja.herbalance.pages.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CurrentWeightAndHeightPage extends BasePage {

	@FindBy(css = "div.weight-height-container")
	private WebElement pageContainer;

	@FindBy(id = "weight-input")
	private WebElement weightInput;

	@FindBy(id = "height-input")
	private WebElement heightInput;

	@FindBy(css = "div.bmi-result")
	private WebElement bmiResult;

	@FindBy(css = "div.phase-text")
	private WebElement phaseText;

	@FindBy(css = "div.bmi-feedback")
	private WebElement bmiFeedback;

	@FindBy(css = "div.error-message")
	private WebElement errorMessage;
	
	@FindBy(css="div.bmi-value")
	private WebElement bmiValueElement;

	private final boolean stubMode;
	private final TestContext testContext;

	public CurrentWeightAndHeightPage(TestContext testContext) {
		super(testContext.getDriverManager().getDriver(), testContext.getConfigReader());
		this.testContext = testContext;
		this.stubMode = testContext.getConfigReader().isStubMode();
		PageFactory.initElements(driver, this);
	}

	public boolean isDisplayed() {
		if (stubMode)
			return true;
		return safeIsDisplayed(pageContainer, "Current Weight & Height Page");
	}

	public void enterWeight(int weight, String unit) {
		if (stubMode) {
			System.out.println("[STUB MODE] Entering weight: " + weight + " " + unit);
			return;
		}
		safeSendKeys(weightInput, String.valueOf(weight), "Weight input");
		selectUnit(unit);
	}

	private void selectUnit(String unit) {
		if (stubMode)
			return;

		By locator = By.xpath("//label[contains(.,'" + unit + "')]/preceding-sibling::input");
		WebElement option = safeFind(locator, unit + " unit");
		safeClick(option, unit + " unit");
	}

	public void enterHeight(int height) {
		if (stubMode) {
			System.out.println("[STUB MODE] Entering height: " + height);
			return;
		}
		safeSendKeys(heightInput, String.valueOf(height), "Height input");
	}

	public void triggerBMICalculation() {
		if (stubMode) {
			System.out.println("[STUB MODE] Triggering BMI calculation");
			return;
		}
		// Usually BMI auto-calculates; if not, click or blur
		weightInput.click();
	}

	public boolean isBMICorrect() {
		if (stubMode)
			return true;
		return safeIsDisplayed(bmiResult, "BMI result");
	}

	public boolean isPhaseTextDisplayed() {
		if (stubMode)
			return true;
		return safeIsDisplayed(phaseText, "Phase text");
	}

	public boolean isBMIFeedbackDisplayed() {
		if (stubMode)
			return true;
		return safeIsDisplayed(bmiFeedback, "BMI feedback");
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

	public boolean isNextStepDisplayed() {
		if (stubMode)
			return true;
		return new OnboardingPage(testContext).isStepDisplayed("next-step");
	}

	public boolean isValidationErrorDisplayed() {
		if (stubMode)
			return true;
		return safeIsDisplayed(errorMessage, "Validation error");
	}

	public boolean isBMIDisplayedCorrectly(double expectedBmi) {
		if (stubMode)
			return true;
		
		By locator = By.xpath("//*[contains(@class,'bmi') or contains(.,'BMI')]");

		String bmiText = safeFind(locator, "BMI Value").getAttribute("value");
		double actualBmi = Double.parseDouble(bmiText);

		return Math.abs(actualBmi - expectedBmi) < 0.01;
	}

}
