package com.hypinx.coding.chase.hackerrank;

import com.hypinx.coding.misc.TestCaseValidator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Question_27_Find_Total_Weight {

    public static void main(String[] args) {
        List<Integer> input = new ArrayList<>(Arrays.asList(5,4,1,3,2));
        int expected = 3;
        int result = findTotalWeight(input);

        TestCaseValidator.validateTestCase("1", expected, result);
    }

    private static int findTotalWeight(List<Integer> cans) {
        int totalWeight = 0;

        while(true) {
            int smallestWeight = Integer.MAX_VALUE;
            int smallestIndex = Integer.MAX_VALUE;

            for (int i = 0; i < cans.size(); i++) {
                int can = cans.get(i);
                if (can < smallestWeight && can != -1) {
                    smallestWeight = can;
                    smallestIndex = i;
                }
            }

            // Cleaned up entire beach, everything is -1
            if (smallestWeight == Integer.MAX_VALUE) break;

            // Find left adjacent can
            for (int current = smallestIndex - 1; current >= 0; current--) {
                if (cans.get(current) != -1) {
                    cans.set(current, -1);
                    break;
                }
            }

            // Find right adjacent can
            for (int current = smallestIndex + 1; current < cans.size(); current++) {
                if (cans.get(current) != -1) {
                    cans.set(current, -1);
                    break;
                }
            }

            // Add to total and clean up middle can
            totalWeight += cans.get(smallestIndex);
            cans.set(smallestIndex, -1);
        }

        return totalWeight;
    }
}
