package com.hypinx.coding.capitalone.codesignal.swe;

import java.util.*;

public class Question8 {
    public static void main(String[] args) {
        int[] nums1 = new int[]{2,1,8,7,6,5,4,3};		// 6
        int[] nums2 = new int[]{1, 10, 9, 8, 7, 6, 5, 4, 3, 2};		// 9
        int[] nums3 = new int[]{1, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2};		// 11
        int[] nums4 = new int[]{2, 1, 15, 14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3}; // 13
        int t1 = solution(nums1);
        int t2 = solution(nums2);
        int t3 = solution(nums3);
        int t4 = solution(nums4);
        System.out.println(t1);
        System.out.println(t2);
        System.out.println(t3);
        System.out.println(t4);
    }

    static int solution(int[] nums) {
        if (nums.length == 1) return 1;

        int[] sortedArray = new int[nums.length];
        System.arraycopy(nums, 0, sortedArray, 0, nums.length);
        Arrays.sort(sortedArray);

        int pointerOriginal = 0, pointerSorted = nums.length - 1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == sortedArray[pointerSorted]) {
                int[] temp = new int[nums.length];
                System.arraycopy(nums, i, temp, 0, nums.length - i);
                int diff = nums.length - (nums.length - i);
                // System.out.println(nums.length - i + "|" + diff);
                System.arraycopy(nums, 0, temp, nums.length - i, diff);
                if (validate(temp, sortedArray)) return nums.length - i;
            }

        }

        return -1;
    }

    static boolean validate(int[] temp, int[] sorted) {
        // printArr(temp);
        // printArr(sorted);
        int sortedP = sorted.length - 1;
        for (int i = 0; i < temp.length ; i++) {
            // System.out.println(i + "|" + sortedP);
            if (temp[i] != sorted[sortedP--]) return false;
        }
        return true;
    }

    static void printArr(int[] arr) {
        for (int el : arr) {
            // System.out.print(el + " ");
        }
        // System.out.println();
    }
}