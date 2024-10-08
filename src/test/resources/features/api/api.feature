Feature: Trello API Testing

    Scenario: Get boards for an organization
        Given I have a valid Trello API key and token
        When I send a GET request to organizations endpoint
        Then the response status code should be 200
        And the response should contain a list of boards
        And the response should match the schema "schemas/getBoardsListSchema.json"

    Scenario: Create a board
        Given I have a valid Trello API key and token
        When I create a new board using API request
        Then the response status code should be 200

    Scenario: Update a board name
        Given I have a valid Trello API key and token
        When I create a new board using API request
        And I update the board name to "Updated Board Name"
        Then the response status code should be 200
        And the board name should be updated to "Updated Board Name"

    Scenario: Delete a board
        Given I have a valid Trello API key and token
        When I create a new board using API request
        And I delete the created board
        Then the response status code should be 200

    Scenario: Get a specific board
        Given I have a valid Trello API key and token
        When I create a new board using API request
        And I retrieve the created board
        Then the response status code should be 200
        And the response should contain the board details
        And the response should match the schema "schemas/getBoardSchema.json"

