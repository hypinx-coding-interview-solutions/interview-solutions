package com.hypinx.coding.meta.coding;

import com.hypinx.coding.misc.TestCaseValidator;

public class Question_12_Lowest_Common_Ancestor {

    public static void main(String[] args) {

        // Part 1 ---------------------------------------------------------------
        // Constructing the tree
        TreeNode root = new TreeNode();                 // A
        root.left = new TreeNode();                     // B
        root.right = new TreeNode();                    // C
        root.left.left = new TreeNode();                // D
        root.left.right = new TreeNode();               // E
        root.right.right = new TreeNode();              // G
        root.left.left.left = new TreeNode();           // H
        root.right.right.left = new TreeNode();         // I

        TreeNode p = root.left.left;
        TreeNode q = root.right;
        TreeNode result = Question_12_Part_1_LCA.lowestCommonAncestor(root, p, q);
        TreeNode expected = root;
        TestCaseValidator.validateTestCase("1", result.equals(expected));

        p = root.left;
        q = root.left.right;
        result = Question_12_Part_1_LCA.lowestCommonAncestor(root, p, q);
        expected = root.left;
        TestCaseValidator.validateTestCase("2", result.equals(expected));

        p = root.left;
        q = root.left.left.left;
        result = Question_12_Part_1_LCA.lowestCommonAncestor(root, p, q);
        expected = root.left;
        TestCaseValidator.validateTestCase("2", result.equals(expected));


        // Part 2 ---------------------------------------------------------------
        // Constructing the tree with parent pointers
        TreeNode A = new TreeNode();        // A
        TreeNode B = new TreeNode();        // B
        TreeNode C = new TreeNode();        // C
        TreeNode D = new TreeNode();        // D
        TreeNode E = new TreeNode();        // E
        TreeNode G = new TreeNode();        // G
        TreeNode H = new TreeNode();        // H
        TreeNode I = new TreeNode();        // I

        // Setting up the tree structure
        A.left = B;  B.parent = A;
        A.right = C; C.parent = A;
        B.left = D;  D.parent = B;
        B.right = E; E.parent = B;
        C.right = G; G.parent = C;
        D.left = H;  H.parent = D;
        G.left = I;  I.parent = G;

        p = D;
        q = C;
        result = Question_12_Part_2_LCA.lowestCommonAncestor(p, q);
        expected = A;
        TestCaseValidator.validateTestCase("5", result.equals(expected));

        p = B;
        q = E;
        result = Question_12_Part_2_LCA.lowestCommonAncestor(p, q);
        expected = B;
        TestCaseValidator.validateTestCase("6", result.equals(expected));

        p = B;
        q = H;
        result = Question_12_Part_2_LCA.lowestCommonAncestor(p, q);
        expected = B;
        TestCaseValidator.validateTestCase("7", result.equals(expected));
    }

    static class TreeNode {
        TreeNode left;
        TreeNode right;
        TreeNode parent; // Used for part 2

        TreeNode() {
            this.left = null;
            this.right = null;
            this.parent = null; // Used for part 2
        }
    }

    static class Question_12_Part_1_LCA {

        // Helper function to check if a node exists in the tree
        public static boolean nodeExists(TreeNode root, TreeNode node) {
            if (root == null) return false;
            if (root == node) return true;
            return nodeExists(root.left, node) || nodeExists(root.right, node);
        }

        // Main LCA function
        public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
            if (!nodeExists(root, p) || !nodeExists(root, q)) {
                return null; // Return null if one or both nodes do not exist
            }
            return findLCA(root, p, q);
        }

        private static TreeNode findLCA(TreeNode root, TreeNode p, TreeNode q) {
            if (root == null || root == p || root == q) {
                return root;
            }

            TreeNode left = findLCA(root.left, p, q);
            TreeNode right = findLCA(root.right, p, q);

            if (left != null && right != null) {
                return root;
            }

            return (left != null) ? left : right;
        }
    }

    // Two pointer approach
    static class Question_12_Part_2_LCA {
        public static TreeNode lowestCommonAncestor(TreeNode p, TreeNode q) {
            TreeNode a = p, b = q;

            while (a != b) {
                a = (a == null) ? q : a.parent;
                b = (b == null) ? p : b.parent;
            }

            return a; // The first common ancestor
        }
    }
}


