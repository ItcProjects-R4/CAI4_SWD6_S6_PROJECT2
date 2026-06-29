
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CarRentalPage extends BasePage {

    public CarRentalPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//input")
    WebElement locationField;

    @FindBy(xpath = "//button[@type='submit']")
    WebElement searchButton;

    public void openCarsPage() {
        driver.get("https://www.booking.com/cars");
    }

    public void enterPickUpLocation(String location) {
        type(locationField, location);
    }

    public void clickSearch() {
        click(searchButton);
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

    public String getCurrentURL() {
        return driver.getCurrentUrl();
    }
}