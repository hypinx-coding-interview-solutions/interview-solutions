package com.hypinx.coding.meta.coding;

import com.hypinx.coding.misc.TestCaseValidator;

public class Question_20_Longest_Tree_Diameter {

    static class TreeNode {
        int val;
        TreeNode left, right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    private static int maxDiameter = 0;

    public static int diameterOfBinaryTree(TreeNode root) {
        maxDiameter = 0;
        dfs(root);
        return maxDiameter;
    }

    private static int dfs(TreeNode node) {
        if (node == null) return 0;

        int left = dfs(node.left);
        int right = dfs(node.right);

        // ✅ Add +1 to count the current node itself in the path
        int localDiameterInNodes = left + right + 1;
        maxDiameter = Math.max(maxDiameter, localDiameterInNodes);

        return 1 + Math.max(left, right); // return height of subtree
    }

    public static void main(String[] args) {
        // Constructing the sample tree from your diagram
                /*
              1
             / \
            2   3
           / \   \
          4   5   6
                   \
                    7
        */
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);

        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        root.right.right = new TreeNode(6);
        root.right.right.right = new TreeNode(7);

        int result = diameterOfBinaryTree(root);
        int expected = 6;

        TestCaseValidator.validateTestCase("1", expected, result);
    }
}
