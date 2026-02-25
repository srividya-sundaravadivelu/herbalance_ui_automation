package com.numpyninja.herbalance.pages.onboarding;

import com.numpyninja.herbalance.context.TestContext;
import com.numpyninja.herbalance.pages.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HealthConditionsPage extends BasePage {

    @FindBy(css = "div.health-conditions-container")
    private WebElement pageContainer;

    @FindBy(xpath = "//button[contains(text(),'Continue')]")
    private WebElement continueButton;

    @FindBy(xpath = "//button[contains(text(),'Back')]")
    private WebElement backButton;

    // Example locators — adjust to your UI
    @FindBy(css = "input[name='menopauseStatus']")
    private WebElement menopauseStatusOption;

    @FindBy(css = "input[name='healthCondition']")
    private WebElement healthConditionOption;

    public HealthConditionsPage(TestContext testContext) {
        super(testContext.getDriverManager().getDriver(), testContext.getConfigReader());
        PageFactory.initElements(driver, this);
    }

    // -------------------------
    // PAGE VALIDATION
    // -------------------------
    public boolean isDisplayed() {
        return safeIsDisplayed(pageContainer, "Health Conditions Page");
    }

    // -------------------------
    // ACTIONS
    // -------------------------
    public void clickButton(String buttonName) {
        By locator = By.xpath("//button[contains(text(),'" + buttonName + "')]");
        WebElement button = safeFind(locator, buttonName + " button");
        safeClick(button, buttonName + " button");
    }

    public void selectMenopauseStatus() {
        safeClick(menopauseStatusOption, "Menopause Status Option");
    }

    public void selectOneOrMoreConditions() {
        safeClick(healthConditionOption, "Health Condition Option");
    }

    public void clickContinueWithoutSelection() {
        safeClick(continueButton, "Continue Button");
    }
}
