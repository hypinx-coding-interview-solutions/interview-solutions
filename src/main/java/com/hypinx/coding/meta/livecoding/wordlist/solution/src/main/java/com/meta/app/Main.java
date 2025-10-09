package com.hypinx.coding.meta.livecoding.wordlist.solution.src.main.java.com.meta.app;

import com.hypinx.coding.meta.livecoding.wordlist.solution.test.java.com.meta.app.HelpersTest;
import com.hypinx.coding.meta.livecoding.wordlist.solution.test.java.com.meta.app.SolverTest;

public class Main {

    public static void main(String[] args) throws Exception {
//        String language = "months";
//        List<String> words = Helpers.getWords(language);
//        List<String> solution = Solver.solve(words);
//        System.out.println("The solution for " + language + " is " + solution);

        runHelperTests();
        runSolverTests();
    }

    private static void runHelperTests() {
        HelpersTest helpersTest = new HelpersTest();
        helpersTest.testFrequency();
        helpersTest.testAlphabet();
    }

    private static void runSolverTests() throws Exception {
        SolverTest solverTest = new SolverTest();
        solverTest.testMonths();
        solverTest.testPrimes();
    }
}
