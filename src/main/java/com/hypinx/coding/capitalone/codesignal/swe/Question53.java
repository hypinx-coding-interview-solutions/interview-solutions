package com.hypinx.coding.capitalone.codesignal.swe;

import com.hypinx.coding.misc.TestCaseValidator;

import java.util.Arrays;

public class Question53 {

    public static void main(String[] args) {

        String[] songs = new String[]{"notion:180", "voyage:185", "sample:180"};
        String[] animations = new String[]{"circles:360", "squares:180", "lines:37"};
        String[] expected = new String[]{"squares:1", "lines:5", "squares:1"};
        String[] result = solution(songs, animations);

        TestCaseValidator.validateTestCase("1", Arrays.asList(expected), Arrays.asList(result));

        songs = new String[]{"songone:150", "songtwo:150", "songthree:150", "songfour:150"};
        animations = new String[]{"first:150", "second:75", "third:10"};
        expected = new String[]{"first:1", "first:1", "first:1", "first:1"};
        result = solution(songs, animations);

        TestCaseValidator.validateTestCase("2", Arrays.asList(expected), Arrays.asList(result));

    }

    public static String[] solution(String[] songs, String[] animations) {
        String[] result = new String[songs.length];
        for (int i = 0; i < songs.length; i++) {
            int currentSongLength = Integer.valueOf(songs[i].split(":")[1]);
            for (int j = 0; j < animations.length; j++) {
                String currentAnimation = animations[j].split(":")[0];
                int currentAnimationLength = Integer.valueOf(animations[j].split(":")[1]);

                int divisorResult = currentSongLength % currentAnimationLength;
                int plays = currentSongLength / currentAnimationLength;
                if (divisorResult == 0) {
                    result[i] = currentAnimation + ":" + plays;
                    break;
                }
            }
        }
        return result;
    }
}
