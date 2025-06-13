Feature: Comments API tests

  Scenario: Get comment by id=3 and verify name and email
    Given I send a GET request to "comments/3"
    Then the response status code should be 200
    And the response body's "name" should be "odio adipisci rerum aut animi"
    And the response body's "email" should be "Nikita@garfield.biz"

  Scenario: Post a new comment and verify the ID
    Given I send a POST request to "comments" with body:
      """
      {
        "postId": 1,
        "name": "Melissa",
        "email": "abc@gmail.com"
      }
      """
    Then the response status code should be 201
    And the response body's "id" should be greater than 0
