package com.hypinx.coding.capitalone.codesignal;

public class Question6 {
    public static void main(String[] args) {
        String[] operations = new String[]{"L", "L", "C0", "L", "C3"};
        String res = solution(10, operations);
        System.out.println(res.equals("1100000000"));

        int[] state = new int[]{0,0,0,0,0,0,0,0,0,0};
        String alternateRes = alternateWordingSolution(state, operations);
        System.out.println("Alternate Solution = " + alternateRes.equals("1100000000"));
    }

    static String solution(int n, String[] operations) {
        StringBuilder builder = new StringBuilder();
        while(n-- > 0) builder.append("0");
        String binary = builder.toString();
        boolean prevL = false;

        char[] arr = binary.toCharArray();
        for (int i = 0; i < operations.length; i++) {
            String op = operations[i];
            if (op.contains("L")) {
                // Check if we have a L operation previously where we did nothing, if so there is no point searching again
                if (prevL) continue;
                prevL = setLeftmostBitToOne(arr);
            } else if (op.contains("C")) {
                setIndexToZero(arr, Integer.parseInt(op.split("C")[1]));
                prevL = false;
            }
        }

        return String.valueOf(arr);
    }

    static String alternateWordingSolution(int[] state, String[] operations) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < state.length; i++) {
            builder.append(state[i]);
        }
        String binary = builder.toString();
        boolean prevL = false;

        char[] arr = binary.toCharArray();
        for (int i = 0; i < operations.length; i++) {
            String op = operations[i];
            if (op.contains("L")) {
                // Check if we have a L operation previously where we did nothing, if so there is no point searching again
                if (prevL) continue;
                prevL = setLeftmostBitToOne(arr);
            } else if (op.contains("C")) {
                setIndexToZero(arr, Integer.parseInt(op.split("C")[1]));
                prevL = false;
            }
        }

        return String.valueOf(arr);
    }

    static boolean setLeftmostBitToOne(char[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == '0') {
                arr[i] = '1';
                return false;
            }
        }
        return true;
    }

    static void setIndexToZero(char[] arr, int index) {
        arr[index] = '0';
    }
}