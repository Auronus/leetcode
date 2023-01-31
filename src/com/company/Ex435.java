package com.company;

import java.util.Arrays;

class Ex435 {
    public int eraseOverlapIntervals(int[][] intervals) {
        System.out.println("Erase overlap intervals: " + Arrays.toString(intervals));
        Arrays.sort(intervals, (a, b) -> (a[1] - b[1]));

        int neededElements = intervals.length;
        int start = intervals[0][0];
        int end = intervals[0][1];
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] < end) {
                neededElements--;
            } else {
                start = intervals[i][0];
                end = intervals[i][1];
            }
        }
        return intervals.length - neededElements;
    }
}
