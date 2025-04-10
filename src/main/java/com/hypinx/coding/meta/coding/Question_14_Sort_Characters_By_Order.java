package com.hypinx.coding.meta.coding;

import com.hypinx.coding.misc.TestCaseValidator;

import java.util.HashMap;
import java.util.Map;

public class Question_14_Sort_Characters_By_Order {

    public static void main(String[] args) {
        String input = "abcab", order = "bca", expected = "bbcaa";
        String result = sortCharactersByOrder(input, order);
        TestCaseValidator.validateTestCase("1", result.equals(expected));
    }

    public static String sortCharactersByOrder(String inputCharacters, String orderCharacters) {
        // Edge cases
        if (orderCharacters.isBlank() || inputCharacters.isBlank()) return inputCharacters;

        // Build frequency map for inputs
        Map<Character, Integer> charFrequency = new HashMap<>();
        for (char ch : inputCharacters.toCharArray()) {
            charFrequency.put(ch, charFrequency.getOrDefault(ch, 0) + 1);
        }

        // Build the result string
        StringBuilder result = new StringBuilder();
        for (char ch : orderCharacters.toCharArray()) {
            if (charFrequency.containsKey(ch)) {
                int count = charFrequency.get(ch);
                for (int i = 0; i < count; i++) {
                    result.append(ch);
                }
                charFrequency.remove(ch);
            }
        }

        for (Map.Entry<Character, Integer> entry : charFrequency.entrySet()) {
            char ch = entry.getKey();
            int count = entry.getValue();
            for (int i = 0; i < count; i++) {
                result.append(ch);
            }
        }

        return result.toString();
    }
}
