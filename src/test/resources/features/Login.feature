@login
Feature: Login functionality

  Background:
    Given I am on the login page

  Scenario: Login Test with page object
    When I login with valid credentials
    Then I should see the successful login message
    And I should see the title "Products"

  Scenario: Should not be able to login with invalid credentials
    When I login with invalid credentials
    Then I should see the login fail message
    And I wait for 2 seconds