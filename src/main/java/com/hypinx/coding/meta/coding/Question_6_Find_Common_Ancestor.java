package com.hypinx.coding.meta.coding;

import com.hypinx.coding.misc.TestCaseValidator;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Question_6_Find_Common_Ancestor {

    static class GenericTreeNode {
        int val;
        GenericTreeNode parent;
        List<GenericTreeNode> children; // Use List instead of array

        GenericTreeNode(int val) {
            this.val = val;
            this.parent = null;
            this.children = new ArrayList<>(); // Initialize as an ArrayList
        }
    }

    public class FirstCommonAncestorGenericTree {

        public static GenericTreeNode findFirstCommonAncestor(GenericTreeNode p, GenericTreeNode q) {
            Set<GenericTreeNode> ancestors = new HashSet<>();

            // Step 1: Add all ancestors of p to the set
            while (p != null) {
                ancestors.add(p);
                p = p.parent;
            }

            // Step 2: Traverse up from q and find the first common ancestor
            while (q != null) {
                if (ancestors.contains(q)) {
                    return q;
                }
                q = q.parent;
            }

            return null; // No common ancestor found (unlikely if both nodes are in the same tree)
        }

        public static void main(String[] args) {
            // Example Generic Tree:
            //         1
            //       / | \
            //      2  3  4
            //     /|\     \
            //    5 6 7     8
            //               \
            //                9
            GenericTreeNode root = new GenericTreeNode(1);
            GenericTreeNode node2 = new GenericTreeNode(2);
            GenericTreeNode node3 = new GenericTreeNode(3);
            GenericTreeNode node4 = new GenericTreeNode(4);
            GenericTreeNode node5 = new GenericTreeNode(5);
            GenericTreeNode node6 = new GenericTreeNode(6);
            GenericTreeNode node7 = new GenericTreeNode(7);
            GenericTreeNode node8 = new GenericTreeNode(8);
            GenericTreeNode node9 = new GenericTreeNode(9);

            root.children.add(node2);
            root.children.add(node3);
            root.children.add(node4);
            node2.parent = root;
            node3.parent = root;
            node4.parent = root;

            node2.children.add(node5);
            node2.children.add(node6);
            node2.children.add(node7);
            node5.parent = node2;
            node6.parent = node2;
            node7.parent = node2;

            node4.children.add(node8);
            node8.parent = node4;
            node8.children.add(node9);
            node9.parent = node8;

            GenericTreeNode p = node6; // Node 6
            GenericTreeNode q = node7; // Node 7

            GenericTreeNode ancestor = findFirstCommonAncestor(p, q);
            TestCaseValidator.validateTestCase("1", 2, ancestor.val);
        }
    }

}
