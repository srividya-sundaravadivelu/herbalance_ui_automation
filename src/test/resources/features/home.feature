@home
Feature: Navigate to Register and Login from Home page CTAs
  
  Background:
    Given the user is in the home page
    
  Scenario Outline: Navigate to Register tab from Home page CTAs
    When the user clicks the "<cta_button>" button on the home page
    Then the Registration screen should be displayed
    Examples:
      | cta_button                      |
      | Start your personalized journey |
      | Get Started Now                 |
      | Sign Up                         |
      
  Scenario: Navigate to Login tab from Home page
    When the user clicks the Login button
    Then the Login screen should be displayed