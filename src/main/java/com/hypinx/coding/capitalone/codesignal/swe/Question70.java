package com.hypinx.coding.capitalone.codesignal.swe;

import com.hypinx.coding.misc.TestCaseValidator;

import java.util.*;

public class Question70 {

    public static void main(String[] args) {
        String[] commands = {"cd users", "cd codesignal", "cd ..", "cd admin"};
        String result = solution(commands);
        String expected = "/users/admin";

        TestCaseValidator.validateTestCase("1", expected.equals(result));



        commands = new String[]{"cd users", "cd .", "cd admin", "cd /", "cd volumes"};
        result = solution(commands);
        expected = "/volumes";

        TestCaseValidator.validateTestCase("2", expected.equals(result));
    }

    public static String solution(String[] commands) {
        Deque<String> pathStack = new LinkedList<>();

        for (String command : commands) {
            String[] parts = command.split(" "); // Split "cd <arg>"
            String arg = parts[1];

            if (arg.equals("/")) {
                // Reset to root directory
                pathStack.clear();
            } else if (arg.equals("..")) {
                // Move up one level (if not already at root)
                if (!pathStack.isEmpty()) {
                    pathStack.pop();
                }
            } else if (!arg.equals(".")) {
                // Move into the specified subdirectory
                pathStack.push(arg);
            }
        }

        // Construct the final absolute path
        StringBuilder result = new StringBuilder();
        if (pathStack.isEmpty()) {
            return "/"; // Root directory case
        }

        // Convert stack into path
        for (String dir : pathStack) {
            result.insert(0, "/" + dir); // Build path from stack
        }

        return result.toString();
    }

}
