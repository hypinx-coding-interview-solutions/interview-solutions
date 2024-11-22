package com.hypinx.coding.capitalone.casestudy.vcn;

/**
 * This is the original code which is given in the Virtual Card Number Case Study. See VcnTransactionValidatorSolution Parts 1 and 2
 * for the solution
 */
public class VcnTransactionValidatorOriginal {

    public static boolean isValidVcnTransaction(long vcnNumber, int transactionId, int transactionAmount) {
        long isMerchantBound = vcnNumber % 2;
        long cardType = vcnNumber % 4;
        long isMultiUse = vcnNumber % 6;

        long isOnlineTransaction = transactionId = transactionId % 2;
        long isAuth = transactionId % 4;

        // Visa
        if (cardType == 1) {
            return (isMerchantBound == 0 || isMultiUse == 0) &&
                    (isOnlineTransaction != 0 && transactionAmount > 100 || isAuth != 0);
        } else if (cardType == 0) {
            return (transactionAmount <= 100 && isMerchantBound == 0) &&
                    (transactionAmount <= 100 || isMerchantBound == 1);
        }
        return false;
    }
}
