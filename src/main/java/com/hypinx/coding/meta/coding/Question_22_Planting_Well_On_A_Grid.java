package com.hypinx.coding.meta.coding;

import com.hypinx.coding.misc.TestCaseValidator;

import java.util.LinkedList;
import java.util.Queue;

public class Question_22_Planting_Well_On_A_Grid {

    public static void main(String[] args) {
        char[][] grid = new char[][]{
            {'-', '-', '-', '-', '-'},
            {'-', 'H', 'T', '-', '-'},
            {'-', '-', 'H', 'T', '-'},
            {'-', 'T', 'H', '-', '-'},
            {'-', '-', '-', '-', '-'}
        };

        int result = solution(grid);
        int expected = 4;

        TestCaseValidator.validateTestCase("1", expected, result);
    }

    public static final int[][] DIRS = {{0,1}, {1,0}, {0,-1}, {-1,0}};

    public static int solution(char[][] grid) {
        int m = grid.length, n = grid[0].length;

        int[][] dist = new int[m][n];
        int[][] reach = new int[m][n];
        int houseCount = 0;

        // Count houses and perform BFS from each
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 'H') {
                    houseCount++;
                    bfs(grid, i, j, dist, reach, m, n);
                }
            }
        }

        int minDist = Integer.MAX_VALUE;

        // These values are not needed - just for debugging
        int coordI = -1, coordJ = -1;
        // -----------------------------------------------------

        // Find empty cell reachable from all houses with min distance
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '-' && reach[i][j] == houseCount) {
                    minDist = Math.min(minDist, dist[i][j]);
                    // These values are not needed - just for debugging
                    coordI = i;
                    coordJ = j;
                    // -----------------------------------------------------
                }
            }
        }

        // These values are not needed - just for debugging ----
        System.out.println(coordI + ", " + coordJ);
        // -----------------------------------------------------

        return minDist == Integer.MAX_VALUE ? -1 : minDist;
    }

    private static void bfs(char[][] grid, int i, int j, int[][] dist, int[][] reach, int m, int n) {
        boolean[][] visited = new boolean[m][n];
        Queue<int[]> queue = new LinkedList<>();
        // Use offer method because if queue is full it will return false
        // Add method will throw exception if queue is full
        queue.offer(new int[]{i, j});
        visited[i][j] = true;

        int level = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            level++;

            for (int s = 0; s < size; s++) {
                int[] curr = queue.poll();
                for (int[] d : DIRS) {
                    int ni = curr[0] + d[0], nj = curr[1] + d[1];
                    // Check if grid is not a T (we can go through houses)
                    if (ni >= 0 && ni < m && nj >= 0 && nj < n &&
                            grid[ni][nj] != 'T' && !visited[ni][nj]) {
                        dist[ni][nj] += level;
                        reach[ni][nj]++;
                        visited[ni][nj] = true;
                        queue.offer(new int[]{ni, nj});
                    }
                }
            }
        }
    }
}
