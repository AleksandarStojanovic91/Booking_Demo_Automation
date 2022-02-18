package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CommonMethods {
    WebDriver driver;
    int waitTime = 30;

    public CommonMethods(WebDriver driver) {
        this.driver = driver;
    }

    //Selenium wrapper methods START
    public void clickElement(WebElement element) throws InterruptedException {
        WebDriverWait wdWait = new WebDriverWait(driver, waitTime);
        wdWait.until(ExpectedConditions.visibilityOf(element));
        wdWait.until(ExpectedConditions.elementToBeClickable(element));

//        scrollToElement(element);
        try {
            Actions actions = new Actions(driver);
            actions.moveToElement(element).build().perform();
            element.click();
        } catch (StaleElementReferenceException e){
            Thread.sleep(1000);
            element.click();
        }
    }

    public void typeText(WebElement element, String text) throws InterruptedException {
        WebDriverWait wdWait = new WebDriverWait(driver, waitTime);
        wdWait.until(ExpectedConditions.visibilityOf(element));
        wdWait.until(ExpectedConditions.elementToBeClickable(element));

        try {
//            scrollToElement(element);
            Actions actions = new Actions(driver);
            actions.moveToElement(element).build().perform();
            element.sendKeys(text);
        } catch (StaleElementReferenceException e){
            Thread.sleep(1000);
            element.click();
        }
    }

    public String getText(WebElement element){
        WebDriverWait wdWait = new WebDriverWait(driver, waitTime);
        wdWait.until(ExpectedConditions.visibilityOf(element));

        return element.getText();
    }

    public String getValue(WebElement element){
        WebDriverWait wdWait = new WebDriverWait(driver, waitTime);
        wdWait.until(ExpectedConditions.visibilityOf(element));

        return element.getAttribute("value");
    }

    public void selectByValue(WebElement element, String value){
        WebDriverWait wdWait = new WebDriverWait(driver, waitTime);
        wdWait.until(ExpectedConditions.visibilityOf(element));
        wdWait.until(ExpectedConditions.elementToBeClickable(element));

        try {
            Select select = new Select(element);
            select.selectByValue(value);
        } catch (StaleElementReferenceException e){
            Select select = new Select(element);
            select.selectByValue(value);
        }

    }

    public void scroll(WebElement element){
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true)", element);
    }

    public void scroll(String pixels){
        ((JavascriptExecutor)driver).executeScript("window.scrollBy(0,"+pixels+")");
    }

    public void hoverElement(WebElement element){
        WebDriverWait wdWait = new WebDriverWait(driver, waitTime);
        wdWait.until(ExpectedConditions.visibilityOf(element));
        wdWait.until(ExpectedConditions.elementToBeClickable(element));

        Actions actions = new Actions(driver);
        actions.moveToElement(element).build().perform();
    }
    //Selenium wrapper methods END
}