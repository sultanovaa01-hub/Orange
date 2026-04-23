package xiaomi;

import com.codeborne.selenide.*;
import org.junit.jupiter.api.Test;

public class DemoTest extends BaseTest {
//    @Test
//    void demo (){
//        Selenide.open(ConfugurationManager.getBaseConfig().baseUrl());
//    }

    @Test
    void searchBtnTest (){
        homePage.waitForPageToBeLoaded();
        homePage.goToSearchPage();

    }
    @Test
    void categoryTest () {
        homePage.selectSubcategory("Запчасти","Шины");
    }
    @Test
    void lexusTest1 (){
        homePage.goToSearchPage();
        searchPage.selectGen("Lexus","LS", "Lexus LS V Рестайлинг (2021-2026)");

    }
    @Test
    void listCars (){
        homePage.goToSearchPage();
        ElementsCollection cars = searchPage.getCars(
                "Lexus","LS", "Lexus LS V Рестайлинг (2021-2026)");
        cars.forEach(car-> System.out.println(car.getText()));
        cars.shouldBe(CollectionCondition.sizeGreaterThan(0));
    }
}
