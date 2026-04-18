package kg.xiaomi.pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

public class HomePage extends BasePage<HomePage>{
    SelenideElement orangLogo = $x("//img[@alt='client brand banner']");
    SelenideElement adminBtn = $x("//span[text()='Admin']");

    @Override
    public HomePage waitForPageToBeLoaded() {
        orangLogo.shouldBe(visible);
        return Selenide.page(this);
    }
    public AdminPage goToAboutPage (){
       adminBtn.shouldBe(visible).click();
        return Selenide.page(AboutPage.class);
    }
}
