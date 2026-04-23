package xiaomi;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Selenide.*;

public class AmazonTest extends BaseTest {
    @Test
    void testAmazon() {
        open("https://www.amazon.com/");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        $(By.id("twotabsearchtextbox")).sendKeys("Iphone" + Keys.RETURN);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        ElementsCollection brands = $$("#brandsRefinements ul li a");
        //stale element reference -> the html refreshes after clicking first element

        for (SelenideElement brand : brands) {
            brand.click();
        }
    }
}

