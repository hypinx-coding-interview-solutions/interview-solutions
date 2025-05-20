package com.hypinx.coding.meta.coding;

import com.hypinx.coding.misc.TestCaseValidator;

import java.util.Arrays;
import java.util.List;

public class Question_21_Weighted_Sum {

    public static void main(String[] args) {
        List<Object> input = Arrays.asList(8, 4, Arrays.asList(5, Arrays.asList(9), 3), 6);
        int expected = 61;
        int result = depthSum(input);

        TestCaseValidator.validateTestCase("1", expected, result);

    }

    public static int depthSum(List<Object> nestedList) {
        return computeDepthSum(nestedList, 1);
    }

    private static int computeDepthSum(List<Object> list, int depth) {
        int sum = 0;
        for (Object obj : list) {
            if (obj instanceof Integer) {
                sum += (Integer) obj * depth;
            } else if (obj instanceof List) {
                sum += computeDepthSum((List<Object>) obj, depth + 1);
            }
        }
        return sum;
    }
}
