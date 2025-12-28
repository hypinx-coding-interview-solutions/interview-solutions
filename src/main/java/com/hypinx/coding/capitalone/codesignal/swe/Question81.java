package com.hypinx.coding.capitalone.codesignal.swe;

import com.hypinx.coding.misc.TestCaseValidator;

import java.util.HashMap;
import java.util.Map;

public class Question81 {

    public static void main(String[] args) {
        String season = "January";
        int dayCount = 4;
        String initialPhase = "Full";

        String expected = "Twilight";
        String result = solution(season, dayCount, initialPhase);

        TestCaseValidator.validateTestCase("1", expected.equals(result));
    }

    private static String solution(String season, int dayCount, String initialPhase) {
        // Month (season) lengths in a non-leap year
        String[] months = {
                "January","February","March","April","May","June",
                "July","August","September","October","November","December"
        };
        int[] daysInMonth = {31,28,31,30,31,30,31,31,30,31,30,31};

        // Lunar phases in order (cycle length = 8)
        String[] phases = {
                "NewMoon", "Crescent", "Quarter", "Gibbous",
                "Full", "Waning", "Eclipse", "Twilight"
        };

        // Build lookup maps
        Map<String, Integer> monthToIndex = new HashMap<>();
        for (int i = 0; i < months.length; i++) monthToIndex.put(months[i], i);

        Map<String, Integer> phaseToIndex = new HashMap<>();
        for (int i = 0; i < phases.length; i++) phaseToIndex.put(phases[i], i);

        int monthIdx = monthToIndex.get(season);
        int startPhaseIdx = phaseToIndex.get(initialPhase);

        // Days offset from Jan 1 to the target date (0-based)
        int offset = 0;
        for (int i = 0; i < monthIdx; i++) offset += daysInMonth[i];
        offset += (dayCount - 1);

        int resultIdx = (startPhaseIdx + offset) % phases.length;
        return phases[resultIdx];
    }
}
