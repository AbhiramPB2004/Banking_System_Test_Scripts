Feature: User Profile Occupation and Annual Income API Validation

  Background:
    Given User has valid session cookie


  @ProfileUpdateAPI
  Scenario Outline: Verify PUT /user/me with invalid occupation values
    When User sends PUT request with occupation "<Occupation>"
    Then Response status code should be 400
    And Response message should be "<ExpectedMessage>"

    Examples:
      | Occupation                                                                 | ExpectedMessage                                    |
      |                                                                            | occupation cannot be empty or null.                |
      | SDE223                                                                     | occupation must contain only alphabets and spaces. |
      | SDE@                                                                       | occupation must contain only alphabets and spaces. |
      | aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa| value too long for type character varying(100)     |


  @ProfileUpdateAPI
  Scenario Outline: Verify PUT /user/me with invalid annual income values
    When User sends PUT request with annual income "<AnnualIncome>"
    Then Response status code should be 400
    And Response message should be "<ExpectedMessage>"

    Examples:
      | AnnualIncome | ExpectedMessage                              |
      | -500000      | annual_income must be greater than 0.        |
      | 0            | annual_income must be greater than 0.        |
      | ABCDE        | annual_income must be a numeric value        |