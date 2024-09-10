package com.hypinx.coding.capitalone.codesignal.swe;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Question48 {

    public static void main(String[] args) throws Exception {
        int[] input = new int[]{5,7,6,9,2};
        int[] result = solution(input);
        int[] expected = new int[]{5,9,2,7,6};
        if (!Arrays.equals(result, expected)) {
            throw new Exception("Expected [" + Arrays.toString(expected) + "] but got [" + Arrays.toString(result) + "]");
        }
        input = new int[]{10, 8, 6, 4, 2};
        result = solution(input);
        expected = new int[]{10,6,4,2,8};
        if (!Arrays.equals(result, expected)) {
            throw new Exception("Expected [" + Arrays.toString(expected) + "] but got [" + Arrays.toString(result) + "]");
        }
        input = new int[]{1,2,3,4,5,6,7,8,9};
        result = solution(input);
        expected = new int[]{1,3,5,7,9,2,4,6,8};
        if (!Arrays.equals(result, expected)) {
            throw new Exception("Expected [" + Arrays.toString(expected) + "] but got [" + Arrays.toString(result) + "]");
        }
    }

    public static int[] solution(int[] arr) {
        List<Integer> first = new ArrayList<>(List.of(arr[0]));
        List<Integer> second = new ArrayList<>(List.of(arr[1]));

        for (int i = 2; i < arr.length; i++) {
            int current = arr[i];
            int greaterInFirst = countGreaterElements(first, current);
            int greaterInSecond = countGreaterElements(second, current);
            if (greaterInFirst > greaterInSecond) {
                first.add(current);
            } else if (greaterInFirst < greaterInSecond) {
                second.add(current);
            } else {
                if (first.size() <= second.size()) {
                    first.add(current);
                } else {
                    second.add(current);
                }
            }
        }

        first.addAll(second);

        return first.stream().mapToInt(Integer::intValue).toArray();
    }

    private static int countGreaterElements(List<Integer> arr, int target) {
        int count = arr.stream().filter(el -> el > target).toList().size();
//        System.out.println("Counting elements greater than " + target + " is " + count);
        return count;
    }
}
