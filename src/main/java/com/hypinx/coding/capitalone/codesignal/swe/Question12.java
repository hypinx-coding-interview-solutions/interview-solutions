package com.hypinx.coding.capitalone.codesignal.swe;

import java.util.*;

public class Question12 {
    public static void main(String[] args) {
        String[] commands = {"ls", "cp", "mv", "mv", "mv", "!1", "!3", "!6"};
        int[] result = solution(commands);
        System.out.println("[# of times for “cp”, # of times for “ls”, # of times for “mv”]: " + Arrays.toString(result));
    }

    public static int[] solution(String[] commands) {
        List<String> resolvedIndex = new ArrayList<String>();
        int[] result = new int[3];
        for (int i = 0; i < commands.length; i++) {
            String command = commands[i];
            if (!command.contains("!")) {
                resolvedIndex.add(command);
            } else {
                command = resolveIndex(resolvedIndex, commands, command);
            }
            increment(result, command);
        }


        return result;
    }

    public static String resolveIndex(List<String> resolvedIndex, String[] commands, String command) {
        if (command.contains("!")) {
            String current = commands[Integer.parseInt(command.split("!")[1]) - 1];
            return resolveIndex(resolvedIndex, commands, current);
        } else {
            return command;
        }
    }

    public static void increment(int[] result, String command) {
        if (command.equals("cp")) {
            result[0]++;
        } else if (command.equals("ls")) {
            result[1]++;
        } else {
            result[2]++;
        }
    }
}