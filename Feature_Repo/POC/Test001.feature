Feature: Validate Pet component REST APIs

  @tag1
  Scenario: Validate Pet Store Inventory
    Given URI is "http://petstore.swagger.io/v2/store/inventory"
    When Perform GET request
    Then Validate response "200"

  @tag2
  Scenario Outline: Place an order for a pet
    Given URI is "http://petstore.swagger.io/v2/store/order"
    Then Use sheet "Sample" and <Row> and <Column>
    When Perform POST request
    Then verify the <ResponseCode>

    Examples: 
      | Description  | Row | Column | ResponseCode |
      | Valid Json   |   0 |      0 |          200 |
      | Invalid Json |   0 |      2 |          400 |
