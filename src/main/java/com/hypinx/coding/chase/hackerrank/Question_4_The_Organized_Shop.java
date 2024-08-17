package com.hypinx.coding.chase.hackerrank;

import com.hypinx.coding.misc.TestCaseValidator;

import java.util.Arrays;
import java.util.List;

public class Question_4_The_Organized_Shop {

    public static void main(String[] args) {
        List<Integer> items = Arrays.asList(6,5,9,7,3);
        int expected = 3;
        int result = getMinimumOperations(items);

        TestCaseValidator.validateTestCase("1", expected, result);

        items = Arrays.asList(2,1,4,7,2);
        expected = 0;
        result = getMinimumOperations(items);

        TestCaseValidator.validateTestCase("2", expected, result);
    }

    /**
     * Does not pass all test cases
     */
    public static int getMinimumOperations(List<Integer> items) {
        int[] itemsArr = items.stream().mapToInt(Integer::intValue).toArray();
        int n = itemsArr.length;
        int operations = 0;
        for (int i = 0; i < n - 1; i++) {
            while ((itemsArr[i] % 2) == (itemsArr[i + 1] % 2)) {
                itemsArr[i + 1] = (int) Math.floor(itemsArr[i + 1] / 2.0);
                operations++;
            }
        }

        return operations;
    }


}
