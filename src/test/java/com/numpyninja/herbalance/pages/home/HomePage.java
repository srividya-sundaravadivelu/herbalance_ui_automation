package com.numpyninja.herbalance.pages.home;

import com.numpyninja.herbalance.context.TestContext;
import com.numpyninja.herbalance.pages.base.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends BasePage {

	@FindBy(css = "div.home-container")
	private WebElement homeContainer;

	@FindBy(xpath = "//button[contains(text(),'Start your personalized journey')]")
	private WebElement startJourneyCTA;

	@FindBy(xpath = "//button[contains(text(),'Get Started Now')]")
	private WebElement getStartedCTA;

	@FindBy(xpath = "//button[contains(text(),'Sign Up')]")
	private WebElement signUpCTA;

	@FindBy(xpath = "//button[contains(text(),'Login')]")
	private WebElement loginButton;

	public HomePage(TestContext testContext) {
		super(testContext.getDriverManager().getDriver(), testContext.getConfigReader());
		PageFactory.initElements(driver, this);
	}

	public boolean isHomePageDisplayed() {
		return safeIsDisplayed(homeContainer, "Home Page Container");
	}

	public void clickCTA(String ctaName) {
		switch (ctaName.toLowerCase()) {
		case "start your personalized journey":
			safeClick(startJourneyCTA, "Start Journey CTA");
			break;

		case "get started now":
			safeClick(getStartedCTA, "Get Started Now CTA");
			break;

		case "sign up":
			safeClick(signUpCTA, "Sign Up CTA");
			break;

		default:
			System.out.println("Unknown CTA button: " + ctaName);
		}
	}

	public void clickLoginButton() {
		safeClick(loginButton, "Login Button");
	}
}
