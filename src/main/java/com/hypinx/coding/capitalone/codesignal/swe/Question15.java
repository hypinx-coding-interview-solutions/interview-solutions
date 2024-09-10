package com.hypinx.coding.capitalone.codesignal.swe;

import java.util.*;

public class Question15 {
    public static void main(String[] args) {
        int[][] operations = new int[6][];
        operations[0] = new int[]{1,2};
        operations[1] = new int[]{1,5};
        operations[2] = new int[]{2,3,2};
        operations[3] = new int[]{2,3,3};
        operations[4] = new int[]{2,1,1};
        operations[5] = new int[]{2,1,2};

        String expected = "1010";
        String result = solution(operations);
        System.out.println(expected.equals(result));
    }

    public static String solution(int[][] operations) {
        TreeSet<Integer> obstacles = new TreeSet<>();
        StringBuilder result = new StringBuilder();
        for (int[] operation: operations) {
            if (operation[0] == 1) {
                obstacles.add(operation[1]);
            } else {
                Integer obstacle = obstacles.ceiling(operation[1]);
                if (obstacle == null || obstacle >= operation[1] + operation[2]) {
                    result.append("1");
                } else {
                    result.append("0");
                }
            }
        }

        return result.toString();
    }
}