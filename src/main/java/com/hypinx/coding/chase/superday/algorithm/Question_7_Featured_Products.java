package com.hypinx.coding.chase.superday.algorithm;

import com.hypinx.coding.misc.TestCaseValidator;

import java.util.*;

public class Question_7_Featured_Products {

    public static void main(String[] args) {
        String[] products = {"yellowShirt", "redHat", "blackShirt", "bluePants", "redHat", "pinkHat", "blackShirt", "yellowShirt", "greenPants", "greenPants"};
        String expected = "yellowShirt";
        String result = mostFeaturedProduct(products);

        TestCaseValidator.validateTestCase("1", expected.equals(result));
    }

    public static String mostFeaturedProduct(String[] products) {
        Map<String, Product> occurrences = new HashMap<>();

        for (String product : products) {
            Product current = occurrences.getOrDefault(product, new Product(product, 0));
            current.incrementCount();
            occurrences.putIfAbsent(product, current);
        }

        List<Product> allProducts = new ArrayList<>(occurrences.values());

        // Sort based on higher count, if tie, sort based on alphabetical order (asc) and choose last product in the list
        allProducts.sort((a, b) -> {
            if (b.getCount() == a.getCount()) {
                return b.getProduct().compareTo(a.getProduct());
            }

            return Integer.compare(b.getCount(), a.getCount());
        });

        return allProducts.get(0).getProduct();
    }

    static class Product {
        private String product;
        private int count;

        public Product(String product, int count) {
            this.product = product;
            this.count = count;
        }

        public String getProduct() { return this.product; }
        public int getCount() { return this.count; }
        public void incrementCount() {
            this.count++;
        }
    }
}
