package com.hypinx.coding.capitalone.codesignal.swe;

import java.util.*;

public class Question11 {
    public static void main(String[] args) {
        int[] arr = new int[]{1,5,7,3,2,1};
        int[] expected = new int[]{1,5,3,7,1,2};
        int[] res = solution(arr);
        System.out.println(Arrays.equals(arr, res));
    }

    public static int[] solution(int[] arr) {
        for (int i = 0; i < arr.length - 1; i += 2) {
            if (arr[i] > arr[i + 1]) {
                int temp = arr[i];
                arr[i] = arr[i + 1];
                arr[i + 1] = temp;
            }
        }
        return arr;
    }

}