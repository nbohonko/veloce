@testSuite

Feature: Contacts form validation

  Scenario: Validate Contacts form fields filling and form sending
    Given user opens default URL
    And user closes Cookie bar
    Then user navigates to Contacts page from the Home page
    When user switches to Contacts Frame
    Then user validates Contacts Frame elements
    And user validates Contacts Frame elements state
      | First name | true  |
      | Last name  | true  |
      | Email      | true  |
      | Company    | true  |
      | Submit     | false |

    When user fills Contacts Frame elements
      | First name | John |
    Then user validates Contacts Frame elements state
      | Submit | false |

    When user fills Contacts Frame elements
      | Last name | Doe |
    Then user validates Contacts Frame elements state
      | Submit | false |

    When user fills Contacts Frame elements
      | Company | Google |
    Then user validates Contacts Frame elements state
      | Submit | false |

    When user fills Contacts Frame elements
      | Email | 12345 |
    Then user validates Contacts Frame elements state
      | Submit | false |

    When user fills Contacts Frame elements
      | Email | john.doe@google.com |
    Then user validates Contacts Frame elements state
      | Submit | true |
    And user clicks on Submit button in Contacts Frame

    Then user validates Contacts Second Frame elements
    And user validates Contacts Frame elements state
      | First name | true  |
      | Last name  | true  |
      | Email      | true  |
      | Company    | true  |
      | City       | true  |
      | State      | true  |
      | Submit     | false |

    When user fills Contacts Frame elements
      | First name | John |
    Then user validates Contacts Frame elements state
      | Submit | false |

    When user fills Contacts Frame elements
      | Last name | Doe |
    Then user validates Contacts Frame elements state
      | Submit | false |

    When user fills Contacts Frame elements
      | Company | Google |
    Then user validates Contacts Frame elements state
      | Submit | false |

    When user fills Contacts Frame elements
      | Email | 12345 |
    Then user validates Contacts Frame elements state
      | Submit | false |

    When user fills Contacts Frame elements
      | Email | john.doe@google.com |
    Then user validates Contacts Frame elements state
      | Submit | false |

    When user fills Contacts Frame elements
      | State | N/A |
    Then user validates Contacts Frame elements state
      | Submit | false |

    When user fills Contacts Frame elements
      | City | Riga |
    Then user validates Contacts Frame elements state
      | Submit | false |

    When user clicks on Submit button in Contacts Frame
    Then user does not see an error in Contact Frame