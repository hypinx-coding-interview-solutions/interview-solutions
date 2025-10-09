package com.hypinx.coding.meta.livecoding.wordlist.problem.test.java.com.meta.app;

import com.hypinx.coding.meta.livecoding.wordlist.problem.src.main.java.com.meta.app.Helpers;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SolverTest {

    private int validate(List<String> language, List<String> selection) throws Exception {
        // Make sure the selected words are actually in the language
        for (String word : selection) {
            if (!language.contains(word)) {
                throw new Exception(word + "not in language");
            }
        }

        // Make sure the words contain no duplicate characters
        int numberOfCharacters = 0;
        for (String word : selection) {
            numberOfCharacters += word.length();
        }

        Set<Character> uniqueCharacters = new HashSet<>();
        for (String word : selection) {
            for (char c : word.toCharArray()) {
                uniqueCharacters.add(c);
            }
        }

        if (numberOfCharacters != uniqueCharacters.size()) {
            throw new Exception("Number of characters does not equal unique characters");
        }

        return numberOfCharacters;
    }

    public void testMonths() throws Exception {
        List<String> months = Helpers.getWords("months");
        // TODO: Implement solve() then call it for real
        List<String> solution = Arrays.asList("feb", "mar", "jun", "oct");
        int n = validate(months, solution);
        if (n != 12) {
            throw new Exception("Excepted 12 but got " + n);
        }
    }

    public void testPrimes() throws Exception {
        List<String> primes = Helpers.getWords("primes");
        // TODO: Implement solve() then call it for real
        List<String> solution = Arrays.asList("59", "67", "283", "401");
        int n = validate(primes, solution);
        if (n != 10) {
            throw new Exception("Excepted 10 but got " + n);
        }
    }
}
