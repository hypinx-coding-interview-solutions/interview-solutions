package com.hypinx.coding.capitalone.codesignal.swe;

import java.util.*;

public class Question44 {

    public static void main (String[] args) throws Exception {
        String sample1 = "penelope lives in hawaii";
        String[] expected1 = new String[]{"in", "penelope", "lives", "hawaii"};
        String[] result1 = solution(sample1);

        for (int i = 0; i < expected1.length; i++) {
            if (!expected1[i].equals(result1[i])) {
                printArr(expected1, result1);
                throw new Exception("Test case failed");
            }
        }
    }

    public static String[] solution(String text) {
        String[] words = text.split(" ");
        if (words.length < 2) return words;

        List<WordCount> allWords = new ArrayList<>();
        Set<Character> vowels = Set.of('a','e','i','o','u');

        for (int i = 0; i < words.length; i++) {
            String current = words[i].toLowerCase();
            WordCount wordCount = new WordCount(current);

            int vowelCount = 0, consonants = 0;
            for (int j = 0; j < current.length(); j++) {
                if (vowels.contains(current.charAt(j))) {
                    vowelCount++;
                } else {
                    consonants++;
                }
            }

            wordCount.difference = Math.abs(consonants - vowelCount);
            allWords.add(wordCount);
        }

        allWords.sort((a,b) -> {
            if (a.difference == b.difference) {
                return a.getWord().compareTo(b.getWord());
            }

            return Integer.compare(a.difference, b.difference);
        });

        String[] result = new String[allWords.size()];
        int counter = 0;
        for (WordCount wc : allWords) {
            result[counter++] = wc.getWord();
        }

        return result;
    }

    private static void printArr(String[] exp, String[] res) {
        System.out.print("Expected = ");
        for (String el : exp) {
            System.out.print(el + " ");
        }
        System.out.println();
        System.out.print("Result was = ");
        for (String el : res) {
            System.out.print(el + " ");
        }
        System.out.println();
    }

    static class WordCount {
        String word;
        int vowels;
        int consonants;
        int difference;

        public WordCount(String word) {
            this.word = word;
        }

        public String getWord() { return this.word; }
    }
}