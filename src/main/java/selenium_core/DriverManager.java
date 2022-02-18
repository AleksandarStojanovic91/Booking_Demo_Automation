package selenium_core;

import org.openqa.selenium.WebDriver;

public abstract class DriverManager {
    WebDriver driver;

    protected abstract void createWebDriver(String version);

    public void quitWebDriver(){
        if(null != driver){
            driver.quit();
            driver = null;
        }
    }

    public WebDriver getWebDriver(String version){
        if(null == driver){
            createWebDriver(version);
        }
        return driver;
    }

}