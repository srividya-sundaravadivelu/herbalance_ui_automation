@dietaryPreferences
Feature: dietary preferences

  #Background:
    #Given the user is on the dietary preference selection screen
    
  Scenario: Continue button is enabled when a dietary preference is selected
    When the user selects a dietary preference
    Then the "Continue" button should be enabled on the dietary preferences page
    
  Scenario: Continue button is disabled when no dietary preference is selected
    When the user doesn’t select any dietary preference
    Then the Continue button should be disabled on the dietary preferences page
    
  Scenario: Navigate to the next onboarding step after selecting dietary preference
    When the user clicks on Continue after selecting dietary preference
    Then the user should navigate to the "physical-activity-level" onboarding step
    
  Scenario: Navigate back to the previous onboarding step
    When the user clicks "Back" Button on the dietary preferences page
    Then the user should navigate to the "current-weight-and-height" onboarding step