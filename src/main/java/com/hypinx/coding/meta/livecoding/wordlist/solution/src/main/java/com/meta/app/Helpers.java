package com.hypinx.coding.meta.livecoding.wordlist.solution.src.main.java.com.meta.app;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Helpers {

    public static List<String> getWords(String languageName) {
        try {
            Path path = Paths.get(ClassLoader.getSystemResource("data/" + languageName + ".txt").toURI());
            return Files.readAllLines(path);
        } catch (IOException | URISyntaxException e) {
            return new ArrayList<>();
        }

    }

    public static Map<Character, Integer> getLetterFrequencies(List<String> words) {
        Map<Character, Integer> frequencies = new HashMap<>();

        for (String word : words) {
            // SOLUTION
            for (char c : word.toCharArray()) {
                frequencies.put(c, frequencies.getOrDefault(c, 0) + 1);
            }
        }

        return frequencies;
    }

    public static List<Character> getAlphabet(List<String> words) {
        List<Character> alphabet = new ArrayList<>(getLetterFrequencies(words).keySet());
        Collections.sort(alphabet);
        return alphabet;
    }
}
