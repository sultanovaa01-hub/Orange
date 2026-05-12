package kg.ebay.pages;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$x;

public class HomePageAutomation extends BasePage<HomePageAutomation> {

    private final SelenideElement navigationBar = $x("//ul[@class='nav navbar-nav']");
    private final SelenideElement signupLink = $x("//a[@href='/login']");
    private final SelenideElement loggedInLabel = $x("//a[contains(text(),'Logged in as')]");

    public RegistrationPage clickSignupLink() {
        signupLink.shouldBe(visible, clickable).click();
        return new RegistrationPage();
    }

    public HomePageAutomation shouldBeLoggedIn() {
        loggedInLabel.shouldBe(visible);
        return this;
    }

    @Override
    public HomePageAutomation waitForPageToBeLoaded() {
        navigationBar.shouldBe(visible);
        return this;
    }
}