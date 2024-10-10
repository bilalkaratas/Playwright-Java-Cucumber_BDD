package steps;

import core.BrowserFactory;
import core.utilities.ConfigurationReader;
import core.utilities.WebUtils;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.LoginPage;

public class StepDefinitions {
    LoginPage loginPage = new LoginPage(BrowserFactory.getPage());

    @Given("I am on the login page")
    public void iAmOnTheLoginPage() {
        loginPage.goToLoginPage();
    }

    @When("I login with valid credentials")
    public void iLoginWithValidCredentials() {
        String username = ConfigurationReader.get("username");
        String password = ConfigurationReader.get("password");
        loginPage.login(username, password);
    }

    @Then("I should see the successful login message")
    public void iShouldSeeTheSuccessfulLoginMessage() {
        loginPage.verifySuccessfulLogin();
    }

    @And("I should see the title {string}")
    public void iShouldSeeTheTitle(String expectedTitle) {
        loginPage.verifyTitle(expectedTitle);
    }

    @When("I login with invalid credentials")
    public void iLoginWithInvalidCredentials() {
        loginPage.login("invalid_user", "invalid_password");
    }

    @Then("I should see the login fail message")
    public void iShouldSeeTheLoginFailMessage() {
        loginPage.verifyLoginFailMessage();
    }

    @And("I wait for {int} seconds")
    public void iWaitForSeconds(int seconds)  {
        WebUtils.waitFor(seconds);
    }
}
