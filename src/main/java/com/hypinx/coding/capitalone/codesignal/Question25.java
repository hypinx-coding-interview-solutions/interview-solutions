package com.hypinx.coding.capitalone.codesignal;

public class Question25 {
    public static void main(String[] args) {
        String[] words = {"aaab", "abbb", "caddie", "code", "aaaab", "baaaa"};
        int n = 3;
        int count = countInterestingWords(words, n);
        System.out.println("Number of interesting words: " + count);
    }

    public static int countInterestingWords(String[] words, int n) {
        int count = 0;
        for (String word : words) {
            if (isInteresting(word, n)) {
                count++;
            }
        }
        return count;
    }

    public static boolean isInteresting(String word, int n) {
        for (int i = 0; i <= word.length() - n; i++) {
            char alpha = word.charAt(i);
            int j = i + 1;
            boolean hasDifferentCharBefore = (i > 0 && word.charAt(i - 1) != alpha);
            boolean hasDifferentCharAfter = (j < word.length() && word.charAt(j) != alpha);

            while (j < word.length() && word.charAt(j) == alpha) {
                j++;
            }

            if (j - i >= n && (hasDifferentCharBefore || i == 0) && (hasDifferentCharAfter || j == word.length())) {
                return true;
            }
        }
        return false;
    }

}
