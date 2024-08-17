package com.hypinx.coding.chase.hackerrank;

import com.hypinx.coding.misc.TestCaseValidator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Question_5_System_And_Strings {

    public static void main(String[] args) {
        List<String> input = Arrays.asList("lgzpc", "lchxlo", "xrwzg");
        List<String> expected = Arrays.asList("Chris", "Alex", "Chris");
        List<String> result = getWho(input);

        TestCaseValidator.validateTestCase("1", expected, result);
    }

    public static List<String> getWho(List<String> s) {
        List<String> result = new ArrayList<>();
        String vowels = "aeiou";

        for (String str : s) {
            int[] vowelByIndex = new int[str.length() + 1];
            vowelByIndex[0] = 0;
            int vowelCount = 0;
            int counter = 1;
            int highestOddCounterIndex = -1;
            for (char c : str.toCharArray()) {
                if ((vowels.indexOf(c) != -1)) {
                    vowelCount++;
                    // If odd mark the index
                    if (vowelCount % 2 == 1) highestOddCounterIndex = counter;
                    vowelByIndex[counter++] = vowelCount;
                } else {
                    vowelByIndex[counter++] = vowelByIndex[counter - 1];
                }
            }

            // Odd
            if (vowelCount % 2 == 1) {
                result.add("Alex");
            } else {
                // Remove highest odd counter substring
                if (highestOddCounterIndex == -1) {
                    // No odd value found
                    result.add("Chris");
                } else {
                    int res = vowelByIndex[vowelByIndex.length - 1] - vowelByIndex[highestOddCounterIndex];
                    if (res % 2 == 0) {
                        // If even Chris can remove entire string
                        result.add("Chris");
                    } else {
                        // If odd then has no move
                        result.add("Alex");
                    }
                }
            }
        }
        return result;
    }
}
