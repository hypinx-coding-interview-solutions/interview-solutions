package com.hypinx.coding.capitalone.codesignal.swe;

import java.util.Arrays;
import java.util.LinkedList;

public class Question50 {

    public static void main(String[] args) throws Exception {
        int[] input1 = new int[]{1,4,2,11};
        int[] input2 = new int[]{10,1,8,4};
        int[] expected = new int[]{7, 13, 25, 25};
        int[] result = solution(input1, input2);
        validateResult(result, expected, 1);

        input1 = new int[]{1000};
        input2 = new int[]{500};
        expected = new int[]{500};
        result = solution(input1, input2);
        validateResult(result, expected, 2);

        input1 = new int[]{1,2,3,4,5};
        input2 = new int[]{2,2,1000,2,2};
        expected = new int[]{999,1001,1003,1005,1005};
        result = solution(input1, input2);
        validateResult(result, expected, 3);
    }

    public static int[] solution(int[] nums1, int[] nums2) {
        // Outercounter used to iterate over index values in result array
        int outerCounter = 0, n = nums1.length;
        int[] result = new int[n];

        // Wrap nums1 array to Integer
        Integer[] nums1Wrapper = Arrays.stream(nums1).boxed().toArray(Integer[]::new);
        // Use Linkedlist as dequeue in order to have access to front and last elements in removing/adding
        LinkedList<Integer> queue = new LinkedList<>();
        queue.addAll(Arrays.asList(nums1Wrapper));

        for (int i = 0; i < n; i++) {
            // Perform cyclic t cycle from 0 to n-1
            Integer curr = queue.removeLast();
            queue.addFirst(curr);

            int sum = 0, counter = 0;
            // Sum every element against its corresponding element in nums2 using counter to access by index
            for (Integer el : queue) {
                sum += Math.abs(el - nums2[counter++]);
            }

            result[outerCounter++] = sum;
        }

        // Sort in ascending order
        Arrays.sort(result);
        return result;
    }

    public static void validateResult(int[] res, int[] exp, int testCase) throws Exception {
        if (!Arrays.equals(res, exp)) {
            throw new Exception("Test case " + testCase + "failed. Result = " + Arrays.toString(res) + " but expected = " + Arrays.toString(exp));
        }
    }
}
