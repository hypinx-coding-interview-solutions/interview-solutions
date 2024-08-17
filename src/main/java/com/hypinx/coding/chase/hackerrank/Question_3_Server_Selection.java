package com.hypinx.coding.chase.hackerrank;

import com.hypinx.coding.misc.TestCaseValidator;

import java.util.Arrays;

public class Question_3_Server_Selection {

    public static void main(String[] args) {
        int[] server = {1,1,1};
        int expectedLoad = 4;
        int result = getMinServers(expectedLoad, server);
        int expected = -1;

        TestCaseValidator.validateTestCase("1", expected, result);

        server = new int[]{1,1,2,4,4};
        expectedLoad = 10;
        result = getMinServers(expectedLoad, server);
        expected = 3;

        TestCaseValidator.validateTestCase("2", expected, result);
    }

    public static int getMinServers(int expectedLoad, int[] server) {
        // Sort the server array in descending order
        // Since Arrays.sort does not support reverse order on primitives, we will sort normally
        Arrays.sort(server);

        int minServers = 0;
        int totalRequests = 0;
        int i = server.length - 1; // Start from the largest element

        // Iterate through the sorted servers to find the minimum number of servers needed
        while (totalRequests < expectedLoad && i >= 0) {
            if (totalRequests + server[i] <= expectedLoad) {
                totalRequests += server[i];
                minServers++;
            }
            i--;
        }

        // If the total requests match the expected load, return the number of servers
        if (totalRequests == expectedLoad) {
            return minServers;
        } else {
            return -1;
        }
    }
}
