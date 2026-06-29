import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class StaysPage extends BasePage {

    // WebElements (Locators)
    @FindBy(name = "ss")
    WebElement destinationField;

    @FindBy(xpath = "//button[@type='submit']")
    WebElement searchButton;

    @FindBy(css = "button[aria-label='Dismiss sign-in info.']")
    WebElement closePopup;

    @FindBy(xpath = "//button[contains(., 'Check-in date')]")
    WebElement calendarButton;

    @FindBy(css = "[aria-label*='3 July 2026']")
    WebElement checkInDate;

    @FindBy(css = "[aria-label*='4 July 2026']")
    WebElement checkOutDate;

    @FindBy(xpath = "//*[contains(text(), 'Enter a destination to start searching')]")
    WebElement errorMessage;

    // Constructor to initialize elements using PageFactory from BasePage
    public StaysPage(WebDriver driver) {
        super(driver);
        driver.get("https://www.booking.com/?lang=en-us");
    }

    // Enter destination city
    public void enterDestination(String city) {
        type(destinationField, city);
    }

    // Click search button with explicit wait
    public void clickSearch() {
        click(searchButton);
    }

    // Close sign-in popup if it appears, ignore if not displayed
    public void closeSignInPopup() {
        try {
            click(closePopup);
        } catch (Exception e) {
            System.out.println("Popup not displayed");
        }
    }

    // Open the calendar popup
    public void openCalendar() {
        click(calendarButton);
    }

    // Select check-in and check-out dates
    public void selectDates() {
        click(checkInDate);
        click(checkOutDate);
    }

    // Check if the mandatory destination error message is displayed
    public boolean isErrorMessageDisplayed() {
        try {
            return isDisplayed(errorMessage);
        } catch (Exception e) {
            return false;
        }
    }
}