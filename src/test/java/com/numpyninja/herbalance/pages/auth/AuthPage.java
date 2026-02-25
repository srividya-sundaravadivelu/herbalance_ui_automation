package com.numpyninja.herbalance.pages.auth;

import com.numpyninja.herbalance.context.TestContext;
import com.numpyninja.herbalance.pages.base.BasePage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AuthPage extends BasePage {

	// ============================
	// PAGE CONTAINER
	// ============================
	@FindBy(css = "div.auth-container")
	private WebElement authContainer;

	// ============================
	// TABS
	// ============================
	@FindBy(xpath = "//button[contains(text(),'Login')]")
	private WebElement loginTab;

	@FindBy(xpath = "//button[contains(text(),'Sign Up')]")
	private WebElement signupTab;

	// ============================
	// LOGIN FORM
	// ============================
	@FindBy(id = "login-email")
	private WebElement loginEmailInput;

	@FindBy(id = "login-password")
	private WebElement loginPasswordInput;

	@FindBy(id = "login-submit")
	private WebElement loginSubmitButton;

	@FindBy(css = "div.login-error")
	private WebElement loginErrorMessage;

	// ============================
	// SIGNUP FORM
	// ============================
	@FindBy(id = "signup-email")
	private WebElement signupEmailInput;

	@FindBy(id = "signup-password")
	private WebElement signupPasswordInput;

	@FindBy(id = "signup-confirm-password")
	private WebElement signupConfirmPasswordInput;

	@FindBy(id = "signup-terms")
	private WebElement signupTermsCheckbox;

	@FindBy(id = "signup-submit")
	private WebElement signupSubmitButton;

	// ============================
	// SIGNUP ERROR MESSAGES
	// ============================
	@FindBy(css = "div.error-email-format")
	private WebElement emailFormatError;

	@FindBy(css = "div.error-password-required")
	private WebElement passwordRequiredError;

	@FindBy(css = "div.error-confirm-password-required")
	private WebElement confirmPasswordRequiredError;

	@FindBy(css = "div.error-password-mismatch")
	private WebElement passwordMismatchError;

	@FindBy(css = "div.error-terms")
	private WebElement termsNotAcceptedError;

	@FindBy(css = "div.error-email-exists")
	private WebElement emailAlreadyUsedError;

	// ============================
	// CONSTRUCTOR
	// ============================
	public AuthPage(TestContext testContext) {
		super(testContext.getDriverManager().getDriver(), testContext.getConfigReader());
		PageFactory.initElements(driver, this);
	}

	// ============================
	// PAGE VALIDATION
	// ============================
	public boolean isAuthPageDisplayed() {
		return safeIsDisplayed(authContainer, "Auth Page Container");
	}

	public boolean isLoginFormDisplayed() {
		return isFormDisplayed("login");
	}

	public boolean isSignupFormDisplayed() {
		return isFormDisplayed("signup");
	}

	public boolean isFormDisplayed(String formName) {
		switch (formName.toLowerCase()) {
		case "login":
			return safeIsDisplayed(loginEmailInput, "Login Email Input");
		case "signup":
		case "sign up":
			return safeIsDisplayed(signupEmailInput, "Signup Email Input");
		default:
			System.out.println("Unknown form: " + formName);
			return false;
		}
	}

	// ============================
	// TAB SWITCHING
	// ============================
	public void clickTab(String tabName) {
		switch (tabName.toLowerCase()) {
		case "login":
			safeClick(loginTab, "Login Tab");
			break;
		case "signup":
		case "sign up":
			safeClick(signupTab, "Signup Tab");
			break;
		default:
			System.out.println("Unknown tab: " + tabName);
		}
	}

	// ============================
	// LOGIN ACTION
	// ============================
	public void login(String email, String password) {
		safeType(loginEmailInput, email, "Login Email");
		safeType(loginPasswordInput, password, "Login Password");
		safeClick(loginSubmitButton, "Login Submit Button");
	}

	public boolean isLoginErrorDisplayed() {
		return safeIsDisplayed(loginErrorMessage, "Login Error Message");
	}

	// ============================
	// REGISTRATION ACTION
	// ============================
	public void register(String email, String password, String confirmPassword, boolean acceptTerms) {
		safeType(signupEmailInput, email, "Signup Email");
		safeType(signupPasswordInput, password, "Signup Password");
		safeType(signupConfirmPasswordInput, confirmPassword, "Signup Confirm Password");

		if (acceptTerms) {
			safeClick(signupTermsCheckbox, "Terms Checkbox");
		}

		safeClick(signupSubmitButton, "Signup Submit Button");
	}

	// ============================
	// REGISTRATION ERROR CHECKS
	// ============================


	public boolean isExpectedResultDisplayed(String expected) {
		if (stubMode)
			return true;

		By locator = By.xpath("//*[contains(.,'" + expected + "')]");
		WebElement msg = safeFind(locator, expected);
		return safeIsDisplayed(msg, expected);
	}

}
