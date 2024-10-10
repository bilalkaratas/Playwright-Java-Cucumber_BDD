package core;

import com.microsoft.playwright.*;
import core.utilities.ConfigurationReader;

// BrowserFactory class is responsible for creating Playwright objects
// and initializing the browser

public class BrowserFactory {

    public static Page page;
    public Browser browser;
    public static BrowserContext context;

    public static ThreadLocal<Page> threadLocalDriver = new ThreadLocal<>();
    public static ThreadLocal<BrowserContext> threadLocalContext = new ThreadLocal<>();

    public Page initBrowser() {
        return initBrowser(ConfigurationReader.get("browser"));
    }

    public Page initBrowser(String browserName) {
        BrowserType browserType = null;
        boolean headless = Boolean.valueOf(ConfigurationReader.get("headless"));
        switch (browserName) {
            case "firefox":
                browserType = Playwright.create().firefox();
                browser = browserType.launch(new BrowserType.LaunchOptions().setHeadless(headless));
                break;
            case "chrome":
                browserType = Playwright.create().chromium();
                browser = browserType.launch(new BrowserType.LaunchOptions().setChannel("chrome").setHeadless(headless));
                break;
            case "webkit":
                browserType = Playwright.create().webkit();
                browser = browserType.launch(new BrowserType.LaunchOptions().setHeadless(headless));
                break;
        }

        context = browser.newContext();
        context.tracing().start(new Tracing.StartOptions().setScreenshots(true).setSnapshots(true).setSources(false));
        page = context.newPage();
        threadLocalDriver.set(page);
        threadLocalContext.set(context);
        return page;
    }

    public static synchronized Page getPage() {
        return threadLocalDriver.get();
    }

    public static synchronized BrowserContext getContext() {
        return threadLocalContext.get();
    }

}