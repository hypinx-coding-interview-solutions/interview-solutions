package com.hypinx.coding.capitalone.codesignal;

public class Question32 {

    public static void main (String[] args) {
        String[] schedule = new String[]{"12:30", "14:00", "19:55"};
        String time = "14:30";
        String expected = "05:25";
        String output = solution(schedule, time);
        System.out.println(expected.equals(output));
    }

    public static String solution(String[] schedule, String time) {
        String[] timeArr = time.split(":");
        int currentHour = Integer.valueOf(timeArr[0]), currentMin = Integer.valueOf(timeArr[1]);
        int hourWait = -1, minWait = -1;

        for (String currentSchedule : schedule) {
            String[] currentScheduleArr = currentSchedule.split(":");
            int currentScheduleHour = Integer.valueOf(currentScheduleArr[0]);
            int currentScheduleMin = Integer.valueOf(currentScheduleArr[1]);

            if (currentScheduleHour < currentHour ||
                currentScheduleHour == currentHour && currentScheduleMin < currentMin)
                continue;

            if (currentScheduleHour == currentHour && currentScheduleMin == currentMin) {
                hourWait = 0;
                minWait = 0;
                break;
            }

            if (currentScheduleMin < currentMin) {
                minWait = 60 - currentMin + currentScheduleMin;
                currentScheduleHour--;
            } else {
                minWait = currentScheduleMin - currentMin;
            }

            hourWait = currentScheduleHour - currentHour;
            break;
        }

        // Build the output string
        return createOutputStringFormat(hourWait, minWait);
    }

    public static String createOutputStringFormat(int hourWait, int minWait) {
        StringBuilder builder = new StringBuilder();
        if (hourWait < 10) builder.append("0");
        builder.append(hourWait + ":");
        if (minWait < 10) builder.append("0");
        builder.append(minWait);
        return builder.toString();
    }
}
