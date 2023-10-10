package com.hypinx.coding.capitalone.powerday.dsa;

import java.util.Arrays;

/* Prompt:

* ANYTHING IN QUOTATION MARKS IS NOT MEANT TO BE READ OUT LOUD. IT IS FOR YOUR REFERENCE

Given an array of numbers, find the pair of numbers that sum closest to zero.

Preliminary Questions to ask the interviewer:
1. What is more than 1 pair of numbers have the same sum closest to zero, do we pick the first result or any?
2. Is the input array containing numbers in random order everything?

Solutions - Verbal Explaination of thought process:
Bad Solution:
- The bad solution will involve using a nest loop. We can fix one number and loop through the rest summing all the possible pairs
while maintaining a record of the smallest absolute value of the sum. This is a bad solution because the 
time complexity if O(n^2) - "THIS IS READ AS "BIG O of N SQUARED". The space complexity will be constant ("O(1)") as we are
always maintaining a result array of size 2.

Optimized Solution:
- The optimized solution would be to sort the numbers which would require some time complexity, but if we have sorted values in 
some order we can use two pointers. One pointer will begin from the left side and another from the right side. If our numbers are
in ascending order then we know as we move the left pointer towards the right, the numbers will get larger hence our sum will increase.
If we move the rightmost pointer to the left, the numbers get smaller hence the sum goes down in value. Depending on what our sum is
we can adjust the pointers accordingly.

This will result in linear time complexity O(N) - "READ AS BIG O OF N". And space complexity will remain constant.

*/

public class Two_Sum_Closest_Zero {

	public static void main(String[] args) {
			int[] res = twoSumClosestToZero(new int[]{3, 7, 10, -4, -2, 10, 11});
			System.out.println(res[0] + "|" + res[1]);

			int[] inputOne = new int[]{2,8,7,0,-1,-5,3,-2};
			int[] inputTwo = new int[]{13,1,4,6,-2,-9,-0};

			int[] resultOne = twoSumClosestToZero(inputOne);
			int[] resultTwo = twoSumClosestToZero(inputTwo);
			System.out.println(resultOne[0] + "|" + resultOne[1]);
			System.out.println(resultTwo[0] + "|" + resultTwo[1]);

	}

	public static int[] twoSumClosestToZero(int[] array) {
		int[] result = new int[2];

		if (array.length <= 2) return array;

		int min = Integer.MAX_VALUE;
		Arrays.sort(array);

		int p1 = 0, p2 = array.length - 1;
		int numOne = array[0], numTwo = array[1];

		while(p1 < p2) {
			int sum = array[p1] + array[p2];
			if (Math.abs(sum) <= min) {
				min = Math.abs(sum);
				numOne = array[p1];
				numTwo = array[p2];
			}
			if (sum < 0) {
				p1++;
			} else {
				p2--;
			}
		}

		return new int[]{numOne, numTwo};
	}
}










