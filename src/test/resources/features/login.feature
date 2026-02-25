@login
Feature:  Login using email and password
  Background:
    Given the user is on the Login form
    
  Scenario: Login with valid credentials
    When the user logs in with "valid" credentials
    Then the user should see the dashboard page
    
  Scenario: Login with invalid credentials
    When the user logs in with "invalid" credentials
    Then the user should see error message