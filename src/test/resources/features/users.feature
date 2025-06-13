Feature: Users API test

  Scenario: Verify details of a specific user by ID
  Scenario: Get comment by id=3 and verify name and email
    Given I send a GET request to "users/6"
    Then the response status code should be 200
    And the response body's "address.street" should be "Norberto Crossing"
    And the response body's "address.city" should be "South Christy"