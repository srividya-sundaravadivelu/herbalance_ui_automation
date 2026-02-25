package com.numpyninja.herbalance.pages.onboarding;

import com.numpyninja.herbalance.context.TestContext;
import com.numpyninja.herbalance.pages.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MenstrualCycleAwarenessPage extends BasePage {
	
	 @FindBy(css = "div.menstrual-awareness-container")
    private WebElement pageContainer;

    @FindBy(css = "button.continue")
    private WebElement continueButton;

    @FindBy(css = "button.back")
    private WebElement backButton;

    @FindBy(css = "div.guidance-message")
    private WebElement guidanceMessage;

    @FindBy(css = "input[name='cycle-awareness-option']")
    private WebElement anyOption;

    public MenstrualCycleAwarenessPage(TestContext testContext) {    	
        super(testContext.getDriverManager().getDriver(), testContext.getConfigReader());
        PageFactory.initElements(driver, this);
    }

    public boolean isDisplayed() {
        return safeIsDisplayed(pageContainer, "Menstrual Cycle Awareness Page");
    }

    public void selectAnyOption() {
        safeClick(anyOption, "Any Menstrual Awareness Option");
    }

    public boolean isGuidanceMessageDisplayed() {
        return safeIsDisplayed(guidanceMessage, "Guidance Message");
    }

    public boolean isContinueDisabled() {        
        return safeIsDisplayed(continueButton, "continue button");
    }

    public void clickButton(String buttonName) {
        By locator = By.xpath("//button[contains(text(),'" + buttonName + "')]");
        WebElement button = safeFind(locator, buttonName + " button");
        safeClick(button, buttonName + " button");
    }
}
