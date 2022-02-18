package pages;

import io.cucumber.java.sl.In;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.Map;

public class StaysPage extends BasePage {
    WebDriver driver;

    public StaysPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "#ss")
    WebElement location;
    @FindBy(css = ".sb-searchbox__input.sb-date-field__field")
    List<WebElement> dates;
    @FindBy(css = ".xp__guests__count")
    WebElement guestCount;
    @FindBy(css = "[aria-label=\"Decrease number of Adults\"]")
    WebElement decAdultNum;
    @FindBy(css = "[aria-label=\"Increase number of Adults\"]")
    WebElement incAdultNum;
    @FindBy(css = "[aria-label=\"Decrease number of Children\"]")
    WebElement decChildrenNum;
    @FindBy(css = "[aria-label=\"Increase number of Children\"]")
    WebElement incChildrenNum;
    @FindBy(css = "[aria-label=\"Increase number of Rooms\"]")
    WebElement incRoomsNum;
    @FindBy(xpath = "//span[contains(text(),'Search')]")
    WebElement search;

    public void addLocation(String locationText) throws InterruptedException {
        typeText(location, locationText);
    }

    public void addOccupancyInformation(Map<String,String> data) throws InterruptedException {
        clickElement(guestCount);
        addAdults(data.get("AdultsNum"));
        addChildren(data.get("ChildrenNum"), data.get("ChildrensYears"));
        addRooms(data.get("Rooms"));
    }

    public void addAdults(String num) throws InterruptedException {
        if (num.equals("2")) {
            //Do nothing
        } else if (Integer.parseInt(num) < 2) {
            clickElement(decAdultNum);
        } else {
            for (int i = 3; i <= Integer.parseInt(num); i++) {
                clickElement(incAdultNum);
            }
        }
    }

    public void addChildren(String num, String age) throws InterruptedException {
        String[] ages = age.split(",");
        if(num.equals("0")){
            clickElement(decChildrenNum);
        }
        for (int i = 1; i <= Integer.parseInt(num); i++) {
            clickElement(incChildrenNum);
            selectByValue(driver.findElement(By.cssSelector("[aria-label=\"Child "+i+" age\"]")), ages[i-1]);
        }
    }

    public void addRooms(String num) throws InterruptedException {
        for (int i = 2; i <= Integer.parseInt(num); i++) {
            clickElement(incRoomsNum);
        }
    }

    public void checkInOut(String checkIn, String checkOut) throws InterruptedException {
        //Check is date today's date or futures date
        //check is check out after check in date
        //Check is date displayed if not click next
        clickElement(dates.get(0));
        clickElement(driver.findElement(By.xpath("//td[@data-date='" + checkIn + "']")));
        clickElement(driver.findElement(By.xpath("//td[@data-date='" + checkOut + "']")));
    }

    public void clickSearch() throws InterruptedException {
        clickElement(search);
    }
}
