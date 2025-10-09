package com.hypinx.coding.meta.livecoding.wordlist.problem.src.main.java.com.meta.app;

import com.hypinx.coding.meta.livecoding.wordlist.problem.test.java.com.meta.app.HelpersTest;
import com.hypinx.coding.meta.livecoding.wordlist.problem.test.java.com.meta.app.SolverTest;
import org.h2.command.dml.Help;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        String language = "months";
        List<String> words = Helpers.getWords(language);
        List<String> solution = Solver.solve(words);
        System.out.println("The solution for " + language + " is " + solution);
    }

    private static void runHelperTests() throws Exception {
        HelpersTest helpersTest = new HelpersTest();
        helpersTest.testFrequency();
        helpersTest.testAlphabet();

        SolverTest solverTest = new SolverTest();
        solverTest.testMonths();
        solverTest.testPrimes();
    }
}
