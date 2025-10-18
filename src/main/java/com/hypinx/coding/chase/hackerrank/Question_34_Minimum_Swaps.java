package com.hypinx.coding.chase.hackerrank;

import com.hypinx.coding.misc.TestCaseValidator;

import java.util.List;

public class Question_34_Minimum_Swaps {

    public static void main(String[] args) {
        List<Integer> popularity = List.of(3,1,2);
        int expected = 1;
        int result = minimumSwaps(popularity);

        TestCaseValidator.validateTestCase("1", expected, result);
    }

    public static int minimumSwaps(List<Integer> popularity) {
        return -1;
    }
}
