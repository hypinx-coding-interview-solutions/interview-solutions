package com.hypinx.coding.capitalone.codesignal.swe;

import java.util.*;
class Question9 {

    public static void main(String[] args) {

        // Input 1:
        int[] a1 = new int[]{1,2,3};
        int[] expected1 = new int[]{1,2};
        int[] b1 = new int[]{1,4};
        int[][] queriesA = new int[3][];
        queriesA[0] = new int[]{1,5};
        queriesA[1] = new int[]{0,0,2};
        queriesA[2] = new int[]{1,5};

        // Input 2
        int[] a2 = new int[]{1,2,2};
        int[] expected2 = new int[]{3,4};
        int[] b2 = new int[]{2,3};
        int[][] queriesB = new int[3][];
        queriesB[0] = new int[]{1,4};
        queriesB[1] = new int[]{0,0,1};
        queriesB[2] = new int[]{1,5};

        // Input 3
        int[] a3 = new int[]{1,0,1};
        int[] expected3 = new int[]{1};
        int[] b3 = new int[]{2,0,2};
        int[][] queriesC = new int[1][];
        queriesC[0] = new int[]{1,0};

        int[] ans1 = solution(a1, b1, queriesA);
        int[] ans2 = solution(a2, b2, queriesB);
        int[] ans3 = solution(a3, b3, queriesC);

        System.out.println("Input 1 = " + (Arrays.equals(ans1, expected1)));
        System.out.println("Input 2 = " + (Arrays.equals(ans2, expected2)));
        System.out.println("Input 3 = " + (Arrays.equals(ans3, expected3)));


    }

    static void buildMap(Map<Integer, Integer> map, int[] arr) {
        for (int el : arr) {
            if (map.containsKey(el)) {
                map.put(el, map.get(el) + 1);
            } else {
                map.put(el, 1);
            }
        }
    }

    // Query [0, i, x] -> add x to b[i]
    // Query [1, x] -> Find all a[i] + a[j] = x and add to result array
    // Notes - only array b will change, a stays the same
    public static int[] solution(int[] a, int[] b, int[][] queries) {
        List<Integer> result = new ArrayList<Integer>();

        Map<Integer, Integer> mapForA = new HashMap<Integer, Integer>();
        Map<Integer, Integer> mapForB = new HashMap<Integer, Integer>();
        buildMap(mapForA, a);
        buildMap(mapForB, b);


        // Process each query
        for (int[] query : queries) {
            if (query.length == 3) {
                processQueryTypeOne(a, b, query, mapForA, mapForB);
            } else {
                processQueryTypeTwo(a, b, query, mapForA, mapForB, result);
            }
        }

        printList(result);
        return result.stream().mapToInt(i -> i).toArray();
    }

    static void processQueryTypeOne(int[] a, int[] b, int[] query, Map<Integer, Integer> A, Map<Integer, Integer> B) {
        int index = query[1];
        int valueOld = b[index];
        // Decrement count of old value if it exists
        if (B.containsKey(valueOld)) {
            B.put(valueOld, B.get(valueOld) - 1);
        }
        int newValue = b[index] + query[2];
        // Add in new value
        if (B.containsKey(newValue)) {
            B.put(newValue, B.get(newValue) + 1);
        } else {
            B.put(newValue, 1);
        }
    }

    static void processQueryTypeTwo(int[] a, int[] b, int[] query, Map<Integer, Integer> A, Map<Integer, Integer> B,
                                    List<Integer> result) {
        int total = 0;
        for (int i = 0; i < a.length; i++) {
            int value = a[i];
            int valueToFind = query[1];
            if (B.containsKey(valueToFind - value)) {
                int count = B.get(valueToFind - value);
                total += count;
            }
        }
        result.add(total);
    }

    // Helper method for debugging
    static void printList(List<Integer> result) {
        for (int el : result) {
            System.out.print(el + " ");
        }
        System.out.println();
    }

}
