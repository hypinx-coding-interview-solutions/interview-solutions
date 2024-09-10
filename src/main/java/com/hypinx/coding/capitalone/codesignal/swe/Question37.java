package com.hypinx.coding.capitalone.codesignal.swe;

import java.util.HashMap;
import java.util.Map;

public class Question37 {

    public static void main(String[] args) {
        int[] firstArray = new int[]{5,2,1,6,4};
        int[] secondArray = new int[]{3,5};
        int target = 10;
        int expected = 4;
        int result = solution(firstArray, secondArray, target);
        System.out.println("Expected = " + expected + " | Result = " + result);
        System.out.println(expected == result);
    }

    public static int solution(int[] firstArray, int[] secondArray, int target) {
        Map<Integer, Integer> prefixSumCount = new HashMap<>();
        int count = 0;

        // Calculate prefix sums for the secondArray
        int[] secondArrayPrefixSum = new int[secondArray.length + 1];
        for (int i = 1; i <= secondArray.length; i++) {
            secondArrayPrefixSum[i] = secondArrayPrefixSum[i - 1] + secondArray[i - 1];
        }

        // Iterate over all subarrays in firstArray
        for (int i = 0; i < firstArray.length; i++) {
            int sum = 0;
            for (int j = i; j < firstArray.length; j++) {
                sum += firstArray[j];
                // Check if the complement exists in the secondArray
                int complement = target - sum;
                count += prefixSumCount.getOrDefault(complement, 0);
            }
            // Update prefixSumCount for the current subarray in firstArray
            prefixSumCount.put(sum, prefixSumCount.getOrDefault(sum, 0) + 1);
        }

        return count;
    }
}
