package com.hypinx.coding.vanguard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class IsPangram {

    public static void main(String[] args) {
        List<String> pangram = new ArrayList<>(Arrays.asList("pack my box with five dozen liquor jugs", "this is not a pangram"));
        String expected = "10";
        String result = isPangram(pangram);
        System.out.println(result.equals(expected));
    }

    // REQUIRED
    public static String isPangram(List<String> pangram) {
        // A string is a pangram if it contains every letter of the alphabet. Assume the strings in pangram are all lowercase. We may
        // get other characters such as punctuation and whitespaces.
        // We start by creating a StringBuilder. We append a 1 if the string is a pangram or 0 if it's not
        StringBuilder result = new StringBuilder();
        // We create an empty boolean array of 26 spaces. Each space will be set to false by default
        boolean[] characterPresent = new boolean[26];

        // For every string in the pangram array, we will loop over it and check every character
        for (String word : pangram) {
            for (int i = 0; i < word.length(); i++) {
                // We extract every character in the string one by one
                char currentChar = word.charAt(i);
                // If the character is alphabetic, we can jump to the corresponding index in the boolean array and set to the true
                if (Character.isAlphabetic(currentChar)) {
                    characterPresent[currentChar - 'a'] = true;
                }
            }

            // The validate array will ensure every space in the boolean array is true, which means every letter occurred at least once
            // in the string. If any single index is false it means the letter correspnding to that index was not found.
            result.append(validateArray(characterPresent));
            // Once we are done processing the string, we reset the boolean array back to all false for the next string to process
            resetCharacterPresentArray(characterPresent);
        }

        return result.toString();
    }

    // REQUIRED
    public static String validateArray(boolean[] characterPresent) {
        for (int i = 0; i < characterPresent.length; i++) {
            if (!characterPresent[i]) {
                return "0";
            }
        }

        return "1";
    }

    // REQUIRED
    public static void resetCharacterPresentArray(boolean[] characterPresent) {
        for (int i = 0; i < characterPresent.length; i++) {
            characterPresent[i] = false;
        }
    }
}
