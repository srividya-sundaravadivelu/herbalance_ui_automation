@continueWithoutReport
Feature: continue without report

  Scenario: onboarding without blood report
    #Given the user is on the file upload page after successful registration
    When the user clicks the "Continue without report" button on the file upload page
    Then the user should navigate to the "health-conditions" onboarding step