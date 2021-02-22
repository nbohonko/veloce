@testSuite

Feature: Rest solution prototype

  Scenario: Validate Booking creation, validation and deletion endpoints
    Given user sends static POST request to booking and receives 200 status code
    """
    {
    "firstname" : "Jim",
    "lastname" : "Brown",
    "totalprice" : 472,
    "depositpaid" : true,
    "bookingdates" : {
        "checkin" : "2021-01-01",
        "checkout" : "2021-07-15"
    },
    "additionalneeds" : "Breakfast"
    }
    """
    When user sends GET request to booking/ %savedBookingId and receives 200 status code
    Then user validates response for GET /booking
      | firstname       | Jim        |
      | lastname        | Brown      |
      | totalprice      | 472        |
      | depositpaid     | true       |
      | checkin         | 2021-01-01 |
      | checkout        | 2021-07-15 |
      | additionalneeds | Breakfast  |
    When user sends static PUT request to booking/ %savedBookingId and receives 200 status code
    """
    {
    "firstname" : "Jimmy",
    "lastname" : "Browny",
    "totalprice" : 4721,
    "depositpaid" : false,
    "bookingdates" : {
        "checkin" : "2022-01-01",
        "checkout" : "2022-07-15"
    },
    "additionalneeds" : "Lunch"
    }
    """
    And user sends GET request to booking/ %savedBookingId and receives 200 status code
    Then user validates response for GET /booking
      | firstname       | Jimmy      |
      | lastname        | Browny     |
      | totalprice      | 4721       |
      | depositpaid     | false      |
      | checkin         | 2022-01-01 |
      | checkout        | 2022-07-15 |
      | additionalneeds | Lunch      |
    Then user sends DELETE request to booking/ %savedBookingId and receives 201 status code
    And user sends GET request to booking/ %savedBookingId and receives 404 status code
