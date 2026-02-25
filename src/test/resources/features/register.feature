@register
Feature: User Registration on HerBalance

  Background:
    Given the user is on the Sign up form

  Scenario Outline: Registration scenarios
  When the user registers with "<dataKey>" data
  Then the user should see the correct result for registration "<dataKey>"

	Examples:
	  | dataKey               |
	  | validRegistration     |
	  | invalidEmail          |
	  | missingPassword       |
	  | missingConfirmPassword|
	  | passwordMismatch      |
	  | termsNotAccepted      |
	  | emailAlreadyUsed      |

