@all @home
Feature: Home Page

  Background:
    Given the user navigates to the "LOGIN" page
    And the user logs in with username "standard_user" and password "secret_sauce"
    And the user is on the "HOME" page

  @ui-validation
  Scenario: Global elements appear on the home page
    Then the user should see the "hamburger button"
    Then the user should see the "app logo"
    Then the user should see the "cart button"
    Then the user should see the "footer"
    Then the user should see the "social list"
    Then the user should see the "twitter button"
    Then the user should see the "facebook button"
    Then the user should see the "linkedIn button"
    Then the user should see the "footer info"

  @ui-validation
  Scenario: General elements appear on the home page
    Then the user should see the "products header" on the home page
    Then the user should see the "inventory list" on the home page
    Then the user should see the "product sort select" on the home page
    Then the user should see the "sort active option" on the home page