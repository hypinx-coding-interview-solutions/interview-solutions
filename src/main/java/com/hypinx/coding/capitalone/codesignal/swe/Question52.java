package com.hypinx.coding.capitalone.codesignal.swe;

import com.hypinx.coding.misc.TestCaseValidator;

import java.util.Map;
import java.util.TreeMap;

public class Question52 {

    public static void main(String[] args) {
        int[][] lamps = new int[][]{{-2,3}, {2,3}, {2,1}};
        int result = solution(lamps);
        int expected = 1;

        TestCaseValidator.validateTestCase("1", expected, result);

        lamps = new int[][]{{-2,1}, {2,1}};
        result = solution(lamps);
        expected = -3;

        TestCaseValidator.validateTestCase("2", expected, result);

        lamps = new int[][]{{-1000000000, 100000}};
        result = solution(lamps);
        expected = -1000100000;

        TestCaseValidator.validateTestCase("3", expected, result);
    }

    static int solution(int[][] lamps) {
        TreeMap<Integer, Integer> lightChanges = new TreeMap<>();

        for (int[] lamp : lamps) {
            int start = lamp[0] - lamp[1];
            int end = lamp[0] + lamp[1];

            lightChanges.put(start, lightChanges.getOrDefault(start, 0) + 1);
            lightChanges.put(end + 1, lightChanges.getOrDefault(end + 1, 0) - 1);
        }

        int maxIllumination = 0, currentIllumination = 0, bestCoordinate = -1;

        for (Map.Entry<Integer, Integer> entry : lightChanges.entrySet()) {
            currentIllumination += entry.getValue();
            if (currentIllumination > maxIllumination) {
                maxIllumination = currentIllumination;
                bestCoordinate = entry.getKey();
            }
        }

        return bestCoordinate;
    }

}
