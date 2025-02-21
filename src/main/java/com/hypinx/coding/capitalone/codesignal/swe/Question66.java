package com.hypinx.coding.capitalone.codesignal.swe;

import com.hypinx.coding.misc.TestCaseValidator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Question66 {

    public static void main(String[] args) {
        String[] queries = new String[]{"+4", "+5", "+6", "+4", "+3", "-4"};
        int diff = 1;

        int[] result = solution(queries, diff);
        int[] expected = new int[]{0,0,1,2,4,0};
        TestCaseValidator.validateTestCase("1", expected, result);

        queries = new String[]{"+2", "+2", "+4", "+3", "-2"};
        diff = 1;

        result = solution(queries, diff);
        expected = new int[]{0,0,0,2,0};
        TestCaseValidator.validateTestCase("2", expected, result);

        queries = new String[]{"+2", "+3", "+9", "+4", "+0", "-2", "+6", "+1", "-3", "+12", "+7", "+3", "+3"};
        diff = 3;

        result = solution(queries, diff);
        expected = new int[]{0,0,0,0,0,0,2,2,0,1,2,4,6};
        TestCaseValidator.validateTestCase("3", expected, result);
    }

    public static int[] solution(String[] queries, int diff) {
        List<Integer> numbers = new ArrayList<>();
        List<Integer> result = new ArrayList<>();
        Map<Integer, Integer> frequency = new HashMap<>();

        for (String query : queries) {
            char operation = query.charAt(0);
            int value = Integer.parseInt(query.substring(1));

            if (operation == '+') {
                numbers.add(value);
                frequency.put(value, frequency.getOrDefault(value, 0) + 1);
            } else if (operation == '-') {
                numbers.removeIf(num -> num == value);
                frequency.remove(value);
            }

            result.add(countTriples(frequency, diff));
        }

        return result.stream().mapToInt(i -> i).toArray();
    }

    private static int countTriples(Map<Integer, Integer> frequency, int diff) {
        int count = 0;
        for (int y : frequency.keySet()) {
            int x = y - diff;
            int z = y + diff;

            if (frequency.containsKey(x) && frequency.containsKey(z)) {
                count += frequency.get(x) * frequency.get(y) * frequency.get(z);
            }
        }
        return count;
    }

}
