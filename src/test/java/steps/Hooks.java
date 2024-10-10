package steps;

import com.microsoft.playwright.Page;
import core.BrowserFactory;
import core.utilities.ConfigurationReader;
import core.utilities.WebUtils;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class Hooks {
    private static final Logger log = LoggerFactory.getLogger(Hooks.class);
    private static boolean setUpIsDone = true;
    public BrowserFactory browserFactory;
    public Page page;

    @Before(order = 1)
    public void onStart() {
        if (setUpIsDone) {
            log.info("===============================================================");
            log.info("|     Demo is Starting...");
            log.info("|     Environment : " + ConfigurationReader.get("launchURL"));
            log.info("|     Operating System : " + System.getProperty("os.name"));
            log.info("|     Browser: " + ConfigurationReader.get("browser"));
            log.info("|     Tested by: " + ConfigurationReader.get("qa"));
            log.info("===============================================================\n");
            setUpIsDone = false;
        }
    }

    @Before(order = 2)
    public void launchBrowser() {
        browserFactory = new BrowserFactory();
        page = browserFactory.initBrowser();
    }

    @Before(order = 3)
    public void onTestStart(Scenario scenario) {
        log.info("===============================================================");
        log.info("|          Scenario Name: " + scenario.getName());
        log.info("===============================================================");
    }

    @After(order = 1)
    public void onTestFailure(Scenario scenario) throws IOException {
        if (scenario.isFailed()) {
            WebUtils.takeScreenshot(page, scenario);
        }
    }

    @After(order = 0)
    public void quitBrowser() {
        page.close();
    }
}
