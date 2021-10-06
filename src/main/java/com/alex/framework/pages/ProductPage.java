package com.alex.framework.pages;

import com.alex.framework.pages.blocks.HeaderNavBar;
import com.alex.framework.products.Product;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class ProductPage extends BasePage {

    @FindBy(xpath = "//h1[@class='product-card-top__title']")
    private WebElement titleProduct;

    @FindBy(xpath = "//div[@class='product-card-top__buy']//div[contains(@class, 'product-buy__price product')]")
    private WebElement priceProductElement;

    @FindBy(xpath = "//div[contains(@class, 'additional-sales-tabs__titles')]//div[contains(@class,'additional')]")
    private List<WebElement> listAdditionalSalesTabs;

    @FindBy(xpath = "//label[contains(@class,'ui-radio__item')]//span[contains(@class,'ui-radio__content')]")
    private List<WebElement> listWarranty;

    @FindBy(xpath = "//div[contains(@class, 'multicard product-card-top__multi')]/..//button[contains(text(), 'Купить')]")
    private WebElement buttonBuyProduct;

    private HeaderNavBar headerNavBar = new HeaderNavBar();

    @Step("Click on the additional option {salesTab}")
    public ProductPage clickWarranty(String salesTab) {
        setPriceProduct(priceProductElement, titleProduct);
        return (ProductPage) clickElement(salesTab, listAdditionalSalesTabs, pageManager.getProductPage());
    }

    @Step("Select additional warranty {typeOfWarranty} and click")
    public ProductPage selectAdditionalWarranty(String typeOfWarranty) {
        closeFrame(By.xpath("//div[contains(@class, 'base-modal undefined base-modal_full-on-mobile')]"),
                By.xpath("//i[contains(@class, 'base-modal__header-close-icon handler-active')]/..//div[contains(text(), 'Дополнительная')]"));
        return (ProductPage) clickElement(typeOfWarranty, listWarranty, pageManager.getProductPage());
    }

    @Step("Click button buy on the product page")
    public ProductPage clickButtonBuy() {
        Product.incrementAllCountProduct();
        waitUtilElementToBeClickable(buttonBuyProduct).click();
        setPriceProduct(priceProductElement, titleProduct);
        return pageManager.getProductPage();
    }

    public HeaderNavBar getHeaderNavBar() {
        return headerNavBar;
    }
}
