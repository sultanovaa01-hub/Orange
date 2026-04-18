import com.codeborne.selenide.Selenide;
import kg.xiaomi.utils.file.ConfugurationManager;
import org.junit.jupiter.api.Test;

public class DemoTest extends BaseTest{
    @Test
    void demo (){
        Selenide.open(ConfugurationManager.getBaseConfig().baseUrl());
    }
}
