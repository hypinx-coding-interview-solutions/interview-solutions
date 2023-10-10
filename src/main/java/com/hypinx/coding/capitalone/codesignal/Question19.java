package com.hypinx.coding.capitalone.codesignal;

import java.util.*;

public class Question19 {

    public static String solution(String[] paths) {
        int n = paths.length;
        List<String> normalizedPaths = new ArrayList<>();
        for (String path : paths) {
            String[] components = path.split("/");
            List<String> normalizedComponents = new ArrayList<>();
            for (String component : components) {
                if (component.equals("..")) {
                    if (!normalizedComponents.isEmpty()) {
                        normalizedComponents.remove(normalizedComponents.size() - 1);
                    }
                } else if (!component.equals(".")) {
                    normalizedComponents.add(component);
                }
            }
            StringBuilder sb = new StringBuilder();
            for (String component : normalizedComponents) {
                sb.append("/").append(component);
            }
            normalizedPaths.add(sb.toString());
        }
        int commonLength = Integer.MAX_VALUE;
        for (String path : normalizedPaths) {
            commonLength = Math.min(commonLength, path.length());
        }
        StringBuilder suffix = new StringBuilder();
        for (int i = 1; i <= commonLength; i++) {
            char c = normalizedPaths.get(0).charAt(normalizedPaths.get(0).length() - i);
            for (int j = 1; j < n; j++) {
                if (normalizedPaths.get(j).charAt(normalizedPaths.get(j).length() - i) != c) {
                    return suffix.toString();
                }
            }
            suffix.insert(0, c);
        }
        return suffix.toString();
    }

    public static void main(String[] args) {
        String[] paths1 = new String[] {
                "/a/folder1/../folder1/a/leaf.txt",
                "/b/folder2/../folder1/a/leaf.txt",
                "/a/folder3/folder1/folder1/../a/leaf.txt"
        };
        String sol1 = solution(paths1);
        String expected1 = "/folder1/a/leaf.txt";
        System.out.println(sol1.equals(expected1));

        String[] paths2 = new String[] {
                "/root/folder1/b/../a",
                "/root/folder1/a/leaf",
                "/root/folder1/a/b/../../a/branch"
        };
        String sol2 = solution(paths2);
        String expected2 = "";

        System.out.println(sol2.equals(expected2));
        String[] paths3 = new String[] {
                "/a/b/../../bb/a/leaf.txt",
                "/a/b/../sd/../a/ad/../leaf.txt"
        };
        String sol3 = solution(paths3);
        String expected3 = "/a/leaf.txt";
        System.out.println(sol3.equals(expected3));
    }
}
