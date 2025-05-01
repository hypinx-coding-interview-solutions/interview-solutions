package com.hypinx.coding.meta.coding;

import com.hypinx.coding.misc.TestCaseValidator;

public class Question_18_Fast_Power {

    public static void main(String[] args) {
        double result = fastPower(2, 3);
        TestCaseValidator.validateTestCase("1", 8, result);

        result = fastPower(2, -3);
        TestCaseValidator.validateTestCase("1", 0.125, result);

        result = fastPower(5, -2);
        TestCaseValidator.validateTestCase("1", 0.04, result);

        result = fastPower(3,0);
        TestCaseValidator.validateTestCase("1", 1.0, result);

    }

    public static double fastPower(int base, int exponent) {
        // Edge case: Divide by 0 error, anything 0^(-x) = 1/0 which is not valid
        if (base == 0) {
            if (exponent <= 0) throw new IllegalArgumentException("0 to non-positive power undefined");
            return 0;
        }

        // For negative exponents, evaluate as if they are positive by flipping signs and then return 1/result
        boolean isNegative = exponent < 0;
        if (isNegative) {
            exponent = -exponent; // flip positive
        }

        double result = 1;
        double currentPower = base;

        while (exponent > 0) {
            // Check if exponent is odd
            if (exponent != (exponent / 2) * 2) {
                result *= currentPower;
            }
            currentPower *= currentPower;
            exponent /= 2;
        }

        return isNegative ? 1.0 / result : result;
    }
}
