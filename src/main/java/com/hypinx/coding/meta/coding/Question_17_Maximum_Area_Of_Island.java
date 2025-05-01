package com.hypinx.coding.meta.coding;

import java.util.*;
import com.hypinx.coding.misc.TestCaseValidator;

public class Question_17_Maximum_Area_Of_Island {

    public static void main(String[] args) {
        int[][] grid = new int[][]{
                {1,0}, {0,1}, {1,1}
        };

        int expected = 5;
        int result = largestIsland(grid);

        TestCaseValidator.validateTestCase("1", expected, result);

    }

    // Used to main movement into different directions when doing DFS
    private static final int[][] DIRS = {{1,0}, {-1,0}, {0,1}, {0,-1}};

    public static int largestIsland(int[][] grid) {
        int n = grid.length, m = grid[0].length;
        int islandId = 2; // Start labeling islands from 2
        Map<Integer, Integer> islandArea = new HashMap<>();

        // Step 1: Label islands and record their area
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1) {
                    int area = dfs(grid, i, j, islandId);
                    islandArea.put(islandId, area);
                    islandId++;
                }
            }
        }

        // Step 2: Try flipping each 0
        int maxArea = 0;
        boolean hasZero = false;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 0) {
                    hasZero = true;
                    Set<Integer> seen = new HashSet<>();
                    int area = 1; // Flipped cell counts as 1

                    for (int[] d : DIRS) {
                        int ni = i + d[0], nj = j + d[1];
                        if (ni >= 0 && nj >= 0 && ni < n && nj < m) {
                            int id = grid[ni][nj];
                            if (id > 1 && seen.add(id)) {
                                area += islandArea.get(id);
                            }
                        }
                    }

                    maxArea = Math.max(maxArea, area);
                }
            }
        }

        // Edge case: grid is already all 1s
        if (!hasZero) {
            return n * m;
        }

        return maxArea;
    }

    private static int dfs(int[][] grid, int i, int j, int id) {
        if (i < 0 || j < 0 || i >= grid.length || j >= grid[0].length || grid[i][j] != 1) {
            return 0;
        }

        grid[i][j] = id;
        int area = 1;

        for (int[] d : DIRS) {
            area += dfs(grid, i + d[0], j + d[1], id);
        }

        return area;
    }
}
