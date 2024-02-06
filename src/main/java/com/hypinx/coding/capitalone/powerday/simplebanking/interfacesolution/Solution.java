package com.hypinx.coding.capitalone.powerday.simplebanking.interfacesolution;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Main class should be named 'Solution' and should not be public.

/**
 *  * THIS IS A FULLY FUNCTIONING SOLUTION FOR ALL 3 LEVELS
 *  * Current status: READY TO USE IN AN INTERVIEW
 *  * Updated: September 19, 2023
 *
 * This is a complete solution that uses an interface to solve the problem. This is a cleaner solution but a bit harder to write in code.
 *
 * Explaination for approach the solution after reading Level 1
 *
 * You say the following for LEVEL 1:
 * So because we have two operations create account and deposit, in order to solve this problem I am thinking to use an approach where I
 * define an interface. The interface will help achieve abstraction and encapsulation while defining exactly the core functionality this
 * banking system gives. I will then use a class to implement the interface. I am using this approach because in the future the way we
 * do these actions may change but ultimately these actions will exist always. I will also use an in memory storage that will use the
 * accountId as the key and a corresponding BankAccount object. The BankAccount class will be a model class for each account object we
 * will be working with
 *
 * You can say the following for LEVEL 2:
 *
 * Description given above method on line 218
 *
 * You can say the following for LEVEL 3:
 *
 * Description given above method on line 246
 */
class Solution {
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

        /**
         * I will create an object of the simplifiedBankingInMemory and then for every operation I will invoke the process operation
         * method in this class.
         */
        SimplifiedBankingInMemory simplifiedBankingInMemory = new SimplifiedBankingInMemory();
        for (String[] operation : operations) {
            simplifiedBankingInMemory.processOperations(operation);
        }
    }
}

/**
 * SimplifiedBanking will be an interface that defines all the actions permitted in this application. The underlying implementation
 * can change over time so I'll keep it abstract by using an interface
 */
interface SimplifiedBanking {

    // Level 1 ------------------------------------------------------------------
    boolean createAccount(String accountId);

    double deposit(String accountId, double balance);
    // ------------------------------------------------------------------

    // Level 2 ------------------------------------------------------------------

    /**
     */
    double transfer(String fromId, String toId, double balance);
    // ------------------------------------------------------------------

    // Level 3 ------------------------------------------------------------------
    void topActivity(int n);
    // ------------------------------------------------------------------
}

/**
 * The SimplifiedBankingInMemory will implement the SimplifiedBanking interface which will perform all these operations using an in memory
 * storage.
 */
class SimplifiedBankingInMemory implements SimplifiedBanking {
    /**
     * I will use a Map to hold the in memory storage. The key will be the account id and the value will be the BankAccount object
     */
    private Map<String, BankAccount> accounts;

    // Level 3 -------------------------------------------------------------
    private String previousOperation = null;
    List<BankAccount> sortedAccounts = null;

    /**
     * The constructor will initialize to an empty hash map
     */
    public SimplifiedBankingInMemory() {
        this.accounts = new HashMap<>();
    }

    /**
     * The process operations will take a string array of the operation to perform. I will use extract the operation type which is always
     * the first argument and then use a switch statement in order to invoke the proper action. I will also define the variable for
     * accountId and balance because these could be common in operations. I will assign the values individually in each switch case.
     *
     */
    public void processOperations(String[] operation) {
        String type = operation[0].toLowerCase();
        String accountId;
        double balance;
        switch (type) {
            // Level 1 ------------------------------------------------------------------
            case "create_account":
                // In the create account the accountId is in index 1 so I will assign the value here and then inside a print statement
                // I will print the result of calling the createAccount method
                accountId = operation[1];
                System.out.println(this.createAccount(accountId));
                break;
            case "deposit":
                // AccountId for the deposit action is again in index 1. The balance is index 2 but it's as a String data type so I will
                // parse it into a double to pass into the deposit method.
                accountId = operation[1];
                balance = Double.parseDouble(operation[2]);
                System.out.println(this.deposit(accountId, balance));
                break;
            // ------------------------------------------------------------------
            // Level 2 ------------------------------------------------------------------
            case "transfer":
                // The fromId is index 1, toId is index 2, and balance is index 3 which we need to parse to a double
                String fromId = operation[1], toId = operation[2];
                balance = Double.parseDouble(operation[3]);
                System.out.println(this.transfer(fromId, toId, balance));
                break;
            // ------------------------------------------------------------------
            // Level 3 ------------------------------------------------------------------
            case "top_activity":
                // The number of accounts to get top_activity from is in index 1 and as a String. So using Integer.valueOf we can
                // convert to an int and pass an argument
                topActivity(Integer.valueOf(operation[1]));
                break;
            // ------------------------------------------------------------------
            default:
                System.out.println("Operation " + operation[0] + " not supported.");
        }

        // Level 3 -----------------------------------------------------------------
        previousOperation = type;
    }

    // Level 1 ------------------------------------------------------------------

    /**
     * The createAccount method will accept accountId as an argument. The first edge case to check is to see if the account already exists.
     * By querying the accounts map, if an entry is present it means the account exists so we can return false. Otherwise we can
     * add a new entry to the map and set a new BankAccount object as the value and return true.
     */
    @Override
    public boolean createAccount(String accountId) {

        // Check if the account already exists
        if (accounts.containsKey(accountId)) return false;

        // Proceed to add a new account
        accounts.put(accountId, new BankAccount(accountId));
        return true;
    }

