package com.hypinx.coding.capitalone.codesignal.swe;

import java.util.*;

public class Question42 {

    public static void main(String[] args) throws Exception {
        String sample1 = "CodeSignal";
        int n1 = 3;
        String expected1 = "CodeTignam";
        String result1 = solution(sample1, n1);
        if (!expected1.equals(result1)) {
            throw new Exception("Expected " + expected1 + " but result was " + result1);
        }

        String sample2 = "Quiz, Citizenship, puZZle";
        int n2 = 5;
        String expected2 = "Quiz, Citibenship, quZZle";
        String result2 = solution(sample2, n2);
        if (!expected2.equals(result2)) {
            throw new Exception("Expected " + expected2 + " but result was " + result2);
        }

        String sample3 = "Plain Text";
        int n3 = 2;
        String expected3 = "Pmain Vexv";
        String result3 = solution(sample3, n3);
        if (!expected3.equals(result3)) {
            throw new Exception("Expected " + expected3 + " but result was " + result3);
        }


    }

    public static String solution(String message, int n) {
        List<Character> consonants = List.of('b', 'c', 'd', 'f', 'g', 'h', 'j', 'k', 'l', 'm', 'n', 'p', 'q', 'r', 's', 't', 'v', 'w', 'x', 'y', 'z');
        String[] letters = message.split("");
        int counter = 0;

        for (int i = 0; i < letters.length; i++) {
            char current = letters[i].charAt(0);
            boolean isUppercase = Character.isUpperCase(current);
            current = Character.toLowerCase(current);
            if (!consonants.contains(current)) continue;
            counter++;

            if (counter % n == 0) {
                System.out.println("Current letter = " + current);
                int asciiValue = Integer.valueOf(Character.toLowerCase(current));
                while(!consonants.contains((char) ++asciiValue)) {
                    // If we get to z then loop back to a
                    if (asciiValue > 122) asciiValue = 97;
                }

                char nextValue = (char) asciiValue;
                System.out.println("Replacement letter = " + nextValue);
                // Create the replacement letter and uppercase if originally was uppercase
                String replacement = isUppercase ? "" + Character.toUpperCase(nextValue) : "" + nextValue;
                letters[i] = replacement;
            }
        }

        return String.join("", letters);
    }


}
