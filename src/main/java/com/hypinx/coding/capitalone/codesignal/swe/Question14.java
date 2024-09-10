package com.hypinx.coding.capitalone.codesignal.swe;

import java.util.*;

import java.util.*;

public class Question14 {
    public static void main(String[] args) {
        int[] target = {1,3,1,3,2};
        int[] expected = {1,3,3,2};
        int[] res = solution(target);
        System.out.println(Arrays.equals(expected, res));
    }

    public static int[] solution(int[] target) {
        List<Integer> result = new ArrayList<Integer>();
        if (target.length <= 2) return target;

        result.add(target[0]);
        for (int i = 1; i < target.length - 1; i++) {
            if (target[i] > Math.max(target[i-1], target[i+1])) {
                result.add(target[i]);
            }
        }

        result.add(target[target.length - 1]);
        return result.stream().mapToInt(Integer::intValue).toArray();
    }

}