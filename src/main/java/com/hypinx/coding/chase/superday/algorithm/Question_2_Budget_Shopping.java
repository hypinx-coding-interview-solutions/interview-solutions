package com.hypinx.coding.chase.superday.algorithm;

import com.hypinx.coding.misc.TestCaseValidator;

import java.util.Arrays;
import java.util.List;

public class Question_2_Budget_Shopping {

    public static void main(String[] args) {
        int n = 50;
        List<Integer> bundleQuantities = Arrays.asList(20, 19);
        List<Integer> bundleCosts = Arrays.asList(24, 20);
        int expected = 40;
        int result = budgetShopping(n, bundleQuantities, bundleCosts);

        TestCaseValidator.validateTestCase("1", expected, result);

        n = 4;
        bundleQuantities = Arrays.asList(10);
        bundleCosts = Arrays.asList(2);
        expected = 20;
        result = budgetShopping(n, bundleQuantities, bundleCosts);

        TestCaseValidator.validateTestCase("2", expected, result);

    }

    public static int budgetShopping(int n, List<Integer> bundleQuantities, List<Integer> bundleCosts) {
        return exploreCombinations(n, 0, 0, bundleQuantities, bundleCosts);
    }

    private static int exploreCombinations(int budget, int currentQuantity, int currentCost, List<Integer> bundleQuantities, List<Integer> bundleCosts) {
        // We set the max quantity to whatever we have purchase so far, when we cannot
        // purchase any further this will be the result of doing down this particular path
        int maxQuantity = currentQuantity;


        for (int i = 0; i < bundleQuantities.size(); i++) {
            // The first check is to see if the currentCost plus what we are about
            // to buy is less than the budget. Only then we can make the purchase
            if (currentCost + bundleCosts.get(i) <= budget) {
                // Now we can simulate the purchase by recursively calling the function
                // and passing in the new quantity, and updated cost
                int amount = exploreCombinations(budget, currentQuantity + bundleQuantities.get(i), currentCost + bundleCosts.get(i), bundleQuantities, bundleCosts);
                // If the amount we purchased by doing down this path is greater than
                // the current max, we can update the max to be the current amount
                if (maxQuantity < amount) maxQuantity = amount;
            }
        }

        return maxQuantity;
    }
}
