@home
Feature: Home Page

  Background:
    Given the user navigates to the "LOGIN" page
    And the user logs in with username "standard_user" and password "secret_sauce"
    And the user is on the "HOME" page
    
  @ui-validation
  Scenario: General elements on the home page appear
    Then the user should see the "products header" on the home page
    And the user should see the "hamburger button"