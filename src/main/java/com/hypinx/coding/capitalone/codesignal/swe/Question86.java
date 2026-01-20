package com.hypinx.coding.capitalone.codesignal.swe;

import com.hypinx.coding.misc.TestCaseValidator;

public class Question86 {

    public static void main(String[] args) {
        char[] recording = new char[]{'W', 'w', 'a', 'A', 'a', 'b', 'B'};
        int expected = 2;
        int result = solution(recording);

        TestCaseValidator.validateTestCase("1", expected, result);
    }

    public static int solution(char[] recording) {
        int result = 0;

        if (recording.length <= 1) return 0;

        for (int i = 1; i < recording.length; i++) {
            char current = Character.toLowerCase(recording[i]);
            char prev = Character.toLowerCase(recording[i-1]);
            if (current != prev) result++;
        }

        return result;
    }

}
