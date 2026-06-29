import org.testng.Assert;
import org.testng.annotations.Test;

public class StaysTest extends BaseTest {

    StaysPage staysPage;

    @Test
    public void verifyHotelSearch() {
        staysPage = new StaysPage(driver);
        staysPage.closeSignInPopup();

        // Enter destination and wait for suggestions automatically
        staysPage.enterDestination("Cairo");

        // Open calendar, select dates and search using page methods
        staysPage.openCalendar();
        staysPage.selectDates();
        staysPage.clickSearch();
    }

    @Test
    public void verifySearchWithoutDestinationError() {
        staysPage = new StaysPage(driver);
        staysPage.closeSignInPopup();

        // Click search directly without typing a destination
        staysPage.clickSearch();

        // Verify that the error message is displayed using explicit wait
        boolean isErrorVisible = staysPage.isErrorMessageDisplayed();
        System.out.println("Is error message visible? " + isErrorVisible);

        // Assertion to validate test success
        Assert.assertTrue(isErrorVisible, "Error message did not appear!");
    }
}
