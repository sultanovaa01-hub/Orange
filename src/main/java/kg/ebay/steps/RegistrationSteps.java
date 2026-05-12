package kg.ebay.steps;

import io.qameta.allure.Step;
import kg.ebay.NewUserNameEmail;
import kg.ebay.NewUsers;
import kg.ebay.User;
import kg.ebay.pages.RegistrationPage;
import kg.ebay.pages.HomePageAutomation;

public class RegistrationSteps {

    HomePageAutomation homePageAutomation = new HomePageAutomation();
    RegistrationPage registrationPage = new RegistrationPage();

    @Step("Open signup page")
    public void openSignupPage() {
        homePageAutomation.openSignupPage();
        homePageAutomation.clickSignupLink();
    }
    @Step("Verify Signup Form is visible")
    public void verifySignupFormIsVisible() {
        registrationPage.shouldBeLoaded();
    }
    @Step("Fill first step with name and email")
    public void fillFirstStep(NewUserNameEmail user) {
        registrationPage.registerFirstPage(user.getName(),user.getEmail());
    }
    @Step("Verify account information page is visible")
    public void verifyAccountInformationVisible() {
        registrationPage.shouldSeeAccountInformation();
    }
    @Step("Fill full registration form")
    public void fillSecondStep(NewUsers user) {
        registrationPage.registerSecondPage(user.getTitle(), user.getPassword(), user.getDayOfBirth(),
                user.getMonthOfBirth(), user.getYearOfBirth(), user.getFirstName(), user.getLastName(),
                user.getCompany(), user.getAddress(), user.getCountry(), user.getState(),
                user.getCity(), user.getZipcode(), user.getMobileNumber());
    }
    @Step("Open login page")
    public void openLoginPage() {
        homePageAutomation.openLoginPage();
        homePageAutomation.clickSignupLink();
    }
    @Step("Fill login info")
    public void fillLoginInfo(String email,String password){
        registrationPage.shouldSeeLoginForm();
        registrationPage.enterEmailPassword(email,password);
        registrationPage.setErrorLoginMessage();
    }
}
