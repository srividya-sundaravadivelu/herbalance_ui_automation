@currentWeightAndHeight
Feature: current weight and height

  #Background:
    #Given the user is in the form to enter current weight and height in onboarding page
    
  Scenario Outline: Calculate BMI using different weight units
    When the user enters weight and height for "<testCase>"
    Then the system should calculate and display the correct BMI for "<testCase>"
    
    Examples:
      | testCase      |
      | bmiMetric     |
      | bmiImperial   |
      
  Scenario: 	Display correct menstrual cycle phase
    When the user enters a valid weight and height
    Then the user should see the correct phase and related text
    
  Scenario: 	Display correct BMI feedback text
    When the user enters a valid weight and height
    Then the user should see the correct feedback message based on the calculated BMI
    
  Scenario: 	Navigate to the next onboarding step after entering weight and height
    When the user clicks on Continue after entering valid weight and height
    Then the user should navigate to the "dietary-preferences" onboarding step
    
  Scenario: 	Validation error is shown when weight or height is missing
    When the user clicks on Continue without entering weight and height
    Then the user should see validation error for weight and height
    
  Scenario: 	Navigate back to the previous onboarding step
    When the user clicks Back Button in current weight and height page
    Then the user should navigate to the "menstrual-cycle-phase-calculation" onboarding step