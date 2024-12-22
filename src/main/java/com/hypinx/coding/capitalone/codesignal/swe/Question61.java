package com.hypinx.coding.capitalone.codesignal.swe;

import com.hypinx.coding.misc.TestCaseValidator;

import java.util.*;

public class Question61 {
    public static void main(String[] args) {
        int[] transactions = new int[]{33, 123, 121, 44, 78, 98};
        int[] expected = new int[]{121, 44, 33};
        int[] result = solution(transactions);

        TestCaseValidator.validateTestCase("1", expected, result);

        transactions = new int[]{123, 456, 789};
        expected = new int[]{};
        result = solution(transactions);

        TestCaseValidator.validateTestCase("2", expected, result);

        transactions = new int[]{12321, 111, 232, 999, 1000000, 1221, 9876789};
        expected = new int[]{9876789, 12321, 1221, 999, 232, 111};
        result = solution(transactions);

        TestCaseValidator.validateTestCase("3", expected, result);
    }

    public static int[] solution(int[] transactions) {

        PriorityQueue<Integer> queue = new PriorityQueue(Comparator.reverseOrder());
        Set<Integer> palindromes = new HashSet<>();

        for (int transaction : transactions) {
            String trans = String.valueOf(transaction);
            if (palindromes.contains(transaction) || isPalindrome(trans)) {
                queue.add(transaction);
                palindromes.add(transaction);
            }
        }

        int[] result = new int[queue.size()];
        int counter = 0;
        while(!queue.isEmpty()) {
            result[counter++] = queue.poll();
        }

        return result;
    }

    private static boolean isPalindrome(String transaction) {
        int left = 0, right = transaction.length() - 1;
        while (left < right) {
            if (transaction.charAt(left) != transaction.charAt(right)) return false;
            left++;
            right--;
        }
        return true;
    }
}
