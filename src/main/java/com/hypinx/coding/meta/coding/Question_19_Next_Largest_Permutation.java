package com.hypinx.coding.meta.coding;

import com.hypinx.coding.misc.TestCaseValidator;

public class Question_19_Next_Largest_Permutation {

    public static void main(String[] args) {
        int num = 531;
        int expected = -1;
        int result = nextPermutation(num);
        TestCaseValidator.validateTestCase("1", expected, result);

        num = 315;
        expected = 351;
        result = nextPermutation(num);
        TestCaseValidator.validateTestCase("2", expected, result);

        num = 34722641;
        expected = 34724126;
        result = nextPermutation(num);
        TestCaseValidator.validateTestCase("3", expected, result);
    }

    public static int nextPermutation(int num) {
        char[] digits = Integer.toString(num).toCharArray();
        int n = digits.length;

        // Step 1: Find the first decreasing digit from the right
        int i = n - 2;
        while (i >= 0 && digits[i] >= digits[i + 1]) {
            i--;
        }

        // If no such digit exists, we're at the highest permutation
        if (i < 0) {
            return -1;
        }

        // Step 2: Find the smallest digit on right side > digits[i]
        int j = n - 1;
        while (digits[j] <= digits[i]) {
            j--;
        }

        // Step 3: Swap
        swap(digits, i, j);

        // Step 4: Reverse digits after i
        reverse(digits, i + 1, n - 1);

        return Integer.parseInt(new String(digits));
    }

    private static void swap(char[] arr, int i, int j) {
        char tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    private static void reverse(char[] arr, int start, int end) {
        while (start < end) {
            swap(arr, start++, end--);
        }
    }
}
