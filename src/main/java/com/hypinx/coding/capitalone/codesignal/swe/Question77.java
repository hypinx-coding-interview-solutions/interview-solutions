package com.hypinx.coding.capitalone.codesignal.swe;

import com.hypinx.coding.misc.TestCaseValidator;

import java.util.LinkedList;
import java.util.Queue;

public class Question77 {

    public static void main(String[] args) {
        int[] playerDeckA = {5};
        int[] playerDeckB = {2};
        int expected = 1;
        int result = solution(playerDeckA, playerDeckB);

        TestCaseValidator.validateTestCase("1", expected, result);

        playerDeckA = new int[]{1, 3, 5};
        playerDeckB = new int[]{2, 4, 1};

        expected = 13;
        result = solution(playerDeckA, playerDeckB);

        TestCaseValidator.validateTestCase("2", expected, result);
    }

    public static int solution(int[] playerDeckA, int[] playerDeckB) {
        // Convert arrays to queues for easy manipulation (FIFO)
        Queue<Integer> deckA = new LinkedList<>();
        for (int card : playerDeckA) {
            deckA.offer(card);
        }

        Queue<Integer> deckB = new LinkedList<>();
        for (int card : playerDeckB) {
            deckB.offer(card);
        }

        int rounds = 0;

        // Continue while both decks have cards
        while (!deckA.isEmpty() && !deckB.isEmpty()) {
            rounds++;

            int cardA = deckA.poll(); // top card of A
            int cardB = deckB.poll(); // top card of B

            if (cardA >= cardB) {
                // Player A wins this round
                deckA.offer(cardA);
                deckA.offer(cardB);
            } else {
                // Player B wins this round
                deckB.offer(cardB);
                deckB.offer(cardA);
            }
        }

        // Return the total number of rounds
        return rounds;
    }
}
