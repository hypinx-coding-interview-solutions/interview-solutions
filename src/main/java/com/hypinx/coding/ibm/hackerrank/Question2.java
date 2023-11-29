package com.hypinx.coding.ibm.hackerrank;

import java.util.List;

public class Question2 {
    public static void main(String[] args) {
        List<Integer> coins = List.of(1,1,0,1);
        int expected = 2;
        int result = playSegments(coins);
        System.out.println(expected == result);
    }

    public static int playSegments(List<Integer> coins) {
        if (coins.size() == 1) return coins.get(0) == 1 ? 1 : 0;

        int[] left = new int[coins.size() + 1];
        int[] right = new int[coins.size() + 1];
        left[0] = 0;
        right[right.length - 1] = 0;

        // Build left victory path
        for (int i = 1; i < left.length; i++) {
            int current = coins.get(i - 1) == 0 ? -1 : 1;
            left[i] = left[i-1] + current;
        }

        // Build right victory path
        for (int i = right.length - 2; i >= 0; i--) {
            int current = coins.get(i) == 0 ? -1 : 1;
            right[i] = right[i+1] + current;
        }

        for (int i = 0; i < left.length; i++) {
            if (left[i] > right[i]) {
                return i;
            }
        }
        return 0;
    }
}
