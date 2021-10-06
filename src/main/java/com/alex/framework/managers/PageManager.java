package com.alex.framework.managers;

import com.alex.framework.pages.AfterSearchPage;
import com.alex.framework.pages.CartPage;
import com.alex.framework.pages.MainPage;
import com.alex.framework.pages.ProductPage;
import com.alex.framework.pages.blocks.HeaderNavBar;

public class PageManager {

    private static PageManager pageManager = null;

    private MainPage mainPage;
    private AfterSearchPage afterSearchPage;
    private ProductPage productPage;
    private CartPage cartPage;
    private HeaderNavBar headerNavBar;

    private PageManager(){
    }

    public static PageManager getInstance() {
        if (pageManager == null) {
            pageManager = new PageManager();
        }
        return pageManager;
    }

    public MainPage getMainPage() {
        if (mainPage == null) {
            mainPage = new MainPage();
        }
        return mainPage;
    }

    public AfterSearchPage getAfterSearchPage() {
        if (afterSearchPage == null) {
            afterSearchPage = new AfterSearchPage();
        }
        return afterSearchPage;
    }

    public ProductPage getProductPage() {
        if (productPage == null) {
            productPage = new ProductPage();
        }
        return productPage;
    }

    public CartPage getCartPage() {
        if (cartPage == null) {
            cartPage = new CartPage();
        }
        return cartPage;
    }

    public HeaderNavBar getHeaderNavBar() {
        if (headerNavBar == null) {
            headerNavBar = new HeaderNavBar();
        }
        return headerNavBar;
    }

}
