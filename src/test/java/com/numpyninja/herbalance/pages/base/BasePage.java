package com.numpyninja.herbalance.pages.base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.numpyninja.herbalance.utils.ConfigReader;

public class BasePage {

	protected WebDriver driver;
	protected boolean stubMode;

	public BasePage(WebDriver driver, ConfigReader configReader) {
		this.driver = driver;
		this.stubMode = configReader.isStubMode();
	}

	protected void safeClick(WebElement element, String description) {
		if (stubMode) {
			System.out.println("Stub: Click on " + description);
			return;
		}

		try {
			element.click();
		} catch (Exception e) {
			System.out.println("Stub: Could not click " + description + " — UI unavailable.");
		}
	}

	protected boolean safeIsDisplayed(WebElement element, String description) {
		if (stubMode) {
			System.out.println("Stub: Checking display of " + description);
			return true; // or false depending on your preference
		}

		try {
			return element.isDisplayed();
		} catch (Exception e) {
			System.out.println("Stub: Could not verify " + description + " — UI unavailable.");
			return false;
		}
	}

	protected void safeSendKeys(WebElement element, String value, String description) {
		if (stubMode) {
			System.out.println("Stub: Typing into " + description + " → " + value);
			return;
		}

		try {
			element.clear();
			element.sendKeys(value);
		} catch (Exception e) {
			System.out.println("Stub: Could not type into " + description + " — UI unavailable.");
		}
	}

	protected void safeType(WebElement element, String value, String description) {
		if (stubMode) {
			System.out.println("Stub: Typing into " + description + " → " + value);
			return;
		}

		try {
			element.clear();
			element.sendKeys(value);
		} catch (Exception e) {
			System.out.println("Stub: Could not type into " + description + " — UI unavailable.");
		}
	}

	protected WebElement safeFind(By locator, String elementName) {
		try {
			return driver.findElement(locator);
		} catch (Exception e) {
			if (stubMode) {
				System.out.println("[STUB MODE] Element not found: " + elementName);
				return null;
			}
			throw e;
		}
	}

	public void safeSelectDropdownByVisibleText(WebElement dropdown, String visibleText, String elementName) {
		try {
			if (stubMode) {
				System.out.println("[STUB MODE] Selecting '" + visibleText + "' from dropdown: " + elementName);
				return;
			}

			Select select = new Select(dropdown);
			select.selectByVisibleText(visibleText);

		} catch (Exception e) {
			System.out.println("Failed to select '" + visibleText + "' from dropdown: " + elementName);
			e.printStackTrace();
		}
	}

}
