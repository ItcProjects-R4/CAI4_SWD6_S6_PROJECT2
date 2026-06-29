import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {
    WebDriver driver;
    @BeforeMethod
    public void setUp(){
        WebDriverManager.edgedriver().setup();
        EdgeOptions options = new EdgeOptions();
        options.addArguments("user-data-dir=C:\\Users\\jana\\EdgeProfile");

        driver=new EdgeDriver();
        driver.manage().window().maximize();
        driver.get("https://Booking.com");
    }
    @AfterMethod
    public void tearDown(){
//        driver.quit();
    }
}
