package com.numpyninja.herbalance.pages.onboarding;

import com.numpyninja.herbalance.context.TestContext;
import com.numpyninja.herbalance.pages.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PhysicalActivityLevelPage extends BasePage {

	@FindBy(css = "div.physical-activity-container")
	private WebElement pageContainer;

	@FindBy(css = "button.continue")
	private WebElement continueButton;

	private final boolean stubMode;
	private final TestContext testContext;

	public PhysicalActivityLevelPage(TestContext testContext) {
		super(testContext.getDriverManager().getDriver(), testContext.getConfigReader());
		this.testContext = testContext;
		this.stubMode = testContext.getConfigReader().isStubMode();
		PageFactory.initElements(driver, this);
	}

	public boolean isDisplayed() {
		if (stubMode)
			return true;
		return safeIsDisplayed(pageContainer, "Physical Activity Level Page");
	}

	public void selectActivity(String activityLabel) {

		if (stubMode) {
			System.out.println("[STUB MODE] Simulating selecting activity: " + activityLabel);
			return;
		}

		By locator = By.xpath("//label[contains(.,'" + activityLabel + "')]/preceding-sibling::input");
		WebElement option = safeFind(locator, activityLabel + " option");

		safeClick(option, activityLabel + " option");
	}
	
	public boolean isMessageDisplayed(String expectedMessage) {

		if (stubMode) {
			System.out.println("[STUB MODE] Simulating message display: " + expectedMessage);
			return true;
		}

		By locator = By.xpath("//*[contains(.,'" + expectedMessage + "')]");
		return safeFind(locator, expectedMessage) != null;
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
