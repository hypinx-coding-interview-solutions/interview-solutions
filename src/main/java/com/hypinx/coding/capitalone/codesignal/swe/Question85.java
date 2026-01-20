package com.hypinx.coding.capitalone.codesignal.swe;

import com.hypinx.coding.misc.TestCaseValidator;

public class Question85 {

    public static void main(String[] args) {
        int[] structures = new int[]{1,4,3,2};
        long expected = 4;
        long result = solution(structures);

        TestCaseValidator.validateTestCase("1", expected, result);
    }

    public static long solution(int[] structures) {
        int n = structures.length;

        // Ascending: final[i] = base + i
        long baseAsc = Long.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            baseAsc = Math.max(baseAsc, structures[i] - i);
        }

        long costAsc = 0;
        for (int i = 0; i < n; i++) {
            costAsc += (baseAsc + i) - structures[i];
        }

        // Descending: final[i] = base - i
        long baseDesc = Long.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            baseDesc = Math.max(baseDesc, structures[i] + i);
        }

        long costDesc = 0;
        for (int i = 0; i < n; i++) {
            costDesc += (baseDesc - i) - structures[i];
        }

        return Math.min(costAsc, costDesc);
    }
}
