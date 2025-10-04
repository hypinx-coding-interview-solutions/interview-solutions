package com.hypinx.coding.chase.superday.algorithm;

import com.hypinx.coding.misc.TestCaseValidator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Question_9_Maximizing_The_Final_Element {

    public static void main(String[] args) {
        List<Integer> input = new ArrayList<>();
        input.addAll(Arrays.asList(3,1,3,4));
        int expected = 4;
        int result = getMaxValue(input);

        TestCaseValidator.validateTestCase("1", expected, result);

    }

    public static int getMaxValue(List<Integer> arr) {
        if (arr == null) return -1;

        Collections.sort(arr);

        int maxValue = 0;

        for (int num : arr) {
            if (num > maxValue) {
                maxValue++;
            }
        }

        return maxValue;
    }
}
