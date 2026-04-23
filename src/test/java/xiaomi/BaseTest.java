package xiaomi;

import com.codeborne.selenide.Selenide;
import kg.ebay.pages.RegistrationPage;
import kg.xiaomi.pages.HomePage;
import kg.xiaomi.pages.HomePageAutomation;
import kg.xiaomi.pages.SearchPage;
import kg.xiaomi.utils.file.ConfugurationManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import static com.codeborne.selenide.Configuration.browser;
import static com.codeborne.selenide.Selenide.open;

public class BaseTest {

    public static HomePage homePage;
    public static SearchPage searchPage;
    public static RegistrationPage registrationPage;
    //public static HomePageAutomation homePageAutomation;

    @BeforeAll
    public static void setup() {
        browser = "chrome";

        homePage = new HomePage();
        searchPage = new SearchPage();
        open(ConfugurationManager.getBaseConfig().baseUrl());
        //open("https://automationexercise.com/login");

        registrationPage = new RegistrationPage();
       // homePageAutomation = new HomePageAutomation();

    }

    @AfterAll
    public static void tearDown() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
