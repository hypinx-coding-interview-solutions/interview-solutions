package com.hypinx.coding.capitalone.codesignal.swe;

import com.hypinx.coding.misc.TestCaseValidator;

import java.util.*;

/**
 * Solution was created after assessment - has not been tested yet to see if all test cases pass
 */
public class Question74 {

    public static void main(String[] args) {

        String[] members = new String[]{"id42", "id158", "id23"};

        String[][] events = new String[][]{
                {"MESSAGE", "0", "ALL id158 id42"},
                {"OFFLINE", "1", "id158"},
                {"MESSAGE", "2", "id158 id158"},
                {"OFFLINE", "3", "id23"},
                {"MESSAGE", "60", "HERE id158 id42 id23"},
                {"MESSAGE", "61", "HERE"},
        };

        String[] expected = new String[]{"id158=4", "id23=2", "id42=3"};
        String[] result = solution(members, events);

        TestCaseValidator.validateTestCase("1", expected, result);
    }

    public static String[] solution(String[] members, String[][] events) {
        Map<String, Integer> mentionCount = new HashMap<>();
        Map<String, Integer> offlineUntil = new HashMap<>();

        // Initialize mention count
        for (String member : members) {
            mentionCount.put(member, 0);
        }

        for (String[] event : events) {
            String type = event[0];
            int time = Integer.parseInt(event[1]);

            if (type.equals("OFFLINE")) {
                String user = event[2];
                offlineUntil.put(user, time + 60);
            }

            if (type.equals("MESSAGE")) {
                Set<String> mentioned = new HashSet<>();
                String[] tokens = event[2].split(" ");

                for (String token : tokens) {
                    if (token.equals("ALL")) {
                        mentioned.addAll(Arrays.asList(members));
                    } else if (token.equals("HERE")) {
                        for (String user : members) {
                            int until = offlineUntil.getOrDefault(user, -1);
                            if (until <= time) {
                                mentioned.add(user);
                            }
                        }
                    } else if (token.startsWith("id")) {
                        mentioned.add(token);
                    }
                }

                for (String user : mentioned) {
                    if (mentionCount.containsKey(user)) {
                        mentionCount.put(user, mentionCount.get(user) + 1);
                    }
                }
            }
        }

        // Sort the user IDs alphabetically
        List<String> sortedMembers = new ArrayList<>(mentionCount.keySet());
        Collections.sort(sortedMembers);

        String[] result = new String[sortedMembers.size()];
        for (int i = 0; i < sortedMembers.size(); i++) {
            String user = sortedMembers.get(i);
            result[i] = user + "=" + mentionCount.get(user);
        }

        return result;
    }
}
