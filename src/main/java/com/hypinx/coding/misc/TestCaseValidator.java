package com.hypinx.coding.misc;

import org.apache.logging.log4j.util.Strings;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TestCaseValidator {

    public static boolean validateTestCase(String testCase, int expected, int result) {
        if (expected != result) {
            throw new RuntimeException("Test case " + testCase + " failed. Expected " + expected + " but result was " + result);
        }

        return true;
    }

    public static boolean validateTestCase(String testCase, List expected, List result) {
        if (expected.size() != result.size()) {
            throw new RuntimeException("Test case " + testCase + " failed. Expected contains " + expected.size() + " elements and result contains " + result.size() + " elements.");
        }

        if (!expected.equals(result)) {
            throw new RuntimeException("Test case " + testCase + " failed. Expected " + convertListToString(expected, new StringBuilder()) + " but result is " + convertListToString(result, new StringBuilder()));
        }

        return true;
    }

//    private static String convertListToString(List<String> list) {
//        StringBuilder builder = new StringBuilder();
//        builder.append("[");
//        for (String el : list) {
//            builder.append(el);
//            builder.append(", ");
//        }
//        builder.deleteCharAt(builder.lastIndexOf(","));
//        builder.append("]");
//        return builder.toString();
//    }

    private static String convertListToString(List list, StringBuilder builder) {

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) instanceof List) {
                convertListToString((List) list.get(i), builder);
            } else {
                // Not a list
                builder.append("[");

                for (int j = 0; j < list.size(); j++) {
                    builder.append(list.get(j));
                    builder.append(", ");

                }
                // Delete trailing command and whitespace when iterating over single list
                builder.deleteCharAt(builder.lastIndexOf(","));
                builder.deleteCharAt(builder.lastIndexOf(" "));
                builder.append("], ");
                return builder.toString();            }
        }

        // Delete trailing command and whitespace when closing off final list (if multi dimensional list)
        builder.deleteCharAt(builder.lastIndexOf(" "));
        builder.deleteCharAt(builder.lastIndexOf(","));
        builder.insert(0, "[");
        builder.append("]");
        return builder.toString();
    }
}
