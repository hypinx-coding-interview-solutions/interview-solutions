package com.hypinx.coding.capitalone.codesignal.swe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Question46 {

    public static void main(String[] args) throws Exception {
        String[] input1 = new String[]{
                "John: 5",
                "Michael: 4",
                "Ruby: 2",
                "Ruby: 5",
                "Michael: 5"
        };
        String expected1 = "John";
        String result1 = solution(input1);
        if (!expected1.equals(result1)) throw new Exception("Expected " + expected1 + " but was " + result1);

        String[] input2 = new String[]{
              "Kate: 5",
              "Kate: 5",
              "Maria: 2",
              "John: 5",
              "Michael: 4",
              "John: 4"
        };

        String expected2 = "Kate";
        String result2 = solution(input2);
        if (!expected2.equals(result2)) throw new Exception("Expected " + expected2 + " but was " + result2);

        System.out.println("All tests passed!");

    }

    public static String solution(String[] input) {
        Map<String, StudentRecord> allRecords = new HashMap<>();

        for (String record : input) {
            String name = record.split(":")[0];
            int grade = Integer.parseInt(record.split(":")[1].trim());

            StudentRecord current = allRecords.getOrDefault(name, new StudentRecord(name));
            current.addGradeAndCalculateNewAverage(grade);
            allRecords.put(name, current);
        }

        List<StudentRecord> list = new ArrayList<>(allRecords.values());
        list.sort((a, b) -> {
            return Double.compare(b.getAverage(), a.getAverage());
        });

        return list.get(0).getName();
    }

    static class StudentRecord {
        private String name;
        private int totalGrade;
        private double totalRecordCount;
        private double average;

        public StudentRecord(String name) {
            this.name = name;
            this.totalGrade = 0;
            this.totalRecordCount = 0;
            this.average = 0;
        }

        public String getName() { return this.name; }

        public double getAverage() { return this.average; }

        public void addGradeAndCalculateNewAverage(int grade) {
            this.totalGrade += grade;
            this.totalRecordCount++;
            this.average = (totalGrade / totalRecordCount);
        }
    }
}