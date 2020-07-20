Feature: Login Functionality

  @positive
  Scenario: Login as an Admin and verify Dashboard page
    Given user is on landing page
    When  user enters correct username "Admin" and password "admin123" credentials
    Then  user should be able to login and verify "Admin" on welcome


    @negative
  Scenario: Login with wrong credentials and verify error message
    Given user is on landing page
    When  user enters wrong credentials as "test" and "password"
    Then error message is displayed as "Invalid credentials"
