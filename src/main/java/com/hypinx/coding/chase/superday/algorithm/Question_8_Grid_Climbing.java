package com.hypinx.coding.chase.superday.algorithm;

import com.hypinx.coding.misc.TestCaseValidator;

import java.util.List;

public class Question_8_Grid_Climbing {

    public static void main(String[] args) {
        List<List<Integer>> grid = List.of(
                List.of(1,1,1),
                List.of(0,1,0),
                List.of(0,0,0),
                List.of(1,1,0)
        );

        int expected = 5;
        int result = numberOfConnections(grid);

        TestCaseValidator.validateTestCase("1", expected, result);
    }

    public static int numberOfConnections(List<List<Integer>> gridOfNodes) {
        int previousCount = 0, result = 0;

        for (List<Integer> currentRow : gridOfNodes) {
            // Every row we check, we set the current count equal to 0
            int currentCount = 0;

            for (int currentNode : currentRow) {
                // Check if we have a 1 node and add to the currentCount
                if (currentNode == 1) currentCount++;
            }

            // Result updates based on current node count times count from previous row
            result += currentCount * previousCount;

            // Only update the previous count if we had at least 1 node present, skip empty rows
            if (currentCount != 0) previousCount = currentCount;
        }

        return result;
    }
}
