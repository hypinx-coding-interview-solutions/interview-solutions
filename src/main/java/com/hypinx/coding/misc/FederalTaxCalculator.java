package com.hypinx.coding.misc;

import java.util.List;

public class FederalTaxCalculator {

    public static void main(String[] args) {
        calculateFederalTax(100000.00, "Married Joint");
    }

    public static double calculateFederalTax(double taxableIncome, String filingStatus) {
        double StandardDeduction = 27700;
        List<Double> marriedList = List.of(11000.00, 44725.00, 95375.00, 182100.00, 231250.00, 578125.00);
        List<Integer> rates = List.of(10, 12, 22, 24, 32, 35);
        int highestRate = 37;

        int counter = 0;
        double totalFederalTax = 0;
        while (taxableIncome > 0) {
            double currentLimit = marriedList.get(counter);
            double currentAmount = taxableIncome - currentLimit;
            if (taxableIncome - currentLimit < 0) {
                currentAmount = taxableIncome - currentLimit;
            }

            if (counter > 0) {
                currentLimit = currentLimit - marriedList.get(counter - 1);
            }

            totalFederalTax += currentLimit * (rates.get(counter) / 100.00);
            taxableIncome = taxableIncome - currentLimit;
            counter++;
        }

        System.out.println("Total federal tax = " + totalFederalTax);
        return totalFederalTax;
    }
}
