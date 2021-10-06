package com.alex.framework.pages;

import com.alex.framework.pages.blocks.HeaderNavBar;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MainPage extends BasePage {

    @FindBy(xpath = "//div[@class = 'dropdown-city']")
    private WebElement location;

    @FindBy(xpath = "//a[@class = 'btn btn-additional']")
    private WebElement buttonAcceptLocation;

    private HeaderNavBar headerNavBar = new HeaderNavBar();

    public HeaderNavBar getHeaderNavBar() {
        return headerNavBar;
    }

}
