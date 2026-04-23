package kg.xiaomi.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

public class HomePage extends BasePage<HomePage> {
    SelenideElement mashinaKgSearchBtn = $x("//a[@class='menu-items']");

    @Override
    public HomePage waitForPageToBeLoaded() {
        mashinaKgSearchBtn.shouldBe(visible);
        return Selenide.page(this);
    }

    public SearchPage goToSearchPage (){
        mashinaKgSearchBtn.shouldBe(visible);
        mashinaKgSearchBtn.click();
        return Selenide.page(SearchPage.class);
    }

    public SelenideElement selectCategory(String category) {
        SelenideElement passengerCarCatSearch = $x
                ("//a[contains(@class,'menu-items') and normalize-space()='" + category + "']")
                .shouldBe(Condition.visible);

        passengerCarCatSearch.hover();
        return passengerCarCatSearch;
    }

    public void selectSubcategory(String category, String subcategory) {
        SelenideElement categoryElement = selectCategory(category);

        categoryElement.parent()
                .$x(".//ul//a[contains(.,'" + subcategory + "')]")
                .shouldBe(Condition.visible)
                .click();
    }
}

