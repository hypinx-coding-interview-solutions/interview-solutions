package com.hypinx.coding.meta.virtualonsite;

import java.util.*;

public class Question_1_Word_Dictionary {
    // Trie Node class
    static class TrieNode {
        Map<Character, TrieNode> children;
        boolean isEnd;

        TrieNode() {
            children = new HashMap<>();
            isEnd = false;
        }
    }

    // Trie root
    private static TrieNode root;

    // Setup function to preprocess the dictionary
    public static void setup(Set<String> dictionary) {
        root = new TrieNode();
        for (String word : dictionary) {
            insert(word);
        }
    }

    // Helper function to insert a word into the trie
    private static void insert(String word) {
        TrieNode current = root;
        for (char c : word.toCharArray()) {
            current.children.putIfAbsent(c, new TrieNode());
            current = current.children.get(c);
        }
        current.isEnd = true; // Mark the end of the word
    }

    // isMatch function to check if the word exists (supports dots)
    public static boolean isMatch(String word) {
        return search(word, 0, root);
    }

    // Helper function to perform a recursive search
    private static boolean search(String word, int index, TrieNode node) {
        if (index == word.length()) {
            return node.isEnd; // Check if it's the end of a valid word
        }

        char c = word.charAt(index);
        if (c == '.') {
            // If the current character is '.', explore all children
            for (TrieNode child : node.children.values()) {
                if (search(word, index + 1, child)) {
                    return true;
                }
            }
            return false; // No match found for '.'
        } else {
            // Regular character: proceed if it exists in children
            if (!node.children.containsKey(c)) {
                return false; // No matching path
            }
            return search(word, index + 1, node.children.get(c));
        }
    }

    public static void main(String[] args) {
        // Example usage
        setup(new HashSet<>(Arrays.asList("foo", "bar", "baz")));

        System.out.println(isMatch("foo")); // true
        System.out.println(isMatch("garply")); // false
        System.out.println(isMatch("f.o")); // true
        System.out.println(isMatch("..")); // false
        System.out.println(isMatch("...")); // true
    }
}