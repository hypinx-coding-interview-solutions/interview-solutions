package com.hypinx.coding.meta.coding;

import com.hypinx.coding.models.BinaryTreeNode;

import java.util.*;

public class Question_8_Vertical_Tree_Traversal {

    static class TreeNode {
        int val;
        TreeNode left, right;

        TreeNode(int val) {
            this.val = val;
            this.left = null;
            this.right = null;
        }
    }

    public static class VerticalOrderTraversal {
        // Pair is used to identify the horizontal distance of each node from the root
        static class Pair {
            BinaryTreeNode node;
            int hd; // Horizontal Distance

            Pair(BinaryTreeNode node, int hd) {
                this.node = node;
                this.hd = hd;
            }
        }

        public static void verticalOrder(BinaryTreeNode root) {
            if (root == null) {
                return;
            }

            // TreeMap to store nodes grouped by horizontal distance
            TreeMap<Integer, List<Integer>> columnMap = new TreeMap<>();

            // Queue for BFS, storing nodes and their HD
            Queue<Pair> queue = new LinkedList<>();
            queue.add(new Pair(root, 0));

            while (!queue.isEmpty()) {
                Pair current = queue.poll();
                BinaryTreeNode currentNode = current.node;
                int hd = current.hd;

                // Add the current node to the TreeMap
                columnMap.putIfAbsent(hd, new ArrayList<>());
                columnMap.get(hd).add(currentNode.value);

                // Process the left and right children
                if (currentNode.left != null) {
                    queue.add(new Pair(currentNode.left, hd - 1));
                }
                if (currentNode.right != null) {
                    queue.add(new Pair(currentNode.right, hd + 1));
                }
            }

            // Print the vertical order
            for (List<Integer> column : columnMap.values()) {
                for (int value : column) {
                    System.out.print(value + " ");
                }
            }
        }

        public static void main(String[] args) {
            // Construct the tree
            BinaryTreeNode root = new BinaryTreeNode(6);
            root.left = new BinaryTreeNode(3);
            root.right = new BinaryTreeNode(4);
            root.left.left = new BinaryTreeNode(5);
            root.left.left.right = new BinaryTreeNode(2);
            root.left.left.right.left = new BinaryTreeNode(9);
            root.left.left.right.right = new BinaryTreeNode(7);
            root.right.left = new BinaryTreeNode(1);
            root.right.right = new BinaryTreeNode(0);
            root.right.right.left = new BinaryTreeNode(8);

            verticalOrder(root); // Expected Output: 5 9 3 2 6 1 7 4 8 0
        }
    }
}
