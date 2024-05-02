package com.hypinx.coding.capitalone.codesignal;

public class Question39 {

    public static void main(String[] args) throws Exception {
        int[] sampleVisits = new int[]{300,200,100,200,500};
        int sampletarget = 700;
        int result = solution(sampleVisits, sampletarget);
        int expected = 3;
        if (result != expected) {
            throw new Exception("Result is " + result + " and expected " + expected);
        }

        System.out.println("All tests passed");


    }

    public static int solution(int[] visits, int target) {
        int total = 0;
        for (int i = 0; i < visits.length; i++) {
            total += visits[i];
            if (total >= target) return i;
        }

        return -1;
    }
}
