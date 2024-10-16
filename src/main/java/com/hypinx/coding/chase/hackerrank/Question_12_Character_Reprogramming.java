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

    public static int getMaxDeletions(String input) {
        Stack<Character> upDown = new Stack<>();
        Stack<Character> leftRight = new Stack<>();
        int size = input.length();

        for(char C : input.toCharArray()) {
            if(C == 'U' || C == 'D') {
                if(upDown.isEmpty() || upDown.peek() == C) {
                    upDown.push(C);
                } else if(! upDown.isEmpty()) {
                    upDown.pop();
                }

            } else {
                if(C == 'L' || C == 'R') {
                    if(leftRight.isEmpty() || leftRight.peek() == C) {
                        leftRight.push(C);
                    } else if (! leftRight.isEmpty()) {
                        leftRight.pop();
                    }
                }
            }
        }

        return (size - (upDown.size() + leftRight.size()));
    }

}
