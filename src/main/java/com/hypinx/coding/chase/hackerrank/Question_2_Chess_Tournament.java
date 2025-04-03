package com.hypinx.coding.chase.hackerrank;

import com.hypinx.coding.misc.TestCaseValidator;

import java.util.*;

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

    /**
     * This solution works but passes 11/15 test cases. Rest fail due to time complexity
     */
    /*
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
    */

    /**
     * This solution passes all test cases
     */
    public static int getPotentialOfWinner(List<Integer> potential, long k) {
        int maxPotential = Collections.max(potential); // shortcut for early return
        if (k >= potential.size()) return maxPotential;

        int currentWinner = potential.get(0);
        int consecutiveWins = 0;

        for (int i = 1; i < potential.size(); i++) {
            int challenger = potential.get(i);
            if (currentWinner > challenger) {
                consecutiveWins++;
            } else {
                currentWinner = challenger;
                consecutiveWins = 1;
            }
            if (consecutiveWins == k) return currentWinner;
        }

        // If no one hits k, maxPotential wins by default
        return currentWinner;
    }

}
