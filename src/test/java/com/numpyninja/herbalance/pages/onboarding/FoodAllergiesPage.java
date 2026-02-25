package com.numpyninja.herbalance.pages.onboarding;

import com.numpyninja.herbalance.context.TestContext;
import com.numpyninja.herbalance.pages.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FoodAllergiesPage extends BasePage {

	@FindBy(css = "div.food-allergies-container")
	private WebElement pageContainer;

	@FindBy(css = "div.error-message")
	private WebElement errorMessage;

	private final boolean stubMode;
	public FoodAllergiesPage(TestContext testContext) {
		super(testContext.getDriverManager().getDriver(), testContext.getConfigReader());
		this.stubMode = testContext.getConfigReader().isStubMode();
		PageFactory.initElements(driver, this);
	}

	public boolean isDisplayed() {
		if (stubMode)
			return true;
		return safeIsDisplayed(pageContainer, "Food Allergies Page");
	}

	public void selectFoodPreference(String label) {
		if (stubMode) {
			System.out.println("[STUB MODE] Selecting preference: " + label);
			return;
		}

		By locator = By.xpath("//label[contains(.,'" + label + "')]/preceding-sibling::input");
		WebElement option = safeFind(locator, label + " option");
		safeClick(option, label + " option");
	}

	private Boolean stubRequiresFoodsToAvoid = null;

	public void setStubRequiresFoodsToAvoid(boolean value) {
		this.stubRequiresFoodsToAvoid = value;
	}

	public boolean requiresFoodsToAvoid() {

		if (stubMode) {
			return stubRequiresFoodsToAvoid != null && stubRequiresFoodsToAvoid;
		}

		WebElement section = safeFind(By.cssSelector("div.foods-to-avoid-section"), "Foods to avoid section");
		return safeIsDisplayed(section, "Foods to avoid section");
	}

	public boolean isErrorDisplayed(String expectedMessage) {

		if (stubMode) {
			System.out.println("[STUB MODE] Simulating error message: " + expectedMessage);
			return true;
		}

		By locator = By.xpath("//*[contains(.,'" + expectedMessage + "')]");
		WebElement error = safeFind(locator, "Error message");

		return safeIsDisplayed(error, "Error message");
	}

	public void goToFoodsToAvoidScreen() {
		if (stubMode) {
			System.out.println("[STUB MODE] Navigating to foods to avoid screen");
			return;
		}
		// UI navigation logic
	}

	public void selectFoodToAvoid(String foodName) {
		if (stubMode) {
			System.out.println("[STUB MODE] Selecting food to avoid: " + foodName);
			return;
		}

		By locator = By.xpath("//label[contains(.,'" + foodName + "')]/preceding-sibling::input");
		WebElement option = safeFind(locator, foodName + " option");
		safeClick(option, foodName + " option");
	}

	public boolean isInfoMessageDisplayed(String expectedMessage) {
		if (stubMode)
			return true;

		By locator = By.xpath("//*[contains(.,'" + expectedMessage + "')]");
		return safeFind(locator, expectedMessage) != null;
	}

	public void selectMoreThanThreeFoods() {
		if (stubMode) {
			System.out.println("[STUB MODE] Selecting more than 3 foods");
			return;
		}
		// UI logic to select 4+ foods
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

}
