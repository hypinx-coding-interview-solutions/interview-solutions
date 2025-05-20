package com.hypinx.coding.meta.coding;

import com.hypinx.coding.misc.TestCaseValidator;

public class Question_24_First_Element_Greater_Than_K {

    public static void main(String[] args) {
        int[] input = {1, 3, 5, 7, 9};
        int expected = 3;
        int k = 5;
        int result = firstGreater(input, k);

        TestCaseValidator.validateTestCase("1", expected, result);
    }


    public static int firstGreater(int[] nums, int k) {
        int low = 0, high = nums.length - 1;
        int result = -1;

        while (low <= high) {
            // We can calculate midpoint as (low + high) / 2 however there is a risk for overflow when we work with very large
            // arrays. So another way to do so to avoid potential overflow is low + (high - low) / 2.
            // With this approach we are subtracting and adding a small offset which is low. Both low and
            // high - low will be less than the array size which is less than Integer.MAX_VALUE
            int mid = low + (high - low) / 2;

            if (nums[mid] > k) {
                result = mid;       // found a candidate
                high = mid - 1;     // look for earlier
            } else {
                low = mid + 1;      // go right
            }
        }

        return result;
    }
}
