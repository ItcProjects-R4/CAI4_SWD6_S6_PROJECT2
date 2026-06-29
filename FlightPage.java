
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class FlightPage extends BasePage {

    private final By flightsTab    = By.xpath("//a[contains(@aria-label,'Flights') or .//span[text()='Flights']]");

    // ─── Search Form ───────────────────────────────
    @FindBy(css = "[aria-controls='flight-trip-type-dropdown'][role='combobox']")
    WebElement tripTypeDropdown;

    @FindBy(css = "input[aria-label='Origin location']")
    WebElement originInput;

    @FindBy(css = "[role='button'][aria-label='Swap origin and destination locations']")
    WebElement swapButton;

    // كتابة اسم المدينة



    @FindBy(xpath = "//*[contains(@aria-label,'Departure')]")
    WebElement departureDateBtn;

    @FindBy(xpath = "//*[contains(@aria-label,'Return')]")
    WebElement returnDateBtn;

    @FindBy(css = "[aria-controls='flight-horizontal-search-form-travelers-dropdown'][role='combobox']")
    WebElement travelersDropdown;

    @FindBy(css = "button[aria-label='Search'][type='submit']")
    WebElement searchBtn;

    // ─── Filters ───────────────────────────────────
    @FindBy(css = "#horizontalFilters [role='button'][aria-label='Scroll right']")
    WebElement scrollRightBtn;

    @FindBy(xpath = "//section[@id='horizontalFilters']//div[@role='button' and normalize-space()='Stops']")
    WebElement stopsFilter;

    // ─── Results List ──────────────────────────────
    @FindBy(css = "#flight-results-list-wrapper .Fxw9-result-item-container")
    WebElement firstResultCard;

    @FindBy(css = "[role='button'][aria-label='Share']")
    WebElement shareBtn;

    @FindBy(css = "a.oVHK-fclink[href*='book/flight']")
    WebElement priceLink;

    @FindBy(xpath = ".//span[normalize-space()='Select']/ancestor::a[1]")
    WebElement selectBtn;

    @FindBy(css = "[id$='-details']")
    WebElement detailsPane;

    @FindBy(css = "div.qQvr-wrapper[role='button'][aria-label^='Price breakdown']")
    WebElement priceBreakdownBtn;

    // ─── Constructor ───────────────────────────────
    public FlightPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }


    // ─── Actions (Search Form) ─────────────────────

    public void selectTripType() { tripTypeDropdown.click(); }
    public void enterOrigin(String city) { originInput.clear(); originInput.sendKeys(city); }
    // ── Destination ─────────────────────────────────
    public void enterDestination(String city) {
        // اضغط على حقل الـ destination الأول
        WebElement destField = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//input[@aria-label='Destination location']")
                )
        );
        destField.clear();
        destField.sendKeys(city);
    }

    public void selectDestination(String city) {
        WebElement suggestion = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.cssSelector("li[role='option'][aria-label*='" + city + "']")
                )
        );
        suggestion.click();
    }
    public void swapLocations() { swapButton.click(); }

    public void setDepartureDate() {
        wait.until(ExpectedConditions.elementToBeClickable(departureDateBtn));
        departureDateBtn.click();
    }

    public void setReturnDate() {
        wait.until(ExpectedConditions.elementToBeClickable(returnDateBtn));
        returnDateBtn.click();
    }

    public void selectDateByText(String date) {
        By dateCell = By.xpath(
                "//div[@role='button'][contains(@aria-label,'" + date + "')]"
        );
        wait.until(ExpectedConditions.elementToBeClickable(dateCell));
        driver.findElement(dateCell).click();
    }

    public void selectTravelers() { travelersDropdown.click(); }
    public void clickSearch() { searchBtn.click(); }

    // ─── Actions (Filters & Results) ───────────────
    public void clickScrollRight() { scrollRightBtn.click(); }
    public void clickStopsFilter() { stopsFilter.click(); }
    public void openFirstResult() { firstResultCard.click(); }
    public void clickShare() { shareBtn.click(); }
    public void clickPriceLink() { priceLink.click(); }
    public void clickSelect() { selectBtn.click(); }
    public void expandDetails() { detailsPane.click(); }
    public void openPriceBreakdown() { priceBreakdownBtn.click(); }

    // ─── Select Specific Date ───────────────────────────────

    // ─── Results Helpers ───────────────────────────────element pravite not used out class
    public boolean isFirstResultDisplayed() {
        try {
            return firstResultCard.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void openFirstResultCard() {
        firstResultCard.click();
    }

}
