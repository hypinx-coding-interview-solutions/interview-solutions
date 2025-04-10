package com.hypinx.coding.meta.coding;

import com.hypinx.coding.misc.TestCaseValidator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Question_15_Merge_Intervals {

    public static void main(String[] args) {
        List<int[]> list1 = Arrays.asList(new int[]{1, 2}, new int[]{3, 9});
        List<int[]> list2 = Arrays.asList(new int[]{4, 6}, new int[]{8, 10}, new int[]{11, 12});

        List<int[]> expected = Arrays.asList(new int[]{1,2}, new int[]{3,10}, new int[]{11,12});
        List<int[]> result = mergeIntervals(list1, list2);

        TestCaseValidator.validateTestCase("1", expected, result);
    }

    public static List<int[]> mergeIntervals(List<int[]> list1, List<int[]> list2) {
        List<int[]> merged = new ArrayList<>();
        List<int[]> all = new ArrayList<>();

        // Step 1: Merge both lists using two-pointer approach
        int i = 0, j = 0;
        while (i < list1.size() && j < list2.size()) {
            if (list1.get(i)[0] <= list2.get(j)[0]) {
                all.add(list1.get(i++));
            } else {
                all.add(list2.get(j++));
            }
        }

        while (i < list1.size()) all.add(list1.get(i++));
        while (j < list2.size()) all.add(list2.get(j++));

        // Step 2: Merge overlapping intervals
        for (int[] interval : all) {
            if (merged.isEmpty() || merged.get(merged.size() - 1)[1] < interval[0]) {
                merged.add(interval);
            } else {
                int[] last = merged.get(merged.size() - 1);
                last[1] = Math.max(last[1], interval[1]); // merge
            }
        }

        return merged;
    }
}
