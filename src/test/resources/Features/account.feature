Feature: Account functionality
  Background:
    Given user should be on login page
    When user enters valid credentials and click on login button
    Then user should be navigated to home page

  @Createaccount
  Scenario: Create_Lead_with_Mandatory_Fields_TC04
    When user click on new account
    And fill all mandatory fields and click on save
    Then account should be created successfully