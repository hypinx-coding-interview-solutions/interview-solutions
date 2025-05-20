package com.hypinx.coding.meta.coding;

import com.hypinx.coding.misc.TestCaseValidator;

public class Question_23_Remove_Duplicates_In_Memory {

    public static void main(String[] args) {
        int[] nums = {0, 0, 1, 1, 2, 3, 3};
        int expected = 4;
        int result = removeDuplicates(nums);

        TestCaseValidator.validateTestCase("1", expected, result);
    }

    public static int removeDuplicates(int[] nums) {
        if (nums.length == 0) return 0;

        int write = 1;

        for (int read = 1; read < nums.length; read++) {
            if (nums[read] != nums[write - 1]) {
                nums[write] = nums[read];
                write++;
            }
        }

        return write;
    }
}
