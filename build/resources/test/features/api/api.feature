Feature: API Testing

    Scenario: Successful GET request
        Given an API endpoint "https://jsonplaceholder.typicode.com"
        When a GET request is made to "/posts/1"
        Then the response status code should be 200
        And the response should contain "userId"
