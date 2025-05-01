package com.hypinx.coding.chase.hackerrank;

import com.hypinx.coding.misc.TestCaseValidator;

import java.util.*;

public class Question_22_Net_Profit_From_Trading {

    public static void main(String[] args) {
        List<String> events = List.of("BUY googl 20", "BUY appl 50", "CHANGE googl 6", "QUERY", "SELL appl 10", "CHANGE appl -2", "QUERY");

        List<Long> expected = List.of(120L, 40L);
        List<Long> result = solution(events);

        TestCaseValidator.validateTestCase("1", expected, result);
    }

    public static List<Long> solution(List<String> events) {
        long runningProfit = 0;
        Map<String, Long> shares = new HashMap<>();
        List<Long> result = new ArrayList<>();

        for (String event : events) {
            String[] split = event.split(" ");
            String action = split[0];
            String share;
            long quantity;

            switch (action) {
                case "BUY":
                    share = split[1];
                    quantity = Long.valueOf(split[2]);
                    shares.put(share, shares.getOrDefault(share, 0L) + quantity);
                    break;
                case "SELL":
                    share = split[1];
                    quantity = Long.valueOf(split[2]);
                    shares.put(share, shares.getOrDefault(share, 0L) - quantity);
                    break;
                case "QUERY":
                    result.add(runningProfit);
                    break;
                case "CHANGE":
                    share = split[1];
                    quantity = Long.valueOf(split[2]);
                    runningProfit = runningProfit + (quantity * shares.get(share));
                    break;
            }
        }

        return result;
    }
}
