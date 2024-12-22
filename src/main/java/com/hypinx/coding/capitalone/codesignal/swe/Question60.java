package com.hypinx.coding.capitalone.codesignal.swe;

import com.hypinx.coding.misc.TestCaseValidator;

public class Question60 {

    public static void main(String[] args) {
        String[] input = new String[]{"HeLLo", "Data", "science"};
        String[] expected = new String[]{"HELLO", "ataD", "SCIENCE"};
        String[] result = solution(input);

        TestCaseValidator.validateTestCase("1", expected, result);

        input = new String[]{};
        expected = new String[]{};
        result = solution(input);

        TestCaseValidator.validateTestCase("2", expected, result);

        input = new String[]{"Word", "List", "Algorithm"};
        expected = new String[]{"droW", "tsiL", "ALGORITHM"};
        result = solution(input);

        TestCaseValidator.validateTestCase("3", expected, result);
    }

    public static String[] solution(String[] wordsList) {
        String[] result = new String[wordsList.length];
        int counter = 0;
        for (String word : wordsList) {
            if (word.length() % 2 == 0) {
                result[counter] = reverseString(word);
            } else {
                result[counter] = word.toUpperCase();
            }
            counter++;
        }

        return result;
    }

    private static String reverseString(String word) {
        StringBuilder builder = new StringBuilder();
        builder.append(word);
        return builder.reverse().toString();
    }
}
