
import org.testng.Assert;
import org.testng.annotations.Test;

public class CarRentalTest extends BaseTest {
    @Test(priority = 1)
    public void openCarRentalPage() throws InterruptedException {

        CarRentalPage carPage = new CarRentalPage(driver);

        carPage.openCarsPage();

        Thread.sleep(3000);

        Assert.assertTrue(driver.getCurrentUrl().contains("cars"));
    }

    @Test(priority = 2)
    public void searchCarRentalInCairo() throws InterruptedException {

        CarRentalPage carPage = new CarRentalPage(driver);

        carPage.openCarsPage();
        Thread.sleep(2000);

        carPage.enterPickUpLocation("Cairo");
        Thread.sleep(2000);

        carPage.clickSearch();
        Thread.sleep(5000);

        Assert.assertTrue(driver.getCurrentUrl().contains("cars"));
    }

    @Test(priority = 3)
    public void searchCarRentalInAlexandria() throws InterruptedException {

        CarRentalPage carPage = new CarRentalPage(driver);

        carPage.openCarsPage();
        Thread.sleep(2000);

        carPage.enterPickUpLocation("Alexandria");
        Thread.sleep(2000);

        carPage.clickSearch();
        Thread.sleep(5000);

        Assert.assertTrue(driver.getCurrentUrl().contains("cars"));
    }

    @Test(priority = 4)
    public void searchCarRentalInGiza() throws InterruptedException {

        CarRentalPage carPage = new CarRentalPage(driver);

        carPage.openCarsPage();
        Thread.sleep(2000);

        carPage.enterPickUpLocation("Giza");
        Thread.sleep(2000);

        carPage.clickSearch();
        Thread.sleep(5000);

        Assert.assertTrue(driver.getCurrentUrl().contains("cars"));
    }

    @Test(priority = 5)
    public void validateCarRentalTitle() throws InterruptedException {

        CarRentalPage carPage = new CarRentalPage(driver);

        carPage.openCarsPage();
        Thread.sleep(3000);

        Assert.assertFalse(driver.getTitle().isEmpty());
    }

    @Test(priority = 6)
    public void validateCurrentURL() throws InterruptedException {

        CarRentalPage carPage = new CarRentalPage(driver);

        carPage.openCarsPage();
        Thread.sleep(3000);

        Assert.assertTrue(driver.getCurrentUrl().contains("booking"));
    }

    @Test(priority = 7)
    public void printCarRentalInformation() throws InterruptedException {

        CarRentalPage carPage = new CarRentalPage(driver);

        carPage.openCarsPage();
        Thread.sleep(3000);

        System.out.println("Title : " + driver.getTitle());
        System.out.println("URL : " + driver.getCurrentUrl());

        Assert.assertNotNull(driver.getTitle());
    }
}