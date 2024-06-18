Feature: Login Functionality

    Scenario: Successful Login with Valid Credentials
        Given user is on Login page
        When user clicks Log in button
        Then user is navigated to the Home page
