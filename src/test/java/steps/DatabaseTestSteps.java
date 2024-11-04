package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
public class DatabaseTestSteps {
    private Connection connection;

    @Given("the setup data is inserted into the database")
    public void insertSetupData() throws Exception {
        // Connect to the database
        connection = DriverManager.getConnection("jdbc:your_database_url", "username", "password");

        // Insert setup data as per the DML statements
        Statement stmt = connection.createStatement();
        stmt.executeUpdate("INSERT INTO ACCOUNT_MASTER (ACCT_ID, ACCT_NO, ...) VALUES (...)");
        stmt.executeUpdate("INSERT INTO SEC_MASTER (SEC_TYPE, SYMBOL, ...) VALUES (...)");
        stmt.executeUpdate("INSERT INTO SVL_TRD (...) VALUES (...)");

        stmt.close();
    }

    @When("a message is put into the processing queue")
    public void putMessageInQueue() throws IOException {
        // Execute the command to put a message in the queue
        Runtime.getRuntime().exec("your_command_to_put_message");
    }

    @When("the processing thread is started")
    public void startProcessingThread() throws IOException {
        // Start the processing thread
        Runtime.getRuntime().exec("your_command_to_start_processing");
    }

    @Then("the results should match the expected values in the tables")
    public void verifyResults() throws Exception {
        Statement stmt = connection.createStatement();

        // Perform SQL queries to validate the expected results
        ResultSet rs = stmt.executeQuery("SELECT * FROM SVL_TRD WHERE ...");

        while(rs.next()) {
            // Assert the values match the expected results
            assert(rs.getString("column_name").equals("expected_value"));
        }

        rs.close();
        stmt.close();
        connection.close();
    }
}
