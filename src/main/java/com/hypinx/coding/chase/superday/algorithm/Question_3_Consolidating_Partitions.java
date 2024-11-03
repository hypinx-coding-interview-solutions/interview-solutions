package com.hypinx.coding.chase.superday.algorithm;

import com.hypinx.coding.misc.TestCaseValidator;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Question_3_Consolidating_Partitions {

    public static void main(String[] args) {
        List<Integer> used = List.of(1,2,3);
        List<Integer> totalCapacity = List.of(3,3,3);
        int expected = 2;
        int result = minPartitions(used, totalCapacity);

        TestCaseValidator.validateTestCase("1", expected, result);

        used = List.of(3,2,1,3,1);
        totalCapacity = List.of(3,5,3,5,5);
        expected = 2;
        result = minPartitions(used, totalCapacity);

        TestCaseValidator.validateTestCase("2", expected, result);

    }

    public static int minPartitions(List<Integer> used, List<Integer> totalCapacity) {
        int minPartitions = 0;

        // Sort based on larger partition capacity first
        List<Integer> sortedTotalCapacity = totalCapacity.stream()
                .sorted(Collections.reverseOrder())
                .collect(Collectors.toList());

        // Compute the sum of the capacity the current units are using
        int totalUsedCapacity = 0;
        for (int currentUsed: used) {
            totalUsedCapacity += currentUsed;
        }

        /*
        Loop over the sorted total capacity list. Substract the partition capacity
        from the total current used capacity. Then increment minPartitions required
        by 1 to indicate a new partition is used. If we still have current used
        capacity remaining, we will continue. Otherwise we can exit the loop early
         */
        for (int i = 0; i < totalCapacity.size(); i++) {
            int currentTotalCapacity = sortedTotalCapacity.get(i);
            totalUsedCapacity -= currentTotalCapacity;
            minPartitions++;
            if (totalUsedCapacity <= 0) break;
        }

        return minPartitions;
    }

}
