package com.numpyninja.herbalance.stepdefinitions.onboarding;

import com.fasterxml.jackson.databind.JsonNode;
import com.numpyninja.herbalance.context.TestContext;
import com.numpyninja.herbalance.navigation.NavigationHelper;
import com.numpyninja.herbalance.pages.onboarding.MedicationsAndSupplementsPage;
import com.numpyninja.herbalance.testdata.TestDataManager;
import io.cucumber.java.en.*;
import org.testng.Assert;

public class MedicationsAndSupplementsSteps {

    private final MedicationsAndSupplementsPage medsPage;

    public MedicationsAndSupplementsSteps(TestContext testContext) {
        new NavigationHelper(testContext);
        this.medsPage = new MedicationsAndSupplementsPage(testContext);
    }

    // ------------------------------------------------------------
    // JSON HELPERS
    // ------------------------------------------------------------

    private JsonNode getMedicationOptions() {
        return TestDataManager.get()
                .get("onboarding")
                .get("medicationOptions");
    }

    private JsonNode getMedications() {
        return TestDataManager.get()
                .get("onboarding")
                .get("medications");
    }

    private JsonNode findOptionById(String id) {
        for (JsonNode option : getMedicationOptions()) {
            if (option.get("id").asText().equals(id)) {
                return option;
            }
        }
        throw new IllegalArgumentException("Invalid medication option key: " + id);
    }

    private JsonNode findMedicationByKey(String key) {
        JsonNode node = getMedications().get(key);
        if (node == null) {
            throw new IllegalArgumentException("Invalid medication key: " + key);
        }
        return node;
    }

    // ------------------------------------------------------------
    // SCENARIO 1: USER SELECTS NOTHING
    // ------------------------------------------------------------

    @When("the user doesn’t select any option in medications and supplements")
    public void the_user_doesnt_select_any_option() {
        // Intentionally empty
    }

    @Then("the system should display error message in medications and supplements page")
    public void the_system_should_display_error_message() {

        String expectedError = TestDataManager.get()
                .get("onboarding")
                .get("medicationsAndSupplements")
                .get("noOptionSelectedError")
                .asText();

        Assert.assertTrue(
                medsPage.isErrorDisplayed(expectedError),
                "Expected error NOT displayed: " + expectedError
        );
    }

    // ------------------------------------------------------------
    // SCENARIO 2: USER SELECTS AN OPTION
    // ------------------------------------------------------------

    @When("the user selects medication preference {string}")
    public void the_user_selects_medication_preference(String optionKey) {

        JsonNode option = findOptionById(optionKey);
        medsPage.selectOption(option.get("label").asText());
    }

    @Then("the system should behave based on medication preference {string}")
    public void the_system_should_behave_based_on_preference(String optionKey) {

        JsonNode option = findOptionById(optionKey);
        boolean requiresDetails = option.get("requiresDetails").asBoolean();

        medsPage.setStubRequiresDetails(requiresDetails);

        Assert.assertEquals(
                medsPage.requiresDetails(),
                requiresDetails,
                "System behavior mismatch for preference: " + optionKey
        );
    }

    // ------------------------------------------------------------
    // SCENARIO 3: OPTION REQUIRES MEDICATION DETAILS BUT USER ADDS NONE
    // ------------------------------------------------------------

    @When("the user selects an option that requires medication details but clicks Complete without adding any")
    public void the_user_selects_option_requiring_details_but_does_not_add_any() {

        for (JsonNode option : getMedicationOptions()) {
            if (option.get("requiresDetails").asBoolean()) {
                medsPage.selectOption(option.get("label").asText());
                break;
            }
        }

        medsPage.clickButton("Complete");
    }   
   


    @Then("the system should display medication details required error")
    public void the_system_should_display_medication_details_required_error() {

        String expectedError = TestDataManager.get()
                .get("onboarding")
                .get("medicationsAndSupplements")
                .get("noMedicationSelectedError")
                .asText();

        Assert.assertTrue(
                medsPage.isErrorDisplayed(expectedError),
                "Expected error NOT displayed: " + expectedError
        );
    }

    // ------------------------------------------------------------
    // MEDICATION SELECTION
    // ------------------------------------------------------------

    @Given("the user is on the select or add new medications or supplements screen")
    public void the_user_is_on_select_or_add_screen() {
        medsPage.goToMedicationSelectionScreen();
    }

    @When("the user selects {string}")
    public void the_user_selects_medication(String medicationKey) {

        JsonNode med = findMedicationByKey(medicationKey);
        medsPage.selectMedication(med.get("name").asText());
    }

    @Then("the correct informational message should be displayed for {string}")
    public void the_correct_info_message_should_be_displayed(String medicationKey) {

        JsonNode med = findMedicationByKey(medicationKey);
        String expectedMessage = med.get("message").asText();

        Assert.assertTrue(
                medsPage.isInfoMessageDisplayed(expectedMessage),
                "Incorrect informational message for: " + medicationKey
        );
    }

    // ------------------------------------------------------------
    // MULTIPLE MEDICATIONS
    // ------------------------------------------------------------

    @When("the user selects more than 3 medications or supplements")
    public void the_user_selects_more_than_three() {
        medsPage.selectMoreThanThreeMedications();
    }

    @Then("the system should display the first 3 relevant messages and a \"+<remainingCount> more\" indicator for medications")
    public void the_system_should_display_summary_message() {
        Assert.assertTrue(
                medsPage.isSummaryMessageDisplayed(),
                "Summary message NOT displayed"
        );
    }
    
    // ------------------------------------------------------------
    // ADD NEW MEDICATION
    // ------------------------------------------------------------
    
    
    @When("the user adds a new medication or supplement")
    public void the_user_adds_new_medication_or_supplement() {
        medsPage.addNewMedication();
    }
    
    @Then("the system should display the correct informational message for that medication or supplement")
    public void the_system_should_display_correct_info_for_new_medication() {
        Assert.assertTrue(
            medsPage.isNewMedicationInfoDisplayed(),
            "New medication info message NOT displayed"
        );
    }



    // ------------------------------------------------------------
    // NAVIGATION
    // ------------------------------------------------------------

    @When("the user clicks on Complete after selecting or adding new medication or supplement")
    public void the_user_clicks_complete_after_selection() {
        medsPage.clickButton("Complete");
    }

    @Then("the user should be navigated to plans page")
    public void the_user_should_be_navigated_to_plans_page() {
        Assert.assertTrue(
                medsPage.isPlansPageDisplayed(),
                "Plans page NOT displayed"
        );
    }

    @When("the user clicks \"Back\" Button in medications and supplements page")
    public void the_user_clicks_back_button() {
        medsPage.clickButton("Back");
    }
}
