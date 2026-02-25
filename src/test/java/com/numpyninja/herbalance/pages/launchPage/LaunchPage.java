//package com.numpyninja.herbalance.pages.launchPage;
//
//import com.numpyninja.herbalance.context.TestContext;
//import com.numpyninja.herbalance.pages.base.BasePage;
//import org.openqa.selenium.support.PageFactory;
//
//public class LaunchPage extends BasePage {
//
//	private final TestContext testContext;
//
//	// ====== Constructor ======
//	public LaunchPage(TestContext testContext) {
//		super(testContext.getDriverManager().getDriver(), testContext.getConfigReader());
//		this.testContext = testContext;
//		PageFactory.initElements(driver, this);
//	}
//
//	// ====== Actions ======
//	public void launchApplication() {
//		String url = testContext.getConfigReader().getBaseUrl();
//		driver.get(url);
//	}
//}
