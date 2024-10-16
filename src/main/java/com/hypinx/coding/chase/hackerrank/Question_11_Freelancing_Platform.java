package com.hypinx.coding.chase.hackerrank;

import com.hypinx.coding.misc.TestCaseValidator;

import java.util.Arrays;
import java.util.List;

public class Question_11_Freelancing_Platform {

    public static void main(String[] args) {
        int numProjects = 2;
        List<Integer> projectId = List.of(1,1);
        List<Integer> bid = List.of(4,7);
        long expected = -1;
        long result = minCost(numProjects, projectId, bid);

        TestCaseValidator.validateTestCase("1", expected, result);

        projectId = List.of(0,1,0,1,1);
        bid = List.of(4,74,47,744,7);
        expected = 11;
        result = minCost(numProjects, projectId, bid);

        TestCaseValidator.validateTestCase("2", expected, result);

    }

    public static long minCost(int numProjects, List<Integer> projectId, List<Integer> bid) {
        long[] minBids = new long[numProjects];
        Arrays.fill(minBids, Long.MAX_VALUE);

        for (int i = 0; i < projectId.size(); i++) {
            int projId = projectId.get(i);
            int currentBid = bid.get(i);
            minBids[projId] = Math.min(minBids[projId], currentBid);
        }

        long totalCost = 0;

        for (int i = 0; i < numProjects; i++) {
            if (minBids[i] == Long.MAX_VALUE) return -1;
            totalCost += minBids[i];
        }

        return totalCost;
    }
}
