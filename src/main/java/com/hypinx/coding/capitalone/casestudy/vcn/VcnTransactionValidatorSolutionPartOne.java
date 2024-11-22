package com.hypinx.coding.capitalone.casestudy.vcn;

public class VcnTransactionValidatorSolutionPartOne {

    /*
    1. Okay so first I will use a separate utility method to extract the digit from the vcnNumber,
    so let me implement that.

    So now that the utility method is made, I will update the digit extract to call this method

    Next I will flip the cardType check to start with 0 first then 1 since our comment starts with Visa.

    For Visa our rule one was that it cannot be multi use and is merchant bound. So isMerchBound needs to
    be equal to 1 and isMultiUse needs to be equal to 0. And both of these need to be true so we have to use
    a logical AND operation not a OR.

    And the second rule stated that it must be an online transaction so isOnline is equal to 1, the amount
    needs to be strictly less than 100 and it cannot be an authorization charge so isAuth does not equal to 0.
    So let me make the changes here.

    So that solves the condition issue for Visa, now for Mastercard the rule was the amount needs to be less
    than 100 and is cannot be merchant bound.

    The second rule was that is can be merchant bound and amount is over 100. So we need to change the amount
    to be strictly greater than 100 and change the OR operation to AND since both conditions need to be true.

     */
    public static boolean isValidVcnTransaction(long vcnNumber, long transactionId, double transactionAmount) {

        long isMerchBound = getDigitFromNumber(vcnNumber, 15);
        long cardType = getDigitFromNumber(vcnNumber, 14);
        long isMultiUse = getDigitFromNumber(vcnNumber, 13);

        long isOnline = getDigitFromNumber(transactionId, 7);
        long isAuthCharge = getDigitFromNumber(transactionId, 6);

        if (cardType == 0) {
            return (isMerchBound == 1 && isMultiUse == 0) || (isOnline == 1 && transactionAmount < 100 && isAuthCharge != 0);
        }
        else if (cardType == 1) {
            return (transactionAmount < 100 && isMerchBound == 0) || (isMerchBound == 1 && transactionAmount > 100);
        }

        return false;
    }


    private static long getDigitFromNumber(long number, int digit) {
        String numberString = String.valueOf(number);
        if (digit < 0 || digit >= numberString.length()) return -1;

        return Long.parseLong(Character.toString(numberString.charAt(digit)));
    }
}
