package com.hypinx.coding.goldmansachs.powerday;

import java.util.HashSet;
import java.util.Set;

/**
 * Given an array of positive integers increment any duplicate elements until all elements end up being unique. At the end of this  operation the sum of elements must be minimum.
 *
 * Solution:
 *
 * Time Complexity: O(N) - Requires one full traversal to verify and/or create unique input list
 * Space Complexity: O(N) - Uses a set to store the unique values where N is the size of the input list. Even if all numbers are exactly the same, we will have N unique numbers after incrementing
 */
public class Question1 {

    public static void main(String[] args) {
        int[] input = new int[]{3,3,3,4,5,6};
        int expected = 33;
        int result = sumOfUniqueArray(input);
        System.out.println(expected == result);
    }

    public static int sumOfUniqueArray(int[] input) {
        Set<Integer> set = new HashSet<>();

        // Loop over every element in input list
        for (int i = 0; i < input.length; i++) {
            int current = input[i];
            // Check the set, if the element exists, we increment by 1 and repeat process until element no longer in set
            while (set.contains(current)) {
                current++;
            }
            // Add the unique element to the set
            set.add(current);

            // Update the input list with the new element
            input[i] = current;
        }

        return calculateSum(input);


    }

    private static int calculateSum(int[] input) {
        int sum = 0;
        for (int el : input) sum += el;
        return sum;
    }
}
