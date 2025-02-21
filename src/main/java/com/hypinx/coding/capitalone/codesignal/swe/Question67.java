package com.hypinx.coding.capitalone.codesignal.swe;

import java.util.*;

/*
    Forgot to take test case data. This same solution was used in the Codesignal problem and it passed
    all test cases.
 */
public class Question67 {

    public static void main(String[] args) {

    }

    public static String[] solution(String[] members, String[][] events) {
        Map<Integer, Integer> mentionCount = new TreeMap<>();
        Set<Integer> allUsers = new HashSet<>();
        Map<Integer, Integer> offlineUntil = new HashMap<>();

        for (String member : members) {
            int userId = Integer.parseInt(member.substring(2));
            allUsers.add(userId);
            mentionCount.put(userId, 0);
        }

        for (String[] event : events) {
            String eventType = event[0];
            int timestamp = Integer.parseInt(event[1]);

            if (eventType.equals("MESSAGE")) {
                String[] mentions = event[2].split(" ");
                Set<Integer> mentionedUsers = new HashSet<>();

                if (Arrays.asList(mentions).contains("ALL")) {
                    mentionedUsers.addAll(allUsers);
                } else {
                    for (String mention : mentions) {
                        if (mention.startsWith("id")) {
                            int userId = Integer.parseInt(mention.substring(2));
                            mentionedUsers.add(userId);
                        }
                    }
                }

                for (int userId : mentionedUsers) {
                    if (!offlineUntil.containsKey(userId) || offlineUntil.get(userId) <= timestamp) {
                        mentionCount.put(userId, mentionCount.getOrDefault(userId, 0) + 1);
                    }
                }
            } else if (eventType.equals("OFFLINE")) {
                int userId = Integer.parseInt(event[2]);
                offlineUntil.put(userId, timestamp + 60);
            }
        }

        List<String> result = new ArrayList<>();
        for (int userId : mentionCount.keySet()) {
            result.add("id" + userId + "=" + mentionCount.get(userId));
        }

        return result.toArray(new String[0]);
    }
}
