Feature: user Feature
  Scenario: A client gets the user
    Given the following user
    When the client request the user
    Then return the user