package com.hypinx.coding.meta.livecoding.wordlist.solution.test.java.com.meta.app;

import com.hypinx.coding.meta.livecoding.wordlist.problem.src.main.java.com.meta.app.Helpers;
import com.hypinx.coding.meta.livecoding.wordlist.solution.src.main.java.com.meta.app.Solver;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SolverTest {

    private int validate(List<String> language, List<String> selection) throws Exception {
        // Make sure the selected words are actually in the language
        for (String word : selection) {
            if (!language.contains(word)) {
                throw new Exception(word + " not in language");
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
        List<String> months = List.of("jan", "feb", "mar", "apr", "may", "jun", "jul", "aug", "sep", "oct", "nov", "dec");
        // TODO: Implement solve() then call it for real
//        List<String> solution = Arrays.asList("feb", "mar", "jun", "oct");
        List<String> solution = Solver.solve(months);
        int n = validate(months, solution);
        if (n != 12) {
            throw new Exception("Excepted 12 but got " + n);
        }
    }

    public void testPrimes() throws Exception {
        List<String> primes = List.of(
            "2", "3", "5", "7", "11", "13", "17", "19", "23", "29",
            "31", "37", "41", "43", "47", "53", "59", "61", "67", "71",
            "73", "79", "83", "89", "97", "101", "103", "107", "109", "113",
            "127", "131", "137", "139", "149", "151", "157", "163", "167", "173",
            "179", "181", "191", "193", "197", "199", "211", "223", "227", "229",
            "233", "239", "241", "251", "257", "263", "269", "271", "277", "281",
            "283", "293", "307", "311", "313", "317", "331", "337", "347", "349",
            "353", "359", "367", "373", "379", "383", "389", "397", "401", "409",
            "419", "421", "431", "433", "439", "443", "449", "457", "461", "463",
            "467", "479", "487", "491", "499", "503", "509", "521", "523", "541",
            "547", "557", "563", "569", "571", "577", "587", "593");

        // TODO: Implement solve() then call it for real
        List<String> solution = Solver.solve(primes);
        int n = validate(primes, solution);
        if (n != 10) {
            throw new Exception("Excepted 10 but got " + n);
        }
    }
}
