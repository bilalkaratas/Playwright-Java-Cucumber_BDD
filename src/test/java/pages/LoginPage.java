package pages;

import com.microsoft.playwright.Page;
import core.utilities.ConfigurationReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static core.constants.Errors.LOGIN_FAILURE_MESSAGE;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class LoginPage {
    private static final Logger log = LoggerFactory.getLogger(LoginPage.class);
    private Page page;

    private final String usernameField = "[data-test='username']";
    private final String passwordField = "[data-test='password']";
    private final String loginButton = "[data-test='login-button']";
    private final String loginMessageField = "[data-test='error']";
    private final String title = ".title";

    public LoginPage(Page page) {
        this.page = page;
    }

    public void goToLoginPage() {
        String url = ConfigurationReader.get("launchURL");
        page.navigate(url);
        log.info("Navigated to {}", url);
    }

    public void login(String username, String password) {
        page.fill(usernameField, username);
        page.fill(passwordField, password);
        page.click(loginButton);
        log.info("Login attempt by {}", username);
    }

    public void verifyTitle(String expectedTitle) {
        String actualTitle = page.locator(title).textContent();
        assertTrue(actualTitle.contains(expectedTitle));
    }

    public void verifySuccessfulLogin() {
        verifyTitle("Products");
        log.info("Login successful!");
    }

    public void verifyLoginFailMessage() {
        assertTrue(getLoginMessageText().contentEquals(LOGIN_FAILURE_MESSAGE.getMessage()));
        log.info("Login failed as expected!");
    }

    public String getLoginMessageText() {
        return page.locator(loginMessageField).textContent();
    }
}