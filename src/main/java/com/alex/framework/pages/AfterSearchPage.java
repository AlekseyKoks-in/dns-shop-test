package com.alex.framework.pages;

import com.alex.framework.pages.blocks.HeaderNavBar;
import com.alex.framework.products.Product;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.Locale;

public class AfterSearchPage extends BasePage {

    @FindBy(xpath = "//div[@id='search-results']//div[contains(@class, 'catalog-product ui-button-widget')]//a[contains(@class, 'catalog-product__name ui-link')]")
    private List<WebElement> listFoundDescriptionElements;

    @FindBy(xpath = "//div[@id='search-results']//div[contains(@class, 'catalog-product ui-button-widget')]")
    private List<WebElement> listFoundElements;

    @FindBy(xpath = "//span[contains(text(),'Игра Cyberpunk 2077 (PS4)')]/../..//button[contains(@class, 'button-ui buy')]")
    private WebElement buttonBuyCyberpunk;

    private HeaderNavBar headerNavBar = new HeaderNavBar();

    @Step("Click on the found product by its description")
    public ProductPage clickOnTheFoundElement(Product product) {
        return (ProductPage) clickElement(product.getProductDescription(), listFoundDescriptionElements, pageManager.getProductPage());
    }

    public void setPriceProduct(Product product) {
        int priceProductInteger = 0;
        for (WebElement foundElement : listFoundElements) {
            String textElement = foundElement.getText().toLowerCase();
            if (textElement.contains(product.getProductDescription().toLowerCase())) {
                WebElement priceProduct = waitUtilElementToBeVisible(foundElement.findElement(By.xpath(".//div[contains(@class, 'product-buy product')]")));
                String priceProductString = priceString(priceProduct.getText());
                priceProductInteger = Integer.parseInt(priceProductString.replaceAll(" ", ""));
            }
        }
        product.setPrice(priceProductInteger);
    }

    @Step("Click button buy on the page after search")
    public AfterSearchPage clickBuyProduct(Product product) {
        setPriceProduct(product);
        Product.incrementAllCountProduct();
        String textElement;
        for (WebElement foundElement : listFoundElements) {
            textElement = foundElement.getText().toLowerCase(Locale.ROOT);
            if (textElement.contains(product.getProductDescription().toLowerCase(Locale.ROOT))) {
                waitUtilElementToBeClickable(buttonBuyCyberpunk).click();
                wait.until(ExpectedConditions.textToBe(By.xpath("//span[contains(@class, 'cart-link__badge')]"), Product.getAllCountProduct() + ""));
                return pageManager.getAfterSearchPage();
            }
        }
        Assertions.fail("Product with name " + product.getProductDescription() + " is not found");
        return pageManager.getAfterSearchPage();
    }

    public HeaderNavBar getHeaderNavBar() {
        return headerNavBar;
    }

}
