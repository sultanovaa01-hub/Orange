package kg.ebay.pages;

import com.codeborne.selenide.SelenideElement;
import kg.ebay.User;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class RegistrationPage {

    SelenideElement loginForm = $x("//div[@class='login-form']");
    SelenideElement emailLogin = $("input[data-qa='login-email']");
    SelenideElement passwordLogin = $("input[data-qa='login-password']");
    SelenideElement errorLoginMessage = $x("//p[text()='Your email or password is incorrect!']");
    SelenideElement loginBtn = $("button[data-qa='login-button']");
    SelenideElement nameInput = $("input[data-qa='signup-name']");
    SelenideElement emailInput = $("input[data-qa='signup-email']");
    SelenideElement signupBtn = $("button[data-qa='signup-button']");
    SelenideElement passwordField = $("#password");
    SelenideElement day = $("select[data-qa='days']");
    SelenideElement month = $("select[data-qa='months']");
    SelenideElement year = $("select[data-qa='years']");
    SelenideElement newsletter = $("input[id='newsletter']");
    SelenideElement firstName = $("input[id='first_name']");
    SelenideElement lastName = $("input[id='last_name']");
    SelenideElement company = $("input[id='company']");
    SelenideElement address = $("input[data-qa='address']");
    SelenideElement country = $("select[data-qa='country']");
    SelenideElement state = $("input[id='state']");
    SelenideElement city = $("input[id='city']");
    SelenideElement zipcode = $("input[id='zipcode']");
    SelenideElement mobile = $("input[id='mobile_number']");
    SelenideElement create_acc = $("button[data-qa='create-account']");

    public void shouldBeLoaded (){
    $("h2").shouldHave(text("Login to your account")).shouldBe(visible);
    }

    public void shouldSeeAccountInformation(){
        $x("//b[text()='Enter Account Information']").shouldBe(visible);
    }
    public void registerFirstPage (String name, String email) {
        nameInput.shouldBe(visible).sendKeys(name + Keys.RETURN);
        emailInput.shouldBe(visible).sendKeys(email);
        signupBtn.shouldBe(visible).click();
    }

    public void registerSecondPage (String title,String password, int dayInput,
                                      String monthInput, int yearInput, String firstNameInput, String lastNameInput,
                                      String companyInput, String addressInput, String countryInput,
                                      String stateInput, String cityInput, int zipcodeInput, int mobileInput){

        $("input[value='"+ title+"']").shouldBe(visible).click();
        passwordField.shouldBe(visible).sendKeys(password);
        day.shouldBe(visible).selectOption(dayInput);
        month.shouldBe(visible).selectOption(monthInput);
        year.shouldBe(visible).selectOption(String.valueOf(yearInput));
        newsletter.shouldBe(visible).click();
        firstName.shouldBe(visible).sendKeys(firstNameInput);
        lastName.shouldBe(visible).sendKeys(lastNameInput);
        company.shouldBe(visible).sendKeys(companyInput);
        address.shouldBe(visible).sendKeys(addressInput);
        country.shouldBe(visible).selectOption(countryInput);
        state.shouldBe(visible).sendKeys(stateInput);
        city.shouldBe(visible).sendKeys(cityInput);
        zipcode.shouldBe(visible).sendKeys(String.valueOf(zipcodeInput));
        mobile.shouldBe(visible).sendKeys(String.valueOf(mobileInput));
        create_acc.shouldBe(visible).click();
    }

    public  void shouldSeeLoginForm(){
        loginForm.shouldBe(visible);
    }
    public void enterEmailPassword (String email,String password){
        emailLogin.shouldBe(visible).sendKeys(email);
        passwordLogin.shouldBe(visible).sendKeys(password);
        loginBtn.shouldBe(visible).click();
    }
    public void setErrorLoginMessage (){
        errorLoginMessage.should(appear);
    }
}
