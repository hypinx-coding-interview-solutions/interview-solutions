package com.hypinx.coding.meta.virtualonsite;

import com.hypinx.coding.misc.TestCaseValidator;

import java.util.*;

public class Question_10_Vector_Dot_Product {

    // Class to represent a run-length encoded vector
    static class RLEVector {
        List<int[]> runs; // Each element is (value, count)

        RLEVector() {
            runs = new ArrayList<>();
        }

        // Add a value with its count to the vector
        public void add(int value, int count) {
            if (count <= 0) return;
            runs.add(new int[]{value, count});
        }

        // Calculate the dot product with another RLE vector
        public int dotProduct(RLEVector other) {
            int i = 0, j = 0; // Pointers for this and other vector
            int result = 0;

            while (i < this.runs.size() && j < other.runs.size()) {
                int[] run1 = this.runs.get(i);
                int[] run2 = other.runs.get(j);

                int value1 = run1[0], count1 = run1[1];
                int value2 = run2[0], count2 = run2[1];

                // Calculate the overlap length
                int overlap = Math.min(count1, count2);
                result += value1 * value2 * overlap;

                // Adjust the counts
                if (count1 == overlap) {
                    i++; // Move to next run in this vector
                } else {
                    this.runs.get(i)[1] -= overlap;
                }

                if (count2 == overlap) {
                    j++; // Move to next run in the other vector
                } else {
                    other.runs.get(j)[1] -= overlap;
                }
            }

            return result;
        }
    }

    public static void main(String[] args) {
        // Example compressed vectors
        RLEVector vector1 = new RLEVector();
        vector1.add(2, 3);
        vector1.add(4, 3);
        vector1.add(5, 3);

        RLEVector vector2 = new RLEVector();
        vector2.add(1, 2);
        vector2.add(4, 4);

        // Calculate the dot product
        int result = vector1.dotProduct(vector2);
        int expected = 60;

        TestCaseValidator.validateTestCase("1", expected, result);

        // Print the result
        System.out.println("Dot Product: " + result); // Expected: 60
    }
}
