package com.hypinx.coding.capitalone.codesignal;

import java.util.*;

class Question10 {

    public static void main(String[] args) {

        String[][] input1 = new String[3][];
        input1[0] = new String[]{"hello", "world"};
        input1[1] = new String[]{"How", "areYou", "doing"};
        input1[2] = new String[]{"Please", "look", "and", "align", "to", "the", "center"};
        int width1 = 16;
        String[] expected1 = new String[]{"******************", "*  hello world   *","*How areYou doing*", "*Please look and *", "*  align to the  *", "*     center     *", "******************"};
        String[] res1 = solution(input1, width1);

        System.out.println("Input 1 = " + Arrays.equals(res1, expected1));

        String[][] input2 = new String[1][];
        input2[0] = new String[]{"LeMur", "is", "the", "best"};
        int width2 = 20;
        String[] expected2 = new String[]{"**********************", "* LeMur is the best  *", "**********************"};
        String[] res2 = solution(input2, width2);
        System.out.println("Input 2 = " + Arrays.equals(res2, expected2));

    }

    public static String[] solution(String[][] paragraphs, int width) {
        List<String> result = new ArrayList<String>();

        // Builder top and bottom border:
        StringBuilder borders = new StringBuilder();
        while(borders.length() < width + 2) {
            borders.append("*");
        }
        String topAndBottom = borders.toString();
        // Add border to top
        result.add(topAndBottom);

        for (int i = 0; i < paragraphs.length; i++) {
            String paragraph = String.join(" ", paragraphs[i]);
            processParagraph(width, paragraph, result);
        }

        // Add border to bottom
        result.add(topAndBottom);

        // String[] array = list.toArray(new String[0]);
        return result.toArray(new String[0]);
    }

    public static void processParagraph(int width, String paragraph, List<String> result) {

        int currentWidth = 0;
        StringBuilder builder = new StringBuilder();



        while (paragraph.length() > 0) {

            if (paragraph.length() > width) {
                // Check if 16th index is the end of the word or sentence
                String substring;
                if (paragraph.charAt(width) == ' ' || paragraph.length() == width) {
                    substring = paragraph.substring(0, width);
                    paragraph = paragraph.substring(width);
                } else {
                    // Falls on a letter - go back till we find the first whitespace
                    int end = width;
                    while(paragraph.charAt(end) != ' ') {
                        end--;
                    }
                    // Move end up by 1 to the first letter of next word that didn't fit
                    substring = paragraph.substring(0, end);
                    // Apply padding
                    substring = applyPadding(substring, width);
                    // Add to result
                    paragraph = paragraph.substring(end);
                }
                result.add(substring);
            } else {
                // Current paragraph length less than width
                result.add(applyPadding(paragraph, width));
                paragraph = "";
            }
        }
    }

    public static String applyPadding(String substring, int width) {
        int difference = width - substring.length();

        StringBuilder builder = new StringBuilder();
        builder.append(substring);

        // Odd - append extra space to end
        if (difference % 2 == 1) {
            builder.append(" ");
            difference--;
        }

        // Apply padding
        int half = difference / 2;
        StringBuilder padding = new StringBuilder();
        while(half-- > 0) {
            padding.append(" ");
        }

        // Add border left and right to text
        builder.insert(0, "*" + padding);
        builder.append(padding + "*");

        return builder.toString();
    }

    // Helper method to view list elements
    public static void printList(List<String> list) {
        for (String el : list) {
            System.out.println(el);
        }

    }
}