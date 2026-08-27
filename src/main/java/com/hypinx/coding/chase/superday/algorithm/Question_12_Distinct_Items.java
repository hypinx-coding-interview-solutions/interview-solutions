package com.hypinx.coding.chase.superday.algorithm;

import com.hypinx.coding.misc.TestCaseValidator;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Question_12_Distinct_Items {

    public static void main(String[] args) {
        int n = 10, m = 3, k = 10;
        List<Integer> arr = List.of(1,3,8);
        int maxDistinctItems = findMaxDistinctItems(n, k, arr);

        TestCaseValidator.validateTestCase("1", 5, maxDistinctItems);

    }

    private static int findMaxDistinctItems(int n, int k, List<Integer> arr) {

        // Put all items owned into hash set to remove duplicates
        Set<Integer> owned = new HashSet<>(arr);

        int maxDistinctItems = owned.size();

        for (int i = 1; i <= n; i++) {

            // If we already own the item we can skip
            if (owned.contains(i)) continue;

            // If the item costs more than the remaining budget, stop the loop
            if (i > k) break;

            // Substract the item cost from the budget and increment the max items owned by 1
            k -= i;
            maxDistinctItems++;
        }

        return maxDistinctItems;
    }
}
