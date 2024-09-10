package com.hypinx.coding.capitalone.codesignal.swe;

import java.util.Arrays;

public class Question34 {
    public static void main(String[] args) {

        int n = 8;
        String[] expected = new String[]{
                "********",
                "*      *",
                "*      *",
                "*      *",
                "*      *",
                "*      *",
                "*      *",
                "********"
        };

        String[] result = solution(n);
        System.out.println(Arrays.equals(expected, result));
    }

    public static String[] solution(int n) {
        StringBuilder border = new StringBuilder();
        StringBuilder frame = new StringBuilder();
        String[] result = new String[n];

        for (int i = 0; i < n; i++) {
            border.append("*");
            if (i == 0 || i == n-1) {
                frame.append("*");
            } else {
                frame.append(" ");
            }
        }

        String frameString = frame.toString();
        String borderString = border.toString();

        result[0] = borderString;
        result[n-1] = borderString;
        for (int i = 1; i < n-1; i++) {
            result[i] = frameString;
        }
        return result;
    }

    // Utility method for printing arrays, for testing only - not needed in main code
    public static void printArr(String[] arr) {
        for (String value : arr) {
            System.out.println(value);
        }
    }
}
