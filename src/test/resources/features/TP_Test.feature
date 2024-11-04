Feature: Database Testing for Trivial Sell

  Scenario: Verify successful processing according to FIFO LRM
    Given the setup data is inserted into the database
    When a message is put into the processing queue
    And the processing thread is started
    Then the results should match the expected values in the tables