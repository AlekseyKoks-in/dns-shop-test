package com.alex.framework.products;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Product {

    private double price;
    private String productDescription;
    private static final List<Product> listProducts = new ArrayList<>();
    private static int allCountProduct = 0;

    public Product(String productDescription) {
        this.productDescription = productDescription;
        listProducts.add(this);
    }

    public static int getAllCountProduct() {
        return allCountProduct;
    }

    public static void incrementAllCountProduct() {
        allCountProduct++;
    }

    public  static void decrementAllCountProduct() {
        allCountProduct--;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public static double getSum() {
        double sum = 0;
        for (Product product : listProducts) {
            sum += product.getPrice();
        }
        return sum;
    }

    public static Double getPriceProductByName(String nameProduct) {
        Double price = null;
        for (Product product : listProducts) {
            if (product.getProductDescription().contains(nameProduct)) {
                price = product.getPrice();
                break;
            }
        }
        return price;
    }

    public static void removeProductFromCart(String name) {
        listProducts.removeIf(product -> product.getProductDescription().toLowerCase(Locale.ROOT).contains(name.toLowerCase(Locale.ROOT)));
    }

    public static List<Product> getListProducts() {
        return listProducts;
    }

    @Override
    public String toString() {
        return String.format("description - %s, price = %.2f\n", getProductDescription(), getPrice());
    }

}
