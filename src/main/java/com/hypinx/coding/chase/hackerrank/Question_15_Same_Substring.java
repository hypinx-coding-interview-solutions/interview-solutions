package com.hypinx.coding.chase.hackerrank;

import com.hypinx.coding.misc.TestCaseValidator;
import com.sun.source.util.Plugin;

public class Question_15_Same_Substring {

    public static void main (String[] args) {
       String s = "uaccd", t = "gbbeg";
       int expected = 3;
       int result = sameSubstring(s, t, 4);

       TestCaseValidator.validateTestCase("1", expected, result);

       s = "hffk";
       t = "larb";
       expected = 0;
       result = sameSubstring(s, t, 3);

       TestCaseValidator.validateTestCase("2", expected, result);

    }

    public static int sameSubstring(String s, String t, int K) {
        int totalLength = s.length();
        int start = 0, currentCost = 0, maxLength = 0;
        for (int end = 0; end < totalLength; end++) {
            currentCost += Math.abs(s.charAt(end) - t.charAt(end));

            while (currentCost > K) {
                currentCost -= Math.abs(s.charAt(start) - t.charAt(start));
                start++;
            }

            maxLength = Math.max(maxLength, end - start + 1);
        }

        return maxLength;
    }
}
