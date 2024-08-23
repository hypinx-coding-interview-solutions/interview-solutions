package com.hypinx.coding.chase.superday.algorithm;

public class Question_1_Count_Substrings_Having_Distinct_Characters {
    public static int findSubstrings(String s) {
        if (s.isEmpty()) return 0;
        int sizeOfString = s.length();

        int result = 0;
        int[] charFrequency = new int[26];

        int left = 0, right = 0;

        while (left < sizeOfString) {
            // Check all characters in substring from left to right are distinct
            if (right < sizeOfString && (charFrequency[s.charAt(right) - 'a'] == 0)) {
                // Increment count of right character
                charFrequency[s.charAt(right) - 'a']++;
                // Add every substring ending with right pointer and starting with any index between left/right to result
                result += (right - left) + 1;
                right++;
            } else {
                // Move right pointer to the left
                charFrequency[s.charAt(left) - 'a']--;
                left++;
            }
        }

        return result;
    }

}
