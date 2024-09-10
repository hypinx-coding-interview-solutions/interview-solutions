package com.hypinx.coding.capitalone.codesignal.swe;

import java.util.*;

public class Question43 {

    public static void main(String[] args) throws Exception {
        int[] forest1 = new int[]{25,0,50,0,0,0,0,15,0,0,45};
        int bird1 = 4;
        int[] expected1 = new int[]{7,2,10};
        int[] result1 = solution(forest1, bird1);

        int[] forest2 = new int[]{50,0,50};
        int bird2 = 1;
        int[] expected2 = new int[]{2,0};
        int[] result2 = solution(forest2, bird2);

        int[] forest3 = new int[]{25,25,0,25,25};
        int bird3 = 2;
        int[] expected3 = new int[]{3,1,4,0};
        int[] result3 = solution(forest3, bird3);

        for (int i = 0; i < result1.length; i++) {
            if (result1[i] != expected1[i]) {
                printArr(expected1, result1);
                throw new Exception("Test case 1 failed");
            }
        }

        for (int i = 0; i < result2.length; i++) {
            if (result2[i] != expected2[i]) {
                printArr(expected2, result2);
                throw new Exception("Test case 2 failed");
            }
        }

        for (int i = 0; i < result3.length; i++) {
            if (result3[i] != expected3[i]) {
                printArr(expected3, result3);
                throw new Exception("Test case 3 failed");
            }
        }
    }

    public static int[] solution(int[] forest, int bird) {
        int nestIndex = forest[bird];
        int stickLength = 100;
        String direction = "R";
        List<Integer> result = new ArrayList<>();

        while(stickLength > 0) {
            int temp = bird;
            int stickFound;
            if (direction.equals("R")) {
                // Fly right until a non-zero value is found. Set direction to fly L next iteration
                while(forest[++temp] == 0);
                direction = "L";
            } else {
                // Fly left until a non-zero value is found. Set direction to fly R next iteration
                while(forest[--temp] == 0);
                direction = "R";
            }

            // Get the stick value and change to 0 to indicate stick has been picked up
            stickFound = forest[temp];
            forest[temp] = 0;
            // Add the index to the result array in which a stick was found
            result.add(temp);
            // Reduce the stick target length by the stick length found.
            stickLength -= stickFound;
        }

        return result.stream().mapToInt(Integer::intValue).toArray();
    }

    private static void printArr(int[] exp, int[] res) {
        System.out.print("Expected: ");
        for (int el : exp) {
            System.out.print(el + " ");
        }
        System.out.println();
        System.out.print("Result was: ");

        for (int el : res) {
            System.out.print(el + " ");
        }
        System.out.println();
    }
}
