package com.hypinx.coding.meta.coding;

import com.hypinx.coding.misc.TestCaseValidator;

import java.util.HashMap;
import java.util.Map;

public class Question_5_Continuous_Subarray_Sum {

    public static void main(String[] args) {
        int[] nums = new int[]{5,3,1,4,2};
        int target = 10;
        boolean expected = true;
        boolean result = hasSubarrayWithSum(nums, target);

        TestCaseValidator.validateTestCase("1", expected, result);
    }

    public static boolean hasSubarrayWithSum(int[] nums, int target) {
        Map<Integer, Integer> prefixSums = new HashMap<>();
        int currentSum = 0;

        for (int num : nums) {
            currentSum += num;

            // Check if the current sum equals the target
            if (currentSum == target) {
                return true;
            }

            // Check if there exists a prefix sum such that currentSum - prefixSum = target
            if (prefixSums.containsKey(currentSum - target)) {
                return true;
            }

            // Store the current sum in the hash map
            prefixSums.put(currentSum, prefixSums.getOrDefault(currentSum, 0) + 1);
        }

        return false;
    }
}
