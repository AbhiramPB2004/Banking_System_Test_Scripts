Feature: User Profile Update API Validation

  Background:
    Given User has valid session cookie


  @ProfileUpdateAPI
  Scenario: Verify successful profile update using PUT /user/me
    When User sends PUT request to "/user/me" with valid profile details
    Then Response status code should be 200
    And Response message should be "Profile updated successfully."


  @ProfileUpdateAPI
  Scenario Outline: Verify PUT /user/me with invalid full_name values
    When User sends PUT request with full_name "<FullName>"
    Then Response status code should be 400
    And Response message should be "<ExpectedMessage>"

    Examples:
      | FullName   | ExpectedMessage                                  |
      |            | full_name cannot be empty or null.               |
      | Ab         | full_name must be at least 3 characters long.    |
      | 123        | full_name must not contain numerics              |
      | Abhirm@342 | full_name must not contain special character     |



  @ProfileUpdateAPI
  Scenario Outline: Verify PUT /user/me with invalid phone values
    When User sends PUT request with phone "<Phone>"
    Then Response status code should be 400
    And Response message should be "<ExpectedMessage>"

    Examples:
      | Phone          | ExpectedMessage                               |
      |                | phone cannot be empty or null.                |
      | 73376953       | Phone number must contain exactly 10 digits.  |
      | 733769531414   | Phone number must contain exactly 10 digits.  |
      | 73376953A      | Phone number must contain exactly 10 digits.  |
      | 73376@5314     | Phone number must contain exactly 10 digits.  |


  @ProfileUpdateAPI
  Scenario Outline: Verify PUT /user/me with invalid address values
    When User sends PUT request with address "<Address>"
    Then Response should match expected validation "<ExpectedMessage>"

    Examples:
      | Address | ExpectedMessage                    |
      |         | address cannot be empty or null.   |
      | ab      | Address should be Above 10 characters |
      |asdasdadssadadsadadadnjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjlllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllll| Address cannot exceed 100 characters|