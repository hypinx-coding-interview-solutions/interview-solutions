package com.hypinx.coding.chase.superday.algorithm;

import com.hypinx.coding.misc.TestCaseValidator;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Question_6_Optimizing_Resource_Allocation {

    public static void main(String[] args) {
        List<Integer> input = List.of(1,2,3,7,3,5);
        int k = 3;
        long expected = 15;
        long result = findOptimalResources(input, k);

        TestCaseValidator.validateTestCase("1", expected, result);
    }

    public static long findOptimalResources(List<Integer> arr, int k) {
        if (arr == null || arr.size() < k) return -1;

        Set<Integer> windowSet = new HashSet<>();
        long currentSum = 0;
        long maxSum = -1;

        int left = 0;
        for (int right = 0; right < arr.size(); right++) {
            int val = arr.get(right);

            // If duplicate, shrink the window from left until dup is removed
            while(windowSet.contains(val)) {
                windowSet.remove(arr.get(left));
                currentSum -= arr.get(left);
                left++;
            }

            // Add new element to window
            windowSet.add(val);
            currentSum += val;

            // If window size exceeds k, remove from left
            if (right - left + 1 > k) {
                windowSet.remove(arr.get(left));
                currentSum -= arr.get(left);
                left++;
            }

            // If window is valid and is the size of k, check if we have new max
            if (right - left + 1 == k) {
                maxSum = Math.max(maxSum, currentSum);
            }
        }

        return maxSum;
    }
}
