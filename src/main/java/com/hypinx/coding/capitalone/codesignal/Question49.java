package com.hypinx.coding.capitalone.codesignal;

import java.util.HashSet;
import java.util.Set;

public class Question49 {

    public static void main(String[] args) throws Exception {
        String[] words1 = new String[]{"back", "backdoor", "gammon", "backgammon", "comeback", "come", "door"};
        int expected1 = 3;
        int result1 = solution(words1);
        if (expected1 != result1) {
            throw new Exception("Input 1: Expected " + expected1 + " but got " + result1);
        }

        String[] words2 = new String[]{"abc", "a", "a", "b", "ab", "ac"};
        int expected2 = 8;
        int result2 = solution(words2);
        if (expected2 != result2) {
            throw new Exception("Input 1: Expected " + expected2 + " but got " + result2);
        }

        String[] words3 = new String[]{"co", "code", "codesi", "codesign", "codesignal"};
        int expected3 = 10;
        int result3 = solution(words3);
        if (expected3 != result3) {
            throw new Exception("Input 3: Expected " + expected3 + " but got " + result3);
        }
    }

    /**
     * This solution may not pass with optimal performance on codesignal but it is a working solution
     */
    public static int solution(String[] words) {
        Set<String> cache = new HashSet<>();
        int count = 0;
        for (int i = 0; i < words.length - 1; i++) {
            String first = words[i];
            for (int j = i + 1; j < words.length; j++) {
                String second = words[j];
                if (cache.contains(first.concat(":").concat(second)) ||
                        cache.contains(second.concat(":".concat(first)))) {
                    count++;
                } else {
                    if (first.equals(second) || first.startsWith(second) || second.startsWith(first)) {
                        count++;
                        cache.add(first.concat(":").concat(second));
                    }
                }
            }
        }

        return count;
    }
}
