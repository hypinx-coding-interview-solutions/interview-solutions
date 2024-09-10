package com.hypinx.coding.capitalone.codesignal.swe;

public class Question17 {
    public static void main(String[] args) {

        int initial = 1500;
        int[] changes = new int[]{-100, -300, 450, 500, -500, -600};
        String result = solution(initial, changes);
        String expected = "beginner";
        System.out.println(result.equals(expected));
    }

    public static String solution(int initial, int[] changes) {
        for (int i = 0; i < changes.length; i++) {
            initial += changes[i];
        }

        String rank;
        if (initial < 1000) {
            rank = "beginner";
        } else if (initial < 1500) {
            rank = "intermediate";
        } else if (initial < 2000) {
            rank = "advanced";
        } else {
            rank = "pro";
        }

        return rank;
    }
}
