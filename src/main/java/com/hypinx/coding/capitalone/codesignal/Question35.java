package com.hypinx.coding.capitalone.codesignal;

import java.util.HashMap;
import java.util.Map;

public class Question35 {
    public static void main(String[] args) {
        String sentence1 = "Doddle moddle Pepper unsuccessfully";
        int expected1 = 3;
        int result1 = solution(sentence1);
        System.out.println(expected1 == result1);

        String sentence2 = "yummmmy yummmy yummmy yummmy yummy";
        int expected2 = 4;
        int result2 = solution(sentence2);
        System.out.println(expected2 == result2);
    }

    public static int solution(String sentence) {
        // Map holds word and true if it contains any one letter that repeats 3 or more times, false otherwise
        Map<String, Boolean> wordsSearched = new HashMap<>();
        String[] words = sentence.split(" ");
        int[] letters = new int[26];
        int count = 0;

        for (String word : words) {
            word = word.toLowerCase();
            if (wordsSearched.containsKey(word)) {
                if (wordsSearched.get(word)) {
                    count++;
                    continue;
                }
            }

            resetArray(letters);
            for (int i = 0; i < word.length(); i++) {
                int index = word.charAt(i) - 'a';
                letters[index]++;
                if (letters[index] == 3) {
                    count++;
                    wordsSearched.put(word, true);
                    break;
                }
            }

            // If we have not added the word to the map from the previous loop, it means we did not find any letter that repeated at least 3 times.
            // So we can add the word manually and set to false
            if (!wordsSearched.containsKey(word)) {
                wordsSearched.put(word, false);
            }
        }

        return count;
    }

    private static void resetArray(int[] letters) {
        for (int i = 0; i < letters.length; i++) {
            letters[i] = 0;
        }
    }
}
