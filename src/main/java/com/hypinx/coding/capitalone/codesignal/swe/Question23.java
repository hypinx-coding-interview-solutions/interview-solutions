package com.hypinx.coding.capitalone.codesignal.swe;

import java.util.*;

public class Question23 {
    public static void main(String[] args) {
        String panel = "2311453915";
        String[] codes = new String[]{"0211", "639"};
        String[] expected = new String[]{"not found", "11", "not found", "39", "not found"};
        String[] result = solution(panel, codes);
        System.out.println(equals(expected, result));

    }

    public static boolean equals(String[] expected, String[] result) {
        for (int i = 0; i < expected.length; i++) {
            if (!expected[i].equals(result[i])) return false;
        }
        return true;
    }

    public static String[] solution(String panel, String[] codes) {
        List<String> result = new ArrayList<String>();
        for (int i = 0; i < codes.length; i++) {
            String code = codes[i];
            for (int j = 0; j < code.length() - 1; j++) {
                String pattern = code.substring(j+1);
                int index = Integer.parseInt(code.substring(0, j+1));
                if (index < 0 || index > panel.length()) {
                    result.add("not found");
                    continue;
                }
                StringBuilder check = new StringBuilder();
                for (int k = index; k < index + pattern.length() && k < panel.length(); k++) {
                    check.append(panel.charAt(k));
                }
                if (check.toString().equals(pattern)) {
                    result.add(pattern);
                    continue;
                }
                result.add("not found");
            }
        }

        return result.toArray(new String[result.size()]);
    }
}