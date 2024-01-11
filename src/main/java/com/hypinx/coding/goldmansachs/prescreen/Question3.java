package com.hypinx.coding.goldmansachs.prescreen;

import java.util.*;

public class Question3 {

    public static void main (String[] args) {
        String input = "abcdefghijklmnop";
        String expected = "qrstuvwxyz";
        String result = makePanagram(input);

        System.out.println(expected.equals(result));
    }

    public static String makePanagram(String sentence) {
        List<Character> missingLettersList = new ArrayList<>();

        sentence = sentence.toLowerCase();

        // add each character to the set
        for(int i = 0; i < sentence.length(); i++){
            char current = sentence.charAt(i);
            if (current >= 'a' && current <= 'z'){
                if (!missingLettersList.contains(current)) {
                    missingLettersList.add(current);
                }
            }
        }

        Collections.sort(missingLettersList);

        // check which characters are missing
        StringBuilder missingChars = new StringBuilder();
        for(char c = 'a'; c <= 'z'; c++){
            if(!missingLettersList.contains(c)){
                missingChars.append(c);
            }
        }

        return missingChars.toString();
    }
}
