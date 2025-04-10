package com.hypinx.coding.meta.coding;

import java.util.*;

public class Question_9_City_Population {

    public static void main(String[] args) {
        Map<String, Integer> cityPopulation = new HashMap<>();
        cityPopulation.put("NY", 7);
        cityPopulation.put("SF", 5);
        cityPopulation.put("LA", 8);

        RandomCitySelector selector = new RandomCitySelector(cityPopulation);

        // Generate random cities to test the distribution
        Map<String, Integer> result = new HashMap<>();
        for (int i = 0; i < 1000000; i++) {
            String city = selector.getRandomCity();
            result.put(city, result.getOrDefault(city, 0) + 1);
        }

        // Print the results
        for (Map.Entry<String, Integer> entry : result.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    static class RandomCitySelector {
        private List<String> cities;
        private List<Integer> cumulativeSums;
        private int totalPopulation;
        private Random random;

        public RandomCitySelector(Map<String, Integer> cityPopulation) {
            cities = new ArrayList<>();
            cumulativeSums = new ArrayList<>();
            totalPopulation = 0;
            random = new Random();

            // Build cumulative sums and cities list
            for (Map.Entry<String, Integer> entry : cityPopulation.entrySet()) {
                String city = entry.getKey();
                int population = entry.getValue();

                cities.add(city);
                totalPopulation += population;
                cumulativeSums.add(totalPopulation);
            }
        }

        public String getRandomCity() {
            int randomValue = random.nextInt(totalPopulation) + 1; // [1, totalPopulation]
            int index = binarySearch(randomValue);
            return cities.get(index);
        }

        private int binarySearch(int value) {
            int low = 0, high = cumulativeSums.size() - 1;

            while (low < high) {
                int mid = low + (high - low) / 2;
                if (cumulativeSums.get(mid) < value) {
                    low = mid + 1;
                } else {
                    high = mid;
                }
            }
            return low;
        }
    }
}
