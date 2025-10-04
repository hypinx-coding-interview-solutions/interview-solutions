package com.hypinx.coding.meta.coding;

import com.hypinx.coding.misc.TestCaseValidator;
import com.hypinx.coding.models.BinaryTreeNode;

import java.util.*;

public class Question_25_Perimeter_Traversal {

    public static void main(String[] args) {

        /*
                1
               / \
              2   3
                 /
                5
         */
        BinaryTreeNode root = new BinaryTreeNode(1);
        root.left = new BinaryTreeNode(2);
        root.right = new BinaryTreeNode(3);
        root.right.left = new BinaryTreeNode(5);
        List<Integer> expected = List.of(5,1,2,3,5);
        List<Integer> result = perimeterTraversal(root);

        TestCaseValidator.validateTestCase("1", expected, result);

        root = new BinaryTreeNode(1);
        root.left = new BinaryTreeNode(2);
        root.right = new BinaryTreeNode(3);
        root.left.right = new BinaryTreeNode(5);
        expected = List.of(5,1,2,3,5);
        result = perimeterTraversal(root);

        TestCaseValidator.validateTestCase("2", expected, result);

        root = new BinaryTreeNode(1);
        root.left = new BinaryTreeNode(2);
        root.left.left = new BinaryTreeNode(6);
        root.left.right = new BinaryTreeNode(5);
        root.right = new BinaryTreeNode(3);
        root.right.left = new BinaryTreeNode(4);
        expected = List.of(6,2,1,3,4);
        result = perimeterTraversal(root);

        TestCaseValidator.validateTestCase("3", expected, result);
    }

    public static List<Integer> perimeterTraversal(BinaryTreeNode root) {
        if (root == null) return new ArrayList<>();

        Map<Integer, Integer> leftMost = new HashMap<>();
        Map<Integer, Integer> rightMost = new HashMap<>();

        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(root, 0));
        int maxLevel = 0;

        while (!q.isEmpty()) {
            Pair p = q.poll();
            BinaryTreeNode node = p.node;
            int level = p.level;
            maxLevel = Math.max(maxLevel, level);

            leftMost.putIfAbsent(level, node.value);
            rightMost.put(level, node.value);

            if (node.left != null)
                q.add(new Pair(node.left, level + 1));
            if (node.right != null)
                q.add(new Pair(node.right, level + 1));
        }

        List<Integer> result = new ArrayList<>();

        // Left boundary: bottom-up
        for (int i = maxLevel; i >= 0; i--) {
            result.add(leftMost.get(i));
        }

        // Right boundary: top-down
        for (int i = 1; i <= maxLevel; i++) {
            result.add(rightMost.get(i));
        }

        return result;
    }

    static class Pair {
        BinaryTreeNode node;
        int level;
        Pair(BinaryTreeNode n, int l) {
            node = n;
            level = l;
        }
    }
}
