Feature: As a user I want to verify the help and support options on the RBC Contact Us website

  Scenario: User should be able to access the Contact Us page
    Given User navigates to the RBC home page
    When User navigates to the Contact Us page in the footer
    Then The page should contain the text "Let us help you find the answers you're looking for..."

  Scenario Outline: User should be able to search for 'credit card' in the help section
    When User enters '<question>' in the search box
    And Clicks the search button
    Then User should see search results related to '<question>'

    Examples:
      | question |
      | credit card |