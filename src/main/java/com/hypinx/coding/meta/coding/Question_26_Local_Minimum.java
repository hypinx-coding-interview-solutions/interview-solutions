package com.hypinx.coding.meta.coding;

import com.hypinx.coding.misc.TestCaseValidator;

import java.util.List;

public class Question_26_Local_Minimum {

    public static void main(String[] args) {

        int[] arr = new int[]{5,9,7,10,12};
        List<Integer> expected = List.of(5,7);
        int result = findLocalMinimum(arr);

        TestCaseValidator.validateTestCase("1", expected.contains(result));
    }

    public static int findLocalMinimum(int[] arr) {
        int n = arr.length;
        if (n == 1) return arr[0];
        if (arr[0] <= arr[1]) return arr[0];
        if (arr[n - 1] <= arr[n - 2]) return arr[n - 1];

        int low = 1, high = n - 2;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            // Check if mid is a local minimum
            if (arr[mid] <= arr[mid - 1] && arr[mid] <= arr[mid + 1]) {
                return arr[mid];
            }
            // If left neighbor is smaller, local min must be on the left
            else if (arr[mid - 1] < arr[mid]) {
                high = mid - 1;
            }
            // Otherwise, move right
            else {
                low = mid + 1;
            }
        }
        return -1;
    }
}
