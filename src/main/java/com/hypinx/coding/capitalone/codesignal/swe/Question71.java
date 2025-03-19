package com.hypinx.coding.capitalone.codesignal.swe;

import com.hypinx.coding.misc.TestCaseValidator;

import java.util.*;

public class Question71 {

    public static void main(String[] args) {
        int[] heights = new int[]{2,7,8,5,1,6,3,9,4};
        int[] expected = new int[]{6,8,7,5,2,9,4,3,1};
        int[] result = solution(heights);

        TestCaseValidator.validateTestCase("1", expected, result);

        heights = new int[]{7, 2, 5, 6, 3};
        expected = new int[]{6, 5, 3, 7, 2};
        result = solution(heights);

        TestCaseValidator.validateTestCase("2", expected, result);
    }

    public static int[] solution(int[] heights) {
        List<Integer> highlightOrder = new ArrayList<>();
        LinkedList<Integer> heightList = new LinkedList<>();

        // Convert array to LinkedList for efficient removals
        for (int h : heights) {
            heightList.add(h);
        }

        while (!heightList.isEmpty()) {
            int minHighlight = Integer.MAX_VALUE;
            ListIterator<Integer> iterator = heightList.listIterator();
            List<Integer> highlights = new ArrayList<>();

            // Find highlights and determine the minimum highlight in one pass
            Integer prev = null;
            while (iterator.hasNext()) {
                int i = iterator.nextIndex();
                int current = iterator.next();
                Integer next = iterator.hasNext() ? heightList.get(i + 1) : null;

                boolean isHighlight = (prev == null || current > prev) && (next == null || current > next);

                if (isHighlight) {
                    highlights.add(current);
                    minHighlight = Math.min(minHighlight, current);
                }

                prev = current;
            }

            if (highlights.isEmpty()) break;

            // Remove the minimum highlight using an iterator to avoid index shifting issues
            iterator = heightList.listIterator();
            while (iterator.hasNext()) {
                if (iterator.next().equals(minHighlight)) {
                    iterator.remove();
                    break;
                }
            }

            highlightOrder.add(minHighlight);
        }

        // Convert List<Integer> to int[]
        return highlightOrder.stream().mapToInt(i -> i).toArray();
    }
}
