package com.hypinx.coding.capitalone.codesignal.swe;

import java.util.HashMap;
import java.util.Map;

public class Question21 {

    public static int[] solution(int[] queries) {
        int n = queries.length;
        int[] result = new int[n];

        // Initialize a map to store the houses and their contiguous segment lengths.
        Map<Integer, Integer> houseSegments = new HashMap<>();
        int maxSegment = 0;

        for (int i = 0; i < n; i++) {
            int house = queries[i];

            // Calculate the length of the contiguous segment after placing the house.
            int leftLength = houseSegments.getOrDefault(house - 1, 0);
            int rightLength = houseSegments.getOrDefault(house + 1, 0);
            int currentSegment = leftLength + 1 + rightLength;

            // Update the contiguous segment lengths for neighboring houses.
            houseSegments.put(house - leftLength, currentSegment);
            houseSegments.put(house + rightLength, currentSegment);

            // Update the maximum segment length.
            maxSegment = Math.max(maxSegment, currentSegment);

            result[i] = maxSegment;
        }

        return result;
    }

    public static void main(String[] args) {
        int[] queries = {2, 1, 3};
        int[] result = solution(queries);

        for (int i = 0; i < result.length; i++) {
            System.out.println(result[i]);
        }
    }
}