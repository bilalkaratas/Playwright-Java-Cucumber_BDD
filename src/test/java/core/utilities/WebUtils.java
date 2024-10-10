package core.utilities;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.Tracing;
import core.BrowserFactory;
import io.cucumber.java.Scenario;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class WebUtils {

    public static void takeScreenshot(Page page, Scenario scenario) throws IOException {
        String fileName = scenario.getName().replaceAll(" ", "_") + ".png";
        byte[] screenshot = page.screenshot();
        scenario.attach(screenshot, "image/png", fileName);

        Path screenshotDir = Paths.get("screenshots");
        if (!Files.exists(screenshotDir)) {
            Files.createDirectories(screenshotDir);
        }
        Files.write(screenshotDir.resolve(fileName), screenshot, StandardOpenOption.CREATE);
        BrowserFactory.context.tracing().stop(new Tracing.StopOptions().setPath(Paths.get("target/" + fileName)));
    }

    public static void waitFor(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
