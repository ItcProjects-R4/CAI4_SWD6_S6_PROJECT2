import org.testng.Assert;
import org.testng.annotations.Test;

public class ChangeLanguageTest extends BaseTest {

    @Test
    public void testChangeLanguage() {
        HomePage homePage = new HomePage(driver);
        homePage.CloseSignInPopup();
        homePage.acceptCookiesIfPresent();

        homePage.openLanguageMenu();
        homePage.selectFrench();

        String langAfterFrench = homePage.getHtmlLangAttribute();
        Assert.assertTrue(langAfterFrench != null && langAfterFrench.toLowerCase().startsWith("fr"),
                "Page language did not change to French. Found: " + langAfterFrench);

        homePage.openLanguageMenu();
        homePage.selectEnglish();

        String langAfterEnglish = homePage.getHtmlLangAttribute();
        Assert.assertTrue(langAfterEnglish != null && langAfterEnglish.toLowerCase().startsWith("en"),
                "Page language did not revert to English. Found: " + langAfterEnglish);
    }
}