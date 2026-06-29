import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage extends BasePage {
    @FindBy(xpath = "//a[@data-testid='header-sign-in-button']")
    WebElement SignInButton;

    @FindBy(name="username")
    WebElement EmailField;

    @FindBy(xpath = "//a[contains(@class,'social-button-google')]")
    WebElement GoogleAccount;

    @FindBy(xpath = "//a[contains(@class,'social-button-apple')]")
    WebElement AppleAccount;

    @FindBy(xpath = "//a[contains(@class,'social-button-facebook')]")
    WebElement FacebookAccount;

    @FindBy(xpath = "//button[@type='submit']")
    WebElement SubmitButton;

    @FindBy(xpath = "//a[contains(@href,'/sign-in/recovery')]")
    WebElement RecoverAccount;

    @FindBy(xpath = "//button[@aria-label='إخفاء المعلومات حول تسجيل الدخول.']")
     WebElement closePopup;

    @FindBy(name="code_0")
    WebElement OTPFirstField;

    @FindBy(xpath = "//button[@type='submit']")
    WebElement VerifyEmailButton;


    public LoginPage(WebDriver driver){
        super(driver);
    }
    public void clickSignIn(){
        click(SignInButton);
    }
    public void EnterEmail(String Email){
        type(EmailField,Email);
    }
    public void clickSubmit(){
        click(SubmitButton);
    }
    public void clickGoogleLogin(){
        click(GoogleAccount);
    }
    public void clickAppleLogin(){
        click(AppleAccount);
    }
    public void clickFacebookLogin(){
        click(FacebookAccount);
    }
    public void clickRecoverAccount(){
        click(RecoverAccount);
    }
    public void LoginEmail(String email){
        EnterEmail(email);
        click(SubmitButton);
    }
    public void CloseSignInPopup() {
        try {
            click(closePopup);
        } catch (Exception e) {
            System.out.println("Popup not displayed");
        }
    }
    public void EnterOTP(String otp) throws Exception {

        if (otp == null) {
            throw new RuntimeException("OTP not found in email");
        }
        // Normalize: شيل أي مسافات وحوّل لـ Uppercase
        otp = otp.replace(" ", "").toUpperCase();
        System.out.println("Normalized OTP: " + otp);

        // اطبع كل الـ inputs اللي موجودة في الصفحة علشان نتأكد إن الخانات ظهرت
        driver.findElements(By.tagName("input"))
                .forEach(el -> System.out.println("Input name: " + el.getAttribute("name")));

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        for(int i = 0; i < 6; i++){
            WebElement field = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.name("code_" + i))
            );
            field.click();
            field.sendKeys(String.valueOf(otp.charAt(i)));
        }
        click(VerifyEmailButton);
    }

    public void waitForEmailField(){
        wait.until(ExpectedConditions.visibilityOf(EmailField));
    }



}
