Feature: KYC API Validation

  Background:
    Given User has valid unverified KYC session cookie



  @KYCAPI
  Scenario Outline: Verify successful KYC update using PATCH /user/kyc
    When User sends PATCH request with Aadhaar "<Aadhaar>" and PAN "<PAN>"
    Then Response status code should be 200
    And Response message should be "<ExpectedMessage>"

    Examples:
      | Aadhaar     | PAN        | ExpectedMessage           |
      | 666635466677 | ABADE1234A | KYC updated successfully. |



  @KYCAPI
  Scenario Outline: Verify PATCH /user/kyc with Aadhaar less than 12 digits
    When User sends PATCH request with Aadhaar "<Aadhaar>" and PAN "<PAN>"
    Then Response status code should be 400
    And Response message should be "<ExpectedMessage>"

    Examples:
      | Aadhaar    | PAN        | ExpectedMessage                                   |
      | 1234567890 | ABCDE1234F | Aadhaar number must be exactly 12 numeric digits. |



  @KYCAPI
  Scenario Outline: Verify PATCH /user/kyc with Aadhaar greater than 12 digits
    When User sends PATCH request with Aadhaar "<Aadhaar>" and PAN "<PAN>"
    Then Response status code should be 400
    And Response message should be "<ExpectedMessage>"

    Examples:
      | Aadhaar       | PAN        | ExpectedMessage                                   |
      | 12345678901234 | ABCDE1234F | Aadhaar number must be exactly 12 numeric digits. |



  @KYCAPI
  Scenario Outline: Verify PATCH /user/kyc with alphabetic Aadhaar value
    When User sends PATCH request with Aadhaar "<Aadhaar>" and PAN "<PAN>"
    Then Response status code should be 400
    And Response message should be "<ExpectedMessage>"

    Examples:
      | Aadhaar     | PAN        | ExpectedMessage                                   |
      | ABCDEFGHIJKL | ABCDE1234F | Aadhaar number must be exactly 12 numeric digits. |



  @KYCAPI
  Scenario Outline: Verify PATCH /user/kyc with special character Aadhaar value
    When User sends PATCH request with Aadhaar "<Aadhaar>" and PAN "<PAN>"
    Then Response status code should be 400
    And Response message should be "<ExpectedMessage>"

    Examples:
      | Aadhaar      | PAN        | ExpectedMessage                                   |
      | 1234@567#901 | ABCDE1234F | Aadhaar number must be exactly 12 numeric digits. |



  @KYCAPI @adhaarNull
  Scenario Outline: Verify PATCH /user/kyc with null Aadhaar value
    When User sends PATCH request with Aadhaar "<Aadhaar>" and PAN "<PAN>"
    Then Response status code should be 400
    And Response message should be "<ExpectedMessage>"

    Examples:
      | Aadhaar | PAN        | ExpectedMessage                                   |
      |          | AAADE1234F | Aadhaar number must be exactly 12 numeric digits. |



  @KYCAPI
  Scenario Outline: Verify duplicate Aadhaar validation
    When User sends PATCH request with Aadhaar "<Aadhaar>" and PAN "<PAN>"
    Then Response status code should be 400
    And Response message should be "<ExpectedMessage>"

    Examples:
      | Aadhaar     | PAN        | ExpectedMessage                 |
      | 546635466677| AAADE1234F | This Aadhaar number is already registered to another account. |

  @KYCAPI
  Scenario Outline: Verify Aadhaar update restriction after submission
    Given User has valid verified KYC session cookie
    When Verified KYC user sends PATCH request with Aadhaar "<Aadhaar>" and PAN "<PAN>"
    Then Response status code should be 400
    And Response message should be "<ExpectedMessage>"

    Examples:
      | Aadhaar     | PAN        | ExpectedMessage                                          |
      | 885555466677 | AAAAE1234F | KYC is already verified and cannot be edited. |
  @KYCAPI
  Scenario Outline: Verify valid PAN update
    When User sends PATCH request with Aadhaar "<Aadhaar>" and PAN "<PAN>"
    Then Response status code should be 200
    And Response message should be "<ExpectedMessage>"

    Examples:
      | Aadhaar     | PAN        | ExpectedMessage           |
      | 666635466677 | PQRSX6789K | KYC updated successfully. |



  @KYCAPI
  Scenario Outline: Verify invalid PAN format validation
    When User sends PATCH request with Aadhaar "<Aadhaar>" and PAN "<PAN>"
    Then Response status code should be 400
    And Response message should be "<ExpectedMessage>"

    Examples:
      | Aadhaar     | PAN        | ExpectedMessage               |
      | 666635466677 | AB12345CDE | Invalid PAN format (e.g., ABCDE1234F). |