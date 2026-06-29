import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AttractionsPage extends BasePage {


    @FindBy(xpath = "//a[contains(@href,'/attractions') or .//span[contains(text(),'Attractions')]]")
    private WebElement attractionsNavLink;

    @FindBy(xpath = "//input[@placeholder='Search destinations, attractions'] | //input[contains(@id,'attractions-searchbox')]")
    private WebElement searchInput;

    @FindBy(xpath = "//button[@type='submit' and (contains(.,'Search') or contains(@aria-label,'Search'))]")
    private WebElement searchButton;

    @FindBy(xpath = "(//div[contains(@data-testid,'card') or contains(@class,'result-card')])[1]")
    private WebElement firstAttractionCard;

    @FindBy(xpath = "//h3[contains(@class,'title')] | //span[contains(@class,'name')]")
    private WebElement attractionTitle;

    @FindBy(xpath = "//div[contains(@data-testid,'price')] | //span[contains(@class,'price')]")
    private WebElement attractionPrice;

    @FindBy(xpath = "//span[contains(@class,'rating') or contains(@data-testid,'rating')]")
    private WebElement attractionRating;

    @FindBy(xpath = "//button[contains(.,'Filter') or contains(@data-testid,'filter')]")
    private WebElement filterButton;

    @FindBy(xpath = "//input[@type='range' and contains(@aria-label,'rating')] | //label[contains(.,'Rating')]")
    private WebElement ratingFilter;

    @FindBy(xpath = "//label[contains(.,'Location')] | //button[contains(.,'Location')]")
    private WebElement locationFilter;

    @FindBy(xpath = "//button[contains(.,'Apply') or @data-testid='apply-filters']")
    private WebElement applyFiltersButton;


    @FindBy(xpath = "//h1[contains(@class,'name') or @data-testid='attraction-name']")
    private WebElement attractionDetailName;

    @FindBy(xpath = "//div[contains(@data-testid,'description') or contains(@class,'description')]")
    private WebElement attractionDescription;

    @FindBy(xpath = "//span[contains(@data-testid,'review') or contains(@class,'review-score')]")
    private WebElement reviewScore;


    @FindBy(xpath = "//button[contains(.,'Book') or contains(.,'Get tickets') or @data-testid='cta-button']")
    private WebElement bookTicketsButton;

    @FindBy(xpath = "//input[@type='number' and contains(@aria-label,'Adult')] | //select[contains(@aria-label,'Adult')]")
    private WebElement adultCountInput;

    @FindBy(xpath = "//button[contains(.,'Confirm') or contains(.,'Reserve') or @data-testid='confirm-button']")
    private WebElement confirmBookingButton;


    AttractionsPage(WebDriver driver) {
        super(driver);
    }


    public void goToAttractionsPage() {
        click(attractionsNavLink);
    }

    public void searchAttraction(String destination) {
        type(searchInput, destination);
        click(searchButton);
    }

    public void clickFirstAttractionResult() {
        scrollToElement(firstAttractionCard);
        click(firstAttractionCard);
    }

    public String getAttractionDetailName() {
        return getText(attractionDetailName);
    }

    public String getAttractionDescription() {
        return getText(attractionDescription);
    }

    public String getReviewScore() {
        return getText(reviewScore);
    }

    public boolean isAttractionDetailNameDisplayed() {
        return isDisplayed(attractionDetailName);
    }

    public boolean isAttractionDescriptionDisplayed() {
        return isDisplayed(attractionDescription);
    }


    public void openFilters() {
        click(filterButton);
    }

    public void selectLocationFilter() {
        scrollToElement(locationFilter);
        click(locationFilter);
    }

    public void selectRatingFilter() {
        scrollToElement(ratingFilter);
        click(ratingFilter);
    }

    public void applyFilters() {
        click(applyFiltersButton);
    }

    public void applyLocationFilter() {
        openFilters();
        selectLocationFilter();
        applyFilters();
    }

    public void applyRatingFilter() {
        openFilters();
        selectRatingFilter();
        applyFilters();
    }


    public void clickBookTickets() {
        scrollToElement(bookTicketsButton);
        click(bookTicketsButton);
    }

    public void setAdultCount(String count) {
        type(adultCountInput, count);
    }

    public void confirmBooking() {
        click(confirmBookingButton);
    }

    public void bookTicket(String adultCount) {
        clickBookTickets();
        setAdultCount(adultCount);
        confirmBooking();
    }

    public String getAttractionTitle() {
        return getText(attractionTitle);
    }

    public String getAttractionPrice() {
        return getText(attractionPrice);
    }

    public String getAttractionRating() {
        return getText(attractionRating);
    }
}