@medicationsAndSupplements
Feature: Medications and Supplements

  # Scenario 1: No option selected
  Scenario: Error is shown when no option is selected
    When the user doesn’t select any option in medications and supplements
    Then the system should display error message in medications and supplements page

  # Scenario 2: System behavior based on selected option
  Scenario Outline: System behavior when a medication or supplement option is selected
    When the user selects medication preference "<optionKey>"
    Then the system should behave based on medication preference "<optionKey>"

    Examples:
      | optionKey     |
      | prescription  |
      | supplements   |
      | both          |
      | none          |

  # Scenario 3: Required details missing
  Scenario: Validation message is shown when the required medication details are missing
    When the user selects an option that requires medication details but clicks Complete without adding any
    Then the system should display medication details required error

  # Scenario 4: Informational messages for known medications
  Scenario Outline: Display informational message for selected medication or supplement
    Given the user is on the select or add new medications or supplements screen
    When the user selects "<medicationKey>"
    Then the correct informational message should be displayed for "<medicationKey>"

    Examples:
      | medicationKey     |
      | thyroidMedication |
      | vitaminD          |
      | iron              |

  # Scenario 5: Informational message for newly added medication
  Scenario: Display informational message when adding a new medication or supplement
    Given the user is on the select or add new medications or supplements screen
    When the user adds a new medication or supplement
    Then the system should display the correct informational message for that medication or supplement

  # Scenario 6: Summary message for more than 3 medications
  Scenario: Display summary message when more than three medications or supplements are selected
    Given the user is on the select or add new medications or supplements screen
    When the user selects more than 3 medications or supplements
    Then the system should display the first 3 relevant messages and a "+<remainingCount> more" indicator for medications

  # Scenario 7: Navigate to plans page after completing selection
  Scenario: Navigate to plans page after completing medication selection
    Given the user is on the select or add new medications or supplements screen
    When the user clicks on Complete after selecting or adding new medication or supplement
    Then the user should be navigated to plans page

  # Scenario 8: Navigate to plans page when option does not require details
  Scenario: Navigate to plans page when selected option does not require medication details
    When the user selects medication preference "none"
    And the user clicks on Complete after selecting or adding new medication or supplement
    Then the user should be navigated to plans page

  # Scenario 9: Back navigation
  Scenario: User navigates back to previous onboarding step
    When the user clicks "Back" Button in medications and supplements page
    Then the user should navigate to the "food-allergies" onboarding step
