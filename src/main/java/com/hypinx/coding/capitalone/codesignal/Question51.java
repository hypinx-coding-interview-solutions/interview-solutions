package com.hypinx.coding.capitalone.codesignal;

public class Question51 {

    public static void main(String[] args) throws Exception {

        int[] audiobooks = new int[]{5,6,7,8};
        String[] logs = new String[]{
                "LISTEN 5",
                "LISTEN 5",
                "LISTEN 4",
                "LISTEN 4",
                "DROP 1",
                "LISTEN 1"
        };
        int expected = 2;
        int result = solution(audiobooks, logs);
        assertTrue(result, expected, "1");

        audiobooks = new int[]{5,6,7,8};
        logs = new String[]{
                "LISTEN 2",
                "LISTEN 6",
                "LISTEN 4",
                "LISTEN 4",
                "LISTEN 1",
                "LISTEN 1",
        };
        expected = 1;
        result = solution(audiobooks, logs);
        assertTrue(result, expected, "1");

        audiobooks = new int[]{5,6,7,8};
        logs = new String[]{
                "LISTEN 2",
                "LISTEN 6",
                "LISTEN 5",
                "LISTEN 4",
                "LISTEN 1",
                "LISTEN 1",
        };
        expected = 2;
        result = solution(audiobooks, logs);
        assertTrue(result, expected, "1");
    }

    public static int solution(int[] audiobooks, String[] logs) {
        Audiobook[] audiobooksArray = new Audiobook[audiobooks.length];

        Audiobook highestListenedBook = new Audiobook(-1, -1, -1, true, false);

        // Initialize audiobooks array
        int counter = 0;
        int totalCompletedBooks = 0;
        for (int audiobook : audiobooks) {
            Audiobook audiobookItem = new Audiobook(counter, audiobook, 0, true, false);
            audiobooksArray[counter++] = audiobookItem;
        }

        // Iterate over logs
        counter = 0;
        int max = audiobooksArray.length;
        for (int i = 0; i < logs.length; i++) {
            String log = logs[i];
            String logType = log.split(" ")[0];
            int logValue = Integer.valueOf(log.split(" ")[1]);

            // We've completed all the books and/or lost interest - no point in continuing to check the remainder of the logs
            if (totalCompletedBooks == max) break;

            if (logType.equalsIgnoreCase("LISTEN")) {
                // Ensure book is NOT completed and we STILL have interest
                while(true) {
                    Audiobook currentBook = audiobooksArray[counter];
                    if (!currentBook.completed && currentBook.interest) {
                        currentBook.listenTime += logValue;
                        if (currentBook.listenTime >= currentBook.time) {
                            currentBook.completed = true;
                            totalCompletedBooks++;
                        }

                        // If listen time on current book is greater than highest record book OR
                        // if listen time on current book = that of highest book AND index on current book > highest book
                        if ((currentBook.listenTime > highestListenedBook.listenTime) ||
                                ((currentBook.listenTime == highestListenedBook.listenTime) && (currentBook.index > highestListenedBook.index))) {
                            highestListenedBook = currentBook;
                        }
                        break;
                    } else {
                        counter = incrementCounter(counter, max);
                    }
                }
            } else {
                // Drop book
                audiobooksArray[logValue].interest = false;
                totalCompletedBooks++;
            }

            counter = incrementCounter(counter, max);
        }

        return highestListenedBook.index;
    }

    public static void assertTrue(int result, int expected, String testCase) throws Exception {
        if (result != expected) {
            throw new Exception("Test case " + testCase + " failed. Result is " + result + " but expected " + expected);
        }
    }

    public static int incrementCounter(int counter, int max) {
        if (++counter == max) counter = 0;
        return counter;
    }

    private static class Audiobook {
        public int index;
        public int time;
        public int listenTime;
        public boolean interest;
        public boolean completed;

        public Audiobook(int index, int time, int listenTime, boolean interest, boolean completed) {
            this.index = index;
            this.time = time;
            this.listenTime = listenTime;
            this.interest = interest;
            this.completed = completed;
        }
    }
}
