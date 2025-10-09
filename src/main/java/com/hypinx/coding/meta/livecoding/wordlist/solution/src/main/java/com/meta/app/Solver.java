package com.hypinx.coding.meta.livecoding.wordlist.solution.src.main.java.com.meta.app;

import java.util.*;
import java.util.stream.Collectors;

public class Solver {

    /*
    // Normal solution - Not optimized

    public static List<String> solve(List<String> words) {
        List<String> bestResult = new ArrayList<>();
        backtrack(words, 0, new HashSet<>(), new ArrayList<>(), bestResult);
        return bestResult;
    }

    private static void backtrack(
            List<String> words,
            int index,
            Set<Character> usedChars,
            List<String> currentResult,
            List<String> bestResult) {

        // Calculate the number of unique characters in the current result
        Set<Character> currentUniqueChars = new HashSet<>();
        for (String word : currentResult) {
            for (char c : word.toCharArray()) {
                currentUniqueChars.add(c);
            }
        }

        Set<Character> bestResultUniqueChars = new HashSet<>();
        for (String word : bestResult) {
            for (char c : word.toCharArray()) {
                bestResultUniqueChars.add(c);
            }
        }

        if (currentUniqueChars.size() > bestResultUniqueChars.size()) {
            bestResult.clear();
            bestResult.addAll(currentResult);
        }

        // Explore further combinations
        for (int i = index; i < words.size(); i++) {
            String word = words.get(i);
            Set<Character> currentWordChars = new HashSet<>();

            // Check if we can add the current word
            boolean canAdd = true;
            for (char c : word.toCharArray()) {
                if (usedChars.contains(c) || currentWordChars.contains(c)) {
                    canAdd = false;
                    break;
                }
                currentWordChars.add(c);
            }

            // If we can add the word
            if (canAdd) {
                // Choose the word
                currentResult.add(word);
                usedChars.addAll(currentWordChars);

                // Recurse to the next index
                backtrack(words, i + 1, usedChars, currentResult, bestResult);

                // Backtrack: unchoose the word
                currentResult.remove(currentResult.size() - 1);
                usedChars.removeAll(currentWordChars);
            }
        }
    }

    */

    public static List<String> solve(List<String> words) {
        List<WordInfo> filteredWords = words.stream()
                .map(WordInfo::from)
                .filter(Objects::nonNull) // remove words with duplicate letters
                .collect(Collectors.toList());

        Result bestResult = new Result();
        backtrack(filteredWords, 0, new HashSet<>(), new ArrayList<>(), 0, bestResult);
        return bestResult.words;
    }

    private static void backtrack(
            List<WordInfo> words,
            int index,
            Set<Character> usedChars,
            List<String> currentWords,
            int currentUniqueCount,
            Result bestResult) {

        if (currentUniqueCount > bestResult.uniqueCharCount) {
            bestResult.words = new ArrayList<>(currentWords);
            bestResult.uniqueCharCount = currentUniqueCount;
        }

        for (int i = index; i < words.size(); i++) {
            WordInfo wordInfo = words.get(i);

            // Check for conflict
            boolean conflict = false;
            for (char c : wordInfo.chars) {
                if (usedChars.contains(c)) {
                    conflict = true;
                    break;
                }
            }

            if (!conflict) {
                // Choose
                usedChars.addAll(wordInfo.chars);
                currentWords.add(wordInfo.word);

                backtrack(words, i + 1, usedChars, currentWords,
                        currentUniqueCount + wordInfo.chars.size(), bestResult);

                // Unchoose
                usedChars.removeAll(wordInfo.chars);
                currentWords.remove(currentWords.size() - 1);
            }
        }
    }

    private static class WordInfo {
        String word;
        Set<Character> chars;

        static WordInfo from(String word) {
            Set<Character> set = new HashSet<>();
            for (char c : word.toCharArray()) {
                if (!set.add(c)) {
                    return null; // duplicate letter, invalid word
                }
            }
            WordInfo wi = new WordInfo();
            wi.word = word;
            wi.chars = set;
            return wi;
        }
    }

    private static class Result {
        List<String> words = new ArrayList<>();
        int uniqueCharCount = 0;
    }
}

