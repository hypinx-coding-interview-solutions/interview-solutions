package com.hypinx.coding.chase.hackerrank;

import com.hypinx.coding.misc.TestCaseValidator;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Question_13_Fleet_of_Vehicles {

    public static void main (String[] args) {
        List<Integer> wheels = List.of(4,5,2);
        List<Integer> expected = List.of(2,0,1);
        List<Integer> result = chooseFleets(wheels);

        TestCaseValidator.validateTestCase("1", expected, result);

        wheels = List.of(4,5,6);
        result = chooseFleets(wheels);
        expected = List.of(2,0,2);

        TestCaseValidator.validateTestCase("2", expected, result);

    }

    public static List<Integer> chooseFleets(List<Integer> wheels) {
        int[] result = new int[wheels.size()];
        int count = 0;

        for (int i = 0; i < wheels.size(); i++) {
            int wheel = wheels.get(i);
            count = 0;
            if (wheel > 0 && wheel % 2 == 0) {
                count += Math.floor(wheel / 2 / 2) + 1;
            }
            result[i] = count;
        }

        return Arrays.stream(result).boxed().collect(Collectors.toList());
    }
}
