package com.hypinx.coding.misc;

import org.apache.logging.log4j.util.Strings;

import java.util.Arrays;
import java.util.List;

public class TestCaseValidator {

    public static boolean validateTestCase(String testCase, int expected, int result) {
        if (expected != result) {
            throw new RuntimeException("Test case " + testCase + " failed. Expected " + expected + " but result was " + result);
        }

        return true;
    }

    public static boolean validateTestCase(String testCase, List<String> expected, List<String> result) {
        if (expected.size() != result.size()) {
            throw new RuntimeException("Test case " + testCase + " failed. Expected contains " + expected.size() + " elements and result contains " + result.size() + " elements.");
        }

        for (int i = 0; i < expected.size(); i++) {
            if (!expected.get(i).equals(result.get(i))) {
                throw new RuntimeException("Test case " + testCase + " failed. Expected " + convertListToString(expected) + " but result is " + convertListToString(result));
            }
        }

        return true;
    }

    private static String convertListToString(List<String> list) {
        StringBuilder builder = new StringBuilder();
        builder.append("[");
        for (String el : list) {
            builder.append(el);
            builder.append(", ");
        }
        builder.deleteCharAt(builder.lastIndexOf(","));
        builder.append("]");
        return builder.toString();
    }
}
