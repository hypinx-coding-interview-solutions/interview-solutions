package com.hypinx.coding.meta.virtualonsite;

public class Question7 {

    public static void main(String[] args) {
        String s1 = "abca";
        boolean output1 = isPalindromeWithOneRemoval(s1);
        boolean expected1 = true;
        System.out.println(output1 == expected1);

        String s2 = "a";
        boolean output2 = isPalindromeWithOneRemoval(s1);
        boolean expected2 = true;
        System.out.println(output2 == expected2);

        String s3 = "abcd";
        boolean output3 = isPalindromeWithOneRemoval(s1);
        boolean expected3 = false;
        System.out.println(output1 == expected1);
    }

    public static boolean isPalindromeWithOneRemoval(String s) {
        if (s == null || s.length() <= 1) {
            return true; // An empty string or a string with one character is already a palindrome.
        }

        int left = 0;
        int right = s.length() - 1;

        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                // Check if removing either the left or right character results in a palindrome.
                if (isPalindrome(s, left + 1, right) || isPalindrome(s, left, right - 1)) {
                    return true;
                } else {
                    return false;
                }
            }

            left++;
            right--;
        }

        return true; // The string is already a palindrome.
    }

    private static boolean isPalindrome(String s, int start, int end) {
        while (start < end) {
            if (s.charAt(start) != s.charAt(end)) {
                return false;
            }
            start++;
            end--;
        }
        return true;
    }
}
