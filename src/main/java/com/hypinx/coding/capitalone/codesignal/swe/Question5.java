package com.hypinx.coding.capitalone.codesignal.swe;

public class Question5 {
    public static void main(String[] args) {
        int[] numbers = new int[]{3,3,5,2,3};
        int res = solution(numbers);
        System.out.println(res == 6);
    }

    static int solution(int[] numbers) {
        int finalSolution = 0;
        while(true) {
            int nonZero = -1, index = -1;
            for (int i = 0; i < numbers.length; i++) {
                if (numbers[i] > 0) {
                    nonZero = numbers[i];
                    index = i;
                    break;
                }
            }

            if (nonZero < 0) break;

            for (; index < numbers.length; index++) {
                if (numbers[index] < nonZero) break;
                numbers[index] -= nonZero;
            }

            finalSolution += nonZero;
        }

        return finalSolution;
    }
}