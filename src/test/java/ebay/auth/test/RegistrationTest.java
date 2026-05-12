package ebay.auth.test;

import io.qameta.allure.Owner;
import kg.ebay.NewUserNameEmail;
import kg.ebay.NewUsers;
import kg.ebay.steps.RegistrationSteps;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import xiaomi.BaseTest;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

public class RegistrationTest extends BaseTest {
    RegistrationSteps registrationSteps = new RegistrationSteps();

    @Test
    @DisplayName("User should see signup form")
    @Owner("Aliya")
    @Tag("REGISTER")
    void shouldSeeSignupForm() {
        registrationSteps.openSignupPage();
        registrationSteps.verifySignupFormIsVisible();
    }

    @Test
    @DisplayName("User should proceed to account information after entering name and email")
    @Owner("Aliya")
    @Tag("REGISTER")
    void shouldNavigateToAccountInfo() {
        registrationSteps.openSignupPage();
        registrationSteps.fillFirstStep(NewUserNameEmail.ESMA);
        $x("//h2[text()='New User Signup!']").shouldBe(visible);
    }

    @Test
    @DisplayName("User should complete registration form")
    @Owner("Aliya")
    @Tag("REGISTER")
    void shouldVFillFullRegistrationForm() {
        registrationSteps.openSignupPage();
        registrationSteps.fillFirstStep(NewUserNameEmail.ESMA);
        registrationSteps.fillSecondStep(NewUsers.ESMA);
    }

    @Test
    @DisplayName("User enters incorrect creds")
    @Owner("Aliya")
    @Tag("LOGIN")
    void shouldShowErrorForInvalidLogin(){
        registrationSteps.openLoginPage();
        registrationSteps.fillLoginInfo("baisal@gmail.com","baisal");
    }
}
