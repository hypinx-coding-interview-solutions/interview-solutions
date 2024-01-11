package com.hypinx.coding.meta.virtualonsite;

import java.util.List;
import java.util.ArrayList;

/**
 * You are given a tree of nodes, where each node has a collection of children and has an integer 'id' field. Write a function which takes a node and assigns each node in the tree a unique integer id
 *
 * Both TreeNode and TreeUniqueIds are used in the solution. The main method to run a test execution is in TreeUniqueIds, this is a sample
 * coded by me (not provided by the interviewer)
 *
 * Time Complexity:
 * The time complexity of this code is O(N), where N is the total number of nodes in the tree. This is because we visit each node exactly once in a depth-first manner. The time complexity is linear with respect to the number of nodes in the tree.
 *
 * Space Complexity:
 * The space complexity is O(H), where H is the height of the tree. This space is mainly used by the function call stack during the depth-first traversal. In the worst case, if the tree is highly unbalanced and resembles a linked list, the space complexity could be O(N), where N is the number of nodes. However, for balanced trees, the space complexity would be much smaller, closer to O(log N), where log N is the height of a balanced tree.
 *
 * Follow Up Question: If you want to adapt the code to work with general graphs instead of trees, what are things to consider?
 *
 * Cycle Detection: General graphs can contain cycles. When traversing such graphs, you need to implement cycle detection to avoid infinite loops. One way to do this is to keep track of visited nodes during the traversal and prevent revisiting nodes that have already been visited.
 *
 * Disconnected Components: Graphs can have multiple disconnected components. You might need to ensure that all components are visited. You can achieve this by iterating through all nodes and calling the traversal from unvisited nodes until all nodes are visited.
 *
 * Node Visitation Order: In a tree, you can typically rely on a specific order of node visitation (e.g., pre-order or post-order traversal). In a general graph, the order in which nodes are visited can be arbitrary, depending on the graph structure and traversal algorithm used.
 *
 * Handling Graph Data Structure: The code provided assumes a specific data structure for the graph (i.e., the TreeNode class). In a general graph, you might be dealing with various data structures like adjacency lists or adjacency matrices. You would need to adapt your traversal algorithm to work with the specific graph representation you have.
 *
 * Directed vs. Undirected Graphs: Depending on whether your general graph is directed or undirected, you might need to adjust how you traverse edges and handle cycles.
 *
 * Graph Traversal Algorithm: In the provided code, we used a recursive depth-first traversal. For general graphs, you might consider other traversal algorithms, such as breadth-first search (BFS), depth-first search (DFS), or more advanced algorithms like Dijkstra's algorithm or A* search, depending on the specific problem you're trying to solve.
 *
 * Stopping Criteria: You might need to define a stopping criterion for your traversal, such as reaching a specific target node, exploring a certain number of nodes, or traversing until there are no more unvisited nodes.
 *
 */
class TreeNodeQuestion3 {
    int id;
    List<TreeNodeQuestion3> children;

    TreeNodeQuestion3(int id) {
        this.id = id;
        this.children = new ArrayList<>();
    }
}

public class Question3 {
    // Function to assign unique IDs to nodes in a tree
    public static int assignUniqueIds(TreeNodeQuestion3 root, int nextId) {
        if (root == null) {
            return nextId;
        }

        // Assign the current ID and increment the next available ID
        root.id = nextId++;

        // Recursively assign unique IDs to children
        for (TreeNodeQuestion3 child : root.children) {
            nextId = assignUniqueIds(child, nextId);
        }

        return nextId;
    }

    public static void main(String[] args) {
        // Create a sample tree
        TreeNodeQuestion3 root = new TreeNodeQuestion3(0);
        TreeNodeQuestion3 node1 = new TreeNodeQuestion3(0);
        TreeNodeQuestion3 node2 = new TreeNodeQuestion3(0);
        TreeNodeQuestion3 node3 = new TreeNodeQuestion3(0);

        root.children.add(node1);
        root.children.add(node2);
        node2.children.add(node3);

        // Assign unique IDs to nodes in the tree and get the next available ID
        int nextId = assignUniqueIds(root, 1); // Start with 1

        // Print the assigned IDs
        printTree(root);

        // The next available ID after assignment can be used for other purposes
        System.out.println("Next Available ID: " + nextId);
    }

    // Function to print tree node IDs
    public static void printTree(TreeNodeQuestion3 root) {
        if (root == null) {
            return;
        }

        System.out.println("Node ID: " + root.id);

        for (TreeNodeQuestion3 child : root.children) {
            printTree(child);
        }
    }
}