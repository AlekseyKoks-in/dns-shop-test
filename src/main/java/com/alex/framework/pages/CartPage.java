package com.alex.framework.pages;

import com.alex.framework.products.Product;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class CartPage extends BasePage {

    @FindBy(xpath = "//span[@class='base-ui-radio-button__icon base-ui-radio-button__icon_checked']")
    private WebElement checkBoxAdditionalWarranty;

    @FindBy(xpath = "//div[@class='cart-items__product']")
    private List<WebElement> products;

    @FindBy(xpath = "//div[@class='total-amount__label']//div[@class='price']//div[@class='price__block price__block_main']//span[@class='price__current']")
    private WebElement totalSumInCartElement;

    @FindBy(xpath = "//div[contains(@class, 'group-tabs-menu')]//span[contains(text(),'Вернуть')]")
    private WebElement restoreLastRemoved;


    @Step("Check additional warranty {warranty}")
    public CartPage checkAdditionalWarranty(String warranty) {
        Assertions.assertEquals(warranty, checkBoxAdditionalWarranty.getText());
        return pageManager.getCartPage();
    }

    public double getSumCart() {
        String totalSumInCartString = waitUtilElementToBeVisible(totalSumInCartElement).getText().replaceAll("\\W", "");
        Double totalSumInCartDouble = Double.parseDouble(totalSumInCartString);
        return totalSumInCartDouble;
    }

    @Step("Check the sum cart on the cart page")
    public CartPage checkSumCart() {
        Assertions.assertEquals(Product.getSum(), getSumCart(), "Sum cart is not equals sum bought products");
        return pageManager.getCartPage();
    }

    @Step("Delete product from cart by name {productName}")
    public CartPage deleteProductFromCart(String productName) {
        Product.decrementAllCountProduct();
        String productString;
        for (WebElement product : products) {
            productString = product.getText().toLowerCase();
            if (productString.contains(productName.toLowerCase())) {
                waitUtilElementToBeClickable(product.findElement(By.xpath(".//button[contains(text(), 'Удалить')]"))).click();
                Product.removeProductFromCart(productName);
                wait.until(ExpectedConditions.textToBe(By.xpath("//span[contains(@class, 'cart-link__badge')]"), Product.getAllCountProduct() + ""));
                return pageManager.getCartPage();
            }
        }
        Assertions.fail("The product is not deleted to the card by name");
        return pageManager.getCartPage();
    }

    @Step("Check delete from cart by name {nameProduct}")
    public CartPage checkDeleteFromCart(String nameProduct) {
        Assertions.assertFalse(products.contains(nameProduct));
        Assertions.assertEquals((Product.getPriceProductByName("iphone")), getSumCart());
        return pageManager.getCartPage();
    }

    @Step("Add a product by pressing the plus button by name {productName}")
    public CartPage addProductPlus(String productName) {
        String productString;
        for (WebElement product : products) {
            productString = product.getText().toLowerCase();
            if (productString.contains(productName.toLowerCase())) {
                action.moveToElement(waitUtilElementToBeClickable(product.findElement(
                        By.xpath(".//button[contains(@class, 'count-buttons__button count-buttons__button_plus')]")))).click();
                Product.incrementAllCountProduct();
                wait.until(ExpectedConditions.textToBe(By.xpath("//span[contains(@class, 'cart-link__badge')]"), Product.getAllCountProduct() + ""));
                return pageManager.getCartPage();
            }
        }
        Assertions.fail("The product is not added to the card by name");
        return pageManager.getCartPage();
    }

    @Step("Click restore product")
    public CartPage clickRestoreProduct() {
        waitUtilElementToBeClickable(waitUtilElementToBeVisible(restoreLastRemoved)).click();
        Product.incrementAllCountProduct();
        wait.until(ExpectedConditions.textToBe(By.xpath("//span[contains(@class, 'cart-link__badge')]"), Product.getAllCountProduct() + ""));
        return pageManager.getCartPage();
    }


}
