package com.hypinx.coding.chase.hackerrank;

import com.hypinx.coding.misc.TestCaseValidator;

import java.util.Stack;

public class Question_12_Character_Reprogramming {

    public static void main(String[] args) {

        String input = "URDR";
        int expected = 2;
        int result = getMaxDeletions(input);

        TestCaseValidator.validateTestCase("1", expected, result);
    }

    public static int getMaxDeletions(String s) {
        Stack<Character> upDown = new Stack<>();
        Stack<Character> leftRight = new Stack<>();
        int size = s.length();

        for(char c : s.toCharArray()) {
            if(c == 'U' || c == 'D') {
                if(upDown.isEmpty() || upDown.peek() == c) {
                    upDown.push(c);
                } else if(!upDown.isEmpty()) {
                    upDown.pop();
                }

            } else {
                if(c == 'L' || c == 'R') {
                    if(leftRight.isEmpty() || leftRight.peek() == c) {
                        leftRight.push(c);
                    } else if (!leftRight.isEmpty()) {
                        leftRight.pop();
                    }
                }
            }
        }

        return (size - (upDown.size() + leftRight.size()));
    }

}
