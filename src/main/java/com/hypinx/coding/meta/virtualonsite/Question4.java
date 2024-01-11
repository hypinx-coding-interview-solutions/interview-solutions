package com.hypinx.coding.meta.virtualonsite;

import java.util.HashMap;
import java.util.Map;

// Implement the following data structure:
interface AbstractDataTypeWithLast <K, V> {
    /** Adds a value v to this data structure this can be accessed by get() using key k. */
    public void put(K k, V v);

    /** Returns the value associated with key k. */
    public V get(K k);

    /** Removes value for key k. */
    public void delete(K k);

    /**
     * Returns the most recent key either added with put() or accessed
     * with get() that hasn't been removed by delete().
     */
    public K last();
}

// Examples:
/**
 AbstractDataTypeWithLast<String, Integer> data;
 data.put("a", 1);
 data.put("b", 2);
 data.last();        // => "b"
 data.get("a");      // => 1
 data.last();        // => "a"
 data.delete("a");
 data.last();        // => "b"
*/

/**
 * Solution: AbstractDataTypeWithLastImpl is the solution class and Node is a supporting class. You do not need to create this class, you can simply start to edit the methods provided above during the actual interview. The solution is written this way because it gives compliation errors otherwise.
 *
 * Follow Up Questions:
 *
 * 1. Can you think of some edge cases that might be a risk to this structure? What kind of edge cases do we need to account for?
 *
 * Concurrency: If the data structure is accessed and modified concurrently by multiple threads, you need to ensure thread safety. You might need to use synchronization mechanisms, such as locks or concurrent data structures, to prevent data corruption and race conditions.
 *
 * Duplicate Keys: What should happen if you attempt to put a key that already exists in the data structure? Should it update the existing value, or should it be treated as an error?
 *
 * Null Keys or Values: How should the data structure handle null keys or values if they are allowed in your use case? Ensure that null values or keys do not lead to unexpected behavior.
 *
 * Capacity and Resizing: If your HashMap reaches its load factor and triggers resizing, you need to ensure that the keys' order is maintained correctly during resizing.
 *
 * Load Distribution: Check how well the hash function distributes keys to avoid hash collisions, especially in cases where keys have patterns that might lead to clustering.
 *
 * 2. Will you ever have to worry about performing a delete when there are no nodes present? E.g. On line 143, will this cause a null pointer exception?
 *
 * Answer: No we don't have to worry because even if we have no nodes, if we try to run line 143 then we will simply reference the head node. We will always have head and tail present so we will not run into a null pointer issue on this line.
 *
 * Time Complexity:
 *
 * put(K k, V v): O(1)
 *
 * Inserting or updating a key-value pair in the HashMap (map) has an average time complexity of O(1). It also calls the appendToEnd or moveToEnd method, which has O(1) time complexity.
 *
 * get(K k): O(1)
 *
 * Retrieving a value associated with a key from the HashMap (map) has an average time complexity of O(1). It also calls the moveToEnd method, which has O(1) time complexity.
 *
 * delete(K k): O(1)
 *
 * Removing a key-value pair from the HashMap (map) has an average time complexity of O(1). It also calls the removeNode method, which has O(1) time complexity.
 *
 * last(): O(1)
 *
 * Retrieving the last key from the doubly-linked list (most recent) has O(1) time complexity.
 *
 * Space Complexity: O(N) - the space complexity is linear because it is dependent on the number of nodes we have which is N.
 */

class Node<K, V> {
    K key;
    V value;
    Node<K, V> prev;
    Node<K, V> next;

    Node(K key, V value) {
        this.key = key;
        this.value = value;
    }
}

public class Question4<K,V> {
    private Map<K, Node<K, V>> map;
    private Node<K, V> head;
    private Node<K, V> tail;

    public Question4 () {
        this.map = new HashMap<>();
        this.head = new Node<>(null, null);
        this.tail = new Node<>(null, null);
        head.next = tail;
        tail.prev = head;
    }

    public void put(K k, V v) {
        // If the key exists, update the value and move the node to the end
        if (map.containsKey(k)) {
            Node<K, V> node = map.get(k);
            node.value = v;
            moveToEnd(node);
        } else {
            // Otherwise, create a new node and append it to the end
            Node<K, V> newNode = new Node<>(k, v);
            map.put(k, newNode);
            appendToEnd(newNode);
        }
    }

    public V get(K k) {
        if (map.containsKey(k)) {
            Node<K, V> node = map.get(k);
            moveToEnd(node);
            return node.value;
        }
        return null;
    }

    public void delete(K k) {
        if (map.containsKey(k)) {
            Node<K, V> node = map.remove(k);
            removeNode(node);
        }
    }

    public K last() {
        if (tail.prev != head) {
            return tail.prev.key;
        }
        return null; // No keys in the data structure
    }

    private void appendToEnd(Node<K, V> node) {
        node.prev = tail.prev;
        node.next = tail;
        tail.prev.next = node;
        tail.prev = node;
    }

    private void moveToEnd(Node<K, V> node) {
        removeNode(node);
        appendToEnd(node);
    }

    private void removeNode(Node<K, V> node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    public static void main(String[] args) {
        Question4<String, Integer> data = new Question4<>();
        data.put("a", 1);
        data.put("b", 2);
        System.out.println(data.last()); // Output: "b"
        System.out.println(data.get("a")); // Output: 1
        System.out.println(data.last()); // Output: "a"
        data.delete("a");
        System.out.println(data.last()); // Output: "b"
    }
}