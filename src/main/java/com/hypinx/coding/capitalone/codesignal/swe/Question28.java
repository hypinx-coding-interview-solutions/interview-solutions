package com.hypinx.coding.capitalone.codesignal.swe;

import java.util.Arrays;

public class Question28 {

    public static void main(String[] args) {
        int[] state1 = new int[]{0,0,0,0,0,0,0,0,0,0};
        String[] operations1 = new String[]{"L", "L", "C0", "L", "C3"};
        String result1 = "1100000000";
        System.out.println(solution(state1, operations1).equals(result1));

        int[] state2 = new int[]{1,0};
        String[] operations2 = new String[]{"L", "L", "C1"};
        String result2 = "10";
        System.out.println(solution(state2, operations2).equals(result2));

        int[] state3 = new int[]{0,1,1};
        String[] operations3 = new String[]{"C2", "C2", "C2", "C2", "L", "L"};
        String result3 = "111";
        System.out.println(solution(state3, operations3).equals(result3));
    }

    public static String solution(int[] state, String[] operations) {
        int leftBitIndex = -1;
        for (String op: operations) {
            if (op.contains("L")) {
                // Set left most bit from 0 -> 1
                leftBitIndex = setLeftMostBitToZero(state, leftBitIndex);
            } else {
                // Set C[index] = 1;
                int indexToSetToZero = Integer.parseInt(op.split("C")[1]);
                leftBitIndex = setStateToZero(state, leftBitIndex, indexToSetToZero);
            }
        }
        System.out.println(Arrays.toString(state));
        return convertToString(state);
    }

    public static int setStateToZero(int[] state, int leftBitIndex, int indexToSetToZero) {
        state[indexToSetToZero] = 0;
        // If the left most bit is still less than the index we set to 0, return the existing leftMostBit. Otherwise return the new index we set to zero
        return leftBitIndex < indexToSetToZero ? leftBitIndex : indexToSetToZero;
    }

    public static int setLeftMostBitToZero(int[] state, int leftBitIndex) {
        int i = 0;
        // Start at i = leftMostBit
        if (leftBitIndex != -1) {
            i = leftBitIndex;
        }
        for (; i < state.length; i++) {
            if (state[i] == 0) {
                state[i] = 1;
                return leftBitIndex;
            }
        }

        // If we go over the entire loop and don't return it means all numbers are set to 1 already. So we can return 0
        return 0;
    }

    public static String convertToString(int[] state) {
        StringBuilder builder = new StringBuilder();
        for (int el : state) {
            builder.append(el);
        }

        return builder.toString();
    }

}
