package com.hypinx.coding.chase.superday.algorithm;

import com.hypinx.coding.misc.TestCaseValidator;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Question_11_Data_Corruption {

    public static void main(String[] args) {
        List<Integer> locations = Arrays.asList(1,3,5);
        int k = 2;
        int expected = 2;
        int result = getMinOperations(locations, k);

        TestCaseValidator.validateTestCase("1", expected, result);
    }

    public static int getMinOperations(List<Integer> location, int k) {
        Collections.sort(location);  // ascending order
        int n = location.size();
        int result = 0;
        int p1 = n - 1;
        int placeholder = -1;

        while (p1 >= 0 && p1 > placeholder) {
            result++;  // Corrupt file at p1

            // Look for the first file that becomes corrupted after this shift
            for (int i = p1 - 1; i >= 0; i--) {
                if (location.get(i) - result * k <= 0) {
                    placeholder = i;
                    break;  // All files before this are considered corrupted
                }
            }

            p1--;  // Move to the next file
        }

        return result;
    }
}
