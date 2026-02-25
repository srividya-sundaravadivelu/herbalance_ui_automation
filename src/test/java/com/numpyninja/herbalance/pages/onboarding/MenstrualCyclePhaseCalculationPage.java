package com.numpyninja.herbalance.pages.onboarding;

import com.fasterxml.jackson.databind.JsonNode;
import com.numpyninja.herbalance.context.TestContext;
import com.numpyninja.herbalance.pages.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MenstrualCyclePhaseCalculationPage extends BasePage {

	@FindBy(css = "div.menstrual-phase-calculation-container")
	private WebElement pageContainer;

	@FindBy(css = "input[name='lastPeriodDate']")
	private WebElement lastPeriodDateInput;

	@FindBy(css = "input[name='cycleLength']")
	private WebElement cycleLengthInput;

	@FindBy(css = "div.last-period-error")
	private WebElement lastPeriodError;

	public MenstrualCyclePhaseCalculationPage(TestContext testContext) {
		super(testContext.getDriverManager().getDriver(), testContext.getConfigReader());
		PageFactory.initElements(driver, this);
	}

	public boolean isDisplayed() {
		return safeIsDisplayed(pageContainer, "Menstrual Cycle Phase Calculation Page");
	}

	public void enterLastPeriodDate(String date) {
		safeType(lastPeriodDateInput, date, "Last Period Date");
	}

	public void enterCycleLength(String length) {
		safeType(cycleLengthInput, length, "Cycle Length");
	}

	public boolean isLastPeriodDateErrorDisplayed() {
		return safeIsDisplayed(lastPeriodError, "Last Period Date Error");
	}

	public void clickButton(String buttonName) {
		By locator = By.xpath("//button[contains(.,'" + buttonName + "')]");
		WebElement button = safeFind(locator, buttonName + " button");
		safeClick(button, buttonName + " button");
	}

	public boolean isPhaseAndDayDisplayed(String phase, int day) {

		if (stubMode) {
			System.out.println("[STUB MODE] Simulating phase/day display: " + phase + " - Day " + day);
			return true;
		}
		String expected = phase + " - Day " + day;
		By locator = By.xpath("//*[contains(.,'" + expected + "')]");
		return safeFind(locator, expected) != null;
	}

	public boolean areNextPeriodsDisplayed(JsonNode nextPeriods) {

		if (stubMode) {
			System.out.println("[STUB MODE] Simulating next periods display");
			return true;
		}

		for (JsonNode date : nextPeriods) {
			By locator = By.xpath("//*[contains(.,'" + date.asText() + "')]");
			if (safeFind(locator, "Next Period Date") == null)
				return false;
		}
		return true;
	}

	public boolean areMonthsDisplayed(JsonNode months) {

		if (stubMode) {
			System.out.println("[STUB MODE] Simulating calendar months display");
			return true;
		}

		for (JsonNode month : months) {
			By locator = By.xpath("//*[contains(.,'" + month.asText() + "')]");
			if (safeFind(locator, "Calendar Month") == null)
				return false;
		}
		return true;
	}

	public boolean arePhaseIndicatorsDisplayed() {

		if (stubMode) {
			System.out.println("[STUB MODE] Simulating phase indicators display");
			return true;
		}

		By locator = By.cssSelector(".phase-indicator");
		return safeFind(locator, "Phase Indicator") != null;
	}

}
