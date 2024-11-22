package com.hypinx.coding.capitalone.casestudy.vcn;

public class VcnTransactionValidatorSolutionPartTwo {

    /*
    Questions to ask:
    1. So for the Visa rule, if we have an old card and it has 13 digits, does this
    mean that instead of digit 13, 14, 15 to get the values for multi use, card type,
    and merchant bound, we are now looking at digits 10, 11, 12 for this value?

    Solution:

    Ok so based on our new rules, the vcnNumber is no longer guaranteed to be 16 characters so we cannot
    pass in digit 15, 14, and 13 to extract the values for merchBound, cardType, and multiUse since this
    could lead to an index out of bounds issue. So I think what we should do is first introduce an offset
    value, by default this can be set to 0. Then if the vcnNumber is length 13, we can set the offset to 3.
    And for the digit value we are passing into the method we can do the value minus the offset to ensure
    we get the correct index. And for the cardType we can have another method say getCardType which
    will apply the rules and return the cardType.

    Ok so now let me implement the getCardType method. For this I am thinking I can convert the vcnNumber
    to a string and start by checking the first index. If it's a 4 then we need to check if either
    vcnNumber is length 13 or 16 and this will verify it's a visa card.

    So this handles the scenario for visa. For master card we need to check the first 2 numbers, if its between
    51 and 55 or the first 4 numbers between 2221 to 2720. For both scenarios the length must be 16. So at this
    point, if the length is not 16 we can exit the function and return something like -1. Otherwise
    we can take the substring for first 2 numbers and for first 4 and check if either fall within the specified
    range.

    So I think this is how I would handle these additional rules in this problem.

     */
    public static boolean isValidVcnTransaction(long vcnNumber, long transactionId, double transactionAmount) {

        int offset = 0;
        String vcnNumberAsString = String.valueOf(vcnNumber);
        if (vcnNumberAsString.length() == 13) offset = 3;

        long isMerchBound = getDigitFromNumber(vcnNumber, 15 - offset);
        long cardType = getCardType(vcnNumber);
        long isMultiUse = getDigitFromNumber(vcnNumber, 13 - offset);

        long isOnline = getDigitFromNumber(transactionId, 7);
        long isAuthCharge = getDigitFromNumber(transactionId, 6);

        // Visa
        if (cardType == 0) {
            return (isMerchBound == 1 && isMultiUse == 0) || (isOnline == 1 && transactionAmount < 100 && isAuthCharge != 0);
        }
        // Mastercard
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

    private static long getCardType(long vcnNumber) {
        String numberString = String.valueOf(vcnNumber);
        boolean isLengthSixteen = numberString.length() == 16;

        if (numberString.charAt(0) == '4' && (
            numberString.length() == 13 || isLengthSixteen)) {
                return 0;
        }

        if (isLengthSixteen) {
            int firstTwo = Integer.parseInt(numberString.substring(0, 2));
            int firstFour = Integer.parseInt(numberString.substring(0, 4));
            if ((firstTwo >= 51 && firstTwo <= 55) || (firstFour >= 2221 && firstFour <= 2720)) {
                return 1;
            }
        }

        return -1;
    }
}
