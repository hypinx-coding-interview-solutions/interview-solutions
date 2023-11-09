package com.hypinx.coding.meta.prescreen;

public class MergeMultipleLists {
}

/*

class Solution {
    public static void main(String[] args) {

    }
}

public class MovingAverage {
    private List<Integer> list;
    private int windowSize;
    private double sum = 0.0;
    private List<Double> movingAverages;

    private List<Integer> streamedList;
    private List<Double> movingAveragesStreamed;
    private double streamedSum = 0.0;


    public MovingAverage(List<Integer< list, int windowSize) {
        this.list = list;
        this.windowSize = windowSize;
        this.movingAverages = new ArrayList<>();
        this.streamedList = new ArrayList<>();
        this.movingAveragesStreamed = new ArrayList<>();

    }

    // Time Complexity: O(N)
    // Space Complexity: O(N - windowSize + 1) -> O(N)
    public List<Double> calculateMovingAverages() {
        // Edge cases
        if (windowSize <= 0 || list.isEmpty()) {
            return movingAverages;
        }

        if (windowSize > list.size()) {
            windowSize = list.size();
        }

        // Form the initial sum
        for (int i = 0; i < windowSize; i++) {
            sum += list.get(i);
        }

        // Get the average in window
        movingAverages.add(sum / windowSize);

        int end = windowSize + 1;
        for (int start = 1; end < list.size(); start++, end++) {
            sum = sum + list.get(end) - list.get(start);
            movingAverages.add(sum / windowSize);
        }

        return movingAverages;
    }

    // [1,2,3,4,5], windowSize = 3
    Double update(Integer value){
        streamedList.add(value);

        if (windowSize < streamedList.size()) {
            streamedSum = streamedSum - streamList.get(list.size() - windowSize - 1) + value;
            movingAveragesStreamed.add(streamedSum / windowSize);
        } else {
            // 0 + 1 + 2 + 3
            streamedSum += value;
            movingAveragesStreamed.add(streamedSum / list.size());
        }
    }

// MovingAverage m = new MovingAverage(3);
// System.out.println(m.movingAverage(1)); // 1.0
// System.out.println(m.movingAverage(2)); // 1.5
// System.out.println(m.movingAverage(3)); // 2.0
// System.out.println(m.movingAverage(4)); // 3.0
// System.out.println(m.movingAverage(5)); // 4.0


}

class MergeLists {

    PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> o1 - o2);
    List<Integer> result = new ArrayList<>();

    // Time complexity : O(N)
    // Space complexity: O(2 * N) -> O(N)
    public List<Integer> mergeList(List<List<Integer>> lists) {

        // Build up the pq from lowest to highest
        for (int i = 0; i < lists.size(); i++) {
            List<Integer> currentList = lists.get(i);

            for (int el : currentList) {
                pq.add(el);
            }
        }

        while (!pq.isEmpty()) {
            result.add(pq.poll());
        }
        return result;
    }
}

// Receiving the following lists:
// #1: [1, 4, 5, 8, 9]
// #2: [3, 4, 4, 6]
// #3: [0, 2, 8]
//
// Would yield the following result:
// [0, 1, 2, 3, 4, 4, 4, 5, 6, 8, 8, 9]




// Your previous Plain Text content is preserved below:

// Welcome to Meta!

// This is just a simple shared plaintext pad, with no execution capabilities.

// When you know what language you would like to use for your interview,
// simply choose it from the dropdown in the left bar.

// Enjoy your interview!
// Input:
//  list: [1,2,3,4,5,6,7,8,9]
//  window size: 7
// Output:
//  [4.0,5.0,6.0]
 */
