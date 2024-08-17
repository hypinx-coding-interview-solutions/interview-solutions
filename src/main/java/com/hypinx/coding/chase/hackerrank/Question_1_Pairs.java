package com.hypinx.coding.chase.hackerrank;


import com.hypinx.coding.misc.TestCaseValidator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Question_1_Pairs {

    public static int findNumOfPairs(List<Integer> a, List<Integer> b) {

        int counter = 0;
        Collections.sort(a);
        Collections.sort(b);
        int i = a.size() - 1, j = b.size() - 1;
        while (i >= 0 && j >= 0) {
            if (a.get(i) > b.get(j)) {
                counter++;
                i--;
                j--;
            } else {
                j--;
            }
        }

        return counter;
    }

    public static void main(String[] args) {
        List<Integer> inputA = Arrays.asList(1,2,3,4,5);
        List<Integer> inputB = Arrays.asList(6,6,1,1,1);
        int result = findNumOfPairs(inputA, inputB);
        int expected = 3;

        TestCaseValidator.validateTestCase("1", expected, result);

        inputA = Arrays.asList(2,3,3);
        inputB = Arrays.asList(3,4,5);
        expected = 0;
        result = findNumOfPairs(inputA, inputB);
        TestCaseValidator.validateTestCase("2", expected, result);

    }
}

