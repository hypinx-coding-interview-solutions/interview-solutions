package com.hypinx.coding.capitalone.codesignal.swe;

import com.hypinx.coding.misc.TestCaseValidator;

/**
 * Code doesn't work, needs to be corrected
 */
public class Question55 {

    public static void main(String[] args) {
        int[] serverPowers = new int[]{1,2,1,2,1};
        String[] events = new String[]{"REQUEST",  "REQUEST",  "FAIL 2",  "REQUEST",  "FAIL 3",  "REQUEST",  "REQUEST"};
        int expected = 1;
        int result = solution(serverPowers, events);

        TestCaseValidator.validateTestCase("1", expected, result);

    }

    public static int solution(int[] serversPowers, String[] events) {
        int n = serversPowers.length; // Number of servers
        int[] remainingCapacity = serversPowers.clone(); // Track current capacity for each server
        boolean[] failed = new boolean[n]; // Track which servers are failed
        int lastUsedServer = -1; // Track the last server that handled a request
        int currentServer = 0; // Start with the first server

        // Iterate through each event
        for (String event : events) {
            if (event.equals("REQUEST")) {
                // Find the next available server to handle the request
                int attempts = 0;
                while ((failed[currentServer] || remainingCapacity[currentServer] == 0) && attempts < n) {
                    currentServer = (currentServer + 1) % n; // Move to next server cyclically
                    attempts++;
                }

                // If a valid server was found, process the request
                if (attempts < n) {
                    remainingCapacity[currentServer]--;
                    lastUsedServer = currentServer; // Update the last used server

                    // Move to the next server for the next request
                    currentServer = (currentServer + 1) % n;
                }
            } else if (event.startsWith("FAIL")) {
                // Extract the index of the server to fail
                int failedServer = Integer.parseInt(event.split(" ")[1]);
                failed[failedServer] = true; // Mark the server as failed
            }

            // Reset capacities if we've cycled back to the first server
            if (currentServer == 0) {
                for (int i = 0; i < n; i++) {
                    if (!failed[i]) {
                        remainingCapacity[i] = serversPowers[i];
                    }
                }
            }
        }

        return lastUsedServer; // Return the last server that handled a request
    }
}
