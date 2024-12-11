package com.hypinx.coding.chase.superday.algorithm;

import com.hypinx.coding.misc.TestCaseValidator;

import java.util.*;

public class Question_4_Geological_Sorting {

    public static void main(String[] args) {
        List<Integer> volcanic = List.of(7000, 13400, 7000, 14000);
        List<Integer> nonVolcanic = List.of(7000, 13400, 150000, 7000);
        List<Integer> result = sortIntersect(volcanic, nonVolcanic);

        List<Integer> expected = List.of(13400, 7000, 7000);
        TestCaseValidator.validateTestCase("1", expected, result);

        volcanic = List.of(12000, 7000);
        nonVolcanic = List.of(7000, 12000);
        expected = List.of(12000, 7000);
        result = sortIntersect(volcanic, nonVolcanic);

        TestCaseValidator.validateTestCase("2", expected, result);
    }

    public static List<Integer> sortIntersect(List<Integer> volcanic, List<Integer> nonVolcanic) {

        List<Integer> result = new ArrayList<>();
        // Pass in Comparator.reverseOrder in the constructor of the PriorityQueue to bubble up larger items
        PriorityQueue<Integer> queue = new PriorityQueue<>(Comparator.reverseOrder());

        Map<Integer, Integer> materialFrequency = new HashMap<>();
        populateVolcanicMaterialMap(volcanic, materialFrequency);

        // Loop over the nonVolcanic list, for each element we check if its present in the volcanic list
        for (int nonVolcanicMaterial : nonVolcanic) {
            if (materialFrequency.containsKey(nonVolcanicMaterial)) {
                // If present we fetch the frequency and if greater than 0 we decrement the value and add the
                // common value to the priority queue
                int frequency = materialFrequency.get(nonVolcanicMaterial);
                if (frequency > 0) {
                    materialFrequency.put(nonVolcanicMaterial, --frequency);
                    queue.add(nonVolcanicMaterial);
                }
            }
        }

        // Convert PQ to List
        while(!queue.isEmpty()) {
            result.add(queue.poll());
        }

        return result;

    }

    /**
     * Helper method to populate the initial frequency count map from volcanic materials
     */
    private static void populateVolcanicMaterialMap(List<Integer> materials, Map<Integer, Integer> map) {
        for (int material : materials) {
            map.put(material, map.getOrDefault(material, 1) + 1);
        }
    }
}
