package com.hypinx.coding.capitalone.codesignal.swe;

import com.hypinx.coding.misc.TestCaseValidator;

public class Question79 {

    public static void main(String[] args) {
        int[] input = new int[]{1,2,3,4};
        int threshold = 5;
        int expected = 2;
        int result = solution(input, threshold);

        TestCaseValidator.validateTestCase("1", expected, result);
    }

    public static int solution(int[] dataSizes, int threshold) {
        int n = dataSizes.length;

        int[] prefix = new int[n + 1];
        for (int i = 0; i < n; i++) {
            prefix[i+1] = prefix[i] + dataSizes[i];
        }

        int maxLen = 0;

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j <= n; j++) {
                int subSum = prefix[j] - prefix[i];
                int preSum = prefix[i];
                int diff = Math.abs(subSum - preSum);
                if (diff <= threshold) {
                    maxLen = Math.max(maxLen, j - i);
                }
            }
        }

        return maxLen;
    }
}
