package com.hypinx.coding.chase.hackerrank;

import com.hypinx.coding.misc.TestCaseValidator;

import java.util.List;

public class Question_32_Maximize_Security_Gain {

    public static void main(String[] args) {
        List<Integer> security_val = List.of(2,-3,4,6,2);
        int k = 2;
        int result = gainMaxValue(security_val, k);
        int expected = 8;

        TestCaseValidator.validateTestCase("1", expected, result);
    }

    public static int gainMaxValue(List<Integer> security_val, int k) {
        int n = security_val.size();
        long best = Long.MIN_VALUE;

        // Convert to array for speed
        int[] array = new int[n];
        for (int i = 0; i < n; i++) array[i] = security_val.get(i);

        // For each residue class r (0-based)
        for (int residue = 0; residue < k; residue++) {
            if (residue >= n) break;
            int last = residue + ((n - 1 - residue) / k) * k;

            long suffix = 0;
            for (int i = last; i >= 0; i -= k) {
                suffix += array[i];
                if (suffix > best) best = suffix;
            }
        }
        return (int) best;
    }

}
