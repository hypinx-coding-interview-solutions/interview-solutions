package com.hypinx.coding.capitalone.codesignal.swe;

import com.hypinx.coding.misc.TestCaseValidator;

public class Question65 {

    public static void main(String[] args) {
        String current_time = "14:30";
        String[] departure_times = new String[]{
                "12:30", "14:00", "19:55"
        };
        int expected = 30;
        int result = solution(current_time, departure_times);

        TestCaseValidator.validateTestCase("1", expected, result);

        current_time = "00:00";
        departure_times = new String[]{
                "00:00", "14:00", "19:55"
        };
        expected = -1;
        result = solution(current_time, departure_times);

        TestCaseValidator.validateTestCase("2", expected, result);

        current_time = "14:00";
        departure_times = new String[]{
                "12:30", "14:00", "19:55"
        };
        expected = 90;
        result = solution(current_time, departure_times);

        TestCaseValidator.validateTestCase("3", expected, result);
    }

    private static int solution(String current_time, String[] departure_times) {
        int[] times = new int[departure_times.length];
        int current  = convertToMin(current_time);

        int counter = 0;
        for (String departure_time : departure_times) {
            times[counter++] = convertToMin(departure_time);
        }

        int firstTime = -1;

        for (int i = times.length - 1; i >= 0; i--) {
            if (times[i] < current) {
                firstTime = Math.abs(times[i] - current);
                break;
            }
        }
        return firstTime;
    }

    private static int convertToMin(String time) {
        String[] split = time.split(":");
        int hour = Integer.valueOf(split[0]), min = Integer.valueOf(split[1]);
        return (hour * 60) + min;
    }
}
