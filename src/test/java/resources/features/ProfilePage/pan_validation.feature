Feature: PAN Validation

  Background:
    Given User launches the banking application
    And User logs in using unverified KYC account
    And User navigates to the Profile page


  @PANValidation
  Scenario Outline: Verify valid PAN card format
    When User clicks Update KYC button
    And User clears PAN field
    And User enters PAN number "<PAN>"
    And User clicks Save KYC button
    Then User should see toast message "<ExpectedMessage>"

    Examples:
      | PAN        | ExpectedMessage           |
      | AAADE1234F | KYC updated successfully |


  @PANValidation
  Scenario Outline: Verify PAN card with less than 10 characters
    When User clicks Update KYC button
    And User clears PAN field
    And User enters PAN number "<PAN>"
    And User clicks Save KYC button
    Then User should see toast message "<ExpectedMessage>"

    Examples:
      | PAN      | ExpectedMessage                                 |
      | ABC1234F | Invalid PAN format (e.g., ABCDE1234F).  |


  @PANValidation
  Scenario Outline: Verify PAN card with more than 10 characters
    When User clicks Update KYC button
    And User clears PAN field
    And User enters PAN number "<PAN>"
    And User clicks Save KYC button
    Then User should see toast message "<ExpectedMessage>"

    Examples:
      | PAN          | ExpectedMessage                                 |
      | ABCDE1234FGH | Invalid PAN format (e.g., ABCDE1234F).  |


  @PANValidation
  Scenario Outline: Verify PAN card with special characters
    When User clicks Update KYC button
    And User clears PAN field
    And User enters PAN number "<PAN>"
    And User clicks Save KYC button
    Then User should see toast message "<ExpectedMessage>"

    Examples:
      | PAN        | ExpectedMessage                                                  |
      | ABC@#1234F | Invalid PAN format (e.g., ABCDE1234F).    |




  @PANValidation
  Scenario Outline: Verify PAN card with lowercase letters
    When User clicks Update KYC button
    And User clears PAN field
    And User enters PAN number "<PAN>"
    Then PAN number is automatically converted to uppercase "<PAN>"

    Examples:
      | PAN        |
      | abcde1234f |



  @PANValidation
  Scenario Outline: Verify PAN card with alphabets only
    When User clicks Update KYC button
    And User clears PAN field
    And User enters PAN number "<PAN>"
    And User clicks Save KYC button
    Then User should see toast message "<ExpectedMessage>"

    Examples:
      | PAN        | ExpectedMessage              |
      | ABCDEFGHIJ | Invalid PAN format (e.g., ABCDE1234F). |



  @PANValidation
  Scenario Outline: Verify PAN card with numeric values only
    When User clicks Update KYC button
    And User clears PAN field
    And User enters PAN number "<PAN>"
    And User clicks Save KYC button
    Then User should see toast message "<ExpectedMessage>"

    Examples:
      | PAN        | ExpectedMessage              |
      | 1234567890 | Invalid PAN format (e.g., ABCDE1234F). |



  @PANValidation
  Scenario Outline: Verify PAN card with invalid format
    When User clicks Update KYC button
    And User clears PAN field
    And User enters PAN number "<PAN>"
    And User clicks Save KYC button
    Then User should see toast message "<ExpectedMessage>"

    Examples:
      | PAN        | ExpectedMessage              |
      | AB12345CDE | Invalid PAN format (e.g., ABCDE1234F). |



  @PANValidation
  Scenario Outline: Verify null PAN card validation
    When User clicks Update KYC button
    And User clears PAN field
    And User clicks Save KYC button
    Then User should see toast message "<ExpectedMessage>"

    Examples:
      | ExpectedMessage                       |
      | PAN number cannot be empty or null.  |



  @PANValidation
  Scenario Outline: Verify duplicate PAN card validation
    When User clicks Update KYC button
    And User clears PAN field
    And User enters PAN number "<PAN>"
    And User clicks Save KYC button
    Then User should see toast message "<ExpectedMessage>"

    Examples:
      | PAN        | ExpectedMessage             |
      | ABCDE1234F | This PAN number is already registered to another account.  |