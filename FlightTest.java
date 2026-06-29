
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class FlightTest extends BaseTest {

    @Test
    public void testRoundTrip() throws InterruptedException {

        driver.get("https://booking.kayak.com/flights");

        FlightPage flightPage = new FlightPage(driver);

        Thread.sleep(5000);

        flightPage.selectTripType();
        Thread.sleep(2000);

        driver.findElement(By.xpath(
                        "//*[@id='flight-trip-type-dropdown']//*[contains(text(),'Round-trip')]"))
                .click();

        Thread.sleep(2000);

        flightPage.enterOrigin("Cairo");

        Thread.sleep(2000);

        driver.findElement(By.xpath("(//li[@role='option'])[1]")).click();

        Thread.sleep(2000);

        // Destination


        // Destination
        flightPage.enterDestination("Sharm");
        Thread.sleep(2000);

        flightPage.selectDestination("Sharm");
        Thread.sleep(1000);

        // Departure Date
        flightPage.setDepartureDate();
        Thread.sleep(2000);
        flightPage.selectDateByText("June 30, 2026");
        Thread.sleep(1000);

// Return Date - الـ calendar هيفضل مفتوح
        flightPage.selectDateByText("July 1, 2026");
        Thread.sleep(1000);
        Thread.sleep(2000);

        flightPage.clickSearch();

        Thread.sleep(8000);

        Assert.assertTrue(driver.getCurrentUrl().contains("flights"));

    }

    @Test
    public void testOneWay() throws InterruptedException {

        driver.get("https://booking.kayak.com/flights");
        FlightPage flightPage = new FlightPage(driver);
        Thread.sleep(5000);

        // اختار One-way
        flightPage.selectTripType();
        Thread.sleep(2000);

        driver.findElement(By.xpath(
                        "//*[@id='flight-trip-type-dropdown']//*[contains(text(),'One-way')]"))
                .click();
        Thread.sleep(2000);

        // Origin
        flightPage.enterOrigin("Cairo");
        Thread.sleep(2000);
        driver.findElement(By.xpath("(//li[@role='option'])[1]")).click();
        Thread.sleep(2000);

        // Destination
        flightPage.enterDestination("Sharm");
        Thread.sleep(2000);
        flightPage.selectDestination("Sharm");
        Thread.sleep(1000);

        // Departure Date فقط - مفيش Return في One-way
        flightPage.setDepartureDate();
        Thread.sleep(2000);
        flightPage.selectDateByText("June 30, 2026");
        Thread.sleep(1000);

        // Search
        flightPage.clickSearch();
        Thread.sleep(8000);

        Assert.assertTrue(
                driver.getCurrentUrl().contains("flights"),
                "Results page did not open for One Way!"
        );
    }
}