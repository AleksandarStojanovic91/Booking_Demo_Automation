Feature: Booking

  Scenario Outline: Book a Stay row: "<row>"

    * I load test data from "Booking" "BookingSheet" "<row>"
    * I navigate to Booking
    * I add location
    * I add check in date and check out date
    * I add occupancy information
    * I click search
    * I verify search criteria
    * I see Availability of first result
    * I verify availability information

    Examples:
      | row |
      | 1   |
      | 2   |