Feature: Boards Functionality

    Scenario: Successful new board creation
        Given user is on Login page
        When user logs in with valid credential
        And user creates a new board
        Then new board is correctly displayed
        And

#Modify update
#delete
#retrieve -- create via API and retrieve via UI