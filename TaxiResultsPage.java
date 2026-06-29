import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

import java.util.List;

public class TaxiResultsPage extends BasePage {

    @FindBy(css = "[data-testid='taxi-ride-card'], [class*='ride-card'], [class*='ResultCard']")
    List<WebElement> transferCards;

    @FindBy(xpath = "//*[contains(text(),'£') or contains(text(),'$') or contains(text(),'€')]")
    List<WebElement> priceElements;

    @FindBy(xpath = "//button[@aria-label='إخفاء المعلومات حول تسجيل الدخول.']")
    WebElement closePopup;

    public TaxiResultsPage(WebDriver driver) {
        super(driver);
    }

    public boolean isResultsPageDisplayed() {
        return driver.getCurrentUrl().toLowerCase().contains("search")
                || !driver.findElements(By.cssSelector("[data-testid='taxi-ride-card']")).isEmpty();
    }

    public boolean areTransferCardsDisplayed() {
        return !transferCards.isEmpty() && isDisplayed(transferCards.get(0));
    }

    public boolean arePricesDisplayed() {
        return !priceElements.isEmpty();
    }

    public void CloseSignInPopup() {
        try {
            click(closePopup);
        } catch (Exception e) {
            System.out.println("Popup not displayed");
        }
    }
}
