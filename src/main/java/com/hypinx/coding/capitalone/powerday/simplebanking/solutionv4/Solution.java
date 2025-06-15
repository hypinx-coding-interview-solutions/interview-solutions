package com.hypinx.coding.capitalone.powerday.simplebanking.solutionv4;

import java.util.*;

public class Solution {

    public static void main(String[] args) {
        String[][] operations = {
                // Level 1 ------------------------------------------------------------------
                {"CREATE_ACCOUNT", "account1"},
                {"CREATE_ACCOUNT", "account1"},
                {"CREATE_ACCOUNT", "account2"},
                {"DEPOSIT", "non-existing", "2700"},
                {"DEPOSIT", "account1", "2700"},
                // ------------------------------------------------------------------
                // Level 2 ------------------------------------------------------------------
                {"TRANSFER", "account1", "account2", "2701"},
                {"TRANSFER", "account1", "account2", "200"},
                // ------------------------------------------------------------------
                // Level 3 ------------------------------------------------------------------
                {"TRANSFER", "account1", "account2", "2500"},
                {"DEPOSIT", "account2", "300"},
                {"CREATE_ACCOUNT", "account3"},
                {"DEPOSIT", "account3", "4000"},
                {"TOP_ACTIVITY", "3"},
                {"DEPOSIT", "account2", "1000"},
                {"TOP_ACTIVITY", "2"},
                {"TOP_ACTIVITY", "5"}
                // ------------------------------------------------------------------
        };

        BankingApp app = new BankingApp();
        for (String[] operation : operations) {
            app.processBankingOperation(operation);
        }
    }
}

class BankAccount {
    private String accountId;
    private int amount;
    private int activity;

    public BankAccount(String accountId) {
        this.accountId = accountId;
    }

    public String getAccountId() {
        return this.accountId;
    }

    public int getAmount() {
        return this.amount;
    }

    public int getActivity() {
        return this.activity;
    }

    public int deposit(int amount) {
        this.amount += amount;
        this.activity += amount;
        return this.amount;
    }

    public int withdraw(int amount) {
        this.amount -= amount;
        this.activity += amount;
        return this.amount;
    }
}

class BankingApp {

    private Map<String, BankAccount> accounts;

    public BankingApp() {
        this.accounts = new HashMap<>();
    }

    public void processBankingOperation(String[] operation) {
        String action = operation[0].toUpperCase();
        String accountId = operation[1];
        int amount;

        switch (action) {
            case "CREATE_ACCOUNT":
                System.out.println(this.createAccount(accountId));
                break;
            case "DEPOSIT":
                amount = convertStringToInteger(operation[2]);
                System.out.println(this.deposit(accountId, amount));
                break;
            case "TRANSFER":
                String toId = operation[2];
                amount = convertStringToInteger(operation[3]);
                System.out.println(this.transfer(accountId, toId, amount));
                break;
            case "TOP_ACTIVITY":
                topActivity(convertStringToInteger(operation[1]));
                break;
        }
    }

    private boolean createAccount(String accountId) {
        if (accountExists(accountId)) {
            return false;
        }

        accounts.put(accountId, new BankAccount(accountId));
        return true;
    }

    private int deposit(String accountId, int amount) {
        if (!accountExists(accountId) || amount <= 0) return -1;

        BankAccount account = accounts.get(accountId);
        return account.deposit(amount);
    }

    private int transfer(String fromId, String toId, int amount) {
        if (fromId.equals(toId) ||
            amount <= 0 ||
            !accountExists(fromId) ||
            !accountExists(toId) ||
            accounts.get(fromId).getAmount() < amount
        ) {
            return -1;
        }

        accounts.get(toId).deposit(amount);
        return accounts.get(fromId).withdraw(amount);
    }

    private void topActivity(int n) {
        n = Math.min(n, accounts.size());

        // Use a min heap priority queue to store top N accounts for faster sorting and space optimization
        PriorityQueue<BankAccount> pq = new PriorityQueue<>(Comparator.comparingInt(BankAccount::getActivity));

        for (BankAccount account : accounts.values()) {
            pq.offer(account);
            // Eject the smallest if we go over N size
            if (pq.size() > n) pq.poll();
        }

        List<BankAccount> topN = new ArrayList<>(pq);
        topN.sort((a, b) -> {
            if (a.getActivity() == b.getActivity()) {
                return a.getAccountId().compareTo(b.getAccountId());
            }

            return Integer.compare(b.getActivity(), a.getActivity());
        });

        // Print top N
        for (BankAccount account : topN) {
            System.out.print(account.getAccountId() + "(" + account.getActivity() + ") ");
        }
        System.out.println();
    }

    private boolean accountExists(String accountId) {
        return accounts.containsKey(accountId);
    }

    private int convertStringToInteger(String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private double convertStringToDouble(String value) {
        try {
            return Double.parseDouble(value);
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}
