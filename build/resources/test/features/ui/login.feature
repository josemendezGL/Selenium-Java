Feature: Login Functionality

    Scenario: Successful Login with Valid Credentials
        Given user is on Login page
        When user clicks Log in button
        And user enters valid credentials
        Then user is navigated to the Dashboard page
