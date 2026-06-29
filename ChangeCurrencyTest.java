import org.testng.Assert;
import org.testng.annotations.Test;

public class ChangeCurrencyTest extends BaseTest {

    @Test
    public void testChangeCurrency() {
        HomePage homePage = new HomePage(driver);
        homePage.CloseSignInPopup();
        homePage.acceptCookiesIfPresent();

        homePage.openCurrencyMenu();
        homePage.selectEur();
        Assert.assertTrue(homePage.isSymbolDisplayedOnPage("€"),
                "Euro symbol (€) was not found on the page after currency switch.");

        homePage.openCurrencyMenu();
        homePage.selectUsd();
        Assert.assertTrue(homePage.isSymbolDisplayedOnPage("$"),
                "Dollar symbol ($) was not found on the page after currency switch.");
    }
}
