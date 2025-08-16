package com.hypinx.coding.chase.hackerrank;

import com.hypinx.coding.misc.TestCaseValidator;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Question_31_Latest_K_Requests {

    public static void main(String[] args) {
        List<String> requests = List.of("item1", "item2", "item3", "item1", "item3");
        List<String> expected = List.of("item3", "item1", "item2");
        List<String> result = getLatestKRequests(requests, 3);

        TestCaseValidator.validateTestCase("1", expected, result);
    }

    public static List<String> getLatestKRequests(List<String> requests, int K) {
        Set<String> uniqueRequests = new HashSet<>();
        List<String> result = new ArrayList<>();

        int end = requests.size();

        while (K > 0) {
            end--;
            if (end < 0) break;
            String current = requests.get(end);
            if (uniqueRequests.contains(current)) {
                continue;
            }
            uniqueRequests.add(current);
            result.add(current);
            K--;
        }

        return result;
    }
}
