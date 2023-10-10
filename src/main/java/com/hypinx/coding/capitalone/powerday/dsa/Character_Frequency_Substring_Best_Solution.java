package com.hypinx.coding.capitalone.powerday.dsa;

import java.util.HashMap;
import java.util.Map;

/*
*** NOTE: This is the best solution that can run in constant time after we perform pre processing once. ***
*
Problem Statement:

Question:
Given the following, write a function to return the frequency of a character in any substring of a string.
 • A string of characters
 • A character to find
 • Starting index of the substring
 • Ending index of the substring

Sample Inputs:
S= "capitalone"
c = 'a'
// O(n)
assert freq_count(S, c, 0, 4) == 1 <-- Sample output for start index 0, end index 4
assert freq_count(S, c, 0, 9) == 2 <-- Sample output for start index 0, end index 9

Verbal Approach:

So one approach I can think of that will run in linear time would be to do a single pass over a string. We are checking
every element and just seeing if its equal to the target element and if so we can keep track of the count through a variable. 
This will result in constant space complexity. But the downside to this approach is that if we have a really long string say
10 million characters and we are using this input many times it will be slow and inefficient.

I think a better approach to this problem would be to keep track of the occurrence of every possible character at every index.
So we are dynamically building the result set of our solution for each index of the string. So for example if our string is capitalone
and the letter we are searching for is a, we will have an array the size of the input string length + 1. Every index will indicate
how many times we saw the letter 'a' up until the current index. We will do this for all characters. Then at the very end we can
return the difference of the values at the corresponding start and end index in the array.

* You can start to write this array out in a comment to showcase what you are talking about *
So for example let's say we are dynamically building out the results for character 'a'. The
array would consist of [0,0, 1, 1, 1, 1, 2, 2, 2, 2, 2]
Now if we have start 0, and end = 9, we can check the value at index 9 which is 2 and value at index 0 which is 0. 
The difference of this is 2 so our result would be 2.

The downside is we would lose a lot of time in pre processing and we use up more space but every iteration after with the 
same input string we have constant time.

We will need to use caching to maintain our pre processed data. We will need a global cache which can be a hash map that will 
store the string as the input and the value will be a map with a character as the key and value will be an integer
array the size of input length + 1. The beginning value will be 0 because up until index 0 we have not seen any values before.

* You can write the next line in a comment to showcase *
Map<String, Map<Character, int[]>> memoization = new HashMap<>();

Everytime we iterate over the map to update the individual trackers for every character, we can check the current character at the 
input string, if it is equal to the character map we are checking we take the previous occurrence + 1 and store that in the index value.
Otherwise we can simply take the previous value in the character array and use that as the current since we did not see an occurrence to update.

* You can begin to write code now from main method and downwards*
 */

public class Character_Frequency_Substring_Best_Solution {

    private static Map<String, Map<Character, int[]>> memoization = new HashMap<>();

    public static void main(String[] args) {
        // So this is the sample input we have searching for the character 'a'.
        String s = "capitalone";
        char c = 'a';

        // I'm going to store the output in a variable called res which means result and hardcode the value in a variable called exp 
       // which is what we are expecting. I'll also write another method for validation as if we are writing a unit test case for a real application
        int res1 = frequency(s, c, 0, 4);
        int exp1 = 1;

        int res2 = frequency(s, c, 0, 9);
        int exp2 = 2;

        System.out.println(validateResult(res1, exp1));
        System.out.println(validateResult(res2, exp2));
    }

    // This is the method for validating our result. The reason I am writing a separate method is because the way we validate 
    // may change in the future so we just have to update our logic in this one component. For now we are just checking if the
    //result matches with our expected value
    public static boolean validateResult(int res, int exp) {
        return res == exp;
    }

    public static int frequency(String s, char c, int start, int end) {
        // So we have a couple edge cases to check, we want to ensure we are in the proper boundaries for the substring. 
        // The boundaries would be if start is less than 0, start is greater than end, end is less than start, or end is greater
        // than or equal to the input string length
        if (start < 0 || start > end || end < start || end >= s.length()) {
            // We can return a dummy value like -1 because we know a frequency can never be less than 0. So this can be used
           // to indicate invalid inputs were passed
            return -1;
        }

        // Now we can declare our cache
        Map<Character, int[]> cache;
        // We can first check if we have built this cache already by checking our global cache. If so we can just retrieve that cache 
       // otherwise we have to preprocess
        if (!memoization.containsKey(s)) {
            // If our string is not in the global cache then we want to pre process the input and add it to the global cache for future use.
            cache = preProcess(s);
            memoization.put(s, cache);
        } else {
            // Otherwise if the string exists we can just retrieve it
            cache = memoization.get(s);
        }

        // * PAUSE HERE AND WRITE OUT THE PRE PROCESS METHOD BEFORE CONTINUING FURTHER - STARTING AT LINE 117 *
        // Now we can simply get the occurrence of the target character at the end index and substract from the start index and that is our answer
        return cache.get(c)[end] - cache.get(c)[start];

    }

    // So now I'll write out the pre process method.

    public static Map<Character, int[]> preProcess(String s) {
        Map<Character, int[]> cache = new HashMap<>();

        // We will use a for loop to initialize our cache with an array size of input string + 1 and add to our cache
        for (int i = 0; i < s.length(); i++) {
            char current = s.charAt(i);
            // For every character we will initialize its respective array and add to our cache
            cache.put(current, new int[s.length() + 1]);
        }

        // Now we iterate over the input string once more
        for (int i = 0; i < s.length(); i++) {
            char current = s.charAt(i);
            // For every character in the input string we will iterate over all of our possible characters in our cache map.
            for (Map.Entry<Character, int[]> entry : cache.entrySet()) {
                // We will extract which character in the map we are on and its respective list of occurrences up until each index
                char mapCurrentChar = entry.getKey();
                int[] mapCurrentList = entry.getValue();

                // If the current character map we are on matches with the character in the string we found another occurrence
                if (mapCurrentChar == current) {
                    // So we set the next value to be the previous + 1
                    mapCurrentList[i + 1] = mapCurrentList[i] + 1;
                } else {
                    // If the current character we are on is different than the character map we are working with, we just set it to the previous value
                    mapCurrentList[i+1] = mapCurrentList[i];
                }
            }
        }

        return cache;
    }
}
