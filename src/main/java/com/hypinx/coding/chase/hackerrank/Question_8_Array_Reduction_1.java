package com.hypinx.coding.chase.hackerrank;

import com.hypinx.coding.misc.TestCaseValidator;

import java.util.List;
import java.util.PriorityQueue;

public class Question_8_Array_Reduction_1 {

    public static void main(String[] args) {
        List<Integer> num = List.of(4,6,8);
        int expected = 28;
        int result = reductionCost(num);

        TestCaseValidator.validateTestCase("1", expected, result);

        num = List.of(1,2,3);
        expected = 9;
        result = reductionCost(num);

        TestCaseValidator.validateTestCase("2", expected, result);

        num = List.of(1,2,3,4);
        expected = 19;
        result = reductionCost(num);

        TestCaseValidator.validateTestCase("3", expected, result);


    }

    public static int reductionCost(List<Integer> num) {
        if (num.size() < 2) return 0;

        PriorityQueue<Integer> queue = new PriorityQueue<>(num);
        int totalCost = 0;
        while(queue.size() > 1) {
            int firstMin = queue.poll(), secondMin = queue.poll();
            int currentCost = firstMin + secondMin;
            totalCost += currentCost;
            queue.offer(currentCost);
        }

        return totalCost;
    }
}
