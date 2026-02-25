@menstrualCyclePhaseCalculation
Feature: Menstrual cycle phase calculation

  #Background:
    #Given the user is on the menstrual cycle phase calculation page

  Scenario Outline: Menstrual cycle calculations
    When the user enters a valid last period date and cycle length
    Then the system should display "<expected>" for menstrual cycle phase calculation

    Examples:
      | expected                           |
      | correct phase and day              |
      | next 3 expected period start dates |
      | current and next month calendar    |
      | calendar with phase indicators     |

  Scenario: Validation error is shown when last period date is missing
    When the user clicks "Continue" without selecting last period date
    Then the user should see validation error for last period date

  Scenario: Navigate to the next onboarding step after entering last period date
    When the user clicks "Continue" after entering a valid last period date and cycle length
    Then the user should navigate to the "current-weight-and-height" onboarding step

  Scenario: Navigate back to the previous onboarding step
    When the user clicks "Back" button on the menstrual cycle phase calculation step
    Then the user should navigate to the "menstrual-cycle-awareness" onboarding step
