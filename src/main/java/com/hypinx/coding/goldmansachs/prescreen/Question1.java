package com.hypinx.coding.goldmansachs.prescreen;

import java.util.*;

public class Question1 {

    /**
     * Given Execution entry point.
     */
    public static void main(String[] args) {
        Solution solution = new Solution();
        if (solution.doTestsPass()) {
            System.out.println("All tests passed");
        } else {
            System.out.println("Tests failed");
        }
    }
}

class Solution {

    // Given
    private record Interval (int startTime, int endTime, int price) {}

    // Method to fill in for solution
    private List<Interval> getLowestPrices(List<Interval> vendors) {
        List<Interval> result = new ArrayList<>();

        for (Interval interval : vendors) {
            List<Interval> newResult = new ArrayList<>();

            int currentStart = interval.startTime;
            int currentEnd = interval.endTime;
            int currentPrice = interval.price;

            // Iterate and split intervals
            for (Interval existingInterval : result) {
                if (currentStart > existingInterval.startTime && currentStart <= existingInterval.endTime) {
                    newResult.add(new Interval(existingInterval.startTime, currentStart, existingInterval.price));
                } else {
                    if (currentEnd < existingInterval.endTime && currentEnd >= existingInterval.startTime) {
                        newResult.add(new Interval(currentEnd + 1, existingInterval.endTime, existingInterval.price));
                    }
                }
            }

            newResult.add(new Interval(currentStart, currentEnd, currentPrice));

            for (Interval existingInterval : result) {
                if (currentEnd < existingInterval.startTime || currentStart > existingInterval.endTime) {
                    newResult.add(existingInterval);
                }
            }

            result.clear();
            result.addAll(newResult);
        }

        // Sort by starting time on each interval
        Collections.sort(result, Comparator.comparingInt(interval -> interval.startTime));

        printResult(result);
        return result;
    }

    private static void printResult(List<Interval> list) {
        for (Interval interval : list) {
            System.out.println(interval.startTime + " | " + interval.endTime + " |" + interval.price);
        }
    }


    /** Given calling code
     * Returns true if the tests pass. Otherwise, false.
     */
    public boolean doTestsPass() {
        var inputList =  List.of(
                new Interval(1, 5, 20),
                new Interval(2, 8, 15),
                new Interval(7, 10, 8));
        var expectedList = List.of(
                new Interval(1, 2, 20),
                new Interval(2, 7, 15),
                new Interval(7, 10, 8));

        return expectedList.equals(getLowestPrices(inputList));
    }
}