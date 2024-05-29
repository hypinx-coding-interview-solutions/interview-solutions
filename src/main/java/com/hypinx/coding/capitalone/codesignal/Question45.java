package com.hypinx.coding.capitalone.codesignal;

public class Question45 {

    public static void main(String[] args) throws Exception {
        String[] input = new String[]{"cat", "dog", "ferret", "scorpion"};
        String[] expected = new String[]{"cg", "dt", "fn", "st"};
        String[] result = solution(input);
        validateSolution(expected, result);
    }

    public static String[] solution(String[] input) {
        String[] result = new String[input.length];
        int counter = 0;

        for (int i = 0; i < input.length - 1; i++) {
            String current = input[i], next = input[i+1];
            result[counter++] = "" + current.charAt(0) + next.charAt(next.length() - 1) + "";
        }

        result[counter] = "" + input[input.length - 1].charAt(0) + input[0].charAt(input[0].length() - 1) + "";

        return result;
    }

    public static boolean validateSolution(String[] expected, String[] result) throws Exception {
        for (int i = 0; i < expected.length; i++) {
            if (!expected[i].equals(result[i])) {
                printExpectedOutput(expected, "Expected");
                printExpectedOutput(result, "But got: ");
                throw new Exception("Invalid outcome");
            }
        }

        return true;
    }

    public static void printExpectedOutput(String[] array, String status) {
        System.out.print(status + ": ");
        for (String el : array) {
            System.out.print(el + ", ");
        }
        System.out.println();
    }
}
