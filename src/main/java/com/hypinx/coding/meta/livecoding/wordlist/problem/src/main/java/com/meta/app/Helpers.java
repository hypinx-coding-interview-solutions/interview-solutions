package com.hypinx.coding.meta.livecoding.wordlist.problem.src.main.java.com.meta.app;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Helpers {

    public static List<String> getWords(String languageName) {
        try {
            String languagePath = "data/" + languageName + ".txt";
            return Files.readAllLines(Paths.get(languagePath));
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    public static Map<Character, Integer> getLetterFrequencies(List<String> words) {
        Map<Character, Integer> frequencies = new HashMap<>();

        for (String word : words) {
            char c = word.charAt(0);
            frequencies.put(c, frequencies.getOrDefault(c, 0) + 1);
        }

        return frequencies;
    }

    public static List<Character> getAlphabet(List<String> words) {
        List<Character> alphabet = new ArrayList<>(getLetterFrequencies(words).keySet());
        Collections.sort(alphabet);
        return alphabet;
    }
}
