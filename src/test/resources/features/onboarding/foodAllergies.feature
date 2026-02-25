@foodAllergies
Feature: Food allergies

  #Background:
    #Given the user is on the food allergies page

  Scenario: Error is shown when no food allergy or intolerance option is selected 
	  When the user doesn’t select any option in food allergy or intolerance 
	  Then the system should display the no preference selected error

  Scenario Outline: System behavior when a food allergy or intolerance option is selected
    When the user selects a food preference "<preferenceKey>"
    Then the system should behave based on the selected food preference "<preferenceKey>"

    Examples:
      | preferenceKey          |
      | severe_allergy         |
      | intolerance_sensitivity|
      | no_restrictions        |
      | not_sure               |

  Scenario: Validation message is shown when required foods to avoid are not selected
    When the user selects a food preference option that requires selecting foods to avoid, but clicks continue without selecting the foods to avoid
    Then the system should display the foods to avoid error

  Scenario Outline: Display informational message for selected food to avoid
    Given the user is on the foods to avoid selection screen
    When the user selects food to avoid "<foodKey>"
    Then the system should display the correct informational message for that food "<foodKey>"

    Examples:
      | foodKey      |
      | Dairy        |
      | Gluten       |
      | Peanuts      |
      | Tree Nuts    |

  Scenario: Display summary message when more than three foods to avoid are selected
    Given the user is on the foods to avoid selection screen
    When the user selects more than 3 foods to avoid
    Then the system should display the first 3 relevant messages and a "+<remainingCount> more" indicator

  Scenario: Navigate to the next onboarding step after selecting food allergy information
    When the user clicks on "Continue" after selecting food allergy
    Then the user should navigate to the "medications-and-supplements" onboarding step

  Scenario: Navigate back to the previous onboarding step
    When the user clicks "Back" Button in food allergies page
    Then the user should navigate to the "physical-activity-level" onboarding step
    