package com.hypinx.coding.capitalone.codesignal.swe;

import com.hypinx.coding.misc.TestCaseValidator;

import java.util.HashMap;
import java.util.Map;

public class Question82 {

    public static void main(String[] args) {
        int[] fragments = {1, 212, 12, 12};
        int accessCode = 1212;
        int expected = 3;

        int result = solution(fragments, accessCode);

        TestCaseValidator.validateTestCase("1", expected, result);
    }

    private static int solution(int[] fragments, int accessCode) {
        String target = String.valueOf(accessCode);

        Map<String, Integer> freq = new HashMap<>();
        for (int x : fragments) {
            String s = String.valueOf(x);
            freq.put(s, freq.getOrDefault(s, 0) + 1);
        }

        long ans = 0;

        // Count ordered pairs (i, j), i != j, such that str(fragments[i]) + str(fragments[j]) == target
        for (Map.Entry<String, Integer> e : freq.entrySet()) {
            String left = e.getKey();
            int leftCount = e.getValue();

            if (!target.startsWith(left)) continue;

            String right = target.substring(left.length());
            if (right.isEmpty()) continue; // fragments are positive integers -> no empty fragment

            Integer rightCountObj = freq.get(right);
            if (rightCountObj == null) continue;

            int rightCount = rightCountObj;
            if (left.equals(right)) {
                // ordered pairs from same bucket but different indices
                ans += (long) leftCount * (leftCount - 1);
            } else {
                ans += (long) leftCount * rightCount;
            }
        }

        // If constraints guarantee fit in int, this is safe; otherwise change return type to long.
        return (int) ans;
    }
}
