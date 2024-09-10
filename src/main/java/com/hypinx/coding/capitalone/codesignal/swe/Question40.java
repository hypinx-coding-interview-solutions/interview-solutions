package com.hypinx.coding.capitalone.codesignal.swe;

public class Question40 {

    public static void main(String[] args) throws Exception {
        String sample1 = "dbaca";
        String expected1 = "abdca";
        String result1 = solution(sample1);
        if (!result1.equals(expected1)) {
            throw new Exception("Expected " + expected1 + " but result was " + result1);
        }

        String sample2 = "t";
        String expected2 = "t";
        String result2 = solution(sample2);
        if (!result2.equals(expected2)) {
            throw new Exception("Expected " + expected2 + " but result was " + result2);
        }

        String sample3 = "lav";
        String expected3 = "alv";
        String result3 = solution(sample3);
        if (!result3.equals(expected3)) {
            throw new Exception("Expected " + expected3 + " but result was " + result3);
        }

        String sample4 = "senior";
        String expected4 = "esnior";
        String result4 = solution(sample4);
        if (!result4.equals(expected4)) {
            throw new Exception("Expected " + expected4 + " but result was " + result4);
        }

        System.out.println("All tests passed");
    }

    public static String solution(String word) {
        String smallest = word;
        StringBuilder substring;
        String updatedString;

        // Reverse first k letters
        for (int i = 0; i < word.length(); i++) {
            substring = new StringBuilder();
            substring.append(word.substring(0, i+1));
            substring.reverse();
            updatedString = substring.toString().concat(word.substring(i+1));

            if (replaceSmallestString(smallest, updatedString)) smallest = updatedString;
        }

        // Reverse last k letters
        for (int i = 0; i <= word.length(); i++) {
            substring = new StringBuilder();
            substring.append(word.substring(word.length() - i));
            substring.reverse();
            updatedString = word.substring(0, word.length() - i).concat(substring.toString());
            if (replaceSmallestString(smallest, updatedString)) smallest = updatedString;
        }

        return smallest;
    }

    private static boolean replaceSmallestString(String smallest, String current) {
        return smallest.compareTo(current) > 0;
    }
}
