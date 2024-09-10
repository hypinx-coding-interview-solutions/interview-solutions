package com.hypinx.coding.capitalone.codesignal.swe;

public class Question36 {

    public static void main(String[] args) throws Exception {
        String[] songs = new String[]{"notion:180", "voyage:185", "sample:180"};
        String[] animations = new String[]{"circles:360", "squares:180", "lines:37"};
        String[] expected = new String[]{"squares:1", "lines:5", "squares:1"};
        String[] result = solution(songs, animations);
        validateSolution(expected, result);
        System.out.println(true);


    }

    public static String[] solution(String[] songs, String[] animations) {
        String[] result = new String[songs.length];

        for (int i = 0; i < songs.length; i++) {
            int currentSongLength = Integer.valueOf(songs[i].split(":")[1]);
            for (int j = 0; j < animations.length; j++) {
                String currentAnimation = animations[j].split(":")[0];
                int currentAnimationLength = Integer.valueOf(animations[j].split(":")[1]);
                if (currentSongLength % currentAnimationLength == 0) {
                    result[i] = currentAnimation + ":" + (currentSongLength / currentAnimationLength);
                    break;
                }
            }
        }

        return result;
    }

    public static void validateSolution(String[] expected, String[] solution) throws Exception{
        for (int i = 0; i < expected.length; i++) {
            if (!expected[i].equals(solution[i])) {
                throw new Exception("Error at index " + i + ". Expected " + expected[i] + " but was " + solution[i]);
            }
        }
    }
}
