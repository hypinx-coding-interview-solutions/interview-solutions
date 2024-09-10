package com.hypinx.coding.capitalone.codesignal.swe;

import java.util.*;
public class Question29 {

    public static void main(String[] args) {
        String word1 = "hello";
        String[] skeleton1 = new String[]{"he-lo", "he--o", "-ell-", "hello"};
        String[] expRes1 = new String[]{"he-lo", "hello"};
        String[] res1 = solution(word1, skeleton1);
        System.out.println(Arrays.equals(res1, expRes1));

        String word2 = "allowable";
        String[] skeleton2 = new String[]{"al-ow-b-e", "-l-ow-b-e", "-l-owab-e"};
        String[] expRes2 = new String[]{"al-ow-b-e", "-l-owab-e"};
        String[] res2 = solution(word2, skeleton2);
        System.out.println(Arrays.equals(res2, expRes2));

        String word3 = "abc";
        String[] skeleton3 = new String[]{"---", "abc", "--c", "---", "abc", "abc"};
        String[] expRes3 = new String[]{"abc", "abc", "abc"};
        String[] res3 = solution(word3, skeleton3);
        System.out.println(Arrays.equals(res3, expRes3));
    }

    public static String[] solution(String word, String[] skeletons) {
        Set<String> lettersInSkeleton = new HashSet<>();
        List<String> result = new ArrayList<>();

        for (String skeleton : skeletons) {
            buildLettersInSkeleton(lettersInSkeleton, skeleton);
            boolean possibleToBuild = true;
            for (int i = 0; i < skeleton.length(); i++) {
                String curr = String.valueOf(skeleton.charAt(i));
                if (curr.equals("-")) {
                    // Check which letter is missing
                    String missingLetter = String.valueOf(word.charAt(i));
                    // Check the lettersInSkeleton to see if the letter can be obtained from elsewhere
                    if (!lettersInSkeleton.contains(missingLetter)) {
                        possibleToBuild = false;
                        break;
                    }
                }
            }
            if (possibleToBuild) {
                result.add(skeleton);
            }
            lettersInSkeleton.clear();
        }

        return result.toArray(new String[result.size()]);
    }

    public static void buildLettersInSkeleton(Set<String> lettersInSkeleton, String skeleton) {
        for (int i = 0; i < skeleton.length(); i++) {
            lettersInSkeleton.add(String.valueOf(skeleton.charAt(i)));
        }
        return;
    }
}