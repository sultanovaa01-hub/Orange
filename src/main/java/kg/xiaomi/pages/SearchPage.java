package kg.xiaomi.pages;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Selenide.*;

public class SearchPage extends BasePage <SearchPage>{

    public SelenideElement selectMark (String value){
        SelenideElement markSelect = $x("//button[@title='Марка: любая']").shouldBe(Condition.visible);
        markSelect.click();

        $x("(//div[@class='bs-searchbox']/descendant::input)[1]")
                    .shouldBe(Condition.visible)
                .setValue(value).pressEnter();

            return markSelect;
    }

    public SelenideElement selectModel (String markValue, String modelValue){
        selectMark(markValue);
        SelenideElement modelSelect = $x("//button[@title='Модель: любая']").shouldBe(Condition.visible);
        modelSelect.click();


            $x("(//div[@class='bs-searchbox']/descendant::input)[2]")
                    .shouldBe(Condition.visible)
                    .setValue(modelValue).pressEnter();

        $x("(//div[@class='bs-searchbox']/descendant::input)[2]")
                .shouldBe(Condition.visible).click();

            return modelSelect;
        }

    public SelenideElement selectGen (String markValue, String modelValue,String generationValue){
        selectModel(markValue,modelValue);
        SelenideElement dropdownGen = $x("//button[@title='Поколение: любое']");
        dropdownGen.click();

        SelenideElement genSelect = $x("//option[@title='"+generationValue+"']")
                .shouldBe(Condition.visible);
        genSelect.click();

        //dropdownGen.click();

        $(By.id("search-submit")).click();

        return genSelect;
    }
    public ElementsCollection getCars(String markValue, String modelValue, String generationValue) {

        selectGen(markValue, modelValue, generationValue);

        return $$("div.table-view-list > div")
                .shouldBe(CollectionCondition.sizeGreaterThan(0));
    }
    @Override
    public SearchPage waitForPageToBeLoaded() {
        return null;
    }
}
