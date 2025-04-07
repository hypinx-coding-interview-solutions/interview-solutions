package com.hypinx.coding.chase.hackerrank;

import com.hypinx.coding.misc.TestCaseValidator;

import java.util.List;
import java.util.TreeMap;

public class Question_19_CPU_Scheduling {

    public static void main(String[] args) {
        List<Integer> start = List.of(1, 2, 3);
        List<Integer> end = List.of(3, 3, 5);
        int expected = 3;
        int result = getMinCores(start, end);
        TestCaseValidator.validateTestCase("1", expected, result);

        start = List.of(1, 4, 7);
        end = List.of(2, 4, 10);
        expected = 1;
        result = getMinCores(start, end);
        TestCaseValidator.validateTestCase("2", expected, result);

    }

    public static int getMinCores(List<Integer> start, List<Integer> end) {
        if (start == null || end == null || start.size() == 0) return 0;

        TreeMap<Integer, Integer> timeline = new TreeMap<>();

        // Step 1: Register all events
        for (int i = 0; i < start.size(); i++) {
            // Start of a process → add +1
            timeline.put(start.get(i), timeline.getOrDefault(start.get(i), 0) + 1);
            // End of a process (inclusive) → add -1 to the next time
            timeline.put(end.get(i) + 1, timeline.getOrDefault(end.get(i) + 1, 0) - 1);
        }

        int currentActive = 0;
        int maxCores = 0;

        // Step 2: Sweep through the timeline
        for (int time : timeline.keySet()) {
            currentActive += timeline.get(time);
            maxCores = Math.max(maxCores, currentActive);
        }

        return maxCores;
    }
}
