package com.hypinx.coding.chase.hackerrank;

import com.hypinx.coding.misc.TestCaseValidator;

public class Question_23_Count_Valid_Words {

    public static void main(String[] args) {
        String input = "This is Form16 submis$ion date";
        int expected = 3;
        int result = countValidWords(input);

        TestCaseValidator.validateTestCase("1", expected, result);
    }

    public static int countValidWords (String s) {
        if (s == null || s.isEmpty()) return 0;

        String[] words = s.split("\\s+");
        int validCount = 0;

        for (String word : words) {
            if (isValidWord(word)) {
                validCount++;
            }
        }

        return  validCount;
    }

    private static boolean isValidWord(String word) {
        if (word.length() < 3) return false;

        if (!word.matches("[a-zA-Z0-9]+")) return false;

        boolean hasVowel = false;
        boolean hasConsonant = false;

        for (char c : word.toLowerCase().toCharArray()) {
            if (Character.isLetter(c)) {
                if ("aeiou".indexOf(c) >= 0) {
                    hasVowel = true;
                } else {
                    hasConsonant = true;
                }
            }
        }

        return hasVowel && hasConsonant;
    }
}
