package com.alex.tests;

import com.alex.framework.data.Warranty;
import com.alex.framework.products.Product;
import org.junit.jupiter.api.Test;

public class DnsScenarioTest extends BaseTests{


    @Test
    public void test() {

        pageManager.getMainPage()
                .getHeaderNavBar().searchProduct("iphone")
                .clickOnTheFoundElement(new Product("iphone 12 pro max 256"))
                .clickWarranty("Гарантия")
                .selectAdditionalWarranty(Warranty.WITH_ADDITIONAL_12_MOUNTS.getValue())
                .clickButtonBuy()
                .getHeaderNavBar().searchProduct("Cyberpunk 2077")
                .clickBuyProduct(new Product("Cyberpunk 2077 (PS4)"))
                .getHeaderNavBar().checkSumCart()
                .getHeaderNavBar().clickCart()
                .checkAdditionalWarranty("+ 12 мес.")
                .checkSumCart()
                .deleteProductFromCart("Cyberpunk")
                .checkDeleteFromCart("Cyberpunk");

    }

}
