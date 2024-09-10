package com.hypinx.coding.capitalone.codesignal.swe;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class Question20 {
    public static void main(String[] args) {
        String[] commands = new String[]{
                "goto bucketA",
                "create fileA",
                "create fileB",
                "create fileA",
                "goto bucketB",
                "goto bucketC",
                "create fileA",
                "create fileB",
                "create fileC"
        };
        String expected = "bucketC";
        String result = solution(commands);
        System.out.println(result.equals(expected));
    }

    public static String solution(String[] commands) {
        Map<String, HashSet<String>> map = new HashMap<>();
        String currentBucket = "", largestBucket = "";
        int max = -1;
        for (String command: commands) {
            if (command.contains("goto")) {
                currentBucket = command.split(" ")[1];
                if (!map.containsKey(currentBucket)) {
                    map.put(currentBucket, new HashSet<String>());
                }
            } else {
                String fileName = command.split(" ")[1];
                HashSet<String> currentSet = map.get(currentBucket);
                currentSet.add(fileName);
                if (currentSet.size() > max) {
                    max = currentSet.size();
                    largestBucket = currentBucket;
                }
            }
        }
        return largestBucket;
    }
}