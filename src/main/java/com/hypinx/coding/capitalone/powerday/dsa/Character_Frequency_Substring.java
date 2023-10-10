package com.hypinx.coding.capitalone.powerday.dsa;

/*
Asked in interview for Arjun Poudel on December 21, 2022

Question:
Given the following, write a function to return the frequency of a character in any substring of a string.
 • A string of characters
 • A character to find
 • Starting index of the substring
 • Ending index of the substring

S= "capitalone"
c = 'a'
// O(n)
assert freq_count(S, c, 0, 4) == 1
assert freq_count(S, c, 0, 9) == 2

2 Part Question

1. Write a solution that is linear
2. Write a optimized solution (uses a cache)

As an optional method, write a test case in JUnit to test method 2

*/

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Character_Frequency_Substring {

	// This map will hold the cache with respect to the input string to make sure we don't rebuild the cache every single time
	// This cache is declared as a global variable to use in SolutionTwo, SolutionOne does not use this
	private static Map<String, Map<Character, List<Integer>>> cacheMap = new HashMap<>();

	public static void main (String[] args) {
		String input = "capitalone";
		char find = 'a';
		System.out.println(solutionOne(input, find, 0, 4));
		System.out.println(solutionOne(input, find, 0, input.length() - 1));

		System.out.println(solutionTwo(input, find, 0, 4));
		System.out.println(solutionTwo(input, find, 0, 9));
	}

	/*

	Solution 1: This is first solution where we simply does one pass over the string. We start and end from the index
	values specified. We need to implement a check here to ensure we never go out of bounds so we have 4 conditions 
	in the if statement that check out boundaries.

	If we ever go out of bounds we can return -1. Otherwise if we pass the boundary check we can do a for loop
	and for each character in the string we check if it equals the character we are searching for. If so we can
	increment the counter value for it and return the counter.

	The time complexity is linear O(N) and space complexity is O(1) because we only use one variable to store the value.
	
	*/
	public static int solutionOne(String input, char c, int start, int end) {
		int count = 0;
		// Boundaries of searching
		if (start < 0 || start > end || end < start || end >= input.length()) {
			return -1;
		}
		for (int i = start; i <= end; i++) {
			if (input.charAt(i) == c) count++;
		}

		return count;
	}

	/*

	Solution 2: Optimized solution uses a memory cache. The idea is we can use a map as a cache. The key will be the associated
	characters we have and for it's corresponding value we use a List that stores the index of where the value occurred. Then
	based on the start/end index we can query the map for the character we are searching for to find all occurrences of that
	character by index value. And we just iterate over this list and see how many index values we have that fall between 
	our start/end index of search.

	We can optimize it further by using another Map with key string and value to be the respective cache for that string. In the event
	we are using the same input string we have to keep building the cache. So if we can store the cache we built for the string and if 
	we ever come across that input string again we can simply retrieve the cache we generated prior.

	*/

	public static int solutionTwo(String input, char c, int start, int end) {
		// Cache is the respective cache used for the current input string.
		// We check if the global cacheMap contains a pre built cache already, if it does we retrieve that value otherwise we 
		// call the buildCache method to build the cache
		Map<Character, List<Integer>> cache;
		if (!cacheMap.containsKey(input)) {
			cache = buildCache(input);
			cacheMap.put(input, cache);
		} else {
			cache = cacheMap.get(input);
		}
		
		// Get the respective list of index values for the character we are searching for	
		List<Integer> listFind = cache.get(c);

		// Loop over the list and for each index value between the start/end index, we increment count
		int count = 0;
		for (int el : listFind) {
			if (el >= start && el <= end) count++;
		}

		return count;
	}

	// Helper method in solution 2 to build cache
	public static Map buildCache(String input) {
		// Edge case, if the input is null we return null
		if (input == null) return null;


		Map<Character, List<Integer>> cache = new HashMap<Character, List<Integer>>();

		// Loop over the input string, for each character we check if that key is present. If the key is present get the associated
		// list for that key and add the index value of the occurrence for that character. If the key is NOT present then we add
		// in a new value and create an empty list and add the index value we came across
		for (int i = 0; i < input.length(); i++) {
			char current = input.charAt(i);
			if (!cache.containsKey(current)) {
				List<Integer> list = new ArrayList<Integer>();
				list.add(i);
				cache.put(current, list);
			} else {
				List<Integer> currentList = cache.get(current);
				currentList.add(i);
				cache.put(current, currentList);
			}
		}

		return cache;
	}
}