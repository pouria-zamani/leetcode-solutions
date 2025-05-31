package main.java.com.pouria.leetcode.array;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class P001TwoSum {

    // Using Hash Table
    // Complexity: O(n)
    public static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement)) {
                return new int[]{map.get(complement), i};
            }
            map.put(nums[i], i);
        }

        return new int[]{};
    }

    // Using Brute Force
    // Complexity: O(n^2)
    public static int[] twoSumBruteForce(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[]{};
    }

    // Using Two Pointer technique
    // Complexity: O(n)
    public static int[] twoSumTwoPointer(int[] nums, int target) {
        Arrays.sort(nums);

        int left = 0, right = nums.length - 1;

        while (left < right) {
            int sum = nums[left] + nums[right];

            if (sum == target)
                break;
            else if (sum < target)
                left++;
            else
                right--;
        }

        return new int[]{left, right};
    }
}
