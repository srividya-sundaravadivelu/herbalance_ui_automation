package com.numpyninja.herbalance.pages.onboarding;

import com.numpyninja.herbalance.context.TestContext;
import com.numpyninja.herbalance.pages.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.nio.file.Paths;

public class FileUploadPage extends BasePage {

    @FindBy(css = "div.onboarding-file-upload")
    private WebElement fileUploadContainer;

    @FindBy(css = "div.upload-window")
    private WebElement uploadWindow;

    @FindBy(css = "input[type='file']")
    private WebElement fileInput;

    @FindBy(css = "div.error-invalid-type")
    private WebElement invalidTypeError;

    @FindBy(css = "div.error-file-size")
    private WebElement fileSizeLimitError;

    @FindBy(xpath = "//button[contains(text(),'Analyze Report')]")
    private WebElement analyzeReportButton;

    @FindBy(xpath = "//button[contains(text(),'Upload PDF')]")
    private WebElement uploadPdfButton;

    @FindBy(xpath = "//button[contains(text(),'Change file')]")
    private WebElement changeFileButton;

    @FindBy(css = "div.analysis-success")
    private WebElement analysisSuccessMessage;

    private final boolean stubMode;
    public FileUploadPage(TestContext testContext) {
        super(testContext.getDriverManager().getDriver(), testContext.getConfigReader());
        this.stubMode = testContext.getConfigReader().isStubMode();
        PageFactory.initElements(driver, this);
    }

    // ---------------------------------------------------------
    // PAGE DISPLAY
    // ---------------------------------------------------------
    public boolean isDisplayed() {
        if (stubMode) return true;
        return safeIsDisplayed(fileUploadContainer, "File Upload Page");
    }

    public boolean isUploadWindowDisplayed() {
        if (stubMode) return true;
        return safeIsDisplayed(uploadWindow, "Upload Window");
    }

    // ---------------------------------------------------------
    // FILE UPLOAD
    // ---------------------------------------------------------
    public void uploadFile(String fileName, String fileType) {

        if (stubMode) {
            System.out.println("[STUB MODE] Uploading file: " + fileName + " (" + fileType + ")");
            return;
        }

        String filePath = getFilePath(fileName);

        // Correct way to upload files
        fileInput.sendKeys(filePath);
    }

    private String getFilePath(String fileName) {
        return Paths.get("src", "test", "resources", "files", fileName)
                .toFile()
                .getAbsolutePath();
    }

    // ---------------------------------------------------------
    // BUTTONS
    // ---------------------------------------------------------
    public void clickButton(String buttonName) {

        if (stubMode) {
            System.out.println("[STUB MODE] Clicking button: " + buttonName);
            return;
        }

        By locator = By.xpath("//button[contains(text(),'" + buttonName + "')]");
        WebElement button = safeFind(locator, buttonName + " button");
        safeClick(button, buttonName + " button");
    }

    public boolean isButtonDisplayed(String buttonName) {

        if (stubMode) return true;

        By locator = By.xpath("//button[contains(text(),'" + buttonName + "')]");
        WebElement button = safeFind(locator, buttonName + " button");
        return safeIsDisplayed(button, buttonName + " button");
    }

    public boolean isAnalyzeButtonDisplayed() {
        if (stubMode) return true;
        return safeIsDisplayed(analyzeReportButton, "Analyze Report Button");
    }

    // ---------------------------------------------------------
    // EXPECTED RESULT VALIDATION (JSON-driven)
    // ---------------------------------------------------------
    public boolean isExpectedResultDisplayed(String expected) {

        if (stubMode) {
            System.out.println("[STUB MODE] Simulating expected result: " + expected);
            return true;
        }

        By locator = By.xpath("//*[contains(.,'" + expected + "')]");
        WebElement msg = safeFind(locator, expected);

        return safeIsDisplayed(msg, expected);
    }

    // ---------------------------------------------------------
    // SUCCESS MESSAGE
    // ---------------------------------------------------------
    public boolean isAnalysisSuccessDisplayed() {
        if (stubMode) return true;
        return safeIsDisplayed(analysisSuccessMessage, "Analysis Success Message");
    }
}
