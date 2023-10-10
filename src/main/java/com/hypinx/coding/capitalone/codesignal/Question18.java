package com.hypinx.coding.capitalone.codesignal;

public class Question18 {
    public static void main(String[] args) {

        int[] numbers1 = new int[]{1,1,1,0,0,0};
        int zerosToOne1 = 2;
        int expected1 = 13;
        int result1 = solution(numbers1, zerosToOne1);

        int[] numbers2 = new int[]{1,1};
        int zerosToOne2 = 2;
        int expected2 = 4;
        int result2 = solution(numbers2, zerosToOne2);


        System.out.println(expected1 == result1);
        System.out.println(expected2 == result2);
    }

    public static int solution(int[] numbers, int zeroToOne) {
        int zeros = 0, ones = 0, time = 0;
        for (int i = numbers.length - 1; i >= 0; i--) {
            if (numbers[i] == 0) {
                zeros++;
            } else {
                ones++;
            }
        }

        while (zeros + ones > 0) {
            if (zeros >= zeroToOne) {
                zeros -= zeroToOne;
                ones++;
            } else if (ones > 0) {
                ones--;
                zeros++;
            } else {
                break;
            }
            time++;
        }

        return time;
    }
}