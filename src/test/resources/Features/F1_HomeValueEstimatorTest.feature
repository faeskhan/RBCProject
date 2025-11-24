Feature: As a user I want to verify that the RBC Home Value Estimator handles valid and invalid address inputs correctly

  Scenario: User enters a valid home address
    Given User launches the RBC Home Value Estimator page
    When User enters valid address in the address field
    And Selects the first suggested address
    And Clicks on the Get Started button
    Then User should be redirected to the What is Your Property Type page

  Scenario Outline: User enters an invalid home address
    Given User launches the RBC Home Value Estimator page
    When User enters invalid "<address>" in the address field
    And Selects the first suggested address
    And Clicks on the Get Started button
    Then The system should display an error message "Invalid address"

    Examples:
      | address |
      | Canada |
      | Pakistan |