    /**
     * For the deposit method we take the accountId and balance. The first edge case is if the account does not exist we have no balance
     * to update. So we can return -1. If the account exists then we can query the map to get the account. Then invokve the
     * adjustAccountBalance method and pass in the balance to adjust by. Then finally we can get the updated balance on the account object
     * and return it.
     */
    @Override
    public double deposit(String accountId, double balance) {

        // If account does not exists, return -1
        if (!accounts.containsKey(accountId)) return -1;

        // Proceed to update the balance
        BankAccount bankAccount = accounts.get(accountId);
        bankAccount.adjustAccountBalance(balance);
        return bankAccount.getBalance();
    }
    // ------------------------------------------------------------------

    // Level 2 ------------------------------------------------------------------

    /**
     * The transfer method will have three arguments. The fromId, toId and balance. There's three edge cases we have to check for
     * 1. If either account does not exist
     * 2. If from and to account are the same
     * 3. And if there is not enough balance in fromAccount to make the transfer
     *
     * If all the cases pass we can proceed to make the transfer. And to make the transfer I'm going to utilize the adjustAccountBalance
     * method which is already present from making the deposit. Since the transfer will deduct money from the fromAccount I will just
     * take the balance and multiply by negative one and pass that in. So this will reduce the balance in fromAccount. And then finally
     * return the balance in fromAccount after the transfer has been made
     */
    @Override
    public double transfer(String fromId, String toId, double balance) {
        // Validate both accounts exist
        if (!accounts.containsKey(fromId) || !accounts.containsKey(toId)) return -1;
        // Validate both accounts are unique
        if (fromId.equals(toId)) return -1;
        // Validate there are sufficient funds in from account
        BankAccount fromAccount = accounts.get(fromId);
        if (fromAccount.getBalance() < balance) return -1;

        // Proceed to make the transfer
        BankAccount toAccount = accounts.get(toId);
        // Multiply by negative 1 so we can pass in the negative value of the balance to add to our existing balance
        fromAccount.adjustAccountBalance(balance * (-1));
        toAccount.adjustAccountBalance(balance);
        return fromAccount.getBalance();
    }
    // ------------------------------------------------------------------

    // Level 3 ------------------------------------------------------------------

    /**
     * The argument in the topActivity method will be an integer n, we will lower bound this to n or the total size of the accounts map to avoid any index out of bounds issues. Then I am storing the previous operation we performed into a variable. This is because say we have 100 million accounts. If we have 2 or more back to back top_activity operations, our logic involves getting all the accounts, then performing a sort operation. This can be costly with this many accounts. If we get back to back top_activity, we can simply cache the result and reuse that to have better time complexity.
     *
     * If we have any other operation then it means we have made modifications to one or more accounts, we need to resort as our results have changed.
     *
     * In the event we need to sort our accounts I will use the sort function and use a lambda expression to first check if the activity indicator is the same between 2 accounts. If so then I will sort based on alphabetical order, otherwise sort by activity indicator value.
     */
    @Override
    public void topActivity(int n) {
        // One issue we can have is we might have an n which is greater than the total number of accounts. So because I am going to
        // lower bound this to n or the total size of the accounts. This will prevent a potential index out of bounds error when
        // we loop the sorted account list to print the top activity accounts.
        n = Math.min(n, accounts.size());

        if (previousOperation != null && !previousOperation.equals("top_activity")) {
            // Extract all BankAccounts from accounts map
            sortedAccounts = new ArrayList<>(accounts.values());
            sortedAccounts.sort(((a, b) -> {
                if (a.getActivityIndicator() == b.getActivityIndicator()) {
                    // Sort by the account id if activity indicator is equal
                    return a.getAccountId().compareTo(b.getAccountId());
                }

                // Compare by activity indicator
                return Double.compare(b.getActivityIndicator(), a.getActivityIndicator());
            }));
        }

        // Print the sorted account list
        for (int i = 0; i < n; i++) {
            BankAccount currentAccount = sortedAccounts.get(i);
            System.out.print(currentAccount.getAccountId() + "(" + currentAccount.getActivityIndicator() + ") ");
        }
        System.out.println();
    }
    // ------------------------------------------------------------------
}


// Model class to represent a bank account
class BankAccount {
    // Level 1 ------------------------------------------------------------------
    private String accountId;
    private double balance;
    // ------------------------------------------------------------------

    // Level 3 ------------------------------------------------------------------
    private double activityIndicator;
    // ------------------------------------------------------------------

    public BankAccount(String accountId) {
        // Level 1 ------------------------------------------------------------------
        this.accountId = accountId;
        this.balance = 0;
        // ------------------------------------------------------------------
        // Level 3 ------------------------------------------------------------------
        this.activityIndicator = 0;
        // ------------------------------------------------------------------
    }

    // Level 1 ------------------------------------------------------------------
    public String getAccountId() {
        return this.accountId;
    }

    public double getBalance() {
        return this.balance;
    }
    // ------------------------------------------------------------------

    // Level 3 ------------------------------------------------------------------
    public double getActivityIndicator() {
        return this.activityIndicator;
    }
    // ------------------------------------------------------------------

    public double adjustAccountBalance(double balance) {
        this.balance += balance;
        // Level 3 ------------------------------------------------------------------
        this.activityIndicator += Math.abs(balance);
        // ------------------------------------------------------------------
        return this.balance;
    }
}

