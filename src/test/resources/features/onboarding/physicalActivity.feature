@physicalActivity
Feature: physical activity

  #Background:
    #Given the user is on the Physical Activity Level selection screen
    
  Scenario: Continue button is disabled when no physical activity level is selected
    When the user doesn’t select any physical activity
    Then the Continue button should be disabled on the physical activity level page  
    
  Scenario Outline: Display correct text based on physical activity
    When the user selects a physical activity level "<activity>"
    Then the correct message should be displayed for the selected activity level "<activity>"
    
    Examples: 
      | activity           | 
      | sedentary          |
      | lightlyActive      |
      | moderatelyActive   |
      | veryActive         |
      
  Scenario: 	Navigate to the next onboarding step after selecting physical activity level
    When the user clicks "Continue" on the physical activity level page
    Then the user should navigate to the "food-allergies" onboarding step
    
  Scenario: 	Navigate back to the previous onboarding step
    When the user clicks "Back" on the physical activity level page
    Then the user should navigate to the "dietary-preferences" onboarding step