package com.hypinx.coding.capitalone.casestudy.vcn;

public class VcnTransactionValidatorSolutionPartThree {
    /*
        Improved code
     */
    private static final int VISA = 0;
    private static final int MASTERCARD = 1;
    public static boolean isValidVcnTransaction(long vcnNumber, long transactionId, double transactionAmount) {

        int offset = 0;
        String vcnNumberAsString = String.valueOf(vcnNumber);
        if (vcnNumberAsString.length() == 13) offset = 3;

        long isMerchBound = getDigitAtPosition(vcnNumber, 15 - offset);
        long cardType = getCardType(vcnNumber);
        long isMultiUse = getDigitAtPosition(vcnNumber, 13 - offset);

        long isOnline = getDigitAtPosition(transactionId, 7);
        long isAuthCharge = getDigitAtPosition(transactionId, 6);

        // Visa
        if (cardType == VISA) {
            return (isMerchBound == 1 && isMultiUse == 0) || (isOnline == 1 && transactionAmount < 100 && isAuthCharge != 0);
        }
        // Mastercard
        else if (cardType == MASTERCARD) {
            return (transactionAmount < 100 && isMerchBound == 0) || (isMerchBound == 1 && transactionAmount > 100);
        }

        return false;
    }

    private static long getDigitAtPosition(long number, int position) {
        String numberString = String.valueOf(number);
        if (position < 0 || position >= numberString.length()) {
            throw new IllegalArgumentException("Invalid position " + position + "for digit extraction");
        }

        return Long.parseLong(Character.toString(numberString.charAt(position)));
    }

    private static long getCardType(long vcnNumber) {
        String numberString = String.valueOf(vcnNumber);
        boolean isLengthSixteen = numberString.length() == 16;

        if (numberString.charAt(0) == '4' && (
            numberString.length() == 13 || isLengthSixteen)) {
                return VISA;
        }

        if (isLengthSixteen) {
            int firstTwo = Integer.parseInt(numberString.substring(0, 2));
            int firstFour = Integer.parseInt(numberString.substring(0, 4));
            if ((firstTwo >= 51 && firstTwo <= 55) || (firstFour >= 2221 && firstFour <= 2720)) {
                return MASTERCARD;
            }
        }

        return -1;
    }
}
