package com.hypinx.coding.capitalone.codesignal.swe;

import com.hypinx.coding.misc.TestCaseValidator;

public class Question83 {

    public static void main(String[] args) {
        int[] alpha2beta = {0, 200, 500};
        int[] beta2alpha = {99, 210, 450};
        int missions = 1;

        int expected = 310;
        int result = solution(alpha2beta, beta2alpha, missions);

        TestCaseValidator.validateTestCase("1", expected, result);
    }

    private static int solution(int[] alpha2beta, int[] beta2alpha, int missions) {
        final int TRAVEL = 100;

        long time = 0; // current time at Alpha
        int i = 0;     // pointer in alpha2beta
        int j = 0;     // pointer in beta2alpha

        for (int m = 0; m < missions; m++) {
            // Alpha -> Beta: earliest departure >= time
            while (i < alpha2beta.length && alpha2beta[i] < time) i++;
            long departA = alpha2beta[i];
            long arriveB = departA + TRAVEL;

            // Beta -> Alpha: earliest departure >= arriveB
            while (j < beta2alpha.length && beta2alpha[j] < arriveB) j++;
            long departB = beta2alpha[j];
            long arriveA = departB + TRAVEL;

            time = arriveA; // next mission starts from Alpha at this time
        }

        return (int) time;
    }
}
