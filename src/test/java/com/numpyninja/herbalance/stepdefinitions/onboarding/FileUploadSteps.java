package com.numpyninja.herbalance.stepdefinitions.onboarding;

import com.fasterxml.jackson.databind.JsonNode;
import com.numpyninja.herbalance.context.TestContext;
import com.numpyninja.herbalance.pages.onboarding.FileUploadPage;
import com.numpyninja.herbalance.testdata.TestDataManager;

import io.cucumber.java.en.*;
import org.testng.Assert;

public class FileUploadSteps {

    private FileUploadPage fileUploadPage;

    public FileUploadSteps(TestContext testContext) {
    	fileUploadPage = new FileUploadPage(testContext);
    }

    @When("the user clicks the {string} button on the file upload page")
    public void the_user_clicks_the_button(String buttonName) {
    	fileUploadPage.clickButton(buttonName);
    }

    @Then("the user should see the window for uploading files")
    public void the_user_should_see_the_window_for_uploading_files() {
        Assert.assertTrue(fileUploadPage.isUploadWindowDisplayed(), "Upload window NOT displayed");
    }

    @When("the user uploads {string} file")
    public void the_user_uploads_file(String fileKey) {

        JsonNode file = TestDataManager.get()
        		.get("onboarding")
                .get("fileUpload")
                .get(fileKey);

        String fileName = file.get("fileName").asText();
        String fileType = file.get("fileType").asText();

        fileUploadPage.uploadFile(fileName, fileType);
    }

    @Then("the user should see the correct result for {string}")
    public void the_user_should_see_the_correct_result(String fileKey) {

        JsonNode fileNode = TestDataManager.get()
                .get("onboarding")
                .get("fileUpload")
                .get(fileKey);

        String expected = fileNode.get("expectedResult").asText();

        Assert.assertTrue(
        		fileUploadPage.isExpectedResultDisplayed(expected),
                "Expected result NOT displayed: " + expected
        );
    }


    @Given("a valid PDF file has been uploaded")
    public void a_valid_pdf_file_has_been_uploaded() {

        JsonNode file = TestDataManager.get()
        		.get("onboarding")
                .get("fileUpload")
                .get("validFile");

        fileUploadPage.uploadFile(
                file.get("fileName").asText(),
                file.get("fileType").asText()
        );

        Assert.assertTrue(fileUploadPage.isAnalyzeButtonDisplayed(),
                "Valid file upload did not show Analyze button");
    }

    @Then("the {string} button should be displayed")
    public void the_button_should_be_displayed(String buttonName) {
        Assert.assertTrue(fileUploadPage.isButtonDisplayed(buttonName),
                buttonName + " button is NOT displayed");
    }


    @Then("the file should be analyzed successfully")
    public void the_file_should_be_analyzed_successfully() {
        Assert.assertTrue(fileUploadPage.isAnalysisSuccessDisplayed(),
                "File analysis success message NOT displayed");
    }
}
