package com.hypinx.coding.capitalone.codesignal.swe;

import com.hypinx.coding.misc.TestCaseValidator;

public class Question58 {

    public static void main(String[] args) {
        int[] input = new int[]{4, 50, 100, 65, 2000, 700, 1, 10};
        int expected = 3;
        int result = solution(input);

        TestCaseValidator.validateTestCase("1", expected, result);

        input = new int[]{7, 5017};
        expected = 1;
        result = solution(input);

        TestCaseValidator.validateTestCase("2", expected, result);


        input = new int[]{0, 101, 12};
        expected = 2;
        result = solution(input);

        TestCaseValidator.validateTestCase("3", expected, result);

    }

    public static int solution(int[] a) {
        int count = 0;
        for (int element : a) {
            String number = String.valueOf(element);
            int occ = 0;
            for (char current : number.toCharArray()) {
                if (current == '0') occ++;
            }

            if (occ % 2 == 1) count++;
        }

        return count;
    }
}
