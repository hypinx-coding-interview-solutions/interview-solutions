package com.hypinx.coding.capitalone.powerday.simplebanking.solutionV4;

import java.util.*;

public class Solution {

    /** This solution is overengineering the problem and should only be used if a firm understanding is present **/
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

        SimplifiedBanking simplifiedBanking = new SimplifiedBanking();
        List<String> results = simplifiedBanking.handleRequests(operations);
        results.forEach(System.out::println);
    }
}

class SimplifiedBanking {

    private Map<String, Account> allAccounts;
    private List<String> results = new ArrayList<>();
    private List<Account> cachedSortedAccountsByActivityIndicator;
    private String lastAction = null;


    public SimplifiedBanking() {
        this.allAccounts = new HashMap<>();
    }

    public List<String> handleRequests(String[][] operations) {
        results.clear();
        for (String[] operation : operations) {
            handleRequest(operation);
        }

        return new ArrayList<>(results);
    }

    private void handleRequest(String[] operation) {
        String op = operation[0].toUpperCase();
        String accountId;
        double amount;
        BankingOperations bankingOperation = BankingOperations.valueOf(op);
        String result = "";
        switch (bankingOperation) {
            case CREATE_ACCOUNT:
                accountId = operation[1];
                result = String.valueOf(createAccount(accountId));
                break;
            case DEPOSIT:
                accountId = operation[1];
                amount = Utilities.convertStringToDouble(operation[2]);
                result = String.valueOf(deposit(accountId, amount));
                break;
            case TRANSFER:
                String fromId = operation[1], toId = operation[2];
                amount = Utilities.convertStringToDouble(operation[3]);
                result = String.valueOf(transfer(fromId, toId, amount));
                break;
            case TOP_ACTIVITY:
                topActivity(Utilities.convertStringToInteger(operation[1]));
                break;
        }
        results.add(result);
        lastAction = bankingOperation.name();
    }

    private boolean createAccount(String accountId) {
        if (allAccounts.containsKey(accountId)) return false;

        allAccounts.put(accountId, new Account(accountId));
        return true;
    }

    private double deposit(String accountId, double amount) {
        if (!allAccounts.containsKey(accountId)) return -1;

        Account account = allAccounts.get(accountId);
        return account.updateBalance(amount);
    }

    // X = Src account exists, Y = dest account exists (edge case ~X || ~Y) -> false
    // DeMorgans Law = (~X || ~Y) -> ~(~X || ~Y) -> ~(X && Y) - cleaner solution
    private double transfer(String srcId, String destId, double amount) {
        if (!(allAccounts.containsKey(srcId) && allAccounts.containsKey(destId) && !srcId.equals(destId))) {
            return -1;
        }

        Account src = allAccounts.get(srcId);
        if (src.getBalance() < amount) return -1;

        Account dest = allAccounts.get(destId);
        return AccountUtilities.transfer(src, dest, amount);
    }

    private void topActivity(int n) {
        n = Math.min(n, allAccounts.size());
        List<Account> sortedList;
        if (lastAction != null && lastAction.equals(BankingOperations.TOP_ACTIVITY.name())) {
            sortedList = cachedSortedAccountsByActivityIndicator;
        } else {
            sortedList = new ArrayList<>(allAccounts.values());
            sortedList.sort(new SortAccountsByActivityIndicatorDescending());
            cachedSortedAccountsByActivityIndicator = new ArrayList<>(sortedList);
        }

        StringBuilder topNAccounts = new StringBuilder();
        for (int i = 0; i < n; i++) {
            Account current = sortedList.get(i);
            topNAccounts.append(current.getAccountId() + "(" + current.getActivityIndicator() + ") ");
        }

        results.add(topNAccounts.toString());
    }
}

enum BankingOperations {
    CREATE_ACCOUNT, DEPOSIT, TRANSFER, TOP_ACTIVITY
}

class Account {

    private String accountId;
    private double balance;
    private double activityIndicator;

    public Account(String accountId) {
        this.accountId = accountId;
        this.balance = 0;
        this.activityIndicator = 0;
    }

    public String getAccountId() { return this.accountId; }
    public double getBalance() { return this.balance; }
    public double getActivityIndicator() { return this.activityIndicator; }

    public double updateBalance(double amount) {
        this.balance += amount;
        this.activityIndicator += Math.abs(amount);
        return this.balance;
    }
}

class Utilities {
    public static double convertStringToDouble(String string) {
        double converted;
        try {
            converted = Double.parseDouble(string);
        } catch (InputMismatchException e) {
            throw new InputMismatchException("Failed to convert string data type to double, must provide numerical string");
        }

        return converted;
    }

    public static int convertStringToInteger(String string) {
        int converted;
        try {
            converted = Integer.parseInt(string);
        } catch (InputMismatchException e) {
            throw new InputMismatchException("Failed to convert string data type to int, must provide numerical string");
        }

        return converted;
    }
}

final class AccountUtilities {
    public static double transfer(Account src, Account dest, double balance) {
        dest.updateBalance(balance);
        return src.updateBalance(-balance);

    }
}

class SortAccountsByActivityIndicatorDescending implements Comparator<Account> {

    @Override
    public int compare(Account o1, Account o2) {
        if (o1.getActivityIndicator() == o2.getActivityIndicator()) {
            return o1.getAccountId().compareTo(o2.getAccountId());
        } else {
            return Double.compare(o2.getActivityIndicator(), o1.getActivityIndicator());
        }
    }
}