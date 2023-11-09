package com.hypinx.coding.vanguard;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MissingWords {

    public static void main(String[] args) {

        String s = "I am using HackerRank to improve programming";
        String t = "am HackerRank to improve";

        List<String> expected = new ArrayList<>(Arrays.asList("I", "using", "programming"));
        List<String> result = missingWords(s, t);
        System.out.println(validate(result, expected));
    }

    // REQUIRED
    public static List<String> missingWords(String s, String t) {
        // Split both strings in string array which will be used for parsing
        String[] wordsInString = s.split(" ");
        String[] wordsInSubsequence = t.split(" ");

        // Set pointers for S and T string arrays to 0
        int pointerT = 0, pointerS =0 ;

        // For every word in T array, we traverse S array. If the word matches we set the value to an empty string in S array.
        // Then we pick the next word in T array and repeat the process.
        while (pointerT < wordsInSubsequence.length) {
            String currentWord = wordsInSubsequence[pointerT];
            while (pointerS < wordsInString.length) {
                String current = wordsInString[pointerS];
                if (current.equals(currentWord)) {
                    wordsInString[pointerS] = "";
                    break;
                }
                pointerS++;
            }
            pointerT++;
        }

        // We take the modified S word array and join the strings together to form one string
        String resultInString = String.join(" ", wordsInString);
        // We split the string by delimiter to get a string array then we convert into a mutable list.
        List<String> result = new ArrayList<String>(Arrays.asList(resultInString.split(" ")));
        // For all the strings we set to an empty string, we want to remove them
        result.removeAll(Collections.singleton(""));
        // Finally we return the result
        return result;
    }

    // NOT REQUIRED
    public static boolean validate(List<String> result, List<String> expected) {
        if (result.size() != expected.size()) return false;
        for (int i = 0; i < expected.size(); i++) {
            if (!result.get(i).equals(expected.get(i))) return false;
        }
        return true;
    }
}
