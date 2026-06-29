import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class HomePage extends BasePage {

    @FindBy(xpath = "//a[contains(@href,'taxi') or contains(translate(text(),'AIRPORT TAXIS','airport taxis'),'airport taxi')]")
    WebElement airportTaxisLink;

    @FindBy(id = "onetrust-accept-btn-handler")
    WebElement acceptCookiesBtn;

    @FindBy(css = "button[data-testid='header-language-picker-trigger']")
    WebElement languageIcon;

    @FindBy(css = "button[data-testid='header-currency-picker-trigger']")
    WebElement currencyIcon;

    @FindBy(xpath = "//*[@data-testid='selection-item' or @role='option' or @role='menuitem'][contains(.,'Français')]")
    WebElement frenchOption;

    @FindBy(xpath = "//*[@data-testid='selection-item' or @role='option' or @role='menuitem'][contains(.,'English (UK)')]")
    WebElement englishOption;

    @FindBy(xpath = "//*[@data-testid='selection-item' or @role='option' or @role='menuitem'][contains(.,'EUR')]")
    WebElement eurOption;

    @FindBy(xpath = "//*[@data-testid='selection-item' or @role='option' or @role='menuitem'][contains(.,'USD')]")
    WebElement usdOption;

    @FindBy(tagName = "html")
    WebElement htmlTag;

    @FindBy(tagName = "body")
    WebElement bodyTag;

    @FindBy(xpath = "//button[@aria-label='إخفاء المعلومات حول تسجيل الدخول.']")
    WebElement closePopup;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void acceptCookiesIfPresent() {
        try {
            if (isDisplayed(acceptCookiesBtn)) {
                click(acceptCookiesBtn);
            }
        } catch (Exception ignored) {
            // cookie banner not present
        }
    }

    public AirportTaxiPage clickAirportTaxis() {
        scrollToElement(airportTaxisLink);
        click(airportTaxisLink);
        return new AirportTaxiPage(driver);
    }

    public void openLanguageMenu() {
        click(languageIcon);
    }

    public void selectFrench() {
        click(frenchOption);
    }

    public void selectEnglish() {
        click(englishOption);
    }

    public void openCurrencyMenu() {
        click(currencyIcon);
    }

    public void selectEur() {
        click(eurOption);
    }

    public void selectUsd() {
        click(usdOption);
    }

    public String getHtmlLangAttribute() {
        return htmlTag.getAttribute("lang");
    }

    public boolean isSymbolDisplayedOnPage(String symbol) {
        wait.until(ExpectedConditions.visibilityOf(bodyTag));
        return bodyTag.getText().contains(symbol);
    }

    public void CloseSignInPopup() {
        try {
            click(closePopup);
        } catch (Exception e) {
            System.out.println("Popup not displayed");
        }
    }
}