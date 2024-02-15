package edu.practicum;
import org.junit.rules.ExternalResource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

public class BrowserRule  extends ExternalResource {
    private WebDriver webDriver;

    public WebDriver getWebDriver(){ return webDriver; }

    protected String getDriverPath() {
        return "";
    }

    @Override
    protected void before(){
        System.setProperty("webdriver.chrome.driver", getDriverPath());
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        webDriver = new ChromeDriver(options);
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    @Override
    protected void after(){ webDriver.quit(); }
}
