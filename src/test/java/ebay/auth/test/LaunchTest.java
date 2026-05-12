package ebay.auth.test;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import xiaomi.BaseTest;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverConditions.url;

public class LaunchTest extends BaseTest {

    @Test
    @DisplayName("Check if browser launches")
    @Owner("Aliya S")
    @Tag("LAUNCH")
    void launchBrowser() {
        Selenide.open("https://automationexercise.com/");
        $("img[alt='Website for automation practice']").shouldBe(visible);
    }
    @Test
    @DisplayName("Check if navigation works")
    @Owner("Aliya S")
    @Tag("LAUNCH")
    void shouldNavigateToHomePage() {
        Selenide.open("https://automationexercise.com/");
        webdriver().shouldHave(url("https://automationexercise.com/"));
        $("body").shouldBe(visible);
    }
}
