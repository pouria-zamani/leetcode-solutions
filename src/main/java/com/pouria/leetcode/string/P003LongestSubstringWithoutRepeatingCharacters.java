package main.java.com.pouria.leetcode.string;

import java.util.HashMap;

public class P003LongestSubstringWithoutRepeatingCharacters {
    public int lengthOfLongestSubstring(String s) {
        int sLen = s.length();
        int r = 0, l = 0, maxLength = 0;
        HashMap<Character, Integer> hashmap = new HashMap<>();

        while (r < sLen) {
            if (hashmap.containsKey(s.charAt(r)))
                l = Math.max(l, hashmap.get(s.charAt(r)) + 1);

            maxLength = Math.max(maxLength, r - l + 1);
            hashmap.put(s.charAt(r), r);
            r++;
        }

        return maxLength;
    }
}
