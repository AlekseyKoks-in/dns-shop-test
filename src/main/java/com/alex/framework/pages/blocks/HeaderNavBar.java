package com.alex.framework.pages.blocks;

import com.alex.framework.pages.AfterSearchPage;
import com.alex.framework.pages.BasePage;
import com.alex.framework.pages.CartPage;
import com.alex.framework.products.Product;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HeaderNavBar extends BasePage {

    @FindBy(xpath = "//nav[@id='header-search']//input[contains(@class, 'ui-input-search')]")
    private WebElement fieldSearch;

    @FindBy(xpath = "//span[@class='cart-link__lbl']")
    private WebElement cartElement;

    @Step("Search product {nameSearch}")
    public AfterSearchPage searchProduct(String nameSearch) {
        waitUtilElementToBeVisible(fieldSearch).sendKeys(nameSearch);
        fieldSearch.sendKeys(Keys.ENTER);
        return pageManager.getAfterSearchPage();
    }

    public Double getSumCart() {
        WebElement cart = waitUtilElementToBeVisible(cartElement);
        String cartString = priceString(cart.getText().replaceAll(" ", ""));
        Double cartDouble = Double.parseDouble(cartString);
        return cartDouble;
    }

    @Step("Check the cart sum")
    public AfterSearchPage checkSumCart() {
        Assertions.assertEquals(Product.getSum(), getSumCart(), "The sum of the cart did not become equal to the sum of products");
        return pageManager.getAfterSearchPage();
    }

    @Step("Click on the cart in navigation bar")
    public CartPage clickCart() {
        waitUtilElementToBeClickable(cartElement).click();
        return pageManager.getCartPage();
    }

}
