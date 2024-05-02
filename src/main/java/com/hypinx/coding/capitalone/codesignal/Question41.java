package com.hypinx.coding.capitalone.codesignal;

public class Question41 {

    public static void main(String[] args) throws Exception {
        int sample1 = 52134;
        int expected1 = 3;
        int result1 = solution(sample1);
        if (expected1 != result1) {
            throw new Exception("Expected " + expected1 + " but result was " + result1);
        }

        int sample2 = 12345;
        int expected2 = 3;
        int result2 = solution(sample2);
        if (expected2 != result2) {
            throw new Exception("Expected " + expected2 + " but result was " + result2);
        }

        int sample3 = 104956;
        int expected3 = -5;
        int result3 = solution(sample3);
        if (expected3 != result3) {
            throw new Exception("Expected " + expected3 + " but result was " + result3);
        }

    }

    public static int solution(int n) {
        String number = String.valueOf(n);

        int sum = 0;

        for (int i = 0; i < number.length(); i++) {
            int num = Integer.parseInt(number.charAt(i) + "");

            if (i % 2 == 0) {
                sum += num;
            } else {
                sum -= num;
            }
        }

        return sum;
    }
}
