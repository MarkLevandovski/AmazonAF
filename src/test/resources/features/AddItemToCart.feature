@Cart
Feature: Add Item to Cart

  Scenario Outline: User adds product to the shopping cart
    Given User in on Main Page
    Then User search for productName = <productName>
    Then User selects itemNumber = <itemNumber> item from the list
    Then User selects quantity = <quantity> of product
    Then User adds item to the cart
    Then Verify that product is added to the cart
    Then Verify that subtotal price of products with quantity = <quantity> is correct

    Examples:
      | productName                     | itemNumber | quantity |
      | Best sellers in Digital Cameras | 5          | 2        |
