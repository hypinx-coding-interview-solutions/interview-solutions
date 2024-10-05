package com.hypinx.coding.capitalone.casestudy.vcn;

public class VirtualCardNumber {

    public static boolean validateRule(long virtualCardNumber, long transactionId, double transactionAmount) {

        /*
        First change will be in the way the digits are extracted. The given logic will be something like this which is incorrect.
        This simply gives you the remainder when you divide by 2, 4, or 6. The fix is shown underneath this block what it should be

        long isMerchBound = virtualCardNumber % 2;
        long cardType = virtualCardNumber % 4;
        long isMultiUse = virtualCardNumber % 6;

        long isAuthCharge = transactionId % someNumber
        long isOnline = transactionId % someNumber

         */

        // Corrected way to get digits
        long isMerchBound = virtualCardNumber % 10;         // digit 15
        long cardType = (virtualCardNumber / 10) % 10;      // digit 14
        long isMultiUse = (virtualCardNumber / 100) % 10;  // digit 13

        long isAuthCharge = transactionId % 10;         // digit 6
        long isOnline = (transactionId / 10) % 10;      // digit 7

        // MC
        if (cardType == 1) {
            return (isMerchBound == 0 && transactionAmount < 100) || (isMerchBound == 1 && transactionAmount > 100);
            // Visa
        } else if (cardType == 0) {
            return (isMerchBound == 1 && isMultiUse == 0) || (transactionAmount < 100 && isAuthCharge == 0 && isOnline == 1);
        }

        return false;
    }
}
