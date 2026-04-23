package ebay.auth.test;

import kg.ebay.User;
import kg.ebay.pages.RegistrationPage;
import kg.xiaomi.pages.HomePageAutomation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import xiaomi.BaseTest;

import static com.codeborne.selenide.Selenide.open;

public class RegistrationTest extends BaseTest {

    @Test
    void login(){
        open("https://automationexercise.com");
        HomePageAutomation homePageAutomation = new HomePageAutomation();
        homePageAutomation.clickSignupLink();
        registrationPage.login(User.USER_AIMAN.getEmail(),User.USER_AIMAN.getPassword());
        homePageAutomation.shouldBeLoggedIn();
    }
    @Test
    void registerTest() {

    open("https://automationexercise.com/login");

    registrationPage.register("Aliya", "aliyaaiman@gmail.com", "Mrs", "12345",25, "October",
            2010,"Aliya","Sultanova","IT","Voroshilov","United States",
            "Cali","San Fransisco",12345,213456);
}
}
