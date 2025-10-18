package com.hypinx.coding.chase.hackerrank;

import com.hypinx.coding.misc.TestCaseValidator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Question_35_Table_Of_Contents {

    public static void main(String[] args) {
        List<String> text = Arrays.asList(
                "# Algorithms",
                "This chapter covers the most basic",
                "algorithms.",
                "## Sorting",
                "Quicksort is fast and widely used in",
                "practice",
                "Merge sort is a deterministic algorithm",
                "## Searching",
                "DFS and BFS are widely used graph",
                "searching algorithms",
                "Some variants of DFS are also used in game",
                "theory applications",
                "# Data Structures",
                "This chapter is all about data structures",
                "It's a draft for now and will contain more",
                "sections in the future",
                "# Binary Search Trees"
        );

        List<String> expected = Arrays.asList(
                "1. Algorithms",
                "1.1. Sorting",
                "1.2. Searching",
                "2. Data Structures",
                "3. Binary Search Trees"
        );

        List<String> result = tableOfContents(text);

        TestCaseValidator.validateTestCase("1", expected, result);

    }

    public static List<String> tableOfContents(List<String> text) {
        List<String> result = new ArrayList<>();
        int chapterCount = 0;
        int sectionCount = 0;

        for (String line : text) {
            line = line.trim();

            // Chapter
            if (line.startsWith("# ") && !line.startsWith("##")) {
                chapterCount++;
                sectionCount = 0; // reset for each new chapter
                String chapterTitle = line.substring(2).trim();
                result.add(chapterCount + ". " + chapterTitle);
            }
            // Section
            else if (line.startsWith("## ")) {
                sectionCount++;
                String sectionTitle = line.substring(3).trim();
                result.add(chapterCount + "." + sectionCount + ". " + sectionTitle);
            }
            // Ignore all other lines
        }

        return result;
    }

}
