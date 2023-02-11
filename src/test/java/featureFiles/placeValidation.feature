Feature: Validating place API
#  Test suit
@AddPlace
  Scenario Outline: verify if the place is being successfully added using AddPlaceAPI
#    Test case
    Given Add a place Payload "<name>" "<language>" "<address>"
    When user calls "AddPlaceAPI" with "post" http request
    Then the response with API call got success code 200
    And "status" in response body is "OK"
    And "scope" in response body is "APP"
    And verify place_id created maps to "<name>" using "GetPlaceAPI"

Examples:
  | name    | language | address  |
  | Nafisha | Bangla   | New York |
#  | sponge  | Bob      | UnderSea |

  @DeletePlace
  Scenario: Verify if Delete place functionality is working

    Given DeletePlace Payload
    When user calls "DeletePlaceAPI" with "post" http request
    Then the response with API call got success code 200
    And "status" in response body is "OK"