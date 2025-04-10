package com.hypinx.coding.meta.coding;

import com.hypinx.coding.misc.TestCaseValidator;

import java.util.*;

public class Question_13_Course_Schedule {

    public static void main(String[] args) {
        int courses = 3;
        int[][] prerequisites = {{1, 0}, {2, 1}};
        boolean expected = true;
        boolean result = canFinish(courses, prerequisites);
        TestCaseValidator.validateTestCase("1", expected, result);

        courses = 2;
        prerequisites = new int[][]{{1, 0}, {0, 1}};
        expected = false;
        result = canFinish(courses, prerequisites);
        TestCaseValidator.validateTestCase("2", expected, result);

        courses = 0;
        prerequisites = new int[][]{};
        expected = true;
        result = canFinish(courses, prerequisites);
        TestCaseValidator.validateTestCase("3", expected, result);

        courses = 1;
        prerequisites = new int[][]{{1,0}};
        expected = true;
        result = canFinish(courses, prerequisites);
        TestCaseValidator.validateTestCase("4", expected, result);

        courses = -1;
        prerequisites = null;
        expected = true;
        result = canFinish(courses, prerequisites);
        TestCaseValidator.validateTestCase("5", expected, result);
    }

    public static boolean canFinish(int numCourses, int[][] prerequisites) {
        if (prerequisites == null || prerequisites.length <= 1) return true;

        // Step 1: Build adjacency list and in-degree array
        List<List<Integer>> adjList = new ArrayList<>();
        int[] inDegree = new int[numCourses];

        // Initialize adjacency list
        for (int i = 0; i < numCourses; i++) {
            adjList.add(new ArrayList<>());
        }

        // Fill adjacency list and in-degree array
        for (int[] pair : prerequisites) {
            int course = pair[0];
            int prereq = pair[1];

            adjList.get(prereq).add(course);
            inDegree[course]++; // Track incoming edges
        }

        // Step 2: Enqueue all nodes with in-degree 0 (no prerequisites)
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (inDegree[i] == 0) {
                queue.offer(i);
            }
        }

        // Step 3: Process courses in BFS order
        int count = 0; // Counts processed nodes
        while (!queue.isEmpty()) {
            int course = queue.poll();
            count++; // One course completed

            // Reduce in-degree of all dependent courses
            for (int nextCourse : adjList.get(course)) {
                inDegree[nextCourse]--;

                // If in-degree becomes 0, add to queue
                if (inDegree[nextCourse] == 0) {
                    queue.offer(nextCourse);
                }
            }
        }

        // Step 4: If all courses are processed, return true, else false
        return count == numCourses;
    }

}
