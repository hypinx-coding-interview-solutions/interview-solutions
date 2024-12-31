package com.hypinx.coding.meta.virtualonsite;

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

    public class VerticalOrderTraversal {
        // Pair is used to identify the horizontal distance of each node from the root
        static class Pair {
            TreeNode node;
            int hd; // Horizontal Distance

            Pair(TreeNode node, int hd) {
                this.node = node;
                this.hd = hd;
            }
        }

        public static void verticalOrder(TreeNode root) {
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
                TreeNode currentNode = current.node;
                int hd = current.hd;

                // Add the current node to the TreeMap
                columnMap.putIfAbsent(hd, new ArrayList<>());
                columnMap.get(hd).add(currentNode.val);

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
            TreeNode root = new TreeNode(6);
            root.left = new TreeNode(3);
            root.right = new TreeNode(4);
            root.left.left = new TreeNode(5);
            root.left.left.right = new TreeNode(2);
            root.left.left.right.left = new TreeNode(9);
            root.left.left.right.right = new TreeNode(7);
            root.right.left = new TreeNode(1);
            root.right.right = new TreeNode(0);
            root.right.right.left = new TreeNode(8);

            verticalOrder(root); // Expected Output: 5 9 3 2 6 1 7 4 8 0
        }
    }
}
