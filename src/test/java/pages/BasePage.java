package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;

public class BasePage extends CommonMethods{
    WebDriver driver;

    public BasePage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

}