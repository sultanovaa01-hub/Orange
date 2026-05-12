package kg.xiaomi.pages;

import kg.ebay.pages.HomePageAutomation;

public abstract class BasePage <T extends BasePage>{


    public abstract T waitForPageToBeLoaded (); // T - self referencing generic type
    }
