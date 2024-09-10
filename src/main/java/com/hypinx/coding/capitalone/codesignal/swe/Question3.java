package com.hypinx.coding.capitalone.codesignal.swe;

public class Question3 {
    public static void main(String[] args) {
        String t = "azcabcab", s = "acb";
        int res = solution(t, s);
        System.out.println(res == 2);
    }

    static int solution(String t, String s) {
        char one = s.charAt(0), two = s.charAt(1), three = s.charAt(2);
        int counter = 0;
        for (int i = 0; i < t.length() - 4; i++) {
            if (t.charAt(i) == one && t.charAt(i + 2) == two && t.charAt(i + 4) == three) {
                counter++;
            }
        }

        return counter;
    }
}