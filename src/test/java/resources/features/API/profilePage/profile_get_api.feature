Feature: User Profile GET API Validation

  @GetUserProfileAPI
  Scenario: Verify GET /user/me returns logged-in user profile successfully
    Given User has valid session cookie
    When User sends GET request to "/user/me"
    Then Response status code should be 200
    And Response field "success" should be true


  @GetUserProfileAPI
  Scenario: Verify GET /user/me without session cookie
    When User sends GET request to "/user/me" without cookie
    Then Response status code should be 401
    And Response message should be "Authentication token required."


  @GetUserProfileAPI
  Scenario: Verify GET /user/me with invalid session cookie
    Given User has invalid session cookie
    When User sends GET request to "/user/me"
    Then Response status code should be 401
    And Response message should be "Invalid or expired token."


#  @GetUserProfileAPI
#  Scenario: Verify GET /user/me with expired session cookie
#    Given User has expired session cookie
#    When User sends GET request to "/user/me"
#    Then Response status code should be 401
#    And Response message should be "Invalid or expired token."


  @GetUserProfileAPI
  Scenario: Verify GET /user/me returns correct response structure
    Given User has valid session cookie
    When User sends GET request to "/user/me"
    Then Response should contain field "user_id"
    And Response should contain field "full_name"
    And Response should contain field "email"
    And Response should contain field "phone"
    And Response should contain field "dob"
    And Response should contain field "gender"
    And Response should contain field "address"
    And Response should contain field "aadhaar_number"
    And Response should contain field "pan_number"


  @GetUserProfileAPI
  Scenario: Verify GET /user/me does not expose sensitive fields
    Given User has valid session cookie
    When User sends GET request to "/user/me"
    Then Response should not contain field "password_hash"
    And Response should not contain field "transaction_pin_hash"