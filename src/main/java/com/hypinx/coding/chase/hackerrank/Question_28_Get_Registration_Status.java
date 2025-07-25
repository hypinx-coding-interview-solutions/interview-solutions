package com.hypinx.coding.chase.hackerrank;

import com.hypinx.coding.misc.TestCaseValidator;

import java.util.*;

public class Question_28_Get_Registration_Status {

    public static void main(String[] args) {
        List<String> input = List.of("password1", "password1", "password1");
        List<String> result = getRegistrationStatus(input, 2);
        List<String> expected = List.of("ACCEPT", "ACCEPT", "REJECT");

        TestCaseValidator.validateTestCase("1", expected, result);
    }

    private static List<String> getRegistrationStatus(List<String> passwords, int k) {
        List<String> result = new ArrayList<>();
        Set<String> cappedPasswords = new HashSet<>();
        Map<String, Integer> passwordCount = new HashMap<>();

        for (String password : passwords) {
            if (cappedPasswords.contains(password)) {
                result.add("REJECT");
                continue;
            }

            int count = passwordCount.getOrDefault(password, 0) + 1;
            passwordCount.put(password, count);

            if (count > k) {
                result.add("REJECT");
                cappedPasswords.add(password);
            } else {
                result.add("ACCEPT");
            }
        }

        return result;
    }
}
