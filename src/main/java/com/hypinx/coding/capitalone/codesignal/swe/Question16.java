package com.hypinx.coding.capitalone.codesignal.swe;

public class Question16 {
    public static void main(String[] args) {
        int[] input1 = {4,3,1,4,4,5,5,1};
        int[] pattern1 = {1, 0, -1};
        int expected1 = 1;
        int actual1 = solution(input1, pattern1);

        int[] input2 = {5,7,7,9,9,11,11};
        int[] pattern2 = {1, 0, 1, 0};
        int expected2 = 2;
        int actual2 = solution(input2, pattern2);

        int[] input3 = {5,7,7,9,9,11,11};
        int[] pattern3 = {-1};
        int expected3 = 0;
        int actual3 = solution(input3, pattern3);

        System.out.println(expected1 == actual1);
        System.out.println(expected2 == actual2);
        System.out.println(expected3 == actual3);
    }

    public static int solution(int[] numbers, int[] pattern) {
        // 1 - greater than prev num
        // 0 - equal to prev num
        // -1 - less than prev num

        int count = 0;

        for (int i = 1; i <= numbers.length - pattern.length; i++) {

            boolean match = true;

            for (int j = 0; j < pattern.length; j++) {
                int patternValue = pattern[j];

                if (patternValue == 1) {
                    if (numbers[i+j] <= numbers[i+j-1]) {
                        match = false;
                        break;
                    }
                } else if (patternValue == 0) {
                    if (numbers[i+j] != numbers[i+j-1]) {
                        match = false;
                        break;
                    }
                } else {
                    if (numbers[i+j] >= numbers[i+j-1]) {
                        match = false;
                        break;
                    }
                }
            }

            if (match) count++;
        }

        return count;
    }
}