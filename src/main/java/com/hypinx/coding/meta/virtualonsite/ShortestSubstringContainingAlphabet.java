package com.hypinx.coding.meta.virtualonsite;

import java.util.HashMap;
import java.util.Map;

/**
 * 2.	Write a function that takes an input string and an alphabet, and returns the shortest substring of the input which contains every letter of the alphabet at least once.
 *
 * Input:  "aaccbc"
 * Alphabet: "abc"
 * Output:  "accb"
 *
 * Time Complexity: The time complexity of this algorithm is O(N), where N is the length of the input string.
 *
 * Space Complexity: The space complexity is O(K), where K is the size of the alphabet.
 *
 * Code walkthrough with example (steps) - INCASE YOU ARE ASKED TO STEP THROUGH THE GIVEN INPUT EXAMPLE!
 *
 * Execution:
 *
 * 1. Initialize variables:
     * alphabetPositions is a map to store positions of characters in the alphabet, initially set to {'a': -1, 'b': -1, 'c': -1}.
     * requiredCharacters is set to the length of the alphabet, which is 3.
     * left and right pointers start at 0.
     * minLength and minStart are initially set to Integer.MAX_VALUE.
 * 2. Start iterating through the input string using the right pointer:
     * At right = 0, we encounter 'a'. Since 'a' is part of the alphabet, we update its position to 0, and requiredCharacters becomes 2.
     * At right = 1, we encounter 'a' again. We update its position to 1, but since it's already positioned in the alphabet, we don't decrement requiredCharacters.
     * At right = 2, we encounter 'c'. We update its position to 2. requiredCharacters remains 2.
     * At right = 3, we encounter 'c' again. We update its position to 3, but requiredCharacters remains 2.
     * At right = 4, we encounter 'b'. We update its position to 4, and requiredCharacters becomes 1.
 * 3 . Now, we enter the shrinking phase (while requiredCharacters == 0):
     * At left = 0, we have a potential valid substring "aaccb" with length 5. Since it's shorter than minLength (which is initially Integer.MAX_VALUE), we update minLength to 5, and minStart to 0.
     * We move the left pointer one position to the right (left = 1) and continue.
     * At left = 1, we have "acccb" with length 4. It's still shorter than minLength, so we update minLength to 4 and minStart to 1.
     * We move the left pointer one more position to the right (left = 2) and continue.
     * At left = 2, we have "cccb" with length 3. It's shorter than minLength, so we update minLength to 3 and minStart to 2.
     * We move the left pointer one more position to the right (left = 3) and continue.
     * At left = 3, we have "ccb" with length 2. It's shorter than minLength, so we update minLength to 2 and minStart to 3.
     * We move the left pointer one more position to the right (left = 4) and continue.
     * At left = 4, we have "cb" with length 2. It's shorter than minLength, so we update minLength to 2 and minStart to 4.
     * We move the left pointer one more position to the right (left = 5), and the shrinking phase ends as requiredCharacters is no longer 0.
 * 4. Finally, we return the substring starting from minStart with length minLength, which is "accb," and that's the shortest substring containing all characters from the alphabet.

 Conclusion:
 * So, in this example, the code correctly identifies that the shortest substring containing all characters 'a', 'b', and 'c' is "accb."
 */
public class ShortestSubstringContainingAlphabet {
    public static String findShortestSubstring(String input, String alphabet) {
        // Check for edge cases
        if (input == null || input.isEmpty() || alphabet == null || alphabet.isEmpty()) {
            return "";
        }

        // Create a map to store the positions of characters in the alphabet
        Map<Character, Integer> alphabetPositions = new HashMap<>();
        int requiredCharacters = alphabet.length();
        int left = 0;
        int minLength = Integer.MAX_VALUE;
        int minStart = 0;

        // Initialize the alphabet positions map
        for (int i = 0; i < alphabet.length(); i++) {
            alphabetPositions.put(alphabet.charAt(i), -1);
        }

        // Iterate through the input string using the right pointer
        for (int right = 0; right < input.length(); right++) {
            char rightChar = input.charAt(right);

            // Update alphabet positions map if the character is part of the alphabet
            if (alphabetPositions.containsKey(rightChar)) {
                if (alphabetPositions.get(rightChar) == -1) {
                    requiredCharacters--;
                }
                alphabetPositions.put(rightChar, right);
            }

            // Shrink the window from the left while maintaining the required characters
            while (requiredCharacters == 0) {
                if (right - left + 1 < minLength) {
                    minLength = right - left + 1;
                    minStart = left;
                }

                char leftChar = input.charAt(left);
                if (alphabetPositions.containsKey(leftChar)) {
                    if (alphabetPositions.get(leftChar) == left) {
                        requiredCharacters++;
                    }
                }
                left++;
            }
        }

        // If minLength is not updated, there is no valid substring
        if (minLength == Integer.MAX_VALUE) {
            return "";
        }

        // Return the shortest valid substring
        return input.substring(minStart, minStart + minLength);
    }

    public static void main(String[] args) {
        String input = "aaccbc";
        String alphabet = "abc";
        String expectedOutput = "accb";
        String result = findShortestSubstring(input, alphabet);
        System.out.println(result.equals(expectedOutput)); // Output: "accb"
    }
}
