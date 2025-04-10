package com.hypinx.coding.misc;

import org.apache.logging.log4j.util.Strings;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class TestCaseValidator {

    public static void validateTestCase(String testCase, boolean output) {
        if (!output) {
            throw new RuntimeException("Test case " + testCase + " failed. Expected true but result was false");
        }
    }

    public static void validateTestCase(String testCase, boolean expected, boolean result) {
        if (expected != result) {
            throw new RuntimeException("Test case " + testCase + " failed. Expected true but result was false");
        }
    }

    public static boolean validateTestCase(String testCase, int expected, int result) {
        if (expected != result) {
            throw new RuntimeException("Test case " + testCase + " failed. Expected " + expected + " but result was " + result);
        }

        return true;
    }

    public static boolean validateTestCase(String testCase, long expected, long result) {
        if (expected != result) {
            throw new RuntimeException("Test case " + testCase + " failed. Expected " + expected + " but result was " + result);
        }

        return true;
    }

    // Able to compare any List of Objects - utilizes deepEquals and stringfy methods
    public static void validateTestCase(String testCase, List<?> expected, List<?> result) {
        if (expected.size() != result.size()) {
            System.out.println("Test case " + testCase + " failed. Expected size " + expected.size() + " but received size " + result.size());
            return;
        }

        for (int i = 0; i < expected.size(); i++) {
            Object exp = expected.get(i);
            Object res = result.get(i);

            if (!deepEquals(exp, res)) {
                System.out.println("Test case " + testCase + " failed at index " + i +
                        ". Expected <" + stringify(exp) + "> but received <" + stringify(res) + ">");
                return;
            }
        }

        System.out.println("Test case " + testCase + " passed.");
    }

    public static boolean validateTestCase(String testCase, int[] expected, int[] result) {
        if (expected.length != result.length) {
            throw new RuntimeException("Test case " + testCase + " failed. Expected contains " + expected.length + " elements and result contains " + result.length + " elements.");
        }

        if (!Arrays.equals(result, expected)) {
            throw new RuntimeException("Test case " + testCase + " failed. Expected " + Arrays.toString(expected) + " but result is " + Arrays.toString(result));
        }

        return true;
    }


    public static boolean validateTestCase(String testCase, String[] expected, String[] result) {
        if (expected.length != result.length) {
            throw new RuntimeException("Test case " + testCase + " failed. Expected contains " + expected.length + " elements and result contains " + result.length + " elements.");
        }

        if (!Arrays.equals(result, expected)) {
            Arrays.stream(result).forEach(System.out::println);
            throw new RuntimeException("Test case " + testCase + " failed. Expected " + Arrays.toString(expected) + " but result is " + Arrays.toString(result));
        }

        return true;
    }

    public static <T> boolean validateSingleDimensionalArrays(T[] array1, T[] array2) {
        // Check if both arrays are the same object reference
        if (array1 == array2) {
            return true;
        }

        // Check if either array is null
        if (array1 == null || array2 == null) {
            return false;
        }

        // Use Arrays.deepEquals to compare the arrays
        return Arrays.deepEquals(array1, array2);
    }

    public static <T> boolean validateMultiDimensionalArrays(T[][] array1, T[][] array2) {
        // Check if both arrays are the same object reference
        if (array1 == array2) {
            return true;
        }

        // Check if either array is null
        if (array1 == null || array2 == null) {
            return false;
        }

        // Use Arrays.deepEquals to compare the arrays
        return Arrays.deepEquals(array1, array2);
    }

    // Overloaded method for int[][] arrays
    public static boolean validateMultiDimensionalArrays(int[][] array1, int[][] array2) {
        if (array1 == array2) {
            return true;
        }

        if (array1 == null || array2 == null) {
            return false;
        }

        // Compare dimensions
        if (array1.length != array2.length) {
            return false;
        }

        // Compare elements of each row
        for (int i = 0; i < array1.length; i++) {
            if (!Arrays.equals(array1[i], array2[i])) {
                return false;
            }
        }

        return true;
    }

    // Overloaded method for other primitive types, e.g., double[][]
    public static boolean validateMultiDimensionalArrays(double[][] array1, double[][] array2) {
        if (array1 == array2) {
            return true;
        }

        if (array1 == null || array2 == null) {
            return false;
        }

        if (array1.length != array2.length) {
            return false;
        }

        for (int i = 0; i < array1.length; i++) {
            if (!Arrays.equals(array1[i], array2[i])) {
                return false;
            }
        }

        return true;
    }

    private static String convertListToString(List list, StringBuilder builder) {

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) instanceof List) {
                convertListToString((List) list.get(i), builder);
            } else {
                // Not a list
                builder.append("[");

                for (int j = 0; j < list.size(); j++) {
                    builder.append(list.get(j));
                    builder.append(", ");

                }
                // Delete trailing command and whitespace when iterating over single list
                builder.deleteCharAt(builder.lastIndexOf(","));
                builder.deleteCharAt(builder.lastIndexOf(" "));
                builder.append("], ");
                return builder.toString();            }
        }

        // Delete trailing command and whitespace when closing off final list (if multi dimensional list)
        builder.deleteCharAt(builder.lastIndexOf(" "));
        builder.deleteCharAt(builder.lastIndexOf(","));
        builder.insert(0, "[");
        builder.append("]");
        return builder.toString();
    }

    private static boolean deepEquals(Object a, Object b) {
        if (a == b) return true;
        if (a == null || b == null) return false;

        // Arrays
        if (a.getClass().isArray() && b.getClass().isArray()) {
            if (a instanceof Object[] && b instanceof Object[])
                return Arrays.deepEquals((Object[]) a, (Object[]) b);
            if (a instanceof int[] && b instanceof int[])
                return Arrays.equals((int[]) a, (int[]) b);
            // ...other primitive array checks
            return false;
        }

        // Collections
        if (a instanceof List && b instanceof List) {
            List<?> listA = (List<?>) a;
            List<?> listB = (List<?>) b;
            if (listA.size() != listB.size()) return false;
            for (int i = 0; i < listA.size(); i++) {
                if (!deepEquals(listA.get(i), listB.get(i))) return false;
            }
            return true;
        }

        if (a instanceof Map && b instanceof Map) {
            Map<?, ?> mapA = (Map<?, ?>) a;
            Map<?, ?> mapB = (Map<?, ?>) b;
            if (!mapA.keySet().equals(mapB.keySet())) return false;
            for (Object key : mapA.keySet()) {
                if (!deepEquals(mapA.get(key), mapB.get(key))) return false;
            }
            return true;
        }

        // Fallback to equals()
        return Objects.equals(a, b);
    }

    private static String stringify(Object obj) {
        if (obj == null) return "null";
        if (obj.getClass().isArray()) {
            if (obj instanceof Object[]) return Arrays.deepToString((Object[]) obj);
            if (obj instanceof int[]) return Arrays.toString((int[]) obj);
            if (obj instanceof long[]) return Arrays.toString((long[]) obj);
            if (obj instanceof double[]) return Arrays.toString((double[]) obj);
            if (obj instanceof boolean[]) return Arrays.toString((boolean[]) obj);
            if (obj instanceof char[]) return Arrays.toString((char[]) obj);
            if (obj instanceof byte[]) return Arrays.toString((byte[]) obj);
            if (obj instanceof short[]) return Arrays.toString((short[]) obj);
        }
        return obj.toString();
    }
}
