Feature: Verify functionality of RBC Mortgage Payment Calculator

  Scenario: User should be able to open the mortgage payment calculator
    Given User launches the RBC home page
    When User navigates to Mortgage Calculator page
    Then The calculator page should be displayed
    And The heading should contain "Mortgage Payment Calculator"

  Scenario: User should be able to calculate a mortgage payment estimate
    When User enters amount in the Mortgage Amount field
    And User clicks the Calculate button
    Then The results page should display the estimated monthly payment
    And The result should include the text "Payment will be:"