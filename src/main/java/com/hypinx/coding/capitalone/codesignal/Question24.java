package com.hypinx.coding.capitalone.codesignal;

public class Question24 {
    public static void main(String[] args) {
        int[] arr = new int[]{1,2,1,-4,5,10};
        int[] expected = new int[]{0,1,0,1};
        int[] result = solution(arr);

        System.out.println(equals(result, expected));
    }

    public static boolean equals(int[] exp, int[] res) {
        for (int i = 0; i < exp.length; i++) {
            if (exp[i] != res[i]) return false;
        }
        return true;
    }

    public static int[] solution(int[] arr) {
        int[] res = new int[arr.length - 2];
        int counter = 0;
        for (int i = 1; i < arr.length - 1; i++) {
            int first = arr[i-1], second = arr[i], third = arr[i+1];
            if ((second > first && third > second) || (first > second && second > third)) {
                res[counter++] = 1;
            } else {
                res[counter++] = 0;
            }
        }

        return res;
    }
}