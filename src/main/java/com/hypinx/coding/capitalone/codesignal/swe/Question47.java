package com.hypinx.coding.capitalone.codesignal.swe;

import java.sql.Array;
import java.util.*;

/**
 * This solution works but probably not the optimized solution in which codesignal is looking for. This is problem 4
 * in the assessment (hardest difficulty)
 */
public class Question47 {

    public static void main(String[] args) throws Exception {
        String[] queries1 = new String[]{"+4", "+5", "+2", "-4"};
        int diff1 = 1;
        int[] expected1 = new int[]{0,1,1,0};
        int[] result1 = solution(queries1, diff1);
        validateResults(expected1, result1, 1);

        String[] queries2 = new String[]{"+2", "+2", "+4", "+3", "-2"};
        int diff2 = 1;
        int[] expected2 = new int[]{0,0,0,3,2};
        int[] result2 = solution(queries2, diff2);
        validateResults(expected2, result2, 2);



    }

    public static int[] solution(String[] input, int diff) {
        List<Integer> list = new LinkedList<>();
        int[] result = new int[input.length];

        for (int i = 0; i < input.length; i++) {
            char operation = input[i].charAt(0);
            int num = Integer.valueOf(String.valueOf(input[i].charAt(1)));

            if (operation == '+') {
                addInSortedOrder(list, num);
            } else {
                // Remove last element in the indeces list for the given number
                removeElement(list, num);
            }

            // Calculate difference
            result[i] = calculateDifference(list, diff);
        }

        return result;
    }

    private static void addInSortedOrder(List<Integer> list, int element) {
        int index = Collections.binarySearch(list, element);
        if (index < 0) {
            index = -index - 1; // Convert to insertion point
        }
        list.add(index, element);
    }

    private static void removeElement(List<Integer> list, int target) {
        int index = Collections.binarySearch(list, target);
        if (index >= 0) {
            list.remove(index);
        }
    }

    public static int calculateDifference(List<Integer> list, int diff) {
        int res = 0;
        for (int i = 0; i < list.size() - 1; i++) {
            int outer = list.get(i);
            for (int j = i + 1; j < list.size(); j++) {
                int inner = list.get(j);
                if (outer + inner == diff || Math.abs(outer - inner) == diff) {
                    res++;
                }
            }
        }

        return res;
    }

    private static void validateResults(int[] expected, int[] result, int testCase) throws Exception {
        for (int i = 0; i < expected.length; i++) {
            if (expected[i] != result[i]) throw new Exception("Incorrect results for test case " + testCase);
        }
    }
}
