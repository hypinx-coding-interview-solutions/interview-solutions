package com.hypinx.coding.capitalone.powerday.dsa;

import java.util.ArrayList;
import java.util.List;


/* Question asked on 12/14/2022 Power Day for Risit Jambudi
Interviewer - Paul Lawrence (substitute for Amy Mir)

// For all pairs of digits where there are exactly 3 question marks between them, do all pairings add up to 10.

1. So we are only concerned about ?'s and the first numbers that occur on the left and right side over a pair of exactly 3 ?'s.
The rest of the characters can be considered dummy characters and we ignore correct?
2. Can we assume that our input will contain at least one set of 3 ?'s surrounded by 2 numbers?

/*
Sample inputs:

("arrb6???4xxbl5???eee5", True),
("acc?7??sss?3rr1??????5", True),
("5??aaaaaaaaaaaaaaaaaaa?5?5", True),
("9???1???9???1???9", True),
("aa6?9", False),
("8???2???9", False), | True
("10???0???10", False),
("aa3??oiuqwer?7???2", False) | True

Verbal Approach:

So I think what we can do is take the input string and we can pass it through a method that will do some pre processing on it.
What we are trying to do with pre processing is to clean up the string and remove any dummy characters we have that are not useful. 
This will result in us doing a single pass over the string. Once we have our refined string we can use a sliding window approach.
So basically our window will consist of 5 characters. Since we only care about 3 consecutive ?'s in the center, we can build a
window of size 5. Every iteration we can check if the middle 3 characters are question marks, if so we can attempt to convert the edge values
into integers. If we are successful we can compute the sum and see if it matches the target value. Otherwise if we fail at any of these steps
we can slide the window over essentially removing the first element in the window and adding a new one at the end.



// Main class should be named 'Solution'

Time Complexity Analysis:
// Time: O(N) | Space O(N + M) where N = length of input string and M = length of refined string

This runs in linear time because we need to do pre processing on the string and remove the characters we don't care about.
This will result in doing a single pass on the string.

The space is the sum of the size of the original input + refined string.

*/

class Three_Question_Mark_Sum {
    public static void main(String[] args) {
        String input = "aa3??oiuqwer?7???2";
        
        String refinedString = refineInput(input);
        System.out.println(refinedString);
        
        System.out.println(valid(refinedString, 10));
    }
    

    // Do a single pass on the string to remove the dummy characters
    public static String refineInput(String input) {
        // We want to use string builder here as we want the string to be mutable
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            char current = input.charAt(i);
            if (Character.isDigit(current) || current == '?') {
                builder.append(current);
            }
        }
        
        return builder.toString();
    }
    
    // Once we have our refined string we can call the valid method on it. We pass in the refined string and the target sum
    public static boolean valid(String input, int sum) {
        // Since we only care about 3 question marks and 2 numbers around them, if our input is less than 5 we can return
        // false as it's not possible to achieve the conditions
        if (input.length() < 5) return false;
        
        // We will use a sliding window approach and move the window over the string. For every pass we will remove the
        // first element in the window and append a new one to the end. Then we can check if the middle 3 elements are
        // question marks, if so we can check the edge values to see if they are numbers. If all the conditions pass
        // then we are able to sum the numbers and see the result at the end.
        List<Character> windows = new ArrayList<>();
        
        // Build initial window
        for (int i = 0; i < 5; i++) {
            windows.add(input.charAt(i));
        }
        
        // This end vairable is used to make sure we don't go out of bounds
        int end = 4;
        
        // Setting up a while loop to pass over the entire string
        while (end < input.length()) {
            // Condition to check the sum
            if (windows.get(1) == '?' && windows.get(2) == '?' && windows.get(3) == '?') {
                
                // If the middle 3 characters are question marks in the string we attempt to convert the edge values to
                // numbers
                try {
                    
                    int leftNumber = Character.getNumericValue(windows.get(0));
                    int rightNumber = Character.getNumericValue(windows.get(4));
                    // If we are successful in checking if they are numbers and it sums to the target sum, we can return true
                    if (leftNumber + rightNumber == sum) {
                        return true;
                    }
                } catch (Exception e) {
                    // If we have 3 question marks but failed edge numbers, we can catch the exception and do nothing and continue
                }
            }
            // Increment the end pointer to move the right boundary up of the window up by 1
            end++;
            // If we are still not out of bounds we can add in the new value to the window and remove the front value
            if (end < input.length()) {
                windows.add(input.charAt(end));
                windows.remove(0);
            }
        }
        
        // If we didn't reach the exit condition in the while loop it means we were not successful and can return false
        return false;
    }
}
