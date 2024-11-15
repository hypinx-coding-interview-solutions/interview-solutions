package com.hypinx.coding.capitalone.codesignal.swe;

import com.hypinx.coding.misc.TestCaseValidator;

public class Question57 {

    public static void main(String[] args) {
        int[] input = new int[]{1,3,2,5,1};
        int expected = 8;
        int result = solution(input);

        TestCaseValidator.validateTestCase("1", expected, result);

        input = new int[]{1,2};
        expected = 2;
        result = solution(input);

        TestCaseValidator.validateTestCase("2", expected, result);

        input = new int[]{5,1,3};
        expected = 8;
        result = solution(input);

        TestCaseValidator.validateTestCase("3", expected, result);
    }

    public static int solution(int[] statusReports) {
        int sum = 0;
        for (int i = 0; i < statusReports.length; i++) {
            if (i == 0) {
                if (statusReports[i] > statusReports[i + 1]) {
                    sum += statusReports[i];
                }
            } else if (i == statusReports.length - 1) {
                if (statusReports[i - 1] < statusReports[i]) {
                    sum += statusReports[i];
                }
            } else {
                int left = statusReports[i - 1], right = statusReports[i + 1], current = statusReports[i];
                if (current > left && current > right) sum += current;
            }
        }
        return sum;
    }
}
