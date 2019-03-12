Feature: TotalBalanceTable
Scenario: Open assessment page and verify values
Given I have open the browser
When I open the Mass Mutual Assessment URL
Then Verify the right count of values appear on the screen
  And Verify the values are formatted as currencies
  And Verify the values on the screen are greater than 0
  And Verify the total balance is correct based on the values listed on the screen
  And Verify the total balance matches the sum of the values