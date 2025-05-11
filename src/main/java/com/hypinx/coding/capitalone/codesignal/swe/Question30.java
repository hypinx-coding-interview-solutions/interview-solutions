package com.hypinx.coding.capitalone.codesignal.swe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * This solution only passes around 12/20 test cases on Codesignal
 */
public class Question30 {

    public static void main (String[] args) {

        long startTime = System.currentTimeMillis();
        int[][] lamps1 = new int[][]{{1,7}, {5,11}, {7,9}};
        int[] points1 = new int[]{7,1,5,10,9,15};
        int[] exp1 = new int[]{3,1,2,1,2,0};
        int[] res1 = optimizedSolution(lamps1, points1);
        System.out.println(Arrays.equals(exp1, res1));

        int[][] lamps2 = new int[][]{{2,9}, {6,13}, {8,10}};
        int[] points2 = new int[]{6,8,1,6};
        int[] exp2 = new int[]{2,3,0,2};
        int[] res2 = optimizedSolution(lamps2, points2);
        System.out.println(Arrays.equals(exp2, res2));

        int[][] lamps3 = new int[][]{{1,1000000}};
        int[] points3 = new int[]{1,10,1000,100000};
        int[] exp3 = new int[]{1,1,1,1};
        int[] res3 = optimizedSolution(lamps3, points3);
        System.out.println(Arrays.equals(exp3, res3));
        long elapsedTime = System.currentTimeMillis() - startTime;
        System.out.println("Time to execute = " + (elapsedTime / 1000.0));
    }

    /**
     * This solution passes 12/20 tests on Codesignal. See optimized solution
     */
    public static int[] solution(int[][] lamps, int[] points) {
        // Sort the lamps based on their starting segment of coverage
        Arrays.sort(lamps, Comparator.comparingInt(a -> a[0]));
        int[] result = new int[points.length];
        int counter = 0;

        // Iterate over all the points
        for (int point : points) {
            // Check every lamp to see if the point falls within the interval
            int lampsThatCover = 0;
            for (int i = 0; i < lamps.length; i++) {
                int[] curr = lamps[i];
                if (point >= curr[0] && point <= curr[1]) {
                    lampsThatCover++;
                }
            }

            // Update result
            result[counter++] = lampsThatCover;
        }

        return result;
    }

    /**
     * This solution has not been tested on Codesignal
     */
    public static int[] optimizedSolution(int[][] lamps, int[] points) {
        int n = points.length;
        int[][] queries = new int[n][2];
        for (int i = 0; i < n; i++) {
            queries[i][0] = points[i]; // point value
            queries[i][1] = i;         // original index
        }

        List<int[]> events = new ArrayList<>();

        // Lamp events: (+1) for start, (-1) for end+1
        for (int[] lamp : lamps) {
            events.add(new int[]{lamp[0], 1});
            events.add(new int[]{lamp[1] + 1, -1});
        }

        // Query events: mark with a 0 to distinguish
        for (int[] query : queries) {
            events.add(new int[]{query[0], 0, query[1]});
        }

        // Sort events:
        // If equal x: +1 (start) comes before 0 (query) comes before -1 (end)
        events.sort((a, b) -> {
            if (a[0] != b[0]) return Integer.compare(a[0], b[0]);
            return Integer.compare(b[1], a[1]);
        });

        int activeLamps = 0;
        int[] result = new int[n];

        for (int[] event : events) {
            if (event[1] == 1) {
                activeLamps++; // Lamp started
            } else if (event[1] == -1) {
                activeLamps--; // Lamp ended
            } else {
                // Query
                int pointIndex = event[2];
                result[pointIndex] = activeLamps;
            }
        }

        return result;
    }

    // Utility function for debugging pursposes
    public static void print2dArray(int[][] array) {
        for (int i = 0; i < array.length; i++) {
            int[] sub = array[i];
            for (int j = 0; j < sub.length; j++) {
                System.out.print(sub[j] + " ");
            }
            System.out.println();
        }
    }
}