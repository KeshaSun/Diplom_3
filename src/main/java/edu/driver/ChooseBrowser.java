package edu.driver;

import org.openqa.selenium.WebDriver;

public class ChooseBrowser {

    public static WebDriver driver;

    public static WebDriver chooseWebDriver() {
        WebDriverFactory webDriverFactory = new WebDriverFactory();
        driver = webDriverFactory.getWebDriver();
        return driver;
    }
}