package com.hypinx.coding.capitalone.codesignal;

import java.util.*;

public class Question1 {
    public static void main(String[] args) {
        int[][] lamps = new int[3][2];
        lamps[0] = new int[]{-2,3};
        lamps[1] = new int[]{2,3};
        lamps[2] = new int[]{2,1};

        int res = solution(lamps);
        System.out.println(res == 6);
    }

    static int solution(int[][] lamps) {
        int leftBound = 0, rightBound = 0, frequency = 0, count = 0;
        Map<Integer, Integer> map = new TreeMap<>();
        for (int[] current : lamps) {
            leftBound = (current[0] - current[1]);
            rightBound = (current[0] + current[1] + 1);
            map.put(leftBound, (map.getOrDefault(leftBound , 0) + 1));
            map.put(rightBound, (map.getOrDefault(rightBound, 0) - 1));
        }

        Integer last = null;
        for (int positionKey : map.keySet()) {
            if (frequency == 1) {
                if (last == null) {
                    count++;
                } else {
                    count += positionKey - last;
                }
            }
            frequency += map.get(positionKey);
            last = positionKey;
        }
        return count;
    }
}