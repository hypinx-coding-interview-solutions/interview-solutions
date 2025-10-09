package com.hypinx.coding.meta.livecoding.wordlist.problem.test.java.com.meta.app;

import com.hypinx.coding.meta.livecoding.wordlist.problem.src.main.java.com.meta.app.Helpers;
import com.hypinx.coding.misc.TestCaseValidator;

import java.util.List;
import java.util.Map;

public class HelpersTest {

    public void testAlphabet() {
        List<String> primes = Helpers.getWords("primes");
        List<Character> alphabet = Helpers.getAlphabet(primes);

        TestCaseValidator.validateTestCase("1", List.of("0", "1", "2", "3", "4", "5", "6", "7", "8", "9"), alphabet);
    }

    public void testFrequency() {
        List<String> months = Helpers.getWords("months");
        System.out.println("Size of months = " + months.size());
        months.forEach(System.out::println);

        Map<Character, Integer> frequencies = Helpers.getLetterFrequencies(months);

        TestCaseValidator.validateTestCase("1", Integer.valueOf(5), frequencies.get('a'));
        TestCaseValidator.validateTestCase("2", Integer.valueOf(2), frequencies.get('o'));
        TestCaseValidator.validateTestCase("3", Integer.valueOf(1), frequencies.get('f'));

    }

}
