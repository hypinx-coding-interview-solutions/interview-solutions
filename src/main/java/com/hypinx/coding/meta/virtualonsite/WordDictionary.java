package com.hypinx.coding.meta.virtualonsite;

/**
 * You are given an array of strings (a dictionary). The task is to implement two functions. The first is setup(), which preprocesses the dictionary to your liking. The second isMatch(), that, given a word, returns whether or not the word exists in the dictionary. The word given to isMatch() may contain dots ('.') which match exactly one character, but any character value, as shown in the example below. Words may contain any number of dots in any position.
 *
 * setup({"foo", "bar", "baz"});
 * isMatch("foo");  // returns true
 * isMatch("garply");  // returns false
 * isMatch("f.o");  // returns true (it matches foo)
 * isMatch("..");  // returns false (there are no two-letter words)
 * isMatch("...");	// returns true (any 3 letter word will suffice)
 *
 * Time Complexity:
 *
 * setup() function takes O(N * M) time, where N is the number of words in the dictionary, and M is the average length of the words.
 * isMatch() function has a time complexity of O(L), where L is the length of the word being searched.
 * Space Complexity:
 *
 * The space complexity for the setup() function is O(N * M) due to the trie data structure.
 * The space complexity for the isMatch() function is O(L) due to the recursive call stack.
 *
 * Follow Up: How can we improve the solution to not use ascii based solution, but to use unicode (e.g. any character in a word besides just alphabetical.
 *
 * Solution - Instead of using a TrieNode array of size 26, we have to use a Map of <Character, TrieNode> where Character can be any unicode value. Update the code by replacing with the commented code down below
 *
 * Time and space complexity remain the same
 */
public class WordDictionary {

    // Main method added by us to setup test cases - normally we can omit this and just implement solution
    public static void main(String[] args) {
        WordDictionarySolution dictionarySolution = new WordDictionarySolution();
        dictionarySolution.setup(new String[]{"foo", "bar", "baz"});

        System.out.println(dictionarySolution.isMatch("foo"));    // true
        System.out.println(dictionarySolution.isMatch("garply")); // false
        System.out.println(dictionarySolution.isMatch("f.o"));    // true
        System.out.println(dictionarySolution.isMatch(".."));     // false
        System.out.println(dictionarySolution.isMatch("..."));    // true
    }
}

class TrieNode {
    TrieNode[] children;
    // Map<Character, TrieNode> children;   comment line 44

    boolean isEndOfWord;

    TrieNode() {
        children = new TrieNode[26];
        // children = new HashMap<>();  comment line 50
        isEndOfWord = false;
    }
}

class WordDictionarySolution {
    TrieNode root;

    public WordDictionarySolution() {
        root = new TrieNode();
    }

    public void setup(String[] dictionary) {
        for (String word : dictionary) {
            TrieNode node = root;
            for (char c : word.toCharArray()) {
                int index = c - 'a';
                if (node.children[index] == null) {
                    node.children[index] = new TrieNode();
                }
                node = node.children[index];
                // node.children.putIfAbsent(c, new TrieNode());
                // node = node.children.get(c);
                // Comment lines 67 to 71
            }
            node.isEndOfWord = true;
        }
    }

    public boolean isMatch(String word) {
        return search(root, word, 0);
    }

    private boolean search(TrieNode node, String word, int index) {
        if (node == null) {
            return false;
        }

        if (index == word.length()) {
            return node.isEndOfWord;
        }

        char c = word.charAt(index);
        // char c = word.charAt(index);     comment line 93

        if (c == '.') {
            for (int i = 0; i < 26; i++) {
                // for (TrieNode child : node.children.values()) {      comment line 97
                if (search(node.children[i], word, index + 1)) {
                    return true;
                }
            }
        } else {
            int childIndex = c - 'a';
            return search(node.children[childIndex], word, index + 1);

            // if (node.children.containsKey(c)) {
            //    return search(node.children.get(c), word, index + 1);
            //  }
            // Comment lines 104 and 105
        }

        return false;
    }
}