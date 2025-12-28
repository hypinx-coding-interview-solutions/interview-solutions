package com.hypinx.coding.capitalone.codesignal.swe;

import com.hypinx.coding.misc.TestCaseValidator;

import java.util.Arrays;
import java.util.TreeSet;

public class Question84 {

    public static void main(String[] args) {
        int[] heights = {1,5,4,10,9};
        int viewingGap = 3;
        int expected = 4;
        int result = solution(heights, viewingGap);

        TestCaseValidator.validateTestCase("1", expected, result);
    }

    private static int solution(int[] heights, int viewingGap) {
        int n = heights.length;
        if (n < 2) return 0;

        // If gap <= 0: any pair is comparable -> min abs diff among all values
        if (viewingGap <= 0) {
            int[] a = heights.clone();
            Arrays.sort(a);
            long best = Long.MAX_VALUE;
            for (int i = 1; i < n; i++) {
                best = Math.min(best, (long) a[i] - a[i - 1]);
            }
            return (int) best;
        }

        // If gap >= n, no valid pair (problem statement likely avoids this)
        if (viewingGap >= n) return 0;

        TreeSet<Integer> seen = new TreeSet<>();
        long best = Long.MAX_VALUE;

        // For each i, only indices <= i - viewingGap are allowed
        for (int i = viewingGap; i < n; i++) {
            seen.add(heights[i - viewingGap]);

            Integer floor = seen.floor(heights[i]);
            if (floor != null) best = Math.min(best, Math.abs((long) heights[i] - floor));

            Integer ceil = seen.ceiling(heights[i]);
            if (ceil != null) best = Math.min(best, Math.abs((long) heights[i] - ceil));

            if (best == 0) return 0; // can't do better
        }

        return (int) best;
    }
}
