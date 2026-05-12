package kg.ebay.pages;

import com.codeborne.selenide.Selenide;
import kg.xiaomi.utils.file.ConfugurationManager;

public abstract class BasePage<T extends BasePage<T>> {

    protected String baseUrl = ConfugurationManager.getBaseConfig().baseUrl();

    public T open(String path) {
        Selenide.open(baseUrl + path);
        return (T) this;
    }

    public T openHomePage() {
        return open("");
    }

    public T openSignupPage() {
        return open("/signup");
    }

    public T openLoginPage() {
        return open("/login");
    }

    public abstract T waitForPageToBeLoaded();
}