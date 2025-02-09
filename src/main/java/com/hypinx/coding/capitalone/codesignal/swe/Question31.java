package com.hypinx.coding.capitalone.codesignal.swe;

import com.hypinx.coding.misc.TestCaseValidator;

import java.util.*;

/**
 * Not yet complete, outputs are wrong
 */
public class Question31 {

    public static void main (String[] args) {
        int[][] centers1 = new int[][]{{1,1}, {2,2}, {0,4}};
        int exp1 = 2;
        int res1 = solution(centers1);
        TestCaseValidator.validateTestCase("1", exp1, res1);

        int[][] centers2 = new int[][]{{1,1}, {2,2}, {0,4}, {1,1}};
        int exp2 = 4;
        int res2 = solution(centers2);
        TestCaseValidator.validateTestCase("2", exp2, res2);
        System.out.println(exp2 == res2);

        int[][] centers3 = new int[][]{{0,0}, {0,2}, {2,0}, {2,2}};
        int exp3 = 6;
        int res3 = solution(centers3);
        TestCaseValidator.validateTestCase("3", exp3, res3);

        int[][] centers4 = new int[][]{{0,0}, {0,2}, {2,0}, {2,2}, {1,1}};
        int exp4 = 10;
        int res4 = solution(centers4);
        TestCaseValidator.validateTestCase("4", exp4, res4);
    }

    public static int solution(int[][] centers) {
        Map<String, List<int[]>> buckets = new HashMap<>();
        int overlapCount = 0;

        for (int[] center : centers) {
            int bucketX = center[0] / 3;
            int bucketY = center[1] / 3;
            String bucketKey = bucketX + "," + bucketY;

            // Check current and neighboring buckets
            for (int dx = -1; dx <= 1; dx++) {
                for (int dy = -1; dy <= 1; dy++) {
                    String neighborKey = (bucketX + dx) + "," + (bucketY + dy);
                    if (buckets.containsKey(neighborKey)) {
                        for (int[] other : buckets.get(neighborKey)) {
                            if (Math.abs(center[0] - other[0]) <= 2 && Math.abs(center[1] - other[1]) <= 2) {
                                overlapCount++;
                            }
                        }
                    }
                }
            }

            // Add the current center to its bucket
            buckets.computeIfAbsent(bucketKey, k -> new ArrayList<>()).add(center);
        }

        return overlapCount;
    }

    public static boolean distance(int x1, int y1, int x2, int y2) {
//        return Math.sqrt((Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2)));
        return Math.abs(x2 - x1) <= 2 && Math.abs(y2 - y1) <= 2;
    }

    private static String buildCoordinateString(int x1, int y1, int x2, int y2) {
        StringBuilder coordinateString = new StringBuilder();
        coordinateString.append("[");
        coordinateString.append(x1 + "," + y1);
        coordinateString.append("];[");
        coordinateString.append(x2 + "," + y2);
        coordinateString.append("]");
        return coordinateString.toString();
    }
}
