package com.hypinx.coding.capitalone.codesignal;

import java.util.LinkedList;
import java.util.Queue;

public class Question27 {

    public static void main(String[] args) {
        int[] firstArray = new int[]{25,288,2655,54546,54,555};
        int[] secondArray = new int[]{2,255,266,244,26,5,54547};
        int expectedResult = 4;
        int result = solution(firstArray, secondArray);
        System.out.println(result == expectedResult);
    }

    public static int solution(int [] arr1, int[] arr2) {
        IntegerRepresentation treeArr1 = new IntegerRepresentation();
        IntegerRepresentation treeArr2 = new IntegerRepresentation();
        buildTree(treeArr1, arr1);
        buildTree(treeArr2, arr2);

        int LCP = 0;
        // Find LCP from arr1 from tree2
        for (int el : arr1) {
            String[] split = String.valueOf(el).split("");
            // If current LCP is higher than size of number to check we skip
            if (split.length < LCP) continue;
            // Find the LCP for the current number
            LCP = Math.max(LCP, findLCP(split, treeArr2));
        }

        // Perform sample logic but for arr2 and tree1
        for (int el : arr2) {
            String[] split = String.valueOf(el).split("");
            // If current LCP is higher than size of number to check we skip
            if (split.length < LCP) continue;
            // Find the LCP for the current number
            LCP = Math.max(LCP, findLCP(split, treeArr1));
        }

        return LCP;
    }

    public static int findLCP(String[] num, IntegerRepresentation tree) {
        int counter = 0;
        for (int i = 0; i < num.length; i++) {
            if (tree.integerRepresentation[Integer.parseInt(num[i])] != null) {
                tree = tree.integerRepresentation[Integer.parseInt(num[i])];
                counter++;
            } else {
                return counter;
            }
        }
        return counter;
    }

    public static void buildTree(IntegerRepresentation tree, int[] arr) {
        for (int el : arr) {
            IntegerRepresentation current = tree;
            String num = String.valueOf(el);
            for (int i = 0; i < num.length(); i++) {
                int singleNum = Integer.parseInt(num.charAt(i) + "");
                if (current.integerRepresentation[singleNum] == null) {
                    // Create a new representation here
                    IntegerRepresentation temp = new IntegerRepresentation();
                    current.integerRepresentation[singleNum] = temp;
                    // Update to be the current one
                    current = temp;
                } else {
                    // If already present, just update the current value
                    current = current.integerRepresentation[singleNum];
                }
            }
        }
    }

    public static void printTree(IntegerRepresentation tree) {
        Queue<IntegerRepresentation> queue = new LinkedList<>();
        queue.add(tree);

        while (queue.peek() != null) {
            IntegerRepresentation current = queue.poll();
            for (int i = 0; i < current.integerRepresentation.length; i++) {
                if (current.integerRepresentation[i] != null) {
                    queue.add(current.integerRepresentation[i]);
                    System.out.print(i + " ");
                } else {
                    System.out.print(0 + " ");
                }
            }
            System.out.println();
        }
    }

    static class IntegerRepresentation {
        public IntegerRepresentation[] integerRepresentation;

        public IntegerRepresentation() {
            this.integerRepresentation = new IntegerRepresentation[10];
        }
    }
}