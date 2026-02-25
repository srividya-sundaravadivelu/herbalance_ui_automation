package com.numpyninja.herbalance.pages.dashboard;

import com.numpyninja.herbalance.context.TestContext;
import com.numpyninja.herbalance.pages.base.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DashboardPage extends BasePage {

	@FindBy(css = "div.dashboard-container")
	private WebElement dashboardContainer;

	public DashboardPage(TestContext testContext) {
		super(testContext.getDriverManager().getDriver(), testContext.getConfigReader());
		PageFactory.initElements(driver, this);
	}

	public boolean isDashboardDisplayed() {
		return safeIsDisplayed(dashboardContainer, "Dashboard Container");
	}
}
