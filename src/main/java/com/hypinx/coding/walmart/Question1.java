package com.hypinx.coding.walmart;

public class Question1 {

    public static void main(String[] args) {
        double result = calculatePower(2, 3);     // Expect 8
        System.out.println("2 to the power of 3 = " + result);

        result = calculatePower(4, 3);          // Expect 64
        System.out.println("4 to the power of 3 = " + result);

        result = calculatePower(4, -1);         // Expect 0.25
        System.out.println("4 to the power of -1 = " + result);

        result = calculatePower(-4, 1);         // Expect -4
        System.out.println("2 to the power of 3 = " + result);

        result = calculatePower(-4, -1);        // Expect -0.25
        System.out.println("-4 to the power of -1 = " + result);

        result = calculatePower(4, 0);          // Expect 1
        System.out.println("4 to the power of 0 = " + result);

        result = calculatePower(0, 0);          // Expect 1
        System.out.println("0 to the power of 0 = " + result);
    }

    /**
     * Solution uses a divide and conquer approach. Every recursive call we are dividing the exponent by 2.
     * Then depending on whether the exponent is even or odd, we will take the computed value of the sub problem
     * and multiple with itself or itself and x.
     *
     * Ex. If 2^8 then we have recursive calls for: 2^8, 2^4, 2^2, 2^1, 2^0
     * 2^0 -> return 1
     * In call for 2^1 we have temp = 1. y = 1 = odd so we return x * temp * temp = 2 * 1 * 1 = 2
     * In call for 2^2 we have temp = 2, y = 2 = even so we return temp * temp = 2 * 2 = 4
     * In call for 2^4 we have temp = 4, y = 4 = even, so we return temp * temp = 4 * 4 = 16
     * In call for 2^8 we have temp = 16, y = 8 = even, so we return temp * temp = 16 * 16 = 256
     *
     * Time Complexity: O(log y) - base 2.
     * Space Complexity: O(log y) - base 2.
     *
     * @param x
     * @param y
     * @return
     */
    public static double calculatePower(double x, int y) {
        // Base case: if the exponent is 0, return 1
        if (y == 0) {
            return 1;
        }

        // If the exponent is negative, invert the base and make the exponent positive
        if (y < 0) {
            x = 1 / x;
            y = -y;
        }

        // Recursive case: calculate power using divide and conquer
        // 1 - 2^4
        // 2 - 2^2
        // 3 - 2^1 - exit
        double temp = calculatePower(x, y / 2);

        // If the exponent is even
        if (y % 2 == 0) {
            return temp * temp;
        } else { // If the exponent is odd
            return x * temp * temp;
        }
    }
}

