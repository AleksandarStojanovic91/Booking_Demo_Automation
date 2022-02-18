package selenium_core;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class FirefoxDriverManager extends DriverManager{

    @Override
    public void createWebDriver(String version) {
        System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver"+version+".exe");
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("start-maximized");
        this.driver = new FirefoxDriver(options);
    }

}