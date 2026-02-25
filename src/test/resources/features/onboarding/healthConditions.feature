@healthConditions
Feature: health conditions

  #Background:
    #Given the user is on the health conditions page
    
  Scenario: Optional menopause status selection
    When the user clicks the "Continue" button on the health conditions page after selecting menopause status
    Then the user should navigate to the "personal-details" onboarding step
    
  Scenario:	Navigation to next step without selecting health conditions
    When the user proceeds without selecting any health condition
    Then the user should navigate to the "personal-details" onboarding step
    
  Scenario:	Navigation to next step after selecting 1 or more health conditions
    When the user clicks the "Continue" button on the health conditions page after selecting 1 or more health conditions
    Then the user should navigate to the "personal-details" onboarding step
    
  Scenario:	Navigate back to previous onboarding step
    When the user clicks the "Back" button on the health conditions page
    Then the user should navigate to the "file-upload" onboarding step
     