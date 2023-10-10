package com.hypinx.coding.capitalone.codesignal;

public class Question7 {
    public static void main(String[] args) {
        int[] numbers = new int[]{1,5,4,10,9};
        int res = solution(numbers, 3);
        System.out.println(res == 4);


    }

    static int solution(int[] numbers, int separation) {
        int minimal = Integer.MAX_VALUE;
        int p2 = separation;
        for (int i = 0; i < numbers.length - separation; i++) {
            p2 = i + separation;
            int left = numbers[i];
            while (p2 < numbers.length) {
                int right = numbers[p2];
                int difference = Math.abs(left - right);
                if (difference < minimal) minimal = difference;
                p2++;
            }
        }

        return minimal;
    }
}