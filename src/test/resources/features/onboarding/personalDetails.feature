@personalDetails
Feature: Personal Details

Scenario: User fills out personal details and continues
  When the user enters valid personal details and clicks continue
  Then the user should navigate to the "menstrual-cycle-awareness" onboarding step

Scenario: User tries to continue without entering mandatory details
  When the user clicks on Continue without filling any fields in personal details page
  Then the user should see validation errors for first name, age, and blood pressure status

Scenario: Validation error is shown when only first name is filled
  When the user enters only first name and clicks Continue in personal details page
  Then the user should see validation errors for age, and blood pressure status

Scenario: Navigate back to previous onboarding step
  When the user clicks Back Button in personal details page
  Then the user should navigate to the "health-conditions" onboarding step
