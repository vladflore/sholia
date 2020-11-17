package tech.vladflore.sholia;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testcontainers.containers.BrowserWebDriverContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@Testcontainers
public class SeleniumTest {
    @Container
    public BrowserWebDriverContainer<?> chrome = new BrowserWebDriverContainer<>("selenium/standalone-chrome")
            .withCapabilities(new ChromeOptions());

    @Test
    void shouldStart() {
        RemoteWebDriver driver = chrome.getWebDriver();
        assertNotNull(driver);
    }
}
