Feature: Transaction PIN Reset API Validation

  Background:
    Given User has valid session cookie


  @TransactionPINAPI
  Scenario: Verify successful transaction PIN reset
    When User sends POST request to "/user/reset-transaction-pin" with valid PIN details
    Then Response status code should be 200
    And Response message should be "Transaction PIN reset successfully."


  @TransactionPINAPI
  Scenario: Verify transaction PIN reset with incorrect password
    When User sends POST request with invalid account password
    Then Response status code should be 400
    And Response message should be "Incorrect account password."


  @TransactionPINAPI
  Scenario: Verify transaction PIN reset with weak PIN
    When User sends POST request with weak transaction PIN
    Then Response status code should be 400
    And Response message should be "PIN is too weak. Please choose a more secure PIN."


  @TransactionPINAPI
  Scenario Outline: Verify invalid transaction PIN validations
    When User sends POST request with transaction PIN "<PIN>"
    Then Response status code should be 400
    And Response message should be "<ExpectedMessage>"

    Examples:
      | PIN  | ExpectedMessage                              |
      | 12   | Transaction PIN must be numeric and contain 4 to 6 digits only.             |
      | ABCD | Transaction PIN must be numeric and contain 4 to 6 digits only.        |