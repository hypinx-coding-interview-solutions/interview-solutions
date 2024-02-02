package com.hypinx.coding.goldmansachs.powerday;

/**
 * Given a binary tree, write a function that determines if it is a binary search tree
 *
 * Solution:
 *
 * Time Complexity: O(N) - Requires checking every node in the tree
 * Space Complexity: O(N) - In the event we have a one sided tree, our recursive call stack will have N calls in memory where N
 * is the number of nodes
 */
public class Question3 {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(1);
        root.right = new TreeNode(3);

        boolean expected = true;
        boolean result = isBST(root);
        System.out.println(expected == result);
    }

    public static boolean isBST(TreeNode root) {
        return isBSTHelper(root, null, null);
    }

    private static boolean isBSTHelper(TreeNode node, Integer min, Integer max) {
        // Base case: an empty tree is a BST
        if (node == null) {
            return true;
        }

        // Check if the current node's value is within the valid range
        if ((min != null && node.val < min) || (max != null && node.val > max)) {
            return false;
        }

        // Recursively check the left and right subtrees with updated min and max values
        return isBSTHelper(node.left, min, node.val) && isBSTHelper(node.right, node.val, max);
    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }

}
