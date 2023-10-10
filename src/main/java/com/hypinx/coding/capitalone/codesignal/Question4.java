package com.hypinx.coding.capitalone.codesignal;

public class Question4 {
    public static void main(String[] args) {
        int[] numbers = new int[]{1,3,0,-1,1,4,3};
        int pivot = 2;
        String res = solution(numbers, pivot);
        System.out.println(res == "smaller");
    }

    static String solution(int[] numbers, int pivot) {
        int countGreater = 0, countLess = 0;
        for (int el : numbers) {
            if (el > pivot) {
                countGreater++;
            } else if (el < pivot) {
                countLess++;
            }
        }

        String returnValue = "tie";
        if (countGreater > countLess) returnValue = "greater";
        if (countGreater < countLess) returnValue = "smaller";
        return returnValue;
    }
}