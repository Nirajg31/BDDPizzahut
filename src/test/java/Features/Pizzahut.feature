@Pizzahut
Feature: Place an Order

  Scenario: Successfully placing an order for a pizza
    Given I have launched the application
    When I enter the location as "Pune"
    And I select the very first suggestion from the list
    Then I should land on the Deals page
    And I select the tab as "Pizzas"
    And I add "Schezwan Margherita" to the basket
    And I note down the price displayed on the screen
    Then I should see the pizza "Personal Schezwan Margherita" is added to the cart
    And the price is also displayed correctly
    And I click on the Checkout button
    Then I should be landed on the secured checkout page
    And I enters the personal details into below fields
      |Name|Rajesh Sharma |
      |Mobile|9082695625|
      |Email| abc@xyz.com|
    And I enter the address details
      | 123 Main Street |
      | Some Landmark   |
