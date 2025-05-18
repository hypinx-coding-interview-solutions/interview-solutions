package com.hypinx.coding.capitalone.codesignal.swe;

import com.hypinx.coding.misc.TestCaseValidator;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class Question73 {

    public static void main(String[] args) {

        int[] input = new int[]{1,2,3,2,1};
        int expected = 4;
        int result = solution(input);

        TestCaseValidator.validateTestCase("1", expected, result);

        input = new int[]{4,3,4};
        expected = 9;
        result = solution(input);

        TestCaseValidator.validateTestCase("2", expected, result);

        input = new int[]{1,4,1,10,1};
        expected = 1;
        result = solution(input);

        TestCaseValidator.validateTestCase("3", expected, result);


    }

    public static int solution(int[] input) {
        int[] heights = Arrays.copyOf(input, input.length + 1); // append 0 to flush stack
        heights[heights.length - 1] = 0;

        Deque<Integer> stack = new ArrayDeque<>();
        int maxArea = 0;

        for (int i = 0; i < heights.length; i++) {
            while (!stack.isEmpty() && heights[i] < heights[stack.peek()]) {
                int height = heights[stack.pop()];
                int width = stack.isEmpty() ? i : i - stack.peek() - 1;
                int side = Math.min(height, width);
                maxArea = Math.max(maxArea, side * side);
            }
            stack.push(i);
        }

        return maxArea;
    }

}
