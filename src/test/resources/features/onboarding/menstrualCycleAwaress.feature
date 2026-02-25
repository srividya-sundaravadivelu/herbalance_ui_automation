@menstrualCycleAwareness
Feature: menstrual cycle awareness

  #Background:
    #Given the user is on the menstrual cycle awareness page
    
  Scenario: System behavior when user selects different options for menstrual cycle awareness
    When the user selects an option in menstrual cycle awareness
    Then the system should display the correct guidance message for the selected option
    
  Scenario: Continue button is disabled when no option is selected for menstrual cycle awareness
    When the user doesn’t select any option and tries to click "Continue"
    Then the Continue button should be disabled
    
  Scenario: Navigate back to previous onboarding step    
    When the user clicks the "Back" button on the menstrual cycle awareness page
    Then the user should navigate to the "health-conditions" onboarding step
    
  Scenario: Navigate to the next onboarding step after selecting an option in menstrual cycle awareness
    When the user clicks "Continue" after selecting an option in menstrual cycle awareness
    Then the user should navigate to the "menstrual-cycle-phase-calculation" onboarding step