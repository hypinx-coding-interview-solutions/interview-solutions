package com.hypinx.coding.vanguard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.HashSet;

public class SpamDetection {

    public static void main(String[] args) {
        List<String> subjects = new ArrayList<>(Arrays.asList("free prize worth millions", "ten tips for a carefree lifestyle"));
        List<String> spamWords = new ArrayList<>(Arrays.asList("free", "money", "win", "millions"));

        List<String> expected = new ArrayList<>(Arrays.asList("spam", "not_spam"));
        List<String> result = getSpamEmails(subjects, spamWords);
        System.out.println(validate(result, expected));
    }

    public static List<String> getSpamEmails(List<String> subjects, List<String> spam_words) {
        // Create empty result list
        List<String> result = new ArrayList<>();
        // Create a set, we will convert the spam_words from the list into a set. Spam words are case insensitive so ABC and abc are both
        // considered a spam words. The subject is also case insensitive.
        Set<String> spamWordsSet = new HashSet<>();

        // For each spam word we convert to lowercase
        for (int i = 0; i < spam_words.size(); i++) {
            spamWordsSet.add(spam_words.get(i).toLowerCase());
        }

        for (String subject : subjects) {
            // Convert every subject to lower case for case insensitivity
            subject = subject.toLowerCase();
            // Get every word in the subject
            String[] wordsInSubject = subject.split(" ");
            // Set spam word counter to 0
            int spamWordCounter = 0;
            // For every word in the subject, we check if its in the set. If so we increment the spam counter
            for (String word : wordsInSubject) {
                if (spamWordsSet.contains(word)) {
                    spamWordCounter++;
                }
                // A subject is considered spam if it has 2 or more spam words. We check here, if the subject is spam we add the
                // word "spam" to our result list and break
                if (spamWordCounter >= 2) {
                    result.add("spam");
                    break;
                }
            }

            // Check if the spam counter is less than 2, if so its not a spam so we add "not_spam" to result list
            if (spamWordCounter < 2) result.add("not_spam");
        }

        return result;
    }

    public static boolean validate(List<String> result, List<String> expected) {
        if (result.size() != expected.size()) return false;
        for (int i = 0; i < result.size(); i++) {
            if (!result.get(i).equals(expected.get(i))) return false;
        }

        return true;
    }
}
