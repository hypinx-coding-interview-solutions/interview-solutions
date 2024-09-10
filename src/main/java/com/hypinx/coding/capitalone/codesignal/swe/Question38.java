package com.hypinx.coding.capitalone.codesignal.swe;

import java.util.Arrays;
import java.util.Set;

public class Question38 {

    public static void main(String[] args) {
        String[] reviews = new String[]{
                "very purple and good",
                "very very bad one of the worst",
                "good but worse than analogue",
                ""
        };
        String[] positiveWords = new String[]{
                "good", "best"
        };
        String[] negativeWords = new String[]{
                "bad", "worse", "worst"
        };
        String[] intensifiers = new String[]{
                "very"
        };
        String[] expected = new String[]{
                "positive", "negative", "neutral", "neutral"
        };
        String[] result = solution(reviews, positiveWords, negativeWords, intensifiers);

        System.out.println(Arrays.equals(expected, result));

    }

    public static String[] solution(String[] reviews, String[] positiveWords, String[] negativeWords, String[] intensifiers) {
        Set<String> positiveSet = Set.of(positiveWords);
        Set<String> negativeSet = Set.of(negativeWords);
        Set<String> intensifierSet = Set.of(intensifiers);

        String[] result = new String[reviews.length];

        for (int i = 0; i < reviews.length; i++) {
            String[] review = reviews[i].split(" ");
            int count = 0;
            int segment = 0;
            for (int j = 0; j < review.length; j++) {
                String word = review[j];
                if (intensifierSet.contains(word)) {
                    segment++;
                } else if (positiveSet.contains(word)) {
                    count = count + 1 + segment;
                    segment = 0;
                } else if (negativeSet.contains(word)) {
                    count = count - 1 - segment;
                    segment = 0;
                }
            }

            if (count == 0) {
                result[i] = "neutral";
            } else if (count < 0) {
                result[i] = "negative";
            } else {
                result[i] = "positive";
            }
        }
        return result;
    }
}
