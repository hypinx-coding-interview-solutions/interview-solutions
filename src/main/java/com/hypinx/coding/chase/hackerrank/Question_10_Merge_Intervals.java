package com.hypinx.coding.chase.hackerrank;

import com.hypinx.coding.misc.TestCaseValidator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Question_10_Merge_Intervals {

    public static void main(String[] args) {
        List<List<Integer>> intervals = new ArrayList<>();
        intervals.add(List.of(6, 9));
        intervals.add(List.of(2, 3));
        intervals.add(List.of(9, 11));
        intervals.add(List.of(1, 5));
        intervals.add(List.of(14, 18));

        List<List<Integer>> expected = new ArrayList<>();
        expected.add(List.of(1, 5));
        expected.add(List.of(6, 11));
        expected.add(List.of(14, 18));

        List<List<Integer>> result = getMergedIntervals(intervals);

        TestCaseValidator.validateTestCase("1", expected, result);

    }

    public static List<List<Integer>> getMergedIntervals(List<List<Integer>> intervals) {
        // Convert List<List<Integer>> to int[][]
        int[][] intArrayIntervals = new int[intervals.size()][2];
        for (int i = 0; i < intervals.size(); i++) {
            intArrayIntervals[i][0] = intervals.get(i).get(0);
            intArrayIntervals[i][1] = intervals.get(i).get(1);
        }

        // Sort the intervals based on the start times
        Arrays.sort(intArrayIntervals, (a, b) -> Integer.compare(a[0], b[0]));

        List<int[]> merged = new ArrayList<>();
        int[] prev = intArrayIntervals[0];

        for (int i = 1; i < intArrayIntervals.length; i++) {
            int[] interval = intArrayIntervals[i];
            if (interval[0] <= prev[1]) {
                prev[1] = Math.max(prev[1], interval[1]);
            } else {
                merged.add(prev);
                prev = interval;
            }
        }

        merged.add(prev);

        // Convert the merged int[][] back to List<List<Integer>>
        List<List<Integer>> mergedList = new ArrayList<>();
        for (int[] arr : merged) {
            mergedList.add(Arrays.asList(arr[0], arr[1]));
        }

        return mergedList;
    }
}
