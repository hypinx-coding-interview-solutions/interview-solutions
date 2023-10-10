package com.hypinx.coding.capitalone.powerday.dsa;

/*
Given an integer n, return the number of prime numbers that are strictly less than or equal to n.

Example:

n = 0 or n = 1 -> 0
n = 2 -> 1
n = 3 -> 2
n = 4 -> 2
n = 5 -> 3
n = 7 -> 4
n = 100 -> 25
n = 1000 -> 168

Verbal Approach:

So we can use an iterative approach to solve this problem. The brute force method would be to take every number between [1,n] and then we run a nested 
loop and check if there is any number that is divisble into this number besides 1 and itself. If so then it's not prime and we can exit. The time complexity
for this is O(N^2) as we have to check every combination.

An optimized approach is that we know our base cases which is for n = 0 or n = 1 we have no prime numbers. So we can form an array of booleans with size n + 1. 
Every index value will correspond to whether the index number is prime or not. So for example, index 5 refers to the number 5 and if the value is true it is
NOT a prime number. By default everything will be set to false because we are assuming all numbers are prime and we will set index 0 and 1 to true since we 
know these are NOT prime numbers. Then for every number from 2 to square root of n we will iterate over multiples of this and mark the corresponding index as true.
We only need to iterate over sqrt(n) because every number after will be from some multiple or can be formed as a square root of other numbers. So for example 
if we have n = 100 we start with 2 and every multiple of 2 like 4,6,8,10 we will set to true. Then we move to the next index which is 3, if this is not set to
true already we can process again for this number as it is a prime. Basically we are taking every prime number and marking all of its multiples up until sqrt(n) 
as true since they cannot be a prime number. In the end we will be left with a boolean array of false values which will be the prime numbers in our range. 
We can just sum over this at the end.

Time Complexity is linear O(N) because even though we run the outer loop until sqrt(n) we are still running the inner loop
for every multiple of the prime up until N.

Space Complexity is constant O(N) because we use a boolean array of size n+1 to store true/false values pertaining to 
whether a number is prime or not

*/
public class Prime_Numbers_To_N {

    public static void main(String[] args) {
        System.out.println("N = 2 | res = " + countPrimes(2));
        System.out.println("N = 3 | res = " + countPrimes(3));
        System.out.println("N = 4 | res = " + countPrimes(4));
        System.out.println("N = 5 | res = " + countPrimes(5));
        System.out.println("N = 7 | res = " + countPrimes(7));
        System.out.println("N = 100 | res = " + countPrimes(100));
        System.out.println("N = 100 | res = " + countPrimes(1000));
    }
    public static int countPrimes(int n) {
        // No prime numbers for n < 2
        if (n < 2) {
            return 0;
        }

        // We declare an array of booleans of length n+1. The index value will be associated with the number. Index 0 will be for 0, index 1
        // will be for 1, and so on. Index n will be for the value of n.
        boolean[] numbers = new boolean[n + 1];
        // We set 0 and 1 to true because by default these are not prime numbers
        numbers[0] = true;
        numbers[1] = true;

        // We will loop from 2 until the sqrt of n. If the value is false it means we are on a prime number. Then every multiple of that
        // number we can set to false because we know it's not a prime number. If the number is true that means we came across it before
        // and set it to be a non-prime number, so we do nothing. We only want to loop until sqrt(n) because essentially every number
        // after this will have been addressed as we are searching for multiples. Only the prime numbers will remain as false.
        for (int p = 2; p <= (int)(Math.sqrt(n)); ++p) {
            if (numbers[p] == false) {
                for (int j = p*p; j <= n; j += p) {
                    numbers[j] = true;
                }
            }
        }
        
        int numberOfPrimes = 0;
        // Once we are done processing numbers, every value in the boolean array that is left as false will be a prime number.
        // So we sum them up.
        for (int i = 2; i <= n; i++) {
            if (numbers[i] == false) {
                ++numberOfPrimes;
            }
        }
        
        return numberOfPrimes;
    }
}
