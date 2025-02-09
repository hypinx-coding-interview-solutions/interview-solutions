package com.hypinx.coding.capitalone.codesignal.swe;

import com.hypinx.coding.misc.TestCaseValidator;

public class Question63 {

    public static void main(String[] args) {
        String input = "123 4558 787";
        String expected = "123 22 787";
        String result = solution(input);
        TestCaseValidator.validateTestCase("1", expected.equals(result));

        input = "123456789";
        expected = "123456789";
        result = solution(input);
        TestCaseValidator.validateTestCase("2", expected.equals(result));

        input = "11 8 88 43 711";
        expected = "2 8 16 43 9";
        result = solution(input);
        TestCaseValidator.validateTestCase("3", expected.equals(result));
    }

    public static String solution(String sensorReadings) {
        String[] sensorReadingsArr = sensorReadings.split(" ");
        StringBuilder result = new StringBuilder();

        for (String reading : sensorReadingsArr) {
            buildResultString(reading, result);
        }

        return result.toString().trim();
    }

    private static void buildResultString(String number, StringBuilder result) {
        if (number.length() < 2) {
            result.append(number + " ");
            return;
        }

        boolean repeats = false;
        for (int i = 1; i < number.length(); i++) {
            if (number.charAt(i) == number.charAt(i - 1)) {
                repeats = true;
                break;
            }
        }

        int sum = Integer.parseInt(number);
        if (repeats) {
            sum = 0;
            for (int i = 0; i < number.length(); i++) {
                sum += Integer.parseInt(number.charAt(i) + "");
            }
        }

        result.append(sum + " ");
    }
}
