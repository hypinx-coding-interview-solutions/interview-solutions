package com.hypinx.coding.chase.hackerrank;

import com.hypinx.coding.misc.TestCaseValidator;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;

public class Question_2_Chess_Tournament {

    public static void main(String[] args) {
        List<Integer> potential = Arrays.asList(1,3,2,4,5);
        long k = 2;
        int expected = 3;
        int result = getPotentialOfWinner(potential, k);

        TestCaseValidator.validateTestCase("1", expected, result);

        potential = Arrays.asList(3,2,1,4);
        k = 3;
        expected = 4;
        result = getPotentialOfWinner(potential, k);
        TestCaseValidator.validateTestCase("2", expected, result);

    }

    public static int getPotentialOfWinner(List<Integer> potential, long k) {
        Queue<Integer> queue = new ArrayDeque<>(potential);
        int winner = -1;
        long kCount = k;

        int p1 = -1, p2 = -1;
        while (true) {
            if (p1 == -1) {
                p1 = queue.poll();
            }
            if (p2 == -1) {
                p2 = queue.poll();
            }
            if (p1 > p2) {
                queue.add(p2);
                if (winner != p1) {
                    kCount = k;
                }
                winner = p1;
                p2 = -1;
            } else {
                queue.add(p1);
                if (winner != p2) {
                    kCount = k;
                }
                winner = p2;
                p1 = -1;
            }

            if (--kCount == 0) return winner;
        }
    }
}
