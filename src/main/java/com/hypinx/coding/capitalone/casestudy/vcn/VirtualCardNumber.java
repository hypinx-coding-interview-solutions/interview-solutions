package com.hypinx.coding.capitalone.casestudy.vcn;

public class VirtualCardNumber {

    public static boolean validateRule(long vcnNumber, long transactionId, double transactionAmount) {

        // Corrected way to get digits
        long isMerchBound = getDigitFromNumber(vcnNumber, 15);              // digit 15
        long cardType = determineCardTypeFromVcn(vcnNumber);                     // digit 14
        long isMultiUse = getDigitFromNumber(vcnNumber, 13);                // digit 13

        long isOnline = getDigitFromNumber(transactionId, 7);               // digit 7
        long isAuthCharge = getDigitFromNumber(transactionId, 6);           // digit 6

        // Visa
        if (cardType == 0) {
            // Rule 1: Is not multi use (0) and it is merchant bound (1)
            // Rule 2 (Exception): Must be online (1), amount less than 100, and transaction must be charge (isAuthCharge != 0)
            return (isMerchBound == 1 && isMultiUse == 0) || (isOnline == 1 && transactionAmount < 100 && isAuthCharge != 0);
        }
        // Mastercard
        else if (cardType == 1) {
            // Rule 1: Less than 100, VCN is not merchant bound (0)
            // Rule 2 (Exception): Can be merchant bound (1) and over 100
            return (transactionAmount < 100 && isMerchBound == 0) || (isMerchBound == 1 && transactionAmount > 100);
        }

        return false;
    }

    /**
     * This is a utility method which will retrieve the digit in the index specified. It is 0 index based.
     * The method will divide the number based on 10 to the power of the digit to find starting from right to left
     * on 0 index basis.
     */
    private static long getDigitFromNumber(long number, int digit) {
        String numberString = String.valueOf(number);
        if (digit < 0 || digit >= numberString.length()) return -1;

        int power = numberString.length() - digit;
        int divider = (int) Math.pow(10, power);

        number = number / divider;
        return number % 10;
    }

    private static int determineCardTypeFromVcn(long vcnNumber) {
        String vcnNumberString = String.valueOf(vcnNumber);
        int vcnNumberLength = vcnNumberString.length();
        boolean isSixteenDigits = vcnNumberLength == 16 ? true : false;
        int firstNumber = Integer.valueOf(vcnNumberString.charAt(0));

        // Visa card type
        if (firstNumber == 4 && isSixteenDigits) return 0;

        if (firstNumber == 5) {
            int secondNumber = Integer.valueOf(vcnNumberString.charAt(1));
            // First 2 digits is [51,55] range
            if (secondNumber >= 1 && secondNumber <= 5 && isSixteenDigits) return 1;
        } else if (firstNumber == 2) {
            // First 4 digits are [2221, 2720]
            int firstFourNumbers = Integer.valueOf(vcnNumberString.substring(0, 4));
            if (firstFourNumbers >= 2221 && firstNumber <= 2720 && isSixteenDigits) return 1;
        }

        // Invalid card type
        return -1;
    }
}
