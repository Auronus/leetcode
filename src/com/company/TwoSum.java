package com.company;

import java.util.HashMap;
import java.util.Map;

public class TwoSum {

    public int[] twoSumBrutForce(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }

    public int[] twoSumHashMap(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int secondNumber = target - nums[i];
            if (map.containsKey(secondNumber)) {
                return new int[]{map.get(secondNumber), i};
            }
            map.put(nums[i], i);
        }
        return null;
    }
}
