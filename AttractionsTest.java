import org.testng.Assert;
import org.testng.annotations.Test;

public class AttractionsTest extends BaseTest {
    @Test
    public void testSearchAttractions() {
        AttractionsPage attractionsPage = new AttractionsPage(driver);

        attractionsPage.goToAttractionsPage();
        attractionsPage.searchAttraction("Paris");

        String title = attractionsPage.getAttractionTitle();
        Assert.assertFalse(title.isEmpty(),
                "Attraction title should not be empty after searching for Paris.");
    }
    @Test
    public void testViewAttractionDetails() {
        AttractionsPage attractionsPage = new AttractionsPage(driver);

        attractionsPage.goToAttractionsPage();
        attractionsPage.searchAttraction("Paris");
        attractionsPage.clickFirstAttractionResult();

        Assert.assertTrue(attractionsPage.isAttractionDetailNameDisplayed(),
                "Attraction detail name should be visible on the detail page.");
        Assert.assertTrue(attractionsPage.isAttractionDescriptionDisplayed(),
                "Attraction description should be visible on the detail page.");

        String name = attractionsPage.getAttractionDetailName();
        Assert.assertFalse(name.isEmpty(),
                "Attraction name on detail page should not be empty.");
    }



    @Test
    public void testApplyLocationFilter() {
        AttractionsPage attractionsPage = new AttractionsPage(driver);

        attractionsPage.goToAttractionsPage();
        attractionsPage.searchAttraction("Paris");
        attractionsPage.applyLocationFilter();

        String title = attractionsPage.getAttractionTitle();
        Assert.assertFalse(title.isEmpty(),
                "Attraction title should not be empty after applying location filter.");
    }

    @Test
    public void testApplyRatingFilter() {
        AttractionsPage attractionsPage = new AttractionsPage(driver);

        attractionsPage.goToAttractionsPage();
        attractionsPage.searchAttraction("Paris");
        attractionsPage.applyRatingFilter();

        String rating = attractionsPage.getAttractionRating();
        Assert.assertFalse(rating.isEmpty(),
                "Attraction rating should be visible after applying rating filter.");
    }



    @Test
    public void testBookAttractionTickets() {
        AttractionsPage attractionsPage = new AttractionsPage(driver);

        attractionsPage.goToAttractionsPage();
        attractionsPage.searchAttraction("Paris");
        attractionsPage.clickFirstAttractionResult();
        attractionsPage.bookTicket("2");

        // After confirming, the URL or page should reflect a booking/checkout step.
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(
                currentUrl.contains("booking") || currentUrl.contains("checkout")
                        || currentUrl.contains("ticket") || currentUrl.contains("reserve"),
                "URL should contain a booking/checkout indicator after reserving tickets. Actual URL: " + currentUrl
        );
    }
}