package kg.ebay.pages;

public abstract class BasePage<T extends BasePage>{

    public abstract T waitForPageToBeLoaded (); // T - self referencing generic type
    }
