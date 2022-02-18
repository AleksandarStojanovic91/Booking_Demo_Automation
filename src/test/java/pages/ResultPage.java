package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class ResultPage extends BasePage {
    WebDriver driver;

    public ResultPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "#ss")
    WebElement location;
    @FindBy(css = "#xp__guests__toggle")
    WebElement guests;
    @FindBy(xpath = "//label[text()='Adults']/../..//span[@class='bui-stepper__display']")
    WebElement adultsNum;
    @FindBy(xpath = "//label[text()='Children']/../..//span[@class='bui-stepper__display']")
    WebElement childrenNum;
    @FindBy(xpath = "//label[text()='Rooms']/../..//span[@class='bui-stepper__display']")
    WebElement roomsNum;
    @FindBy(xpath = "//div[@data-placeholder='Check-in Date']")
    WebElement checkInDateEl;
    @FindBy(xpath = "//div[@data-placeholder='Check-out Date']")
    WebElement checkOutDateEl;

    public void verifyLocation(String value){
        Assert.assertEquals(getValue(location), value);
    }

    public void verifyAdultsNum(String value){
        Assert.assertEquals(getText(adultsNum), value);
    }

    public void verifyChildrenNum(String value){
        Assert.assertEquals(getText(childrenNum), value);
    }

    public void verifyRoomsNum(String value){
        Assert.assertEquals(getText(roomsNum), value);
    }

    public void openGuestInfo() throws InterruptedException {
        clickElement(guests);
    }

    public void verifyDates(Map<String, String> data) throws ParseException {
        Date checkInDate = new SimpleDateFormat("yyyy-MM-dd").parse(data.get("CheckIn"));
        Date checkOutDate = new SimpleDateFormat("yyyy-MM-dd").parse(data.get("CheckOut"));

        String pattern = "EEEE, MMMM dd, yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

        String checkInFormatted = simpleDateFormat.format(checkInDate);
        String checkOutFormatted = simpleDateFormat.format(checkOutDate);

        Assert.assertEquals(checkInFormatted, getText(checkInDateEl));
        Assert.assertEquals(checkOutFormatted, getText(checkOutDateEl));
    }
}
