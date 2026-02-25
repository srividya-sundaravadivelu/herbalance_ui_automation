package com.numpyninja.herbalance.pages.onboarding;

import com.numpyninja.herbalance.context.TestContext;
import com.numpyninja.herbalance.pages.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.HashMap;
import java.util.Map;

public class OnboardingPage extends BasePage {

    private final boolean stubMode;
    // -----------------------------------------
    // CENTRALIZED STEP LOCATOR MAP
    // -----------------------------------------
    private static final Map<String, By> STEP_LOCATORS = new HashMap<>();

    static {
        STEP_LOCATORS.put("file-upload", By.cssSelector("div.file-upload-container"));
        STEP_LOCATORS.put("health-conditions", By.cssSelector("div.onboarding-health-conditions"));
        STEP_LOCATORS.put("personal-details", By.cssSelector("div.onboarding-personal-details"));
        STEP_LOCATORS.put("menstrual-cycle-awareness", By.cssSelector("div.onboarding-menstrual-cycle-awareness"));
        STEP_LOCATORS.put("menstrual-cycle-phase-calculation", By.cssSelector("div.onboarding-menstrual-cycle-phase-calculation"));
        STEP_LOCATORS.put("current-weight-and-height", By.cssSelector("div.onboarding-current-weight-and-height"));
        STEP_LOCATORS.put("dietary-preferences", By.cssSelector("div.onboarding-preferences"));
        STEP_LOCATORS.put("physical-activity-level", By.cssSelector("div.onboarding-physical-activity"));
        STEP_LOCATORS.put("food-allergies", By.cssSelector("div.onboarding-allergies"));   
        STEP_LOCATORS.put("medications-and-supplements", By.cssSelector("div.onboarding-medications-and-supplements"));
      
   
       
    }

    public OnboardingPage(TestContext testContext) {
        super(testContext.getDriverManager().getDriver(), testContext.getConfigReader());
        this.stubMode = testContext.getConfigReader().isStubMode();
        PageFactory.initElements(driver, this);
    }

    // -----------------------------------------
    // PAGE DISPLAY VALIDATION
    // -----------------------------------------
    public boolean isOnboardingDisplayed() {
        return isStepDisplayed("onboarding");
    }

    // -----------------------------------------
    // GENERIC STEP VALIDATION
    // -----------------------------------------
    public boolean isStepDisplayed(String stepKey) {

        if (stubMode) {
            System.out.println("[STUB MODE] Simulating step displayed: " + stepKey);
            return true;
        }

        stepKey = stepKey.toLowerCase();

        if (!STEP_LOCATORS.containsKey(stepKey)) {
            throw new IllegalArgumentException("Unknown onboarding step: " + stepKey);
        }

        By locator = STEP_LOCATORS.get(stepKey);
        WebElement stepContainer = safeFind(locator, stepKey + " step");

        return safeIsDisplayed(stepContainer, stepKey + " step");
    }

    // -----------------------------------------
    // GENERIC BUTTON CLICK
    // -----------------------------------------
    public void clickButton(String buttonName) {

        if (stubMode) {
            System.out.println("[STUB MODE] Clicking button: " + buttonName);
            return;
        }

        By locator = By.xpath("//button[contains(text(),'" + buttonName + "')]");
        WebElement button = safeFind(locator, buttonName + " button");
        safeClick(button, buttonName + " button");
    }
}
