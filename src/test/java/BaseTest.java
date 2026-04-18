import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import static com.codeborne.selenide.Configuration.browser;

public class BaseTest {

    @BeforeAll
    public static void setup(){
        browser = "chrome";
    }

}
