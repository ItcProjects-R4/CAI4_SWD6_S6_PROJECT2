import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class AirportTaxiPage extends BasePage {

    @FindBy(css = "input[data-testid='input-location-pick-up'], input[id*='pick-up'], input[placeholder*='pick-up' i]")
    WebElement pickupInput;

    @FindBy(css = "input[data-testid='input-location-drop-off'], input[id*='drop-off'], input[placeholder*='destination' i]")
    WebElement destinationInput;

    @FindBy(css = "li[role='option'], [data-testid='autocomplete-result']")
    WebElement firstAutocompleteSuggestion;

    @FindBy(css = "[data-testid='taxis-searchbox-pickup-date'], input[id*='date'], button[id*='date']")
    WebElement pickupDateField;

    @FindBy(xpath = "//td[not(contains(@class,'disabled'))][1]//span | //button[not(@disabled)][contains(@class,'day')][1]")
    WebElement firstEnabledDay;

    @FindBy(css = "[data-testid='taxis-searchbox-pickup-time'], select[id*='time'], button[id*='time']")
    WebElement pickupTimeField;

    @FindBy(xpath = "//li[contains(@id,'time') or contains(@role,'option')][1]")
    WebElement firstTimeOption;

    @FindBy(css = "[data-testid='taxis-searchbox-passengers'], button[id*='passenger']")
    WebElement passengersField;

    @FindBy(css = "button[aria-label*='increase' i], button[data-testid*='increase']")
    WebElement increasePassengerBtn;

    @FindBy(xpath = "//button[contains(text(),'Apply') or contains(text(),'Done')]")
    WebElement applyPassengersBtn;

    @FindBy(css = "button[data-testid='taxis-searchbox-submit'], button[type='submit']")
    WebElement searchButton;

    @FindBy(xpath = "//*[contains(text(),'Airport') and contains(text(),'Taxi')]")
    WebElement airportTaxiPageHeader;

    @FindBy(xpath = "//button[@aria-label='إخفاء المعلومات حول تسجيل الدخول.']")
    WebElement closePopup;

    public AirportTaxiPage(WebDriver driver) {
        super(driver);
    }

    public boolean isAirportTaxiPageOpened() {
        return isDisplayed(airportTaxiPageHeader) || driver.getCurrentUrl().toLowerCase().contains("airport");
    }

    public void enterPickupLocation(String location) {
        scrollToElement(pickupInput);
        type(pickupInput, location);
        selectFirstSuggestionIfPresent();
    }

    public void enterDestination(String destination) {
        scrollToElement(destinationInput);
        type(destinationInput, destination);
        selectFirstSuggestionIfPresent();
    }

    private void selectFirstSuggestionIfPresent() {
        try {
            if (isDisplayed(firstAutocompleteSuggestion)) {
                click(firstAutocompleteSuggestion);
            }
        } catch (Exception ignored) {
            // no suggestion dropdown appeared
        }
    }

    public void selectPickupDate() {
        try {
            click(pickupDateField);
            click(firstEnabledDay);
        } catch (Exception ignored) {
            // date picker UI may differ
        }
    }

    public void selectPickupTime() {
        try {
            click(pickupTimeField);
            click(firstTimeOption);
        } catch (Exception ignored) {
            // time picker UI may differ
        }
    }

    public void selectPassengers(int passengers) {
        try {
            click(passengersField);
            for (int i = 1; i < passengers; i++) {
                click(increasePassengerBtn);
            }
            try {
                if (isDisplayed(applyPassengersBtn)) {
                    click(applyPassengersBtn);
                }
            } catch (Exception ignored) {
                // no explicit confirm button needed
            }
        } catch (Exception ignored) {
            // passenger selector UI may differ
        }
    }

    public TaxiResultsPage clickSearch() {
        scrollToElement(searchButton);
        click(searchButton);
        return new TaxiResultsPage(driver);
    }

    public void CloseSignInPopup() {
        try {
            click(closePopup);
        } catch (Exception e) {
            System.out.println("Popup not displayed");
        }
    }
}