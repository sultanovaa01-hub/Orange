package xiaomi;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import kg.ebay.pages.RegistrationPage;
import kg.xiaomi.pages.HomePage;
import kg.xiaomi.pages.SearchPage;
import kg.xiaomi.utils.file.ConfugurationManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import static com.codeborne.selenide.Configuration.browser;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.logevents.SelenideLogger.addListener;

public class BaseTest {

    public HomePage homePage;
    public SearchPage searchPage;
    public RegistrationPage registrationPage;

    @BeforeEach
    void setup1() {

        SelenideLogger.removeListener("AllureSelenide");

        addListener("AllureSelenide",
                new AllureSelenide()
                        .screenshots(true)
                        .savePageSource(true)
        );

        //open(ConfugurationManager.getBaseConfig().baseUrl());

        homePage = new HomePage();
        searchPage = new SearchPage();
        registrationPage = new RegistrationPage();
    }

    @AfterAll
    static void tearDown() {
        // Selenide.closeWebDriver();
    }
}