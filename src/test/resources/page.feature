Feature: feature to test login functionality

  Scenario: check login is successful with valid credentials
    Given browser is opened
    And user is on login page
    When user enters valid credentials
    And clicks on login button
    Then user is navigated to the input mail page
