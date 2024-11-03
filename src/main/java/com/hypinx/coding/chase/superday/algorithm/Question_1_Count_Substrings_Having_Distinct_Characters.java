package com.hypinx.coding.chase.superday.algorithm;

public class Question_1_Count_Substrings_Having_Distinct_Characters {

    public static int findSubstrings(String s) {
        // Edge case - if the input is empty return 0
        if (s.isEmpty()) return 0;
        int sizeOfString = s.length();

        int result = 0;
        int[] charFrequency = new int[26];

        int left = 0, right = 0;

        // Loop over every character from left to right
        while (left < sizeOfString) {
            // If the character the right pointer is at has not been seen before we update
            // the frequency counter array and update the result with (right - left + 1)
            // to show all the possible substrings can be formed given the gap between
            // the left and right pointer
            if (right < sizeOfString && (charFrequency[s.charAt(right) - 'a'] == 0)) {
                // Increment count of right character
                charFrequency[s.charAt(right) - 'a']++;
                result += (right - left) + 1;
                right++;
            } else {
                // If the right pointer character has been seen before, decrement the count to reset it
                // And move the left pointer up
                charFrequency[s.charAt(left) - 'a']--;
                left++;
            }
        }

        return result;
    }

}
