package kg.xiaomi.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class HomePageAutomation extends BasePage<HomePageAutomation>{

    SelenideElement navigationBar = $x("//ul[@class='nav navbar-nav']");
    SelenideElement signupLink = $x("//a[@href='/login']");
    SelenideElement loggedInLabel = $x("//a[contains(text(),'Logged in as')]");

    public HomePageAutomation clickSignupLink (){
        signupLink.shouldBe(visible,clickable).click();
        return this;
    }
    public void shouldBeLoggedIn() {
        loggedInLabel.shouldBe(visible);
    }

    @Override
    public HomePageAutomation waitForPageToBeLoaded() {
        navigationBar.shouldBe(visible);
        return this;
    }
}
