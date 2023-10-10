package com.hypinx.coding.capitalone.codesignal;

import java.util.*;

public class Question2 {
    public static void main(String[] args) {
        int finish = 23;
        int[] scooters = new int[]{7,4,14};
        // Expected output
        int expected = 19;
        int res = solution(finish, scooters);
        System.out.println(expected == res);
    }

    static int solution(int finish, int[] scooters) {
        Arrays.sort(scooters);

        int p1 = 0, traveled = 0, current = -1;
        while (p1 < scooters.length) {
            current = scooters[p1];
            if (current + 10 > finish) {
                traveled += finish - current;
                break;
            } else {
                traveled += 10;
                current += 10;
            }

            while (scooters[p1] < current) {
                p1++;
                if (p1 >= scooters.length) break;
            }
        }

        return traveled;
    }
}