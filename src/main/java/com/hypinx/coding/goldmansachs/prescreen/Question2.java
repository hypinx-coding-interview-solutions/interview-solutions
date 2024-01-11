package com.hypinx.coding.goldmansachs.prescreen;

import java.util.*;

public class Question2 {

    public static void main(String[] args) {
        String[][] scores = new String[][]{
                {"Bob", "87"},
                {"Mike", "35"},
                {"Eric", "50"},
                {"Mike", "100"}
        };

        // Averages: Bob 87, Mike 67.5 -> 67, Eric 50
        int expected = 87;
        int result = hishestAverage(scores);
        System.out.println(expected == result);

    }

    /**
     * Time Complexity:
     * N = input length
     * K = student length
     *
     * Runtime is N + K division operations, but since K is usually pretty small it's O(n)
     *
     * Space Complexity: O(N)
     * Explaination - We are creating a map with string as key and list of integers which are scores. This map will essentially be the same
     * size as the input
     */
    public static int hishestAverage(String[][] scores) {
        if(scores == null || scores.length == 0) {
            return -1;
        }
        int highestAve = 0;
        Map<String, List<Integer>> map = new HashMap<>();

        for(int i = 0; i < scores.length; i++) {
            List<Integer> scoreList = map.get(scores[i][0]);
            if(scoreList == null) {
                List<Integer> currentScore = new ArrayList<>();
                currentScore.add(Integer.valueOf(scores[i][1]));
                map.put(scores[i][0], currentScore);
            } else {
                scoreList.add(Integer.valueOf(scores[i][1]));
                map.put(scores[i][0], scoreList);
            }
        }

        //go through the map. find the largest ave
        for(Map.Entry<String, List<Integer>> entry : map.entrySet()) {
            int currentAveScore = aveCalculate(entry.getValue());

            highestAve = Math.max(highestAve, currentAveScore);
        }

        return highestAve;
    }

    private static int aveCalculate(List<Integer> scores) {
        int len = scores.size();
        int sum = 0;
        for(int score : scores) {
            sum += score;
        }

        float ave = sum / len;
        return (int) ave;
    }
}