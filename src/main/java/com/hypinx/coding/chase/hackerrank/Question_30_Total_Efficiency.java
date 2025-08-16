package com.hypinx.coding.chase.hackerrank;

import com.hypinx.coding.misc.TestCaseValidator;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Question_30_Total_Efficiency {

    public static void main(String[] args) {
        List<Integer> skill = Arrays.asList(1,2,3,2);
        long expected = 7;
        long result = getTotalEfficiency(skill);

        TestCaseValidator.validateTestCase("1", expected, result);
    }

    public static long getTotalEfficiency(List<Integer> skill) {
        Collections.sort(skill);
        long result = 0;

        int leftPtr = 0, rightPtr = skill.size() - 1;

        long efficiency = skill.get(leftPtr) + skill.get(rightPtr);

        while (leftPtr < rightPtr) {
            long firstMember = skill.get(leftPtr++), secondMember = skill.get(rightPtr--);
            if (firstMember + secondMember != efficiency) return -1;
            result += firstMember * secondMember;
        }

        return result;
    }
}
