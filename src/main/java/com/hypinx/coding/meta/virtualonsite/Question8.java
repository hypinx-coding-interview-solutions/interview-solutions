package com.hypinx.coding.meta.virtualonsite;

import com.sun.source.tree.Tree;

import java.util.*;

public class Question8 {

    public static void main(String[] args) {
        TreeNodeQuestion8 root = new TreeNodeQuestion8(5);
        root.left = new TreeNodeQuestion8(6);
        root.right = new TreeNodeQuestion8(14);
        root.left.left = new TreeNodeQuestion8(7);
        root.right.left = new TreeNodeQuestion8(12);
        root.right.right = new TreeNodeQuestion8(16);

        Solution solution = new Solution();
        List<Integer> res = solution.verticalTraversal(root);
        System.out.println(res.size());

    }

    /*

            5
           / \
          6  14
         /  / \
        7  12  16

Output = 7, 6, 5, 12, 14, 16
     */

}

class TreeNodeQuestion8 {
    public int val;
    public TreeNodeQuestion8 left, right;

    public TreeNodeQuestion8(int val) {
        this.val = val;
        this.left = this.right = null;
    }
}

class Solution {
    Map<Integer, TreeMap<Integer, List<Integer>>> levelItems;
    int minIndex, maxIndex;
    public List<Integer> verticalTraversal(TreeNodeQuestion8 root) {
        if (root == null) return new ArrayList<>();

        this.levelItems = new HashMap<>();
        this.minIndex = Integer.MAX_VALUE;
        this.maxIndex = Integer.MIN_VALUE;

        dfs(root, 0, 0);

        List<Integer> res = new ArrayList<>();
        for (int i = minIndex; i <= maxIndex; i++) {
            TreeMap<Integer, List<Integer>> treeMap = levelItems.get(i);
            for (int row : treeMap.keySet()) {
                res.addAll(treeMap.get(row));
            }
        }
        return res;
    }

    private void dfs(TreeNodeQuestion8 node, int row, int col) {
        if (node == null) return;
        minIndex = Math.min(minIndex, col);
        maxIndex = Math.max(maxIndex, col);
        levelItems.computeIfAbsent(col, k -> new TreeMap<>())
                .computeIfAbsent(row, k -> new ArrayList<>(node.val));
        dfs(node.left, row + 1, col - 1);
        dfs(node.right, row + 1, col + 1);
    }
}
