Feature: Validating Place APIs

Scenario: Verify if place is being successfully added using AddPlaceAPI
Given Add Place Payload
When user calls "AddplaceAPI" with post http request 
Then The API call is success with status code 200
And "status" in response body "Ok"
And "scope" in response body "APP"