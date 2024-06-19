Feature: Login Functionality

    Scenario: Successful Login with Valid Credentials
        Given user is on Login page
        When user logs in with valid credential
        Then user is navigated to the Dashboard page
