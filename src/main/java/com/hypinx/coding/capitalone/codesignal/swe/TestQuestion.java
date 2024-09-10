package com.hypinx.coding.capitalone.codesignal.swe;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class TestQuestion {

    public static void main(String[] args) {
        Account a1 = new Account(100, "a1");
        Account a2 = new Account(200, "a2");
        Account a3 = new Account(300, "a3");

        PriorityQueue queue = new PriorityQueue<>(new ComparatorFunction());

        queue.add(a1);
        queue.add(a2);
        queue.add(a3);

        a2.value = 500;
    }

    static class Account {
        public int value;
        public String accountName;

        public Account(int value, String accountName) {
            this.value = value;
            this.accountName = accountName;
        }
    }

    static class ComparatorFunction implements Comparator<Account> {

        @Override
        public int compare(Account o1, Account o2) {
            if (o1.value == o2.value) {
                return o1.accountName.compareTo(o2.accountName);
            }

            return Integer.compare(o2.value, o1.value);
        }
    }
}
