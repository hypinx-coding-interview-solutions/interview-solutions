package com.hypinx.coding.capitalone.codesignal.swe;

/**
 * Not yet complete, outputs are wrong
 */
public class Question31 {

    public static void main (String[] args) {
        int[][] centers1 = new int[][]{{1,1}, {2,2}, {0,4}};
        int exp1 = 2;
        int res1 = solution(centers1);
        System.out.println(exp1 == res1);

        int[][] centers2 = new int[][]{{1,1}, {2,2}, {0,4}, {1,1}};
        int exp2 = 4;
        int res2 = solution(centers2);
        System.out.println(exp2 == res2);

        int[][] centers3 = new int[][]{{0,0}, {0,2}, {2,0}, {2,2}};
        int exp3 = 6;
        int res3 = solution(centers3);
        System.out.println(exp3 == res3);

        int[][] centers4 = new int[][]{{0,0}, {0,2}, {2,0}, {2,2}, {1,1}};
        int exp4 = 10;
        int res4 = solution(centers4);
        System.out.println(exp4 == res4);
    }

    public static int solution(int[][] centers) {
        if (centers.length == 1) return 0;
        int collisions = 0;

        for (int i = 0; i < centers.length; i++) {
            for (int j = i + 1; j < centers.length; j++) {
                int[] first = centers[i], second = centers[j];
                int x1 = first[0], y1 = first[1], x2 = second[0], y2 = second[1];
                double distance = distance(x1, y1, x2, y2);
                if (distance <= 2) {
                    System.out.println("Collision");
                    collisions++;
                }
            }
        }

        return collisions;
    }

    public static double distance(int x1, int y1, int x2, int y2) {
        return Math.sqrt((Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2)));
    }
}
