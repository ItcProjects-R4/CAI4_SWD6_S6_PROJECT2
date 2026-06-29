import Gmail.OTPReader;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.openqa.selenium.By;

import java.time.Duration;

public class LoginTest extends BaseTest {

    @Test
    public void LoginWithEmailTest() throws Exception{

        driver.get("https://www.booking.com/signin");

        long testStartTime = System.currentTimeMillis();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.CloseSignInPopup();
        loginPage.clickSignIn();
        loginPage.LoginEmail("Booking.Test.Automation@gmail.com");

        System.out.println("Email submitted successfully");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("code_0")));

        String otp = null;

        for(int i = 0; i < 5; i++){

            otp = OTPReader.getOTP(testStartTime);

            if(otp != null){
                break;
            }

            Thread.sleep(5000);
        }

        if (otp == null) {
            throw new RuntimeException("OTP not found in Gmail inbox");
        }

        System.out.println("OTP is: " + otp);

        loginPage.EnterOTP(otp);

        Assert.assertTrue(driver.getCurrentUrl().contains("booking.com"),
                "Login failed or did not redirect correctly");
    }

    @Test
    public void GoogleLoginTest(){
        driver.get("https://www.booking.com/signin");
        LoginPage loginPage=new LoginPage(driver);
        loginPage.CloseSignInPopup();
        loginPage.clickSignIn();
        loginPage.clickGoogleLogin();
    }

    @Test
    public void AppleLoginTest(){
        driver.get("https://www.booking.com/signin");
        LoginPage loginPage=new LoginPage(driver);
        loginPage.CloseSignInPopup();
        loginPage.clickSignIn();
        loginPage.clickAppleLogin();
    }

    @Test
    public void FacebookLoginTest(){
        driver.get("https://www.booking.com/signin");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.CloseSignInPopup();
        loginPage.clickSignIn();
        loginPage.clickFacebookLogin();

    }

    @Test
    public void RecoverAccountTest(){
        driver.get("https://www.booking.com/signin");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.CloseSignInPopup();
        loginPage.clickSignIn();
        loginPage.clickRecoverAccount();

    }


}
