Feature: Full Name Validation Functionality

  Background:
    Given User launches the banking application
    And User logs in using valid credentials
    And User navigates to the Profile page


  @NameValidation
  Scenario Outline: Verify invalid full name validations
    When User enters invalid full name "<fullName>"
    And User clicks on Save Profile button
    Then Proper validation message "<validationMessage>" should be displayed

    Examples:
      | fullName        | validationMessage                  |
      | AAAAAAAAAAAAAAAAAAAAAAAAAAAAAaAAAAAAAAAAAAAABABABABABABABABABABABABABAB  | Full name exceeds maximum length   |
      |                 | Full Name cannot be empty         |
      | 12345           | Full Name must contain only alphabets   |
      | @@@@@           | Full Name must contain only alphabets|
      | Ab              |full_name must be at least 3 characters long.|
      | A               |full_name must be at least 3 characters long.                    |