@auth
Feature: Auth page navigation

  Background: 
  	Given the user is on the auth page
  
  Scenario: Login form visibility
    Then the Login form should be displayed by default
    
  Scenario Outline: Switch between auth tabs
    When the user clicks on the "<tab>" tab
    Then the "<form>" form should be displayed
    
    Examples:
      | tab      | form     |
      | Login    | Login    |
      | SignUp   | SignUp   |