import org.testng.Assert;
import org.testng.annotations.Test;

public class AirportTaxiSearchTest extends BaseTest {

    @Test
    public void testAirportTaxiSearch() {
        HomePage homePage = new HomePage(driver);
        homePage.CloseSignInPopup();
        homePage.acceptCookiesIfPresent();

        AirportTaxiPage airportTaxiPage = homePage.clickAirportTaxis();
        Assert.assertTrue(airportTaxiPage.isAirportTaxiPageOpened(), "Airport Taxi page did not open.");

        airportTaxiPage.enterPickupLocation("London Heathrow Airport");
        airportTaxiPage.enterDestination("London City Center");
        airportTaxiPage.selectPickupDate();
        airportTaxiPage.selectPickupTime();
        airportTaxiPage.selectPassengers(2);

        TaxiResultsPage resultsPage = airportTaxiPage.clickSearch();

        Assert.assertTrue(resultsPage.isResultsPageDisplayed(), "Results page was not displayed.");
        Assert.assertTrue(resultsPage.areTransferCardsDisplayed(), "No transfer cards were displayed.");
        Assert.assertTrue(resultsPage.arePricesDisplayed(), "No prices were displayed on results page.");
    }
}