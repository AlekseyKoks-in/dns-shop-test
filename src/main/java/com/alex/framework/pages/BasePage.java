package com.alex.framework.pages;

import com.alex.framework.managers.DriverManager;
import com.alex.framework.managers.PageManager;
import com.alex.framework.products.Product;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Locale;

public class BasePage {

    protected final DriverManager driverManager = DriverManager.getInstance();
    protected Actions action = new Actions(driverManager.getDriver());
    protected JavascriptExecutor js = (JavascriptExecutor) driverManager.getDriver();
    protected WebDriverWait wait = new WebDriverWait(driverManager.getDriver(), 10, 1000);
    protected PageManager pageManager = PageManager.getInstance();


    public BasePage() {
        PageFactory.initElements(driverManager.getDriver(), this);
    }

    protected WebElement waitUtilElementToBeClickable(WebElement element) {
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    protected WebElement waitUtilElementToBeVisible(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void setPriceProduct(WebElement priceProductElement, WebElement titleElement) {
        String priceProductString = priceString(waitUtilElementToBeVisible(priceProductElement).getText().replaceAll(" ", ""));
        Double priceProductDouble = Double.parseDouble(priceProductString);
        String titleString = titleElement.getText().toLowerCase(Locale.ROOT);
        for (Product product : Product.getListProducts()) {
            if (titleString.contains(product.getProductDescription())) {
                product.setPrice(priceProductDouble);
            }
        }
    }

    public String priceString(String string) {
        StringBuilder priceString = new StringBuilder();
        for (Character c : string.toCharArray()) {
            if (c.equals('₽')) {
                break;
            }
            priceString.append(c);
        }
        return String.valueOf(priceString);
    }

    public BasePage clickElement(Object descriptionProduct, List<WebElement> listWebElement, BasePage page) {
        String elementText;
        for (WebElement element : listWebElement) {
            elementText = element.getText().toLowerCase(Locale.ROOT);
            if (elementText.contains(String.valueOf(descriptionProduct).toLowerCase(Locale.ROOT))) {
                waitUtilElementToBeClickable(waitUtilElementToBeVisible(element)).click();
                return page;
            }
        }
        return page;
    }

    protected void closeFrame(By byFrame, By byButtonCloseFrame) {
        if (checkPresentElement(byFrame) != null) {
            WebElement frame = wait.until(ExpectedConditions.presenceOfElementLocated(byFrame));
            driverManager.getDriver().switchTo().frame(frame);
            WebElement buttonFrameStockClose = driverManager.getDriver().findElement(byButtonCloseFrame);
            action.moveToElement(buttonFrameStockClose).click().build().perform();
            driverManager.getDriver().switchTo().defaultContent();
        }
    }

    protected WebElement checkPresentElement(By byFrame) {
        wait.withTimeout(Duration.ofSeconds(1)).pollingEvery(Duration.ofMillis(100));
        WebElement element = null;

        try {
            element = wait.until(ExpectedConditions.presenceOfElementLocated(byFrame));
        } catch (TimeoutException ignore) {
        }

        return element;
    }

}